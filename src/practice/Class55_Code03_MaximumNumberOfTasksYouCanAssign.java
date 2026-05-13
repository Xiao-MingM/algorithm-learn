package practice;

import java.util.Arrays;

// 你可以安排的最多任务数目
// 给你 n 个任务和 m 个工人。每个任务需要一定的力量值才能完成
// 需要的力量值保存在下标从 0 开始的整数数组 tasks 中，
// 第i个任务需要 tasks[i] 的力量才能完成
// 每个工人的力量值保存在下标从 0 开始的整数数组workers中，
// 第j个工人的力量值为 workers[j]
// 每个工人只能完成一个任务，且力量值需要大于等于该任务的力量要求值，即workers[j]>=tasks[i]
// 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength
// 你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸
// 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength
// 请你返回 最多 有多少个任务可以被完成。
// 测试链接 : https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/
public class Class55_Code03_MaximumNumberOfTasksYouCanAssign {

    private static final int MAXN = 50001;
    private static final int[] queue = new int[MAXN];
    private static int h, t;

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        // 先排个序，贪心要用
        Arrays.sort(tasks);
        Arrays.sort(workers);
        // 二分区间： 0~工人的数量和任务数的最小值
        int l = 0, r = Math.min(workers.length, tasks.length);
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 如果指定完成的任务数消耗的药丸数量比要求的少说明可以完成，提高要求任务数二分尝试
            if (done(tasks, workers, mid, strength, pills)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    // 要求工人干k个任务能干完吗
    // [3,3,5,6,8,9]
    // [3,4,4, 5,9,10,12] k = 4
    //  0 1 2  3 4 5   6
    private boolean done(int[] tasks, int[] workers, int k, int strength, int pills) {
        int n = workers.length;
        h = t = 0;
        int count = 0;
        // 从最强的k个工人开始挑最容易的k个工作做
        for (int i = n - k, j = 0; i < n; i++) {
            int ability = workers[i];
            // 看看workers[i]能不能解锁tasks
            while (j < k && tasks[j] <= ability) {
                // 解锁的任务进去
                queue[t++] = j++;
            }
            // 看看当前的工人是否有活干，有的话弹出能干的活儿
            if (h < t && tasks[queue[h]] <= ability) {
                h++;
            } else {
                // 没活干或者干不了就得吃药了
                int strengthen = ability + strength;
                // 吃完药再去解锁新任务到队列
                while (j < k && tasks[j] <= strengthen) {
                    queue[t++] = j++;
                }
                // 用吃完药的力量去干最有挑战的活儿，如果发现吃完药都没解锁能干的活直接失败了
                if (h == t || tasks[queue[t - 1]] > strengthen) {
                    return false;
                }
                // 干掉困难任务
                t--;
                // 统计使用药丸一次
                count++;
            }
        }
        return count <= pills;
    }
}
