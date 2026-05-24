package practice;

import common.TreeNode;

// 验证平衡二叉树
// 测试链接 : https://leetcode.cn/problems/balanced-binary-tree/
public class Class37_Code04_BalancedBinaryTree {

    boolean isBalance;
    public boolean isBalanced(TreeNode root) {
        // 我默认你平衡
        isBalance = true;
        // 验证是否平衡
        f(root);
        return isBalance;
    }

    private int f(TreeNode root) {
        if (!isBalance || root == null) {
            return 0;
        }
        int leftHeight = f(root.left);
        int rightHeight = f(root.right);
        // 发现高度差超过1尽早结束
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalance = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

}
