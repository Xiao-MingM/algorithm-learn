package prepare;

import java.util.*;

//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
//
//注意：答案中不可以包含重复的三元组。
public class LeetCode015 {
    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> ans = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 固定第一个数在剩下的范围内跑两数之和
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int target = -nums[i];
            map.clear();
            // 需要保证剩余空间的数据两数之和为target
            for (int j = i + 1; j < n; j++) {
                int sub = target - nums[j];
                if (map.containsKey(sub)) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], sub);
                    Collections.sort(list);
                    ans.add(list);
                }
                map.put(nums[j], j);
            }
        }
        return new ArrayList<>(ans);
    }

    // 双指针
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 出现正数后面必然全是正数
            if (nums[i] > 0) {
                break;
            }
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    // 结算答案
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    // 剪枝去重
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r - 1] == nums[r]) {
                        r--;
                    }
                    l++;
                    r--;
                }
            }
        }
        return ans;
    }

}
