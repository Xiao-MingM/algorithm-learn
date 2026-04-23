package practice;

// 打印n层汉诺塔问题的最优移动轨迹
public class Class38_Code07_TowerOfHanoi {

    public static void main(String[] args) {
        hanoi(3);
    }

    // 1,2,3
    //   2,3               1
    //     3      2        1
    //     3     1,2
    //           1,2       3
    // 1           2       3
    // 1                 2,3
    //                 1,2,3
    public static void hanoi(int n) {
        if (n > 0) {
            f(n, "左", "右", "中");
        }
    }

    /**
     * 定义移动函数
     * @param i 移动1-i层的盘子
     * @param from 从from开始
     * @param to 移动到to
     * @param other 中转other
     */
    private static void f(int i, String from, String to, String other) {
        // 可以直接从from移动到to
        if (i == 1) {
            System.out.println( "1 从 " + from + " 移动到 " + to);
        } else {
            // 把i-1层先移动到other上 （腾出最大片）
            f(i - 1, from, other, to);
            // 把第i层移动到to上 （移动最大片）
            System.out.println( i + " 从 " + from + " 移动到 " + to);
            // 再从other上把数据移动到to上（把最大片小的压在最大片上）
            f(i - 1, other, to, from);
        }
    }
}
