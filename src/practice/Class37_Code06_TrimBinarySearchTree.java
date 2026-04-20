package practice;

import common.TreeNode;

// 修剪搜索二叉树
// 测试链接 : https://leetcode.cn/problems/trim-a-binary-search-tree/
public class Class37_Code06_TrimBinarySearchTree {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        // 如果当前节点比最小值还要小，连头和左孩子都不要了，把右孩子处理完返回上去
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // 如果当前节点比最大值还要大，连头和右孩子都不要了，把左孩子处理完返回上去
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }

        // 当前节点在[low,high]区间时，将左右接好带头返回回去
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

}
