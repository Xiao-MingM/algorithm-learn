package practice;

import java.util.Stack;

// 用递归函数逆序栈
public class Class38_Code06_SortStackWithRecursive {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(1);
        stack.push(6);
        stack.push(8);
        stack.push(6);
        stack.push(5);
        stack.push(2);
        for (Integer i : stack) {
            System.out.print(i + " ");
        }
        System.out.println();
        sort(stack);
        for (Integer i : stack) {
            System.out.print(i + " ");
        }
    }

    public static void sort(Stack<Integer> stack) {
        // 获取栈的深度
        int deep = deep(stack);
        // 栈还有深度时
        while (deep > 0) {
            int max = max(stack, deep);
            int times = times(stack, deep, max);
            down(stack, deep, max, times);
            // 每次处理一批最大值
            deep -= times;
        }
    }

    // 获取栈的深度
    public static int deep(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        // 先弹出栈顶记下来
        Integer cur = stack.pop();
        // 统计栈的深度
        int deep = deep(stack) + 1;
        // 把弹出来数据再压回去
        stack.push(cur);
        // 返回深度
        return deep;
    }

    // 返回栈往下扎deep的深度获取的最大值
    public static int max(Stack<Integer> stack, int deep) {
        // 深度为空，返回一个无穷小的数
        if (deep == 0) {
            return Integer.MIN_VALUE;
        }
        // 先弹出来记住
        Integer cur = stack.pop();
        // 看看下面的最小值是什么
        int max = max(stack, deep - 1);
        // 拿下面返回的数和栈顶元素pk一个最大值
        max = Math.max(max, cur);
        // 将弹出来数压回去
        stack.push(cur);
        return max;
    }

    // 告诉栈，深度和最大值，返回最大值出现的次数
    public static int times(Stack<Integer> stack, int deep, int max) {
        // 栈为空，出现次数是0
        if (deep == 0) {
            return 0;
        }
        // 先弹出来栈顶
        Integer cur = stack.pop();
        // 去看看下面的出现次数
        int times = times(stack, deep - 1, max);
        // 如果当前元素为最大值，加到结果里
        times += cur == max ? 1 : 0;
        // 把当前弹出的元素再压回去
        stack.push(cur);
        return times;
    }

    // 告诉栈，深度，最大值和出现的次数，将最大值沉到最下面
    public static void down(Stack<Integer> stack, int deep, int max, int times) {
        // 走到栈底了
        if (deep == 0) {
            // 将最大值直接压到栈底
            for (int i = 0; i < times; i++) {
                stack.push(max);
            }
        } else {
            // 将栈顶弹出来记一下
            Integer cur = stack.pop();
            // 再往下沉
            down(stack, deep - 1, max, times);
            // 最大值已经沉下去了，把不等于最大值的依次加进去
            if (cur != max) {
                stack.push(cur);
            }
        }
    }
}
