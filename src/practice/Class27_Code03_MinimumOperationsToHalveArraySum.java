package practice;

import java.util.Comparator;
import java.util.PriorityQueue;

// 将数组和减半的最少操作次数
// 测试链接 : https://leetcode.cn/problems/minimum-operations-to-halve-array-sum/
public class Class27_Code03_MinimumOperationsToHalveArraySum {

    //示例 1：
    //
    //输入：nums = [5,19,8,1]
    //输出：3
    //解释：初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
    //以下是将数组和减少至少一半的一种方法：
    //选择数字 19 并减小为 9.5 。
    //选择数字 9.5 并减小为 4.75 。
    //选择数字 8 并减小为 4 。
    //最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
    //nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 >= 33/2 = 16.5 。
    //我们需要 3 个操作实现题目要求，所以返回 3 。
    //可以证明，无法通过少于 3 个操作使数组和减少至少一半。
    //示例 2：
    //
    //输入：nums = [3,8,20]
    //输出：3
    //解释：初始 nums 的和为 3 + 8 + 20 = 31 。
    //以下是将数组和减少至少一半的一种方法：
    //选择数字 20 并减小为 10 。
    //选择数字 10 并减小为 5 。
    //选择数字 3 并减小为 1.5 。
    //最终数组为 [1.5, 8, 5] ，和为 1.5 + 8 + 5 = 14.5 。
    //nums 的和减小了 31 - 14.5 = 16.5 ，减小的部分超过了初始数组和的一半， 16.5 >= 31/2 = 15.5 。
    //我们需要 3 个操作实现题目要求，所以返回 3 。
    //可以证明，无法通过少于 3 个操作使数组和减少至少一半。
    public int halveArray1(int[] nums) {
        PriorityQueue<Double> heap = new PriorityQueue<>(Comparator.reverseOrder());
        double sum = 0;
        for (int num : nums) {
            sum += num;
            heap.offer((double) num);
        }
        // 目标一半
        double half = sum / 2;
        int ans = 0;
        while (half > 0) {
            ans ++;
            Double cur = heap.poll();
            double divided = cur / 2;
            half -= divided;
            heap.offer(divided);
        }
        return ans;
    }

    // 自己实现堆
    private static final int MAXN = 100001;
    private static final long[] heap = new long[MAXN];

    private static int size;
    public int halveArray(int[] nums) {
        int n = nums.length;
        size = n;
        long sum = 0;
        // 自底向上建堆
        for (int i = n - 1; i >= 0; i--) {
            // 累加i位置的数字
            sum += nums[i];
            // 数据加到堆里
            heap[i] = (long) nums[i] << 20;
            // 只会向下影响
            heapify(i);
        }
        // 扩大2的20次方再除
        long half = (sum << 20) / 2;
        int ans = 0;
        while (half > 0) {
            ans ++;
            // 堆头元素除2再放回去
            heap[0] /= 2;
            half -= heap[0];
            heapify(0);
        }
        return ans;
    }



    // 向下转成堆
    private void heapify(int i) {
        int l = 2 * i + 1;
        while (l < size) {
            int best = l + 1 < size && heap[l + 1] > heap[l] ? l + 1 : l;
            best = heap[best] > heap[i] ? best : i;
            if (best == i) {
                break;
            }
            swap(i, best);
            i = best;
            l = 2 * i + 1;
        }
    }

    private void swap(int i, int j) {
        long tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private void heapInsert(int i) {
        while (heap[i] > heap[(i - 1) / 2]) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }
}
