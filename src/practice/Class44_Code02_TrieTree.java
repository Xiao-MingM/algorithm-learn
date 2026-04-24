package practice;


import java.io.*;

// 用固定数组实现前缀树，空间使用是静态的。推荐！
// 测试链接 : https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Class44_Code02_TrieTree {
    // 测出来的全局变量
    private static final int MAXN = 10000001;

    private static final int[][] trieNode = new int[MAXN][26];

    private static final int[] pass = new int[MAXN];

    private static final int[] end = new int[MAXN];

    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        StreamTokenizer in = new StreamTokenizer(br);
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            // 构建一下
            build();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;

                in.nextToken();
                String word = in.sval;

                switch (op) {
                    case 1:
                        insert(word);
                        break;
                    case 2:
                        delete(word);
                        break;
                    case 3:
                        int count = search(word);
                        out.println(count > 0 ? "YES" : "NO");
                        break;
                    case 4:
                        int preCount = prefixNumber(word);
                        out.println(preCount);
                        break;
                    default:
                }
            }
            // 一个用例走完清理一下
            clear();
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void build() {
        // 0不用,cur = 1表示根目录
        cnt = 1;
    }

    public static void clear() {
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < 26; j++) {
                trieNode[i][j] = 0;
            }
            pass[i] = 0;
            end[i] = 0;
        }
    }

    public static void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0; i < word.length(); i++) {
            int path = word.charAt(i) - 'a';
            if (trieNode[cur][path] == 0) {
                // 分配新节点
                trieNode[cur][path] = ++cnt;
            }
            cur = trieNode[cur][path];
            pass[cur]++;
        }
        end[cur]++;
    }

    public static int search(String word) {
        int cur = 1;
        for (int i = 0; i < word.length(); i++) {
            int path = word.charAt(i) - 'a';
            if (trieNode[cur][path] == 0) {
                return 0;
            }
            cur = trieNode[cur][path];
        }
        return end[cur];
    }

    public static int prefixNumber(String pre) {
        int cur = 1;
        for (int i = 0; i < pre.length(); i++) {
            int path = pre.charAt(i) - 'a';
            if (trieNode[cur][path] == 0) {
                return 0;
            }
            cur = trieNode[cur][path];
        }
        return pass[cur];
    }

    public static void delete(String word) {
        if (search(word) > 0) {
            int cur = 1;
            pass[cur]--;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                // 发现减完变成0了直接拦腰斩断
                if (--pass[trieNode[cur][path]] == 0) {
                    trieNode[cur][path] = 0;
                    return;
                }
                cur = trieNode[cur][path];
            }
            end[cur]--;
        }

    }
}
