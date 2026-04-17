package practice;

import java.util.Arrays;
import java.util.Comparator;

// 最长数对链
// 给你一个由n个数对组成的数对数组pairs
// 其中 pairs[i] = [lefti, righti] 且 lefti < righti
// 现在，我们定义一种 跟随 关系，当且仅当 b < c 时
// 数对 p2 = [c, d] 才可以跟在 p1 = [a, b] 后面
// 我们用这种形式来构造 数对链
// 找出并返回能够形成的最长数对链的长度
// 测试链接 : https://leetcode.cn/problems/maximum-length-of-pair-chain/
public class Class72_Code04_MaximumLengthOfPairChain {

    //示例 1：
    //
    //输入：pairs = [[1,2], [2,3], [3,4]]
    //输出：2
    //解释：最长的数对链是 [1,2] -> [3,4] 。
    //示例 2：
    //
    //输入：pairs = [[1,2],[7,8],[4,5]]
    //输出：3
    //解释：最长的数对链是 [1,2] -> [4,5] -> [7,8] 。
    // [1,2] [2,4] [3,4] [5,7] [5,6]  [7,8] [8,9]
    // ends[2,4,7,8,9]
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        // 只按头位置排序就可以
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int[] ends = new int[n + 1];
        int len = 0;
        for (int[] pair : pairs) {
            int find = bs(ends, len, pair[0]);
            if (find == -1) {
                ends[len++] = pair[1];
            } else {
                // 保留更小的结尾，方便后续接入
                ends[find] = Math.min(pair[1], ends[find]);
            }
        }
        return len;
    }

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
