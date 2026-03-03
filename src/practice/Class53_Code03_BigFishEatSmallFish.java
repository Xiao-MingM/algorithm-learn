package practice;

// 大鱼吃小鱼问题
// 给定一个数组arr，每个值代表鱼的体重
// 每一轮每条鱼都会吃掉右边离自己最近比自己体重小的鱼，每条鱼向右找只吃一条
// 但是吃鱼这件事是同时发生的，也就是同一轮在A吃掉B的同时，A也可能被别的鱼吃掉
// 如果有多条鱼在当前轮找到的是同一条小鱼，那么在这一轮，这条小鱼同时被这些大鱼吃
// 请问多少轮后，鱼的数量就固定了
// 比如 : 8 3 1 5 6 7 2 4
// 第一轮 : 8吃3；3吃1；5、6、7吃2；4没有被吃。数组剩下 8 5 6 7 4
// 第二轮 : 8吃5；5、6、7吃4。数组剩下 8 6 7
// 第三轮 : 8吃6。数组剩下 8 7
// 第四轮 : 8吃7。数组剩下 8。
// 过程结束，返回4
// 测试链接 : https://www.nowcoder.com/practice/77199defc4b74b24b8ebf6244e1793de
// 测试链接 : https://leetcode.cn/problems/steps-to-make-array-non-decreasing/
public class Class53_Code03_BigFishEatSmallFish {

    // 小压大是相对稳定状态，违背表示被吃，从左往右吃，所以从右往左遍历被吃
    // [8,3,1,5,6,7,2,4] 从右往左遍历，小压大（遇到大表示被吃了要弹出结算）
    // 4-0来了[4-0]
    // 2-0来了[4-0,2-0]
    // 7-0来了，破坏了小压大，7吃掉2并接替它的工作 max(0+1,0) = 1 [4-0] 弹出2-0
    // 7-1来了，破坏了小压大，7吃掉4并接替它的工作 max(0+1+1,0) = 2 弹出4-0 [7-2]
    // 6-0来了，[7-2, 6-0]
    // 5-0来了，[7-2, 6-0, 5-0]
    // 1-0来了，[7-2, 6-0, 5-0, 1-0]
    // 3-0来了，1-0弹出被吃掉，max(0+1,0) = 1，[7-2, 6-0, 5-0, 3-1]
    // 8-0来了，3-1弹出被吃掉，max(0+1,1) = 1，
    // 8-1来了，5-0弹出被吃掉，max(1+1,0) = 2，
    // 8-2来了，6-0弹出被吃掉，max(2+1,0) = 3，
    // 8-3来了，7-2弹出被吃掉，max(3+1,2) = 4
    public int totalSteps(int[] nums) {
        int n = nums.length;
        // stack[i][0]表示数，stack[i][1]表示被吃掉接替需要完成的次数
        int[][] stack = new int[n][2];
        int r = 0;
        int ans = 0;
        // 从右向左遍历
        for (int i = n - 1; i >= 0; i--) {
            // 初始化当前数组元素
            int[] num = new int[]{nums[i], 0};
            // 破坏小压大触发被吃
            while (r > 0 && stack[r - 1][0] < nums[i]) {
                // 弹出栈顶元素
                int[] cur = stack[--r];
                num[1] = Math.max(cur[1], num[1] + 1);
            }
            ans = Math.max(ans, num[1]);
            // 当前元素入栈
            stack[r++] = num;
        }
        return ans;
    }


    /**
     * 单调栈：从右往左扫，栈中存「右侧的鱼」[体重, 被吃前需要的轮数]。
     * 栈内保持小压大（栈底到栈顶体重递增）；当前鱼 nums[i] 为左侧，栈顶为右侧。
     * 当 栈顶体重 < 当前体重 时弹栈：表示当前鱼吃掉栈顶鱼，当前轮数 = max(当前轮数+1, 栈顶轮数)。
     * 答案 = 所有轮数的最大值。
     */
    public int totalSteps2(int[] nums) {
        int n = nums.length;
        int[][] stack = new int[n][2];
        int r = 0;
        int ans = 0;
        for (int i = n - 1, curTurns; i >= 0; i--) {
            curTurns = 0;
            while (r > 0 && stack[r - 1][0] < nums[i]) {
                curTurns = Math.max(curTurns + 1, stack[--r][1]);
            }
            stack[r][0] = nums[i];
            stack[r++][1] = curTurns;
            ans = Math.max(ans, curTurns);
        }
        return ans;
    }
}
