package practice;

import common.TreeNode;

// 验证完全二叉树
// 测试链接 : https://leetcode.cn/problems/check-completeness-of-a-binary-tree/
public class Class36_Code08_CompletenessOfBinaryTree {


    public static final int MAXN = 101;
    public static final TreeNode[] queue = new TreeNode[MAXN];
    public static int l,r;

    // 层序遍历
    // 1. 有右孩子没有左孩子直接返回 false
    // 2. 如果遇到第一个不完整节点，后面出现的节点必须全是叶子节点
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        l = r = 0;
        queue[r++] = root;
        // 是否出现过叶子节点
        boolean leaf = false;
        while (l < r) {
            TreeNode cur = queue[l++];
            if ((cur.left == null && cur.right != null) || (leaf && (cur.left != null || cur.right != null))) {
                return false;
            }
            if (cur.left != null) {
                queue[r++] = cur.left;
            }
            if (cur.right != null) {
                queue[r++] = cur.right;
            }
            // 开始出现不完整结点
            if (cur.left == null || cur.right == null) {
                leaf = true;
            }
        }
        return true;
    }
}
