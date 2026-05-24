package practice;

import java.util.Arrays;

// 至少有K个重复字符的最长子串
// 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串
// 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度
// 如果不存在这样的子字符串，则返回 0。
// 测试链接 : https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/
public class Class49_Code07_LongestSubstringWithAtLeastKRepeating {

    //示例 1：
    //
    //输入：s = "aaabb", k = 3
    //输出：3
    //解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
    //示例 2：
    //
    //输入：s = "ababbc", k = 2
    //输出：5
    //解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
    // 举例 abacabbac k=2 require = 1, [1,...,26]
    // require = 1, a b a c a bb-2 a c , bb时collect = 1, satisfy = 1 ans = max(0, 2) 2
    // require = 2, aba aca abba c ,     abba时collect = 2, satisfy = 2 ans = max(2,4) 4
    // require = 3, abacabbac      ,     abacabbac collect = 3, satisfy = 3 ans = max(3, 9) 9
    // ......

    public int longestSubstring(String str, int k) {
        char[] s = str.toCharArray();
        int n = s.length;
        // 统计字符出现的次数
        int[] cnt = new int[26];
        int ans = 0;
        // 指定窗口中要出现的字符种类的数量为require
        for (int require = 1; require <= 26; require++) {
            // 清理词频表
            Arrays.fill(cnt, 0);
            // 扩容窗口
            for (int l = 0, r = 0, collect = 0, satisfy = 0; r < n; r++) {
                // 首次出现一个新字符
                cnt[s[r] - 'a']++;
                if (cnt[s[r] - 'a'] == 1) {
                    collect++;
                }
                // 当出现的数字满足k则satisfy+1
                if (cnt[s[r] - 'a'] == k) {
                    satisfy++;
                }
                // 字符数量超了则需要吐
                while (collect > require) {
                    if (cnt[s[l] - 'a'] == 1) {
                        collect--;
                    }
                    // 满足的也跟着-
                    if (cnt[s[l] - 'a'] == k) {
                        satisfy--;
                    }
                    cnt[s[l++] - 'a']--;
                }
                if (satisfy == require) {
                    ans = Math.max(ans, r - l + 1);
                }
            }
        }
        return ans;
    }


}
