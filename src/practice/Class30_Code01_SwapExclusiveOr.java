package practice;

// 用异或运算交换两数的值
public class Class30_Code01_SwapExclusiveOr {

    public static void main(String[] args) {
        int a = 10;
        int b = 3;
        a = a^b;
        b = a^b; // b = a^b^b = a
        a = a^b; // a = a^b^a = b
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        int[] arr = new int[]{3,5};
        System.out.println(arr[0] + " " + arr[1]);
        swap(arr, 0, 1);
        System.out.println(arr[0] + " " + arr[1]);
        arr = new int[]{5,5};
        System.out.println(arr[0] + " " + arr[1]);
        swap(arr, 0, 0);
        System.out.println(arr[0] + " " + arr[1]);
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i]^nums[j];
        nums[j] = nums[i]^nums[j];
        nums[i] = nums[i]^nums[j];
    }
}
