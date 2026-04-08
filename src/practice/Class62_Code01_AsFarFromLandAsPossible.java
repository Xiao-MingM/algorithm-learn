package practice;

// 地图分析
// 你现在手里有一份大小为 n x n 的 网格 grid
// 上面的每个 单元格 都用 0 和 1 标记好了其中 0 代表海洋，1 代表陆地。
// 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的
// 并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。
// 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：
// (x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - x1| + |y0 - y1| 。
// 测试链接 : https://leetcode.cn/problems/as-far-from-land-as-possible/
public class Class62_Code01_AsFarFromLandAsPossible {

    /*  0 0 1 0 1       0 2 1 2 1      3 2 1 2 1
     *  1 0 0 0 0 --->  1 2 2 0 2 ---> 1 2 2 3 2   --> ans = 3 - 1 = 2
     *  0 0 1 0 0       2 2 1 2 0      2 2 1 2 3
     *  0 0 0 0 1       0 0 2 2 1      3 3 2 2 1
     */

    int[] dir = new int[]{-1, 0, 1, 0, -1};
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        // 初始化队列
        Queue queue = new Queue(n * n + 1);
        // 将1都丢进去
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        // 全海洋或者全陆地的情况
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }
        // 定义初始层级
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 依次出队列
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = poll[0] + dir[j];
                    int y = poll[1] + dir[j + 1];
                    // 填要扩张的格子
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        grid[x][y] = level + 1;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
            // 只有扩张成功才计数
            if (!queue.isEmpty()) {
                level++;
            }
        }
        return level - 1;
    }

    static class Queue{
        private final int[][] arr;
        private int front;
        private int rear;

        public Queue(int size) {
            this.arr = new int[size][2];
            front = rear = 0;
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            return (rear + 1) % arr.length == front;
        }

        public void offer(int[] data) {
            if (isFull()) {
                throw new RuntimeException("队列已满");
            }
            arr[rear] = data;
            rear = (rear + 1) % arr.length;
        }

        public int[] poll() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            int[] data = arr[front];
            front = (front + 1) % arr.length;
            return data;
        }

        public int size() {
            return (rear - front + arr.length) % arr.length;
        }

        public int[] peek() {
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            return arr[front];
        }
    }

}
