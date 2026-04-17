package practice;

import java.util.Arrays;
import java.util.Comparator;

// 俄罗斯套娃信封问题
// 给你一个二维整数数组envelopes ，其中envelopes[i]=[wi, hi]
// 表示第 i 个信封的宽度和高度
// 当另一个信封的宽度和高度都比这个信封大的时候
// 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样
// 请计算 最多能有多少个信封能组成一组“俄罗斯套娃”信封
// 即可以把一个信封放到另一个信封里面，注意不允许旋转信封
// 测试链接 : https://leetcode.cn/problems/russian-doll-envelopes/
public class Class72_Code02_RussianDollEnvelopes {

    //示例 1：
    //
    //输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
    //输出：3
    //解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
    //示例 2：
    //
    //输入：envelopes = [[1,1],[1,1],[1,1]]
    //输出：1
    //
    // [2,3][5,4][6,7][6,4][7,8][7,1][8,5]
    // 宽度升序，高度降序，因为同宽的不能相互套，[2,3][2,2][2,1]->只会选一个，但是[2,1][2,2][2,3]在高度上找最长递增子序列三个都符合
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 宽度升序，高度降序（避免同宽错链）
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        // 对高度进行LIS
        int[] ends = new int[n + 1];
        int len = 0;
        // 遍历开始找最长递增子序列
        for (int[] envelope : envelopes) {
            int find = bs(ends, len, envelope[1]);
            // 找不到>=envelopes[i][1]的最大最值，将其加到后面
            if (find == -1) {
                ends[len++] = envelope[1];
            } else {
                // 更新find的最大值
                ends[find] = envelope[1];
            }
        }
        return len;
    }

    // 找 >= num的最左位置
    private int bs(int[] ends, int len, int num) {
        int l = 0, r = len - 1, ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (ends[mid] >= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

}
