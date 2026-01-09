package practice;

import java.util.Arrays;

// 解码方法 II
// 一条包含字母 A-Z 的消息通过以下的方式进行了 编码 ：
// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// 要 解码 一条已编码的消息，所有的数字都必须分组
// 然后按原来的编码方案反向映射回字母（可能存在多种方式）
// 例如，"11106" 可以映射为："AAJF"、"KJF"
// 注意，像 (1 11 06) 这样的分组是无效的，"06"不可以映射为'F'
// 除了上面描述的数字字母映射方案，编码消息中可能包含 '*' 字符
// 可以表示从 '1' 到 '9' 的任一数字（不包括 '0'）
// 例如，"1*" 可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19"
// 对 "1*" 进行解码，相当于解码该字符串可以表示的任何编码消息
// 给你一个字符串 s ，由数字和 '*' 字符组成，返回 解码 该字符串的方法 数目
// 由于答案数目可能非常大，答案对 1000000007 取模
// 测试链接 : https://leetcode.cn/problems/decode-ways-ii/
public class Class66_Code04_DecodeWaysII {
    public int numDecodings1(String s) {
        return f1(s.toCharArray(), 0)%1000000007;
    }

    /**
     * 爆搜
     * @param chars
     * @param i
     * @return
     */
    static int f1(char[] chars, int i) {
        if (i == chars.length)
            return 1;

        int ans;
        if (chars[i] == '0') {
            ans = 0;
        } else {
            // 单个的情况*
            if (chars[i] != '*') {
                ans = f1(chars, i + 1);
                if (i + 1 < chars.length) {
                    // 组合情况11,12...,26
                    if (chars[i + 1] != '*') {
                        if ((chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26) {
                            ans += f1(chars, i + 2);
                        }
                    } else {
                        // 1*
                        if (chars[i] == '1') {
                            ans += 9 * f1(chars, i + 2);
                        }
                        // 2*
                        if (chars[i] == '2') {
                            ans += 6 * f1(chars, i + 2);
                        }
                    }
                }
            } else {
                // 单个情况
                ans = 9 * f1(chars, i + 1);
                if (i + 1 < chars.length) {
                    // 组合情况*1,*2...,*8,*9
                    if (chars[i + 1] != '*') {
                        // * 可为 1 可为 2
                        if (chars[i + 1] - '0' <= 6) {
                            ans += 2 * f1(chars, i + 2);
                        } else
                            // * 只能为 1
                            ans += f1(chars, i + 2);

                    } else {
                        // ** 只有11-26一共15种 没有20
                        ans += 15 * f1(chars, i + 2);
                    }
                }
            }
        }
        return ans;
    }

    static final int mod = 1000000007;

    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        long[] dp = new long[s.length()];
        Arrays.fill(dp, -1);
        return (int) f2(chars, 0, dp);
    }

    static long f2(char[] chars, int i, long[] dp) {
        if (i == chars.length)
            return 1;
        if (dp[i] != -1)
            return dp[i];

        long ans;
        if (chars[i] == '0') {
            ans = 0;
        } else {
            // 单个的情况*
            if (chars[i] != '*') {
                ans = f2(chars, i + 1, dp);
                if (i + 1 < chars.length) {
                    // 组合情况11,12...,26
                    if (chars[i + 1] != '*') {
                        if ((chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26) {
                            ans += f2(chars, i + 2, dp);
                        }
                    } else {
                        // 1*
                        if (chars[i] == '1') {
                            ans += 9 * f2(chars, i + 2, dp);
                        }
                        // 2*
                        if (chars[i] == '2') {
                            ans += 6 * f2(chars, i + 2, dp);
                        }
                    }
                }
            } else {
                // 单个情况
                ans = 9 * f2(chars, i + 1, dp);
                if (i + 1 < chars.length) {
                    // 组合情况*1,*2...,*8,*9
                    if (chars[i + 1] != '*') {
                        // * 可为 1 可为 2
                        if (chars[i + 1] - '0' <= 6) {
                            ans += 2 * f2(chars, i + 2, dp);
                        } else
                            // * 只能为 1
                            ans += f2(chars, i + 2, dp);

                    } else {
                        // ** 只有11-26一共15种 没有20
                        ans += 15 * f2(chars, i + 2, dp);
                    }
                }
            }
        }
        ans %= mod;
        dp[i] = ans;
        return ans;
    }

    public static int numDecodings3(String s) {
        return f3(s);
    }

    static int f3(String s) {
        char[] chars = s.toCharArray();
        long[] dp = new long[s.length() + 1];
        Arrays.fill(dp, -1);
        dp[s.length()] = 1;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                dp[i] = 0;
            } else {
                // 单个的情况*
                if (chars[i] != '*') {
                    dp[i] = dp[i + 1];
                    if (i + 1 < chars.length) {
                        // 组合情况11,12...,26
                        if (chars[i + 1] != '*') {
                            if ((chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26) {
                                dp[i] += dp[i + 2];
                            }
                        } else {
                            // 1*
                            if (chars[i] == '1') {
                                dp[i] += 9 * dp[i + 2];
                            }
                            // 2*
                            if (chars[i] == '2') {
                                dp[i] += 6 * dp[i + 2];
                            }
                        }
                    }
                } else {
                    // 单个情况
                    dp[i] = 9 * dp[i + 1];
                    if (i + 1 < chars.length) {
                        // 组合情况*1,*2...,*8,*9
                        if (chars[i + 1] != '*') {
                            // * 可为 1 可为 2
                            if (chars[i + 1] - '0' <= 6) {
                                dp[i] += 2 * dp[i + 2];
                            } else
                                // * 只能为 1
                                dp[i] += dp[i + 2];

                        } else {
                            // ** 只有11-26一共15种 没有20
                            dp[i] += 15 * dp[i + 2];
                        }
                    }
                }
            }
            dp[i] %= mod;
        }

        return (int) dp[0];
    }


}
