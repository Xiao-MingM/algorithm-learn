package practice;

import common.TreeNode;

// 求完全二叉树的节点个数
// 测试链接 : https://leetcode.cn/problems/count-complete-tree-nodes/
public class Class36_Code09_CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = mostLeft(root, 1);
        return f(root, h, 1);
    }

    /**
     *
     * @param root 根节点
     * @param h 二叉树的总高度
     * @param level 当前所在层级
     * @return
     */
    private int f(TreeNode root, int h, int level) {
        if (level == h) {
            return 1;
        }
        // 当前的右孩子扎到底可以触及高度h，说明左孩子为头结点的树为满二叉树 2^n - 1 + 1(头结点)
        if (mostLeft(root.right, level + 1) == h) {
            return (1 << (h - level)) + f(root.right, h, level + 1);
        } else {
            // 当前的右孩子扎到底不能触及高度h，说明右孩子为头结点的树为满二叉树 2^n - 1 + 1(头结点)
            return (1 << (h - level - 1)) + f(root.left, h, level + 1);
        }
    }

    // 统计当前节点出发向左能扎到第几层
    private int mostLeft(TreeNode cur, int level) {
        while (cur != null) {
            level++;
            cur = cur.left;
        }
        return level - 1;
    }
}
