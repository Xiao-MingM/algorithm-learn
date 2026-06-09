package practice;

import java.util.HashSet;
import java.util.Set;

// 字符串的全部子序列
// 子序列本身是可以有重复的，只是这个题目要求去重
// 测试链接 : https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a
public class Class38_Code01_Subsequences {

    public String[] generatePermutation (String s) {
        Set<String> set = new HashSet<>();
        f(s.toCharArray(), 0, new StringBuilder(), set);
        String[] ans = new String[set.size()];
        int i = 0;
        for (String str : set) {
            ans[i++] = str;
        }
        return ans;
    }

    private void f(char[] chars, int index, StringBuilder path, Set<String> set) {
        if (index == chars.length) {
            set.add(path.toString());
            return;
        }

        // 选择当前字符
        path.append(chars[index]);
        f(chars, index + 1, path, set);

        // 撤销选择，走“不选择当前字符”的分支
        path.deleteCharAt(path.length() - 1);
        f(chars, index + 1, path, set);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @return string字符串一维数组
     */
    public String[] generatePermutation1 (String s) {
        char[] chars = s.toCharArray();
        Set<String> set = new HashSet<>();
        StringBuilder path = new StringBuilder();
        f1(chars, 0, path, set);
        // 答案的size设置为ans的size
        String[] ans = new String[set.size()];
        int i = 0;
        for (String str : set) {
            ans[i++] = str;
        }
        return ans;
    }

    void f1(char[] s, int i, StringBuilder path, Set<String> set) {
        // i越界结算
        if (i == s.length) {
            set.add(path.toString());
        } else {
            // 要s[i]
            path.append(s[i]);
            // 把要s[i]的路径带进去跑
            f1(s, i + 1, path, set);
            // 不要s[i]
            path.deleteCharAt(path.length() - 1);
            // 把不带s[i]的路径带进去跑
            f1(s, i + 1, path, set);
        }
    }
}
