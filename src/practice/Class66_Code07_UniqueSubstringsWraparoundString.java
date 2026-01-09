package practice;

import java.util.HashMap;
import java.util.Map;

// 环绕字符串中唯一的子字符串
// 定义字符串 base 为一个 "abcdefghijklmnopqrstuvwxyz" 无限环绕的字符串
// 所以 base 看起来是这样的：
// "..zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd.."
// 给你一个字符串 s ，请你统计并返回 s 中有多少 不同非空子串 也在 base 中出现
// 测试链接 : https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
public class Class66_Code07_UniqueSubstringsWraparoundString {

//    示例 3：
//
//    输入：s = "zab"
//    输出：6
//    解释：字符串 s 有六个子字符串 ("z", "a", "b", "za", "ab", and "zab") 在 base 中出现。
//    z za zab a ab b

//    zabdfayz  [a,b,d,f,y,z]
//               2 3 1 1 1 2
//    z za zab
//    a ab
//    b
//    d
//    f
//    y yz
    public int findSubstringInWraproundString(String s) {
        // 存以i结尾的最长延长长度，数量为长度累加
        int[] dp = new int[26];

        int[] nums = new int[s.length()];

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            nums[i] = chars[i] - 'a';
        }
        // 第一个下标的dp值初始化为1
        dp[nums[0]] = 1;

        for (int i = 1, pre, cur, len = 1; i < nums.length; i++) {
            cur = nums[i];
            pre = nums[i - 1];

            if ((pre == 25 && cur == 0) || pre + 1 == cur) {
                len++;
            } else {
                len = 1;
            }

            dp[cur] = Math.max(dp[cur], len);
        }
        int ans = 0;
        for (int num : dp) {
            ans += num;
        }

        return ans;
    }


}
