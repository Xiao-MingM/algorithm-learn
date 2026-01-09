package practice;

// 不同的子序列 II
// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
// 因为结果可能很大，答案对 1000000007 取模
// 字符串的 子序列 是经由原字符串删除一些（也可能不删除）
// 字符但不改变剩余字符相对位置的一个新字符串
// 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是
// 测试链接 : https://leetcode.cn/problems/distinct-subsequences-ii/

// 重要理解：为什么长度为n且字符互不相同的字符串，所有子序列数量（包括空集）是2^n？
// 
// 原理：对于每个字符，我们都有两种选择：选或不选
// - 第1个字符：2种选择（选/不选）
// - 第2个字符：2种选择（选/不选）
// - ...
// - 第n个字符：2种选择（选/不选）
// 
// 根据乘法原理：总方案数 = 2 × 2 × ... × 2 (n个2相乘) = 2^n
// 
// 示例：字符串 "abc" (n=3)
// 子序列：空集、a、b、c、ab、ac、bc、abc 共 2^3 = 8 个
// 
// 注意：如果字符串中有重复字符，子序列数量会少于2^n（因为会有重复的子序列）
//    a, b, c
// dp[0, 0, 0] 以对应字母为结尾的数量 newAdd, all = 1
// a, b, a, a, b, c
// i = 0, {"", a} newAdd = all - dp[0] = 1, dp[0] = dp[0] + newAdd = 1, all = all + newAdd = 2
// i = 1, {"", a, b, ab} newAdd = all - dp[1] = 2, dp[1] = dp[1] + newAdd = 2, all = all + newAdd = 4
// i = 2, {"", a, b, ab} {a, aa, ba, aba} -> {"", a, b, ab, aa, ba, aba} newAdd = all - dp[0] = 3, d[0] = 1+3= 4, all = 4 + 3 = 7
// i = 3, {"", a, b, ab, aa, ba, aba} {a, aa, ba, aba, aaa, baa, abaa}-> {"", a, b, ab, aa, ba, aba, aaa, baa, abaa} newAdd = 7 - 4 = 3, dp[0] = 4 + 3 = 7, all = 7 + 3 = 10
// i = 4, {"", a, b, ab, aa, ba, aba, aaa, baa, abaa} {b, ab, bb, abb, aab, bab, abab, aaab, baab ,abaab} -> {"", a, b, ab, aa, ba, aba, aaa, baa, abaa, bb, abb, aab, bab, abab, aaab, baab, abaab}
// newAdd = 10 - 2 = 8, d[1] = 2 + 8 = 10, all = 10 + 8 = 18
public class Class66_Code08_DistinctSubsequencesII {

    int mod = 1000000007;
    public int distinctSubseqII(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[26];
        int all = 1;// 空字符
        for (int i = 0, newAdd; i < chars.length; i++) {
            int index = chars[i] - 'a';
            newAdd = (all - dp[index] + mod) % mod;
            dp[index] = (dp[index] + newAdd) % mod;
            all = (all + newAdd) % mod;
        }
        return (all - 1 + mod) % mod;
    }
}
