package practice;

// 编辑距离
// 给你两个单词 word1 和 word2
// 请返回将 word1 转换成 word2 所使用的最少代价
// 你可以对一个单词进行如下三种操作：
// 插入一个字符，代价a
// 删除一个字符，代价b
// 替换一个字符，代价c
// 测试链接 : https://leetcode.cn/problems/edit-distance/
public class Class68_Code02_EditDistance {

    public int minDistance(String word1, String word2) {

    }

    /**
     * 定义f1函数，s1长度为i转成s2长度为j的代价
     * @param s1
     * @param s2
     * @param i s1的长度
     * @param j s2的长度
     * @param a 新增
     * @param b 删除
     * @param c 替换
     * @return
     */
    int f1(char[] s1, char[] s2, int i, int j, int a, int b, int c) {
        // s1长度为0的时候转成s2的代价为新增的代价，即
        if (i == 0) {
            return j * a;
        }
        // s2长度为0的时候转成s2的代价为删除的代价，即
        if (j == 0) {
            return i * b;
        }
        int p1;
        // 最后一位参与
        // 分相等和不相等
        if (s1[i] == s2[j]) {
            // 相等的时候直接代价为0
            p1 = f1(s1, s2, i - 1, j - 1, a, b, c);
        } else {
            // 最后一位替换的代价
            p1 = f1(s1, s2, i - 1, j - 1, a, b, c) + c;
        }

        // s1的最后一位只搞定s2的前j-1位然后插入一个
        int p2 = f1(s1, s2, i, j - 1, a, b, c) + a;

        // s1的最后一位i - 1不参与，只用s1的前i-1位去搞，相当于删掉s1的最后一位
        int p3 = f1(s1, s2, i - 1, j, a, b, c) + b;

        return Math.min(p1, Math.min(p2, p3));
    }
}
