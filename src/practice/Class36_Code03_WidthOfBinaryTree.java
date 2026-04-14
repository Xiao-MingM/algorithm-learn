package practice;

import common.TreeNode;

// 二叉树的最大特殊宽度，java版
// 测试链接 : https://leetcode.cn/problems/maximum-width-of-binary-tree/
public class Class36_Code03_WidthOfBinaryTree {

    public static final int MAXN = 3001;
    // 节点队列
    public static TreeNode[] queue = new TreeNode[MAXN];
    // 编号队列
    public static int[] seqQueue = new int[MAXN];
    public static int l, r;
    public int widthOfBinaryTree(TreeNode root) {
        int ans = 0;
        if (root == null) {
            return ans;
        }
        l = r = 0;
        queue[r] = root;
        seqQueue[r++] = 1;
        while (l < r) {
            // 记录当前层的宽度
            ans = Math.max(ans, seqQueue[r - 1] - seqQueue[l] + 1);
            // 把这一层处理完
            int size = r - l;
            for (int k = 0; k < size; k++) {
                TreeNode cur = queue[l];
                int seq = seqQueue[l++];
                if (cur.left != null) {
                    queue[r] = cur.left;
                    seqQueue[r++] = 2 * seq;
                }
                if (cur.right != null) {
                    queue[r] = cur.right;
                    seqQueue[r++] = 2 * seq + 1;
                }
            }
        }
        return ans;
    }

}
