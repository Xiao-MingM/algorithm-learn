package practice;

// 最小覆盖子串
// 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串
// 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
// 测试链接 : https://leetcode.cn/problems/minimum-window-substring/
public class Class49_Code03_MinimumWindowSubstring {

    //因此没有符合条件的子字符串，返回空字符串。
    // CABBDAACBDADBAACC  CADD  负债表 [A:-1, B:0, C: -1, D:-2] debt = -4
    // CABBDAACBDADBAACC  [C]   负债表 [A:-1, B:0, C: 0, D:-2] debt = -3 r++
    // l
    // r
    // CABBDAACBDADBAACC  [C,A] 负债表 [A:0, B:0, C: 0, D:-2] debt = -2 r++
    // l
    //  r
    // CABBDAACBDADBAACC  [C,A,B] 负债表 [A:0, B:1, C: 0, D:-2] debt = -2 r++
    // l
    //   r
    // CABBDAACBDADBAACC  [C,A,B,B] 负债表 [A:0, B:2, C: 0, D:-2] debt = -2 r++
    // l
    //    r
    // CABBDAACBDADBAACC  [C,A,B,B,D] 负债表 [A:0, B:2, C: 0, D:-1] debt = -1 r++
    // l
    //     r
    // CABBDAACBDADBAACC  [C,A,B,B,D,A] 负债表 [A:1, B:2, C: 0, D:-1] debt = -1 r++
    // l
    //      r
    // CABBDAACBDADBAACC  [C,A,B,B,D,A,A] 负债表 [A:2, B:2, C: 0, D:-1] debt = -1 r++
    // l
    //       r
    // CABBDAACBDADBAACC  [C,A,B,B,D,A,A,C] 负债表 [A:2, B:2, C: 1, D:-1] debt = -1 r++
    // l
    //        r
    // CABBDAACBDADBAACC  [C,A,B,B,D,A,A,C,B] 负债表 [A:2, B:3, C: 1, D:-1] debt = -1 r++
    // l
    //         r
    // CABBDAACBDADBAACC  [C,A,B,B,D,A,A,C,B,D] 负债表 [A:2, B:3, C: 1, D:0] debt = 0 ans = 10 l++
    // l
    //          r
    // CABBDAACBDADBAACC  [A,B,B,D,A,A,C,B,D] 负债表 [A:2, B:3, C: 0, D:0] debt = 0 ans = min(10,9) = 9 l++
    //  l
    //          r
    // CABBDAACBDADBAACC  [B,B,D,A,A,C,B,D] 负债表 [A:1, B:3, C: 0, D:0] debt = 0 ans = min(9,8) = 9 l++
    //   l
    //          r
    // CABBDAACBDADBAACC  [B,D,A,A,C,B,D] 负债表 [A:1, B:2, C: 0, D:0] debt = 0 ans = min(8,7) = 8 l++
    //    l
    //          r
    // CABBDAACBDADBAACC  [D,A,A,C,B,D] 负债表 [A:1, B:1, C: 0, D:0] debt = 0 ans = min(7,6) = 8 l++
    //     l
    //          r
    // CABBDAACBDADBAACC  [A,A,C,B,D] 负债表 [A:1, B:1, C: 0, D: -1] debt = -1 x
    //      l
    //          r
    // CABBDAACBDADBAACC  [D,A,A,C,B,D,A] 负债表 [A:3, B:1, C: 0, D:0] debt = 0  r++
    //     l
    //           r
    // CABBDAACBDADBAACC  [D,A,A,C,B,D,A,D] 负债表 [A:3, B:1, C: 0, D:1] debt = 0  l++
    //     l
    //            r
    // CABBDAACBDADBAACC  [A,A,C,B,D,A,D] 负债表 [A:3, B:1, C: 0, D:0] debt = 0  ans = min(6,7) = 8 l++
    //      l
    //            r
    // CABBDAACBDADBAACC  [A,C,B,D,A,D] 负债表 [A:2, B:1, C: 0, D:0] debt = 0  ans = min(6,7) = 8 l++
    //       l
    //            r
    // CABBDAACBDADBAACC  [C,B,D,A,D] 负债表 [A:1, B:1, C: 0, D:0] debt = 0  ans = min(5,6) = 5 l++
    //        l
    //            r
    // CABBDAACBDADBAACC  [B,D,A,D] 负债表 [A:1, B:1, C: -1, D:0] debt = -1  x
    //         l
    //            r
    // CABBDAACBDADBAACC  [C,B,D,A,D,B] 负债表 [A:1, B:2, C: 0, D:0] debt = 0  r++
    //        l
    //             r
    // CABBDAACBDADBAACC  [C,B,D,A,D,B,A] 负债表 [A:2, B:2, C: 0, D:0] debt = 0  r++
    //        l
    //              r
    // CABBDAACBDADBAACC  [C,B,D,A,D,B,A,A] 负债表 [A:3, B:2, C: 0, D:0] debt = 0  r++
    //        l
    //               r
    // CABBDAACBDADBAACC  [C,B,D,A,D,B,A,A,C] 负债表 [A:3, B:2, C: 1, D:0] debt = 0  l++
    //        l
    //                r
    // CABBDAACBDADBAACC  [B,D,A,D,B,A,A,C] 负债表 [A:3, B:2, C: 0, D:0] debt = 0  ans = min(5,8) = 5 l++
    //         l
    //                r
    // CABBDAACBDADBAACC  [D,A,D,B,A,A,C] 负债表 [A:3, B:2, C: 0, D:0] debt = 0  ans = min(5,8) = 5 l++
    //          l
    //                r
    // 。。。。。。
    public String minWindow(String str, String tar) {
        int n = str.length();
        char[] s = str.toCharArray();
        char[] t = tar.toCharArray();
        // 债务表
        int[] cnt = new int[256];
        // 负债为target长度
        int debt = t.length;
        // 记录出现答案的起始位置
        int start = 0;
        // 记录出现答案时候的长度
        int len = Integer.MAX_VALUE;
        // 统计负债
        for (char c : t) {
            cnt[c]--;
        }
        // 开始划
        for (int l = 0, r = 0; r < n; r++) {
            // 还完债还出了小于0的数，债务-1
            if (cnt[s[r]]++ < 0) {
                debt--;
            }
            // 左窗口符合不欠债的情况就缩，债务一旦清零就不会再变回去了
            if (debt == 0) {
                // 如果不负债就吐数
                while (cnt[s[l]] > 0) {
                    cnt[s[l++]]--;
                }
                // 债务清零的情况才会出答案
                if (r - l + 1 < len) {
                    start = l;
                    len = r - l + 1;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : str.substring(start, start + len);
    }
}
