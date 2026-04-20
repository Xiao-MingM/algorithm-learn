package practice;

import common.TreeNode;

// 搜索二叉树上寻找两个节点的最近公共祖先
// 测试链接 : https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class Class37_Code02_LowestCommonAncestorBinarySearch {
    /*
     * 搜索二叉树不用递归去调了，直接按照顺序搜索即可
     * 1. 如果找到p，或者q直接返回（p或者q就是公共祖先）
     * 2. 如果节点在p,q大小之间就是最近祖先
     * 3. 比pq最小值小去右边找，比pq最大值大，去左边找
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 开循环去找
        while (root != p && root != q) {
            // 在中间
            if (root.val < Math.max(p.val, q.val) && root.val > Math.min(p.val, q.val)) {
                break;
            }
            root = root.val > Math.max(p.val, q.val) ? root.left : root.right;
        }
        return root;
    }
}
