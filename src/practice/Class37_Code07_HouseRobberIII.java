package practice;

import common.TreeNode;

// 二叉树打家劫舍问题
// 测试链接 : https://leetcode.cn/problems/house-robber-iii/
public class Class37_Code07_HouseRobberIII {
    public int rob(TreeNode root) {
        int[] ans = f(root);
        return Math.max(ans[0], ans[1]);
    }

    // 返回值含义：
    // ans[0]：偷当前节点时，当前子树能获得的最大收益
    // ans[1]：不偷当前节点时，当前子树能获得的最大收益
    private int[] f(TreeNode root) {
        if (root == null) {
            return new int[] { 0, 0 };
        }
        int[] left = f(root.left);
        int[] right = f(root.right);

        // 偷当前节点：左右孩子都不能偷，只能拿左右子树“不偷头”的收益
        int robRoot = root.val + left[1] + right[1];

        // 不偷当前节点：左右孩子可偷可不偷，各自选收益更大的方案
        int skipRoot = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[] { robRoot, skipRoot };
    }

}
