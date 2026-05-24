package practice;

import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 收集累加和等于aim的所有路径
// 测试链接 : https://leetcode.cn/problems/path-sum-ii/
public class Class37_Code03_PathSumII {
    List<List<Integer>> ans;
    LinkedList<Integer> path;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        ans = new ArrayList<>();
        path = new LinkedList<>();
        f(root, 0, targetSum);
        return ans;
    }

    private void f(TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return;
        }
        // 将节点加入路径
        path.addLast(root.val);

        // 符合条件的进行结算
        if (root.left == null && root.right == null) {
            if (targetSum == sum + root.val) {
                ans.add(new ArrayList<>(path));
            }
        }

        f(root.left, sum + root.val, targetSum);
        f(root.right, sum + root.val, targetSum);
        // 还原现场
        path.removeLast();
    }
}
