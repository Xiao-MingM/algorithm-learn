package practice;

import java.util.Arrays;
import java.util.Comparator;

// 两地调度
// 公司计划面试2n个人，给定一个数组 costs
// 其中costs[i]=[aCosti, bCosti]
// 表示第i人飞往a市的费用为aCosti，飞往b市的费用为bCosti
// 返回将每个人都飞到a、b中某座城市的最低费用
// 要求每个城市都有n人抵达
// 测试链接 : https://leetcode.cn/problems/two-city-scheduling/
public class Class89_Code02_TwoCityScheduling {

    //示例 1：
    //
    //输入：costs = [[10,20],[30,200],[400,50],[30,20]]
    //输出：110
    //解释：
    //第一个人去 a 市，费用为 10。
    //第二个人去 a 市，费用为 30。
    //第三个人去 b 市，费用为 50。
    //第四个人去 b 市，费用为 20。
    //
    //最低总费用为 10 + 30 + 50 + 20 = 110，每个城市都有一半的人在面试。
    //          a_i  sub_i
    // [10,20]  10, 10                       [400,50] 400,-350   去b
    // [30,200] 30, 200   ---> sort by sub  [30,20]  30,-10     去b
    // [400,50] 400,-350                     [10,20]  10, 10
    // [30,20]  30,-10                       [30,200] 30, 200
    // 费用=50+20+10+30=110   --->   400+30+10+30-350-10=110
    public int twoCitySchedCost(int[][] costs) {
        int len = costs.length;
        Arrays.sort(costs, Comparator.comparingInt(o -> (o[1] - o[0])));
        int sum = 0;
        int n = len / 2;
        for (int i = 0; i < len; i++) {
            sum += costs[i][0];
            // 前n个人去b地，扣除其差额
            if (i < n) {
                sum += costs[i][1] - costs[i][0];
            }
        }
        return sum;
    }
}
