package practice;

import common.TreeNode;

import java.util.Objects;

// 二叉树按层序列化和反序列化
// 测试链接 : https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/
public class Class36_Code06_LevelorderSerializeAndDeserialize {
    public static class Codec {

        public static final int MAXN = 10001;

        public static final TreeNode[] queue = new TreeNode[MAXN];

        public static int l, r;

        // 1,2,3,#,#,4,5,#,#,#,#
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            if (root == null) {
                return "";
            }
            l = r = 0;
            queue[r++] = root;
            sb.append(root.val).append(",");
            while (l < r) {
                TreeNode cur = queue[l++];
                if (cur.left != null) {
                    queue[r++] = cur.left;
                    sb.append(cur.left.val).append(",");
                } else {
                    sb.append("#").append(",");
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                    sb.append(cur.right.val).append(",");
                } else {
                    sb.append("#").append(",");
                }
            }
            return sb.toString();
        }

        // 1,2,3,#,#,4,5,#,#,#,#
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }
            String[] values = data.split(",");
            int index = 0;
            TreeNode root = generateNode(values[index++]);
            l = r = 0;
            queue[r++] = root;
            while (l < r) {
                TreeNode cur = queue[l++];
                cur.left = generateNode(values[index++]);
                cur.right = generateNode(values[index++]);
                if (cur.left != null) {
                    queue[r++] = cur.left;
                }
                if (cur.right != null) {
                    queue[r++] = cur.right;
                }
            }
            return root;
        }

        TreeNode generateNode(String value) {
            if (Objects.equals(value, "#")) {
                return null;
            } else {
                return new TreeNode(Integer.parseInt(value));
            }
        }
    }
}
