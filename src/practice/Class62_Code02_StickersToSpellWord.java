package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// 贴纸拼词
// 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
// 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们
// 如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
// 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1
// 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的
// 并且 target 被选择为两个随机单词的连接。
// 测试链接 : https://leetcode.cn/problems/stickers-to-spell-word/
public class Class62_Code02_StickersToSpellWord {

    /*
     * i
     * aabbccddz
     * abdf
     * j
     *  i
     * aabbccddz
     * abdf
     *  j
     *   i
     * aabbccddz  sb=a
     * abdf
     *  j
     *   i
     * aabbccddz  sb=a
     * abdf
     *  j
     *    i
     * aabbccddz  sb=a
     * abdf
     *   j
     *     i
     * aabbccddz  sb=ab
     * abdf
     *   j
     *      i
     * aabbccddz  sb=abc
     * abdf
     *   j
     *       i
     * aabbccddz  sb=abcc
     * abdf
     *   j
     *        i
     * aabbccddz  sb=abcc
     * abdf
     *    j
     *         i
     * aabbccddz  sb=abccd
     * abdf
     *    j
     *         i
     * aabbccddz  sb=abccdz
     * abdf
     *     j
     */

    static final int MAXN = 401;

    // 定义队列
    static final String[] queue = new String[MAXN];
    static int l, r;

    // 需要进行遍历的分组数组
    static final List<List<String>> group = new ArrayList<>();
    // 是否已经访问过
    static final HashSet<String> visited = new HashSet<>();

    static {
        // 清理一下分组
        for (int i = 0; i < 26; i++) {
            group.add(new ArrayList<>());
        }
    }

    public int minStickers(String[] stickers, String target) {
        // 清理一下分组
        for (int i = 0; i < 26; i++) {
            group.get(i).clear();
        }
        // 清空队列
        l = r = 0;
        // 清空visited
        visited.clear();
        // 贴纸依次挂上去
        for (String sticker : stickers) {
            sticker = sort(sticker);
            // 26个位置都要挂
            for (int i = 0; i < sticker.length(); i++) {
                // 0号位置必须挂，不相同的位置也挂
                if (i == 0 || sticker.charAt(i) != sticker.charAt(i - 1)) {
                    group.get(sticker.charAt(i) - 'a').add(sticker);
                }
            }
        }
        target = sort(target);
        // target入队
        queue[r++] = target;
        visited.add(target);
        int level = 0;
        // 队列非空
        while (l < r) {
            level++;
            int size = r - l;
            for (int k = 0; k < size; k++) {
                // 出队列
                String t = queue[l++];
                // 用起始位置打头的组里的所有元素遍历
                for (String s : group.get(t.charAt(0) - 'a')) {
                    String next = next(t, s);
                    // 消成""后答案直接就出来了，而且是最小的
                    if (next.isEmpty()) {
                        return level;
                    }
                    // 没有访问过，加入队列继续处理
                    if (visited.add(next)) {
                        queue[r++] = next;
                    }
                }
            }
        }
        return -1;
    }




    static String sort(String str) {
        char[] s = str.toCharArray();
        Arrays.sort(s);
        return String.valueOf(s);
    }

    /**
     * 两个指针消除
     * @param t 目标字符串
     * @param s 贴片
     * @return
     */
    static String next(String t, String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < t.length()) {
            // j已经越界了，把t剩下的部分都记下来即可
            if (j == s.length()) {
                sb.append(t.charAt(i++));
            } else {
                // j处元素比i处大，消不掉，记录并i++
                if (t.charAt(i) < s.charAt(j)) {
                    sb.append(t.charAt(i++));
                // j处元素比i处小，无法处理，j++
                } else if (t.charAt(i) > s.charAt(j)){
                    j++;
                } else {
                    // 可以消掉，一起向前移动
                    i++;
                    j++;
                }
            }
        }
        return sb.toString();
    }
}
