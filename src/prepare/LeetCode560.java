package prepare;

import java.util.HashMap;
import java.util.Map;

// 560. 和为 K 的子数组
// https://leetcode.cn/problems/subarray-sum-equals-k/description/
public class LeetCode560 {
    public int subarraySum(int[] nums, int aim) {
        // aim = aim, v = 出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        map.put(0, 1);
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += map.getOrDefault(sum - aim, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
