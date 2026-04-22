package practice;

import java.util.Stack;

// 用递归函数逆序栈
public class Class38_Code05_ReverseStackWithRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int top = bottomOut(stack);
        reverse(stack);
        stack.push(top);
    }

    // [c,b,a)
    // ans = a, last = bottomOut = c [c, b) push a [b, a) return c
    // ans = b, last = bottomOut = c [c) push b [b)
    // ans = c, last = bottomOut [) return ans

    // 弹出栈底的元素并将整个栈元素往下沉
    public static int bottomOut(Stack<Integer> stack) {
        Integer ans = stack.pop();
        if (stack.isEmpty()) {
            return ans;
        } else {
            // 继续拿
            int last = bottomOut(stack);
            stack.push(ans);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        for (Integer i : stack) {
            System.out.print(i);
        }
        System.out.println();
        reverse(stack);
        for (Integer i : stack) {
            System.out.print(i);
        }
    }
}
