package practice;

// 去除重复字母保证剩余字符串的字典序最小
// 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次
// 需保证 返回结果的字典序最小
// 要求不能打乱其他字符的相对位置
// 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/
public class Class53_Code02_RemoveDuplicateLetters {

    // 维护相对大压小的单调栈，确保字典集还有值的时候的相对单调
    // cbacdcbc -> acdb [a-1,b-2,c-4,d-1]
    // c来了，c入栈，c数量-1 [a-1,b-2,c-3,d-1] ，[c]
    // b来了，违反大压小且c还剩3个可以放心出栈，b入栈，b数量-1 [a-1,b-1,c-3,d-1] [c]
    // a来了，违反大压小且b还剩1个可以放心出栈，a入栈，a数量-1 [a-0,b-1,c-3,d-1] [a]
    // c来了，不违反大压小，c入栈，c数量-1，[a-0,b-1,c-2,d-1] [a,c]
    // d来了，不违反大压小，d入栈，d数量-1，[a-0,b-1,c-2,d-0] [a,c,d]
    // c来了，违反大压小，但是c已经在里面了，c数量-1跳过，[a-0,b-1,c-1,d-0] [a,c,d]
    // b来了，违反大压小，但是d数量已经=0了，不能弹出了，b入栈并-1，[a-0,b-0,c-1,d-0] [a,c,d,b]
    // c来了，违反大压小，但是c已经在里面了，放心跳过，c数量-1，[a-0,b-0,c-0,d-0] [a,c,d,b]
    public String removeDuplicateLetters(String s) {
        char[] chars = s.toCharArray();
        // 定义栈
        char[] stack = new char[26];
        int r = 0;
        // 元素的数量字典
        int[] count = new int[26];
        // 判断字母是否入栈
        boolean[] inStack = new boolean[26];
        // 统计字典项
        for (char c : chars) {
            count[c - 'a']++;
        }
        // 遍历阶段
        for (char c : chars) {
            // 获取字典项下标
            int i = c - 'a';
            // 扫过必消耗一次
            count[i]--;
            // 未入栈的需要处理
            if (!inStack[i]) {
                // 违反大压小且栈顶元素还够扣得
                while (r > 0 && stack[r - 1] > c && count[stack[r - 1] - 'a'] > 0) {
                    // 栈顶元素出栈
                    char cur = stack[--r];
                    // 置为未被访问
                    inStack[cur - 'a'] = false;
                }
                // 入栈
                stack[r++] = c;
                inStack[i]=true;
            }
        }
        return String.valueOf(stack, 0, r);
    }
}
