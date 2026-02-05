package practice;

// 扰乱字符串
// 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
// 步骤1 : 如果字符串的长度为 1 ，算法停止
// 步骤2 : 如果字符串的长度 > 1 ，执行下述步骤：
//        在一个随机下标处将字符串分割成两个非空的子字符串
//        已知字符串s，则可以将其分成两个子字符串x和y且满足s=x+y
//        可以决定是要 交换两个子字符串 还是要 保持这两个子字符串的顺序不变
//        即s可能是 s = x + y 或者 s = y + x
//        在x和y这两个子字符串上继续从步骤1开始递归执行此算法
// 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串
// 如果是，返回true ；否则，返回false
// 测试链接 : https://leetcode.cn/problems/scramble-string/
public class Class69_Code05_ScrambleString {
    public boolean isScramble1(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        return f1(s1, 0, m - 1, s2, 0, n - 1);
    }

    /**
     * 定义函数表示区间s1 l1-r1和 s2 l2-r2是否为扰乱字符串
     * @param s1
     * @param l1
     * @param r1
     * @param s2
     * @param l2
     * @param r2
     * @return
     */
    boolean f1(char[] s1, int l1, int r1, char[] s2, int l2, int r2) {
        // 长度为1的时候判断是否相等
        if (l1 == r1) {
            return s1[l1] == s2[l2];
        }
        // 不交叉的情况
        // s1[l1........r1] s1[l1....i][i + 1....r1]
        // s2[l2........r2] s2[l2....j][j + 1....r2]
        for (int i = l1, j = l2; i < r1; i++, j++) {
            if (f1(s1, l1, i, s2, l2, j) && f1(s1, i + 1, r1, s2, j + 1, r2)) {
                return true;
            }
        }
        // 交叉的情况
        // s1[l1........r1] s1[l1....i][i + 1....r1]
        // s2[l2........r2] s2[l2....j - 1][j....r2]
        for (int i = l1, j = r2; i < r1; i++, j--) {
            if (f1(s1, l1, i, s2, j, r2) && f1(s1, i + 1, r1, s2, l2, j - 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean isScramble2(String str1, String str2) {
        int n = str1.length();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        return f2(s1, s2, 0, 0, n);
    }

    /**
     * 定义函数表示区间s1 l1-r1和 s2 l2-r2是否为扰乱字符串
     * @param s1
     * @param l1
     * @param s2
     * @param l2
     * @param len
     * @return
     */
    boolean f2(char[] s1, char[] s2, int l1,  int l2, int len) {
        // 长度为1的时候判断是否相等
        if (len == 1) {
            return s1[l1] == s2[l2];
        }
        // l1, l2 -> k, l1 + k, l2 + k -> len -k
        for (int k = 1; k < len; k++) {
            if (f2(s1, s2, l1, l2, k) && f2(s1, s2, l1 + k, l2 + k, len - k)) {
                return true;
            }
        }
        // 交叉，用for循环来解决边界问题
        for (int i = l1 + 1, j = l2 + len - 1, k = 1; k < len; i++, j--, k++) {
            if (f2(s1, s2, l1, j, k) && f2(s1, s2, i, l2, len - k)) {
                return true;
            }
        }

        return false;
    }
}
