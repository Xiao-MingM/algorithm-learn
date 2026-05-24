package practice;

import java.io.*;

// 机器人跳跃问题
// 机器人正在玩一个古老的基于DOS的游戏
// 游戏中有N+1座建筑，从0到N编号，从左到右排列
// 编号为0的建筑高度为0个单位，编号为i的建筑的高度为H(i)个单位
// 起初机器人在编号为0的建筑处
// 每一步，它跳到下一个（右边）建筑。假设机器人在第k个建筑，且它现在的能量值是E
// 下一步它将跳到第个k+1建筑
// 它将会得到或者失去正比于与H(k+1)与E之差的能量
// 如果 H(k+1) > E 那么机器人就失去H(k+1)-E的能量值，否则它将得到E-H(k+1)的能量值
// 游戏目标是到达第个N建筑，在这个过程中，能量值不能为负数个单位
// 现在的问题是机器人以多少能量值开始游戏，才可以保证成功完成游戏
// 测试链接 : https://www.nowcoder.com/practice/7037a3d57bbd4336856b8e16a9cafd71
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
// 输入：
// 5
// 3 4 3 2 4
//输出：
// 4
// 数据约束：
//1 <= N <= 10^5
//1 <= H(i) <= 10^5
public class Class51_Code03_RobotPassThroughBuilding {

    static final int MAXN = 100001;
    static final int[] arr = new int[MAXN];
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StreamTokenizer st = new StreamTokenizer(in);
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) st.nval;
            int max = 0;
            for (int i = 1; i <= n; i++) {
                st.nextToken();
                arr[i] = (int) st.nval;
                max = Math.max(arr[i], max);
            }
            int compute = compute(max);
            out.println(compute);
        }
        out.flush();
        out.close();
        in.close();
    }

    // 答案范围：0-sum 应对极端情况数组倒序，全程在扣
    // f函数，指定初始值的范围，返回是否可以顺利走完，当true的时候表示可以走完，还可以调小点，false则不行，需要调大点
    // 单调性：limit越大越能保障走完，右缩边界就可以继续找最优解，limit越小则越可能走不完，左缩左边界找能通关的
    // [0,5,4,3,2,1] 初始值5:5,4,3,2,1 初始值最大值就可以搞定
    static int compute(int max) {
        int ans = 0;
        int l = 0, r = max;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (f(mid, max)) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    // 给个范围判断是否可以顺利走完
    static boolean f(int start, int max) {
        boolean pass = true;
        int energy = start;
        for (int i = 1; i <= n; i++) {
            if (energy <= arr[i]) {
                energy -= arr[i] - energy;
            } else {
                energy += energy - arr[i];
            }
            // 当能量某一时刻超过最大值直接返回true，否则遍历走完会发生int溢出
            if (energy >= max) {
                return true;
            }
            if (energy < 0) {
                return false;
            }
        }
        return pass;
    }

}
