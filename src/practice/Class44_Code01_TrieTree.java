package practice;

public class Class44_Code01_TrieTree {

    // 路是数组实现的
    // 提交时把类名、构造方法改为Trie
    static class Trie {
        static class TrieNode {
            private int pass;
            private int end;
            private final TrieNode[] next;

            public TrieNode() {
                pass = 0;
                end = 0;
                next = new TrieNode[26];
            }
        }

        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            cur.pass++;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (cur.next[path] == null) {
                    cur.next[path] = new TrieNode();
                }
                cur = cur.next[path];
                cur.pass++;
            }
            cur.end++;
        }

        public void erase(String word) {
            if (countWordsEqualTo(word) > 0) {
                TrieNode cur = root;
                cur.pass--;
                for (int i = 0; i < word.length(); i++) {
                    int path = word.charAt(i) - 'a';
                    // 发现pass减到0了直接拦腰斩断
                    if (--cur.next[path].pass == 0) {
                        cur.next[path] = null;
                        return;
                    }
                    cur = cur.next[path];
                }
                cur.end--;
            }
        }

        public int countWordsEqualTo(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int path = word.charAt(i) - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];
            }
            return cur.end;
        }

        public int countWordsStartingWith(String pre) {
            TrieNode cur = root;
            for (int i = 0; i < pre.length(); i++) {
                int path = pre.charAt(i) - 'a';
                if (cur.next[path] == null) {
                    return 0;
                }
                cur = cur.next[path];
            }
            return cur.pass;
        }

    }
}
