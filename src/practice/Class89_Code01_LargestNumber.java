package practice;

import java.util.Arrays;
import java.util.Objects;

// 最大数
// 给定一组非负整数nums
// 重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数
// 测试链接 : https://leetcode.cn/problems/largest-number/
public class Class89_Code01_LargestNumber {

    public String largestNumber(int[] nums) {
        String[] s = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, (a, b)-> (b + a).compareTo(a + b));
        if (Objects.equals(s[0], "0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String str : s) {
            sb.append(str);
        }
        return sb.toString();
    }
}
