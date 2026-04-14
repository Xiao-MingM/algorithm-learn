package practice;

import common.TreeNode;

import java.util.Objects;

// 二叉树先序序列化和反序列化
// 测试链接 : https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Class36_Code05_PreorderSerializeAndDeserialize {

    public static class Codec {

        // 先序序列化
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            g(root, sb);
            return sb.toString();
        }

        public void g(TreeNode root, StringBuilder sb) {
            // 不能return
            if (root == null) {
                sb.append("#").append(",");
            } else {
                sb.append(root.val).append(",");
                g(root.left, sb);
                g(root.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        static int cnt;
        public TreeNode deserialize(String data) {
            String[] values = data.split(",");
            // 初始化cnt
            cnt = 0;
            return g(values);
        }

        public TreeNode g(String[] values) {
            String value = values[cnt++];
            if (Objects.equals(value, "#")) {
                return null;
            } else {
                int val = Integer.parseInt(value);
                TreeNode node = new TreeNode(val);
                node.left = g(values);
                node.right = g(values);
                return node;
            }
        }
    }
}
