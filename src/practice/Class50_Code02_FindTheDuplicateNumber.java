package practice;

// 寻找重复数
// 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n）
// 可知至少存在一个重复的整数。
// 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
// 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
// 测试链接 : https://leetcode.cn/problems/find-the-duplicate-number/
public class Class50_Code02_FindTheDuplicateNumber {
    //为什么常写成
    //slow = nums[0], fast = nums[nums[0]]
    //这是把慢指针先走 1 步、快指针先走 2 步，等价于写成：
    //slow = 0; fast = 0;
    //do {
    //  slow = nums[slow];
    //  fast = nums[nums[fast]];
    //} while (slow != fast);
    //为什么相遇后要 fast = 0，不是 fast = nums[0]
    //Floyd 第二阶段定理是：
    //一根指针从 head 出发
    //另一根从相遇点出发
    //两者每次都走 1 步
    //会在环入口相遇
    //在这题里，head 就是索引 0（因为你构造的是从 0 开始的链）。
    //所以必须复位到 0，即 fast = 0（或把 slow 复位到 0 也行）。
    //如果你复位到 nums[0]，相当于从 head 提前走了一步，路径相位变了，不再保证在入口相遇（有些数据碰巧对，有些会错）。
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
