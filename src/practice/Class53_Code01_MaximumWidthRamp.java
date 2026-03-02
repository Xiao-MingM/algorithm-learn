package practice;

// 最大宽度坡
// 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]
// 这样的坡的宽度为 j - i，找出 A 中的坡的最大宽度，如果不存在，返回 0
// 测试链接 : https://leetcode.cn/problems/maximum-width-ramp/
public class Class53_Code01_MaximumWidthRamp {

    // [9,8,1,0,1,9,4,0,4,1] 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
    // 单调栈维持一种可能性 小压大
    // 比如[9,8,1,0]，左边界可能出现的范围只有这些
    // [9,8,1,0] 右侧遍历9-1，0<1构成坡，弹出3-0，结算 ans = 9-3 = 6
    // [9,8,1] 右侧遍历9-1，1=1构成坡，弹出2-1，9-2 = 7, 结算ans = max(7,6) = 7
    // [9,8] 右侧遍历9-1，8>1, i--
    // ...
    // [9,8] 右侧遍历5-9，8<9,弹出1-8, 5-1= 4,结算ans = max(4,7) = 7
    // [9,8] 右侧遍历5-9，9=9,弹出0-9, 5-0= 5,结算ans = max(5,7) = 7
    // 为什么要小压大，比如[9,8,1,0]如果1入栈，如果后面有个大于1的结算答案4-1肯定没有3-0好
    // 为什么右边遍历出现坡就弹出，因为比如[9,8,1,0] 右侧遍历9-1，0<1构成坡，弹出3-0，9-1已经是最右边的下标了，就算左边还有大于0的，也没有9-1更好
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] stack = new int[n];
        int ans = 0;
        // 从下标1开始遍历，第一个下标一定进栈
        int r = 1;
        // 获取所有的可能性
        for (int i = 1; i < n; i++) {
            // 小压大的栈
            if (nums[stack[r - 1]] > nums[i]) {
                stack[r++] = i;
            }
        }
        // 开始清算
        for (int i = n - 1; i >= 0; i--) {
            // 栈顶元素构成坡
            while (r > 0 && nums[stack[r - 1]] <= nums[i]) {
                // 栈顶元素出栈
                int cur = stack[--r];
                // 结算答案
                ans = Math.max(ans, i - cur);
            }
        }
        return ans;
    }
}
