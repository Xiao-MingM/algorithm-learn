package practice;

// 单词搜索（无法改成动态规划）
// 给定一个 m x n 二维字符网格 board 和一个字符串单词 word
// 如果 word 存在于网格中，返回 true ；否则，返回 false 。
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成
// 其中"相邻"单元格是那些水平相邻或垂直相邻的单元格
// 同一个单元格内的字母不允许被重复使用
// 测试链接 : https://leetcode.cn/problems/word-search/
public class Class67_Code02_WordSearch {

    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (f1(board, i, j, chars, 0))
                    return true;
            }
        }
        return false;
    }

    boolean f1(char[][] board, int i, int j, char[] s, int k) {
        // 单词走完了
        if (k == s.length)
            return true;
        // 越界或者不等于对应的单词返回
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != s[k])
            return false;
        // 记录现场
        char tmp = board[i][j];
        board[i][j] = '0';
        boolean isSuccess = f1(board, i - 1, j, s, k + 1)
                            || f1(board, i + 1, j, s, k + 1)
                            || f1(board, i, j - 1, s, k + 1)
                            || f1(board, i , j + 1, s, k + 1);
        // 恢复现场
        board[i][j] = tmp;
        return isSuccess;
    }

}
