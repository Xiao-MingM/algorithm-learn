package practice;

// 分割数组的最大值(画匠问题)
// 给定一个非负整数数组 nums 和一个整数 m
// 你需要将这个数组分成 m 个非空的连续子数组。
// 设计一个算法使得这 m 个子数组各自和的最大值最小。
// 测试链接 : https://leetcode.cn/problems/split-array-largest-sum/
public class Class51_Code02_SplitArrayLargestSum {

    //输入：nums = [7,2,5,10,8], k = 2
    //输出：18
    //解释：
    //一共有四种方法将 nums 分割为 2 个子数组。
    //其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
    //因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。

    // 猜答案的范围 max~sum 即最大值到累加和
    // partition(nums, limit) 表示用指定的答案去划分需要划分出多少份，即每次从左到右尽可能大的划分，返回划分所需的次数，贪心思想
    // 单调性 limit越大，能划分的次数就可以尽可能少，右区间左缩就可以缩小答案范围，limit越小需要的次数越多，就得左区间右缩
    public int splitArray(int[] nums, int k) {
        int ans = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int l = 0, r = sum;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (partition(nums, mid) <= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 划分
     * @param nums 目标数组
     * @param limit 指定的上限大小
     * @return
     */
    int partition(int[] nums, int limit) {
        // 开始划分算一次
        int part = 1;
        int sum = 0;
        for (int num : nums) {
            // 存在值比limit大，直接返回划分失败
            if (num > limit) {
                return Integer.MAX_VALUE;
            }
            // 超了就得新开一个ans
            if (sum + num > limit) {
                part++;
                // 用当前元素做sum开始统计
                sum = num;
            } else {
                sum += num;
            }
        }
        return part;
    }
}
