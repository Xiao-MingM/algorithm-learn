package practice;

// 不用任何判断语句和比较操作，返回两个数的最大值
// 测试链接 : https://www.nowcoder.com/practice/d2707eaf98124f1e8f1d9c18ad487f76
public class Class30_Code02_GetMaxWithoutJudge {
    // 0转1 1转0
    public static int flip(int n) {
        return n ^ 1;
    }

    public static int sign(int n) {
        return flip(n >>> 31);
    }

    public static int getMax(int a, int b) {
        int sa = sign(a);
        int sb = sign(b);
        // 异号
        int diff = sa ^ sb;
        // 同号
        int same = flip(diff);
        // 异号用sa，同号不会溢出用 a - b
        int returnA = diff * sa + same * sign(a - b);
        int returnB = flip(returnA);
        return returnA * a + returnB * b;
    }

    public static void main(String[] args) {
        int max = getMax(1, 8);
        System.out.println(max);
    }

}
