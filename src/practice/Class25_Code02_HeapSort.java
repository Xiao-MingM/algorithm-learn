package practice;

// 堆结构和堆排序，填函数练习风格
// 测试链接 : https://leetcode.cn/problems/sort-an-array/
public class Class25_Code02_HeapSort {

    public int[] sortArray(int[] nums) {
        if (nums.length > 0) {
            heapSort2(nums);
        }
        return nums;
    }

    private void heapSort2(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            heapify(nums, i, n);
        }
        int size = n;
        while (size > 1) {
            swap(nums, 0, --size);
            heapify(nums, 0, size);
        }
    }

    // 自顶向下建堆
    private void heapSort1(int[] nums) {
        int n = nums.length;
        int size = 0;
        for (int i = 0; i < n; i++) {
            heapInsert(nums, i);
            size++;
        }
        while (size > 0) {
            swap(nums, 0, --size);
            heapify(nums, 0, size);
        }
    }

    //    0
    //   1   2
    //  3 4 5 6
    private void heapInsert(int[] nums, int i) {
        // 当比爹大，向上调整
        while (nums[i] > nums[(i - 1) / 2]) {
            swap(nums, i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void heapify(int[] nums, int i, int size) {
        if (i >= size) {
            return;
        }
        // 左孩子
        int l = 2 * i + 1;
        // 左孩子存在
        while (l < size) {
            // 在左右孩子中找个最大的交换
            int best = l + 1 < size && nums[l + 1] > nums[l] ? l + 1 : l;
            // 在i和best之间找个最大的
            best = nums[i] < nums[best] ? best : i;
            // 无需交换当场结束
            if (best == i) {
                break;
            }
            swap(nums, i, best);
            i = best;
            l = 2 * i + 1;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
