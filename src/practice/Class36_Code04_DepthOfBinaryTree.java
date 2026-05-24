package practice;

import common.TreeNode;

// 求二叉树的最大、最小深度
public class Class36_Code04_DepthOfBinaryTree {

    // 最大深度
    // 测试链接 : https://leetcode.cn/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    // 最小深度
    //     1
    //    2  3
    //   4 5  6
    //  7
    // 测试链接 : https://leetcode.cn/problems/minimum-depth-of-binary-tree/
    public int minDepth(TreeNode root) {
        // 空节点返回0
        if (root == null) {
            return 0;
        }
        // 叶子节点返回1(当前节点)
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftMinDepth = Integer.MAX_VALUE;
        int rightMinDepth = Integer.MAX_VALUE;
        // 只搞带分支的，一直走到叶子节点往上传
        if (root.left != null) {
            leftMinDepth = minDepth(root.left);
        }
        if (root.right != null) {
            rightMinDepth = minDepth(root.right);
        }
        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }
}
