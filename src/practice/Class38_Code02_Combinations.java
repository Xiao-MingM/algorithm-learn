package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的组合
// 答案 不能 包含重复的组合。返回的答案中，组合可以按 任意顺序 排列
// 注意其实要求返回的不是子集，因为子集一定是不包含相同元素的，要返回的其实是不重复的组合
// 比如输入：nums = [1,2,2]
// 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 测试链接 : https://leetcode.cn/problems/subsets-ii/
public class Class38_Code02_Combinations {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        f(nums, 0, new int[nums.length], 0, ans);
        return ans;
    }

    // 从i出发到终点指定path路径为size长度求解答案
    private void f(int[] nums, int i, int[] path, int size, List<List<Integer>> ans) {
        // 到达边界的时候答案加进去
        if (i == nums.length) {
            List<Integer> result = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                result.add(path[j]);
            }
            ans.add(result);
        } else {
            int j = i + 1;
            // 找到下一个不重复的数字
            while (j < nums.length && nums[i] == nums[j]) {
                j++;
            }
            // 不要i位置的时候调递归，size没变
            f(nums, j, path, size, ans);
            for (; i < j; i++) {
                // 依次要1~重复nums[i]个数字
                path[size++] = nums[i];
                f(nums, j, path, size, ans);
            }
        }
    }

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        // 排序方便去重
        Arrays.sort(nums);
        f2(nums,0);
        return result;
    }

    /**
     * 深度优先遍历
     * @param nums 数组
     * @param start 深度遍历的下一个起始位置
     */
    void f2(int[] nums, int start){
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 如果当前元素和上一个元素相同，证明该元素已经被包含了，再遍历会发生重复，直接剪枝走下一个元素
            if (i > start && nums[i] == nums[i-1]){
                continue;
            }
            path.add(nums[i]);
            // 每次递归的位置+1
            f2(nums, i + 1);
            path.removeLast();
        }
    }

}
