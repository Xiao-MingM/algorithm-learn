package practice;

import java.util.Arrays;

// 无重复字符的最长子串
// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
// 测试链接 : https://leetcode.cn/problems/longest-substring-without-repeating-characters/
public class Class49_Code02_LongestSubstringWithoutRepeatingCharacters {
    // abacabbbaadcbcdaabdc
    // a
    // ab
    //   a
    //   ac
    //     a
    //     ab
    //       b
    //        b
    //        ba
    //          a
    //          ad
    //          adc
    //          adcb
    //             bc
    //             bcd
    //             bcda
    //                 a
    //                 ab
    //                 abd
    //                 abdc
    public int lengthOfLongestSubstring(String s) {
        // 记录单个字符上一次出现的位置
        int[] last = new int[256];
        Arrays.fill(last, -1);
        char[] chars = s.toCharArray();
        int n = chars.length;
        int ans = 0;
        for (int l = 0, r = 0; r < n; r++) {
            // 当前字符上一次出现的位置是-1，则可以加进去，否则左边界需要缩
            if (last[chars[r]] != -1) {
                l = Math.max(l, last[chars[r]] + 1);
            }
            // 结算答案
            ans = Math.max(ans, r - l + 1);
            last[chars[r]] = r;
        }
        return ans;
    }
}
