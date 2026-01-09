package practice;

// 最长有效括号
// 给你一个只包含 '(' 和 ')' 的字符串
// 找出最长有效（格式正确且连续）括号子串的长度。
// 测试链接 : https://leetcode.cn/problems/longest-valid-parentheses/
public class Class66_Code06_LongestValidParentheses {

    // (()))()()((())()))
    // 001200102000120100
    // )((()))()((()(())()())
    // 0000123040001001203040
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        char[] chars = s.toCharArray();
        int max = 0;
        for (int i = 1, p; i < chars.length; i++) {
            if (chars[i] == ')') {
                p = i - dp[i - 1] -1;
                if (p >= 0 && chars[p] == '(')
                    dp[i] = dp[i - 1] + 2 + (p - 1 >= 0 ? dp[p - 1] : 0);
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

//    // (()))()()((())()))
//    // 001200102000120100
//    // )((()))()((()(())()())
//    // 0000123040001001203040
//    public int longestValidParentheses(String s) {
//        int[] dp = new int[s.length()];
//        char[] chars = s.toCharArray();
//        int max = 0;
//        for (int i = 0; i < chars.length; i++) {
//            if (chars[i] == ')') {
//                if (i - 1 >= 0) {
//                    if (chars[i - 1] == '(') {
//                        dp[i] = 1;
//                    }
//                    if (chars[i - 1] == ')') {
//                        if (i - dp[i - 1] -1 >= 0) {
//                            if (chars[i - dp[i - 1] -1] == '(') {
//                                dp[i] = dp[i - 1] + 1;
//                            }
//                        }
//                    }
//                    if (chars[i - 1] == '(' && i-2 >=0 && chars[i - 2] == ')') {
//                        dp[i] = dp[i - 2] + 1;
//                    }
//                }
//                max = Math.max(max, dp[i]);
//            }
//        }
//        return max;
//    }
}
