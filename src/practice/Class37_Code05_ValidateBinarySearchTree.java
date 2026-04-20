package practice;

import common.TreeNode;

// 验证搜索二叉树
// 测试链接 : https://leetcode.cn/problems/validate-binary-search-tree/
public class Class37_Code05_ValidateBinarySearchTree {

    static final int MAXN = 10001;
    static final TreeNode[] stack = new TreeNode[MAXN];
    static int r;

    public boolean isValidBST1(TreeNode root) {
        r = 0;
        TreeNode pre = null;
        while (r > 0 || root != null) {
            // 左边扎到底
            if (root != null) {
                stack[r++] = root;
                root = root.left;
            } else {
                // 出栈
                root = stack[--r];
                // 前一个节点比当前节点大，违背规则直接返回false
                if (pre != null && pre.val >= root.val) {
                    return false;
                }
                pre = root;
                root = root.right;
            }
        }
        return true;
    }

    long min, max;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
            return true;
        }
        // 递归看左边是否ok
        boolean lOk = isValidBST2(root.left);
        long lmax = max;
        long lmin = min;
        // 递归看左边是否ok
        boolean rOk = isValidBST2(root.right);
        long rmax = max;
        long rmin = min;

        min = Math.min(Math.min(lmin, rmin), root.val);
        max = Math.max(Math.max(lmax, rmax), root.val);
        return lOk && rOk && lmax < root.val && root.val < rmin;
    }
    TreeNode pre;
    public boolean isValidBST(TreeNode root) {
        // 空为true
        if (root == null) {
            return true;
        }
        // 左边不符合直接返回
        boolean left = isValidBST(root.left);
        if (!left) {
            return false;
        }
        // 不符合的情况返回false
        if (pre != null && pre.val >= root.val) {
            return false;
        }
        pre = root;
        return isValidBST(root.right);
    }

}
