package practice;

// 缺失的第一个正数
// 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
// 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
// 测试链接 : https://leetcode.cn/problems/first-missing-positive/
public class Class50_Code07_FirstMissingPositive {
    //示例 1：
    //
    //输入：nums = [1,2,0]
    //输出：3
    //解释：范围 [1,2] 中的数字都在数组中。
    //示例 2：
    //
    //输入：nums = [3,4,-1,1]
    //输出：2
    //解释：1 在数组中，但 2 没有。
    //示例 3：
    //
    //输入：nums = [7,8,9,11,12]
    //输出：1
    //解释：最小的正数 1 没有出现。
    //
    // [3,4,-1,1]   r为垃圾区
    //  l        r  3跟-1交换
    // [-1,4,3,1]
    //  l        r  -1是垃圾，跟--r交换
    // [1,4,3,-1]
    //  l     r     1是对的，l++
    // [1,4,3,-1]
    //    l   r     4找到的位置是r了，已经放不进去了，发送到垃圾区--r
    // [1,3,4,-1]
    //    l r       3要放的位置是r了，已经放不进去了，发送到垃圾区--r
    // [1,3,4,-1]
    //    lr        lr重合找寻结束，缺失的第一个正数是l + 1
    public int firstMissingPositive(int[] nums) {
        int l = 0, r = nums.length;
        while (l < r) {
            // 先判定正确情况
            if (nums[l] - 1 == l) {
                // 发现它该待的地方了
                l++;
            } else if (nums[l] - 1 >= r || nums[l] - 1 < l || nums[nums[l] - 1] == nums[l]) {
                // 数字应该放的地方超过垃圾区，数字已经小于它应该落的位置，它该去的地方有个和它一样的数，直接发往垃圾区
                swap(nums, l, --r);
            } else {
                // 发往它该待的地方
                swap(nums, l, nums[l] - 1);
            }
        }
        return l + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
