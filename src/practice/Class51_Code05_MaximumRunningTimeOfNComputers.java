package practice;

// 同时运行N台电脑的最长时间
// 你有 n 台电脑。给你整数 n 和一个下标从 0 开始的整数数组 batteries
// 其中第 i 个电池可以让一台电脑 运行 batteries[i] 分钟
// 你想使用这些电池让 全部 n 台电脑 同时 运行。
// 一开始，你可以给每台电脑连接 至多一个电池
// 然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 任意次
// 新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池
// 断开连接和连接新的电池不会花费任何时间。
// 注意，你不能给电池充电。
// 请你返回你可以让 n 台电脑同时运行的 最长 分钟数。
// 测试链接 : https://leetcode.cn/problems/maximum-running-time-of-n-computers/
public class Class51_Code05_MaximumRunningTimeOfNComputers {


    // 答案是什么：同时可以跑的分钟数，范围是什么
    // 求最大运行时间T maxRunTime(n, batteries)=设答案是T，能不能让电脑同时运行T分钟 check(T)
    // 定义 check(T) = “能否同时跑满 T 分钟”。
    // 如果 T 可行，那么更小的时间 T' < T 一定可行。
    // 如果 T 不可行，那么更大的时间一定也不可行。
    // 所以 check(T) 是单调的（true...true false...false 或反过来，根据你怎么写边界），可二分
    // 什么时候可行：sum(min(b_i, T)) >= T*n则符合

    // 最关键的判断公式怎么来？
    //很多人卡在这：到底怎样判断一个 T 可不可行？
    //核心结论：sum(min(b_i, T)) >= T*n则符合
    //其中 b_i 是第 i 块电池容量。
    //为什么是 min(b_i, T)？
    //一块电池在总时长 T 内，最多贡献 T 分钟（因为同一时刻只能接在一台电脑上，且总共就 T 分钟）。
    //它即使容量很大，也不可能在这 T 分钟里贡献超过 T。
    //所以它的有效贡献是 min(容量, T)。
    //为什么求和后和 n*T 比？
    //n 台电脑每台要跑 T 分钟，总需求是 n*T“电脑-分钟”。
    //所有电池在 T 时间窗内能提供的总“电脑-分钟”就是 sum(min(b_i, T))。
    //供给 >= 需求，就可行。
    //这个式子是整题灵魂。
    public long maxRunTime1(int n, int[] batteries) {
        long ans = 0;
        long l = 0, r, sum = 0;
        for (int battery : batteries) {
            sum += battery;
        }
        r = sum;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            // 当前这个电量都能跑，试试再大一点能不能跑
            if (isEnough(n, batteries, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                // 当前这个电量都跑不了，试试再小一点能不能跑
                r = mid - 1;
            }
        }
        return ans;
    }

    // 指定时间T，看看给的电池
    boolean isEnough(int n, int[] batteries, long t) {
        boolean enough = false;
        long all = n * t;
        long sum = 0;
        for (int battery : batteries) {
            sum += Math.min(battery, t);
            // 如果够用则直接返回
            if (sum >= all) {
                return true;
            }
        }
        return enough;
    }

    public long maxRunTime(int n, int[] batteries) {
        long ans = 0;
        long l = 0, r, sum = 0, max = 0;
        for (int battery : batteries) {
            sum += battery;
            max = Math.max(max, battery);
        }
        // 最终供电大于max,则都是碎片电池，取最大值即可
        if (sum > (long) n * max) {
            return sum/n;
        }
        r = sum;
        while (l <= r) {
            long mid = l + ((r - l) >> 1);
            // 当前这个电量都能跑，试试再大一点能不能跑
            if (isEnough(n, batteries, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                // 当前这个电量都跑不了，试试再小一点能不能跑
                r = mid - 1;
            }
        }
        return ans;
    }

}
