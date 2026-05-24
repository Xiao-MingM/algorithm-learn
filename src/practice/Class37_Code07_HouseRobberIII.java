package practice;

import common.TreeNode;

// 二叉树打家劫舍问题
// 测试链接 : https://leetcode.cn/problems/house-robber-iii/
public class Class37_Code07_HouseRobberIII {
    public int rob(TreeNode root) {
        f(root);
        return Math.max(yes, no);
    }

    int yes, no;
    private void f(TreeNode root) {
        if (root == null) {
            yes = 0;
            no = 0;
        } else {
            // 决定偷头结点
            int y = root.val;
            // 不偷头结点
            int n = 0;

            // 偷左边
            f(root.left);
            // 偷头结点只能不偷邻居
            y += no;
            // 不偷头结点可以选择偷或者不偷左孩子的方案
            n += Math.max(yes, no);

            // 偷右边
            f(root.right);
            // 偷头结点只能不偷邻居
            y += no;
            // 不偷头结点可以选择偷或者不偷左孩子的方案
            n += Math.max(yes, no);

            // 更新偷的结果到全局变量里去
            yes = y;
            no = n;
        }
    }

}
