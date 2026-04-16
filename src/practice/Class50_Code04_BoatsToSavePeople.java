package practice;

import java.util.Arrays;

// 救生艇
// 给定数组 people
// people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit
// 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit
// 返回 承载所有人所需的最小船数
// 测试链接 : https://leetcode.cn/problems/boats-to-save-people/
public class Class50_Code04_BoatsToSavePeople {
    // 输入：people = [3,5,3,4], limit = 5
    // 输出：4
    // 输入：people = [3,2,2,1], limit = 3
    // 输出：3
    // 1,2,2,3
    // l     r
    // l   r
    //  lr
    //
    public int numRescueBoats(int[] people, int limit) {
        int n = people.length;
        Arrays.sort(people);
        int ans = 0;
        int l = 0, r = n - 1;
        while (l <= r) {
            // 有人超重了上不了船
            if (people[r] > limit) {
                return -1;
            }
            int two = people[l] + people[r];
            // 最轻的人和最重的人配对，如果超重了，最后一个胖子只能一条船
            if (two <= limit) {
                // 两个人可以凑一艘船
                l++;
            }
            r--;
            ans++;
        }
        return ans;
    }
}
