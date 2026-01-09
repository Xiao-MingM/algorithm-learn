package practice;

import java.util.Arrays;

// 解码方法
// 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）
// 例如，"11106" 可以映射为："AAJF"、"KJF"
// 注意，消息不能分组为(1 11 06)，因为 "06" 不能映射为 "F"
// 这是由于 "6" 和 "06" 在映射中并不等价
// 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数
// 题目数据保证答案肯定是一个 32位 的整数
// 测试链接 : https://leetcode.cn/problems/decode-ways/
public class Class66_Code03_DecodeWays {
    // 暴力尝试
    public static int numDecodings1(String s) {
        char[] chars = s.toCharArray();
        return f1(chars, 0);
    }

    static int f1(char[] chars, int i) {
        // 什么都没有默认有一个
        if (i == chars.length) {
            return 1;
        }
        int ans;
        // 走到0直接中断
        if (chars[i] == '0') {
            ans = 0;
        } else {
            // 当前为一位拼下一位算一个
            ans = f1(chars, i + 1);
            // 开始判断和后面组合的情况
            if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26)
                ans += f1(chars, i + 2);
        }
        return ans;
    }


//    static int f1(char[] chars, int i) {
//        // 什么都没有默认有一个
//        if (i == chars.length) {
//            return 1;
//        }
//        // 走到0直接中断
//        if (chars[i] == '0') {
//            return 0;
//        }
//        // 当前为一位拼下一位算一个
//        int ans = f1(chars, i + 1);
//        // 开始判断和后面组合的情况
//        if (i + 1 < chars.length) {
//            if (chars[i] == '1')
//                ans += f1(chars, i + 2);
//            if (chars[i] == '2') {
//                if (chars[i+1] - '0' <= 6)
//                    ans += f1(chars, i + 2);
//            }
//        }
//        return ans;
//    }


    public static int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return f2(chars, 0, dp);
    }

    static int f2(char[] chars, int i, int[] dp) {
        // 什么都没有默认有一个
        if (i == chars.length)
            return 1;

        if (dp[i] != -1)
            return dp[i];

        int ans;
        // 走到0直接中断
        if (chars[i] == '0') {
            ans = 0;
        } else {
            // 当前为一位拼下一位算一个
            ans = f2(chars, i + 1, dp);
            // 开始判断和后面组合的情况
            if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26)
                ans += f2(chars, i + 2, dp);
        }

        dp[i] = ans;
        return ans;
    }

    static int f3(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            // 走到0直接中断
            if (chars[i] == '0') {
                dp[i] = 0;
            } else {
                // 当前为一位拼下一位算一个
                dp[i] = dp[i + 1];
                // 开始判断和后面组合的情况
                if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26)
                    dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }



//    static int f2(char[] chars, int i, int[] dp) {
//        // 什么都没有默认有一个
//        if (i == chars.length)
//            return 1;
//
//        if (dp[i] != -1)
//            return dp[i];
//
//        // 走到0直接中断
//        if (chars[i] == '0')
//            return 0;
//        // 当前为一位拼下一位算一个
//        int ans = f2(chars, i + 1, dp);
//        // 开始判断和后面组合的情况
//        if (i + 1 < chars.length) {
//            if (chars[i] == '1')
//                ans += f2(chars, i + 2, dp);
//            if (chars[i] == '2') {
//                if (chars[i+1] - '0' <= 6)
//                    ans += f2(chars, i + 2, dp);
//            }
//        }
//        dp[i] = ans;
//        return ans;
//    }
}
