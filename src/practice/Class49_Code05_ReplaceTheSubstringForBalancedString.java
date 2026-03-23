package practice;

// 替换子串得到平衡字符串
// 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
// 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
// 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
// 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
// 请返回待替换子串的最小可能长度。
// 如果原字符串自身就是一个平衡字符串，则返回 0。
// 测试链接 : https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
public class Class49_Code05_ReplaceTheSubstringForBalancedString {

    // 模拟全过程，就是负债表的转化版本
    // WQEWEREWEWRW cnt=[W=5,Q=1,E=4,R=2] need=[W=-2,Q=2,E=-1,R=1] debt = 3
    // W                                  need=[W=-1,Q=2,E=-1,R=1] debt = 2
    // WQ                                 need=[W=-1,Q=3,E=-1,R=1] debt = 2
    // WQE                                need=[W=-1,Q=3,E=0,R=1] debt = 1
    // WQEW                               need=[W=0,Q=3,E=1,R=1] debt = 0 len = max(0,4) = 4
    // WQEWE                              need=[W=0,Q=3,E=1,R=1] debt = 0
    // WQEWER                             need=[W=0,Q=3,E=1,R=2] debt = 0
    // WQEWERE                            need=[W=0,Q=3,E=2,R=2] debt = 0
    // WQEWEREW                           need=[W=1,Q=3,E=2,R=2] debt = 0
    //  QEWEREW                           need=[W=0,Q=3,E=2,R=2] debt = 0 len = max(4,7) = 4
    //   EWEREW                           need=[W=0,Q=2,E=2,R=2] debt = 0 len = max(4,6) = 4
    //    WEREW                           need=[W=0,Q=2,E=1,R=2] debt = 0 len = max(4,5) = 4
    //    WEREWE                          need=[W=0,Q=2,E=2,R=2] debt = 0
    //    WEREWEW                         need=[W=1,Q=2,E=2,R=2] debt = 0
    //     EREWEW                         need=[W=0,Q=2,E=2,R=2] debt = 0 len = max(4,6) = 4
    //      REWEW                         need=[W=0,Q=2,E=1,R=2] debt = 0 len = max(4,5) = 4
    //       EWEW                         need=[W=0,Q=2,E=1,R=1] debt = 0 len = max(4,4) = 4
    //        WEW                         need=[W=0,Q=2,E=0,R=1] debt = 0 len = max(4,3) = 3
    //        WEWR                        need=[W=0,Q=2,E=0,R=2] debt = 0
    //        WEWRW                       need=[W=1,Q=2,E=0,R=2] debt = 0
    //         EWRW                       need=[W=0,Q=2,E=0,R=2] debt = 0 len = max(3,4) = 3
    public int balancedString(String str) {
        int n = str.length();
        int[] s = new int[n];
        int divide = n/4;
        // 词频表
        int[] cnt = new int[4];
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            s[i] = str.charAt(i) == 'Q' ? 0 : str.charAt(i) == 'W' ? 1 : str.charAt(i) == 'E' ? 2 : 3;
            cnt[s[i]]++;
        }

        // 债务
        int debt = 0;
        // 统计负债
        for (int i = 0; i < 4; i++) {
            if (cnt[i] < divide) {
                cnt[i] = 0;
            } else {
                cnt[i] = divide - cnt[i];
                debt -= cnt[i];
            }
        }
        // 已经平衡了
        if (debt == 0) {
            return 0;
        }

        // 滑动
        for (int r = 0, l = 0; r < n; r++) {
            // 开始还债
            if (cnt[s[r]]++ < 0) {
                debt--;
            }
            // 当债务归零的时候向左吐数字
            if (debt == 0) {
                // 可以维持窗口大小不变的时候左边界收缩
                while (cnt[s[l]] > 0) {
                    cnt[s[l++]]--;
                }
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans;
    }
}
