package practice;


import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

// 利用先序与中序遍历序列构造二叉树
// 测试链接 : https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
public class Class36_Code07_PreorderInorderBuildBinaryTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return f(preorder, 0, n - 1, 0, map);
    }

    /**
     * preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * @param preorder 前序数组
     * @param l1 前序数组左边界
     * @param r1 前序数组右边界
     * @param l2 中序数组左边界
     * @param map 数字在中序数组的下标映射
     * @return
     */
    public TreeNode f(int[] preorder, int l1, int r1, int l2, Map<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[l1]);
        // 获取头结点在中序的位置
        Integer i = map.get(preorder[l1]);
        // 获取左孩子节点
        node.left = f(preorder, l1 + 1, l1 + i - l2, l2, map);
        // 获取右孩子节点
        node.right = f(preorder, l1 + i - l2 + 1, r1, i + 1, map);
        return node;
    }
}
