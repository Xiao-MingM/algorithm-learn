package practice;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// 合并K个有序链表
// 测试链接：https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6
public class Class27_Code01_MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param lists ListNode类ArrayList
     * @return ListNode类
     */
    public ListNode mergeKLists (ArrayList<ListNode> lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        // 弹出一个当头
        ListNode head = heap.poll();
        if (head.next != null) {
            heap.offer(head.next);
        }
        ListNode p = head;
        // 堆不为空依次弹出拼接
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            p.next = cur;
            p = cur;
            if (p.next != null) {
                heap.offer(p.next);
            }
        }
        return head;
    }
}
