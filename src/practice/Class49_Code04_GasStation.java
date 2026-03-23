package practice;

// 加油站
// 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
// 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升
// 你从其中的一个加油站出发，开始时油箱为空。
// 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周
// 则返回出发时加油站的编号，否则返回 -1
// 如果存在解，则 保证 它是 唯一 的。
// 测试链接 : https://leetcode.cn/problems/gas-station/
public class Class49_Code04_GasStation {
    // gas = [1,2,3,4,5], cost = [3,4,5,1,2]
    // arr = [-2,-2,-2,3,3]
    // gas = [3,2,6,4,1,4], cost = [6,4,1,5,2,2]
    // arr = [-3,-2,5,-1,-1,2][-3,-2,5,-1,-1,2]
    //        lr                                [-3] l++, r++
    //           lr                             [-2] l++, r++
    //              lr                          [5] 5 r++
    //              l r                         [5, -1] 4 r++
    //              l    r                      [5, -1, -1] 3 r++
    //              l       r                   [5, -1, -1, 2] 5 r++
    //              l          r                [5, -1, -1, 2, -3] 2 r++
    //              l             r             [5, -1, -1, 2, -3, -2] 0 len = 6
    // arr = [-3,-3,5,-1,-1,2][-3,-3,5,-1,-1,2]
    //        lr                                [-3] l=r+1, r = l
    //           lr                             [-2] l=r+1, r = l
    //              lr                          [5] 5 r++
    //              l r                         [5, -1] 4 r++
    //              l    r                      [5, -1, -1] 3 r++
    //              l       r                   [5, -1, -1, 2] 5 r++
    //              l          r                [5, -1, -1, 2, -3] 2 r++
    //              l             r             [5, -1, -1, 2, -3, -3] -1 x
    //                            lr            l = r+1, r = l
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int l = 0, r = 0, sum; l < n; l = r + 1, r = l) {
            sum = 0;
            // 从l出发的位置开始向右扩
            while (sum + gas[r%n] - cost[r%n] >= 0) {
                if (r - l + 1 == n) {
                    return l;
                }
                sum += gas[r%n] - cost[r%n];
                r++;
            }
        }
        return -1;
    }
}
