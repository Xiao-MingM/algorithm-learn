package practice;

// 随机快速排序，填函数练习风格
// 测试链接 : https://leetcode.cn/problems/sort-an-array/
public class Class_Code02_QuickSort {

    public int[] sortArray1(int[] nums) {
        int n = nums.length;
        if (n > 1) {
            quickSort1(nums, 0, n - 1);
        }
        return nums;
    }

    void quickSort1(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = nums[l + (int) (Math.random() * (r - l + 1))];
        int i = partition1(nums, l, r, x);
        quickSort1(nums, l, i - 1);
        quickSort1(nums, i + 1, r);
    }

    // 传统划分
    int partition1(int[] nums, int l, int r, int x) {
        int xi = l, a = l;
        for (int i = l; i <= r; i++) {
            if (nums[i] <= x) {
                swap(nums, a, i);
                // 记录下来x的位置
                if (nums[a] == x) {
                    xi = a;
                }
                a++;
            }
        }
        swap(nums, xi, a - 1);
        return a - 1;
    }

    public int[] sortArray(int[] nums) {
        int n = nums.length;
        if (n > 1) {
            quickSort2(nums, 0, n - 1);
        }
        return nums;
    }

    void quickSort2(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int x = nums[l + (int) (Math.random() * (r - l + 1))];
        partition2(nums, l, r, x);
        int l_ = first;
        int r_ = last;
        quickSort2(nums, l, l_ - 1);
        quickSort2(nums, r_ + 1, r);
    }

    int first, last;
    // 荷兰旗划分
    void partition2(int[] nums, int l, int r, int x) {
        first = l;
        last = r;
        int i = l;
        while (i <= last) {
            if (nums[i] == x) {
                i++;
            } else if (nums[i] < x) {
                swap(nums, first++, i++);
            } else {
                swap(nums, last--, i);
            }
        }
    }

    void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
