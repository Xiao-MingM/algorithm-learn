package practice;

import java.util.Arrays;

// 牛牛和他的朋友们约定了一套接头密匙系统，用于确认彼此身份
// 密匙由一组数字序列表示，两个密匙被认为是一致的，如果满足以下条件：
// 密匙 b 的长度不超过密匙 a 的长度。
// 对于任意 0 <= i < length(b)，有b[i+1] - b[i] == a[i+1] - a[i]
// 现在给定了m个密匙 b 的数组，以及n个密匙 a 的数组
// 请你返回一个长度为 m 的结果数组 ans，表示每个密匙b都有多少一致的密匙
// 数组 a 和数组 b 中的元素个数均不超过 10^5
// 1 <= m, n <= 1000
// 测试链接 : https://www.nowcoder.com/practice/c552d3b4dfda49ccb883a6371d9a6932
public class Class45_Code01_CountConsistentKeys {

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param b int整型二维数组
     * @param a int整型二维数组
     * @return int整型一维数组
     */
    public int[] countConsistentKeys (int[][] b, int[][] a) {
        build();
        StringBuilder sb = new StringBuilder();
        for (int[] nums : a) {
            for (int i = 1; i < nums.length; i++) {
                sb.append(nums[i] - nums[i - 1]).append("#");
            }
            // 插入一条到前缀树
            insert(sb.toString());
            // 清空
            sb.setLength(0);
        }
        int[] ans = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            int[] nums = b[i];
            for (int j = 1; j < nums.length; j++) {
                sb.append(nums[j] - nums[j - 1]).append("#");
            }
            ans[i] = search(sb.toString());
            sb.setLength(0);
        }
        return ans;
    }

    private static final int MAXN = 100005;
    // prefixTree[i][j]， i 表示 第几层 ，j 表示数字对应的第几条分叉，prefixTree[i][j]表示下一个节点所在的层数
    private final int[][] prefixTree = new int[MAXN][12];
    private final int[] pass = new int[MAXN];

    private int cnt;

    private void build() {
        // 所有节点的根
        cnt = 1;
    }

    private int path(char c) {
        if (c == '-') {
            return 10;
        } else if (c == '#') {
            return 11;
        } else {
            return c - '0';
        }
    }

    private void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = path(word.charAt(i));
            if (prefixTree[cur][path] == 0) {
                prefixTree[cur][path] = ++cnt;
            }
            cur = prefixTree[cur][path];
            pass[cur]++;
        }
    }

    private int search(String word) {
        int cur = 1;
        for (int i = 0, path; i < word.length(); i++) {
            path = path(word.charAt(i));
            if (prefixTree[cur][path] == 0) {
                return 0;
            }
            cur = prefixTree[cur][path];
        }
        return pass[cur];
    }

    private void clear() {
        for (int i = 1; i <= cnt; i++) {
            pass[i] = 0;
            Arrays.fill(prefixTree[i], 0);
        }
        cnt = 1;
    }



}
