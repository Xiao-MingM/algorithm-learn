package prepare;

import java.util.Arrays;

// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
public class LeetCode003 {

    public int lengthOfLongestSubstring(String str) {
        int[] last = new int[256];
        Arrays.fill(last, -1);

        int n = str.length();
        int ans = 0;
        char[] s = str.toCharArray();
        for (int l = 0, r = 0; r < n; r++) {
            if (last[s[r]] != -1) {
                l = Math.max(l, last[s[r]] + 1);
            }
            last[s[r]] = r;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
