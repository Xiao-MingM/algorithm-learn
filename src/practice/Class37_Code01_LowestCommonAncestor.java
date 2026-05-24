package practice;

import common.TreeNode;

// 普通二叉树上寻找两个节点的最近公共祖先
// 测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
public class Class37_Code01_LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case  遇到单个节点 命中一个就直接把当前这个往上传
        if (root == null || root == p || root == q) {
            return root;
        }
        // 后序调用
        // 去左边找
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        // 去右边找
        TreeNode r = lowestCommonAncestor(root.right, p, q);

        // 合并结果
        // 分布在两边，当前root就是最近祖先
        if (l != null && r != null) {
            return root;
        }
        // 两边都没找到返回null
        if (l == null && r == null) {
            return null;
        }
        // 两个有一个非空节点，返回有值的那个
        return l != null ? l : r;
    }
}
