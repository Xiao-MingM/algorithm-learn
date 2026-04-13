package practice;

import java.util.ArrayList;
import java.util.List;

// 二叉树的层序遍历
// 测试链接 : https://leetcode.cn/problems/binary-tree-level-order-traversal/
public class Class36_Code01_LevelOrderTraversal {

    public static final int MAXN = 2001;

    public static final TreeNode[] queue = new TreeNode[MAXN];

    public static int l, r;

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> result;
        // 初始化队列为空
        l = r = 0;
        queue[r++] = root;
        // 队列非空
        while (l < r) {
            result = new ArrayList<>();
            int size = r - l;
            for (int i = 0; i < size; i++) {
                // 出队列
                TreeNode cur = queue[l++];
                result.add(cur.val);
                // 入队列
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
            }
            ans.add(result);
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



 
