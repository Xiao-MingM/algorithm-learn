package practice;

import java.util.ArrayList;
import java.util.List;

// 二叉树的锯齿形层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
public class Class36_Code02_ZigzagLevelOrderTraversal {
    public static final int MAXN = 2001;

    public static final TreeNode[] queue = new TreeNode[MAXN];

    public static int l, r;

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> result;
        boolean reverse = false;
        queue[r++] = root;
        while (l < r) {
            result = new ArrayList<>();
            int size = r - l;
            // 将队列里的数据加入结果里
            for (int k = 0, i = reverse ? r - 1 : l, j = reverse ? -1 : 1; k < size; i += j, k++) {
                result.add(queue[i].val);
            }
            ans.add(result);

            for (int k = 0; k < size; k++) {
                TreeNode cur = queue[l++];
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
            }

            // 取反
            reverse = !reverse;
        }
        return ans;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
