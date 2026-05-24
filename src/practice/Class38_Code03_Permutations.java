package practice;

import java.util.ArrayList;
import java.util.List;

// 没有重复项数字的全排列
// 测试链接 : https://leetcode.cn/problems/permutations/
public class Class38_Code03_Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        f(nums, 0, ans);
        return ans;
    }

    // 从i位置开始递归的所有情况
    private void f(int[] nums, int i, List<List<Integer>> ans) {
        // i走到底收集答案
        if (i == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            ans.add(list);
        } else {
            // 枚举所有交换的情况
            for (int j = i; j < nums.length; j++) {
                // 交换一下
                swap(nums, i, j);
                f(nums, i + 1, ans);
                // 恢复现场换回来
                swap(nums, i, j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
