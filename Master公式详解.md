# Master公式（主定理）详解 - 通俗易懂版

## 一、什么是Master公式？

**Master公式**是用来快速计算**分治递归算法**时间复杂度的公式。

### 1.1 适用条件

Master公式只适用于**子问题规模相等**的递归，即：
- 递归函数把问题分成**a个**子问题
- 每个子问题的规模是原问题的 **1/b**
- 除了递归调用外，还需要 **O(n^d)** 的时间来处理

### 1.2 标准形式

如果一个递归函数的时间复杂度可以写成：

```
T(n) = a × T(n/b) + O(n^d)
```

其中：
- **a**：子问题的个数（递归调用次数）
- **b**：每个子问题的规模是原问题的 1/b
- **d**：除了递归外，处理当前问题的时间复杂度是 O(n^d)

那么时间复杂度为：

```
如果 log_b(a) < d  →  T(n) = O(n^d)
如果 log_b(a) = d  →  T(n) = O(n^d × log n)
如果 log_b(a) > d  →  T(n) = O(n^(log_b(a)))
```

---

## 二、通俗理解

### 2.1 形象比喻

想象你在**分蛋糕**：
- **a**：你把蛋糕切成几块（子问题个数）
- **b**：每块是原来的几分之一（子问题规模）
- **d**：切蛋糕和吃蛋糕需要的时间（额外处理时间）

Master公式就是计算：**总共需要多长时间吃完这个蛋糕**

### 2.2 三种情况的理解

#### 情况1：log_b(a) < d
**含义**：切蛋糕的时间 > 吃蛋糕的时间
**结果**：总时间主要花在切蛋糕上 → **O(n^d)**

#### 情况2：log_b(a) = d  
**含义**：切蛋糕的时间 = 吃蛋糕的时间
**结果**：两者平衡，需要乘以 log n → **O(n^d × log n)**

#### 情况3：log_b(a) > d
**含义**：切蛋糕的时间 < 吃蛋糕的时间
**结果**：总时间主要花在吃蛋糕上 → **O(n^(log_b(a)))**

---

## 三、经典例子详解

### 例子1：归并排序（Merge Sort）

```java
public void mergeSort(int[] arr, int left, int right) {
    if (left >= right) return;
    
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);        // 递归调用1：处理左半部分
    mergeSort(arr, mid + 1, right);   // 递归调用2：处理右半部分
    merge(arr, left, mid, right);     // 合并：O(n)
}
```

**分析**：
- **a = 2**：分成2个子问题（左半部分、右半部分）
- **b = 2**：每个子问题规模是原问题的 1/2
- **d = 1**：合并操作是 O(n)，即 O(n^1)

**套公式**：
```
T(n) = 2 × T(n/2) + O(n^1)
```

**计算**：
- log_2(2) = 1
- d = 1
- log_2(2) = 1 = d → **情况2**

**结果**：**T(n) = O(n × log n)** ✅

---

### 例子2：二分查找（Binary Search）

```java
public int binarySearch(int[] arr, int target, int left, int right) {
    if (left > right) return -1;
    
    int mid = (left + right) / 2;
    if (arr[mid] == target) return mid;
    
    if (arr[mid] > target) {
        return binarySearch(arr, target, left, mid - 1);  // 递归调用1次
    } else {
        return binarySearch(arr, target, mid + 1, right); // 递归调用1次
    }
    // 每次只调用一次，比较操作是 O(1)
}
```

**分析**：
- **a = 1**：每次只处理1个子问题（要么左边，要么右边）
- **b = 2**：子问题规模是原问题的 1/2
- **d = 0**：比较操作是 O(1)，即 O(n^0)

**套公式**：
```
T(n) = 1 × T(n/2) + O(1)
```

**计算**：
- log_2(1) = 0
- d = 0
- log_2(1) = 0 = d → **情况2**

**结果**：**T(n) = O(log n)** ✅

---

### 例子3：快速排序（Quick Sort）- 平均情况

```java
public void quickSort(int[] arr, int left, int right) {
    if (left >= right) return;
    
    int pivot = partition(arr, left, right);  // O(n)
    quickSort(arr, left, pivot - 1);          // 递归调用1：左半部分
    quickSort(arr, pivot + 1, right);         // 递归调用2：右半部分
}
```

**分析**（平均情况，pivot在中间）：
- **a = 2**：分成2个子问题
- **b = 2**：每个子问题规模是原问题的 1/2（平均情况）
- **d = 1**：partition操作是 O(n)

**套公式**：
```
T(n) = 2 × T(n/2) + O(n)
```

**计算**：
- log_2(2) = 1
- d = 1
- log_2(2) = 1 = d → **情况2**

**结果**：**T(n) = O(n × log n)** ✅

---

### 例子4：最大子数组问题（分治法）

```java
public int maxSubArray(int[] arr, int left, int right) {
    if (left == right) return arr[left];
    
    int mid = (left + right) / 2;
    int leftMax = maxSubArray(arr, left, mid);           // 递归调用1
    int rightMax = maxSubArray(arr, mid + 1, right);     // 递归调用2
    int crossMax = findCrossMax(arr, left, mid, right);  // O(n)：找跨越中点的最大值
    
    return Math.max(Math.max(leftMax, rightMax), crossMax);
}
```

**分析**：
- **a = 2**：分成2个子问题
- **b = 2**：每个子问题规模是原问题的 1/2
- **d = 1**：findCrossMax是 O(n)

**套公式**：
```
T(n) = 2 × T(n/2) + O(n)
```

**计算**：
- log_2(2) = 1
- d = 1
- log_2(2) = 1 = d → **情况2**

**结果**：**T(n) = O(n × log n)** ✅

---

### 例子5：情况1示例 - 子问题多但处理简单

```java
public void example1(int[] arr, int left, int right) {
    if (left >= right) return;
    
    int mid = (left + right) / 2;
    example1(arr, left, mid);        // 递归调用1
    example1(arr, mid + 1, right);   // 递归调用2
    // 假设这里只是简单的打印，O(1)
    System.out.println("处理完成");
}
```

**分析**：
- **a = 2**：分成2个子问题
- **b = 2**：每个子问题规模是原问题的 1/2
- **d = 0**：打印操作是 O(1)，即 O(n^0)

**套公式**：
```
T(n) = 2 × T(n/2) + O(1)
```

**计算**：
- log_2(2) = 1
- d = 0
- log_2(2) = 1 > 0 → **情况3**

**结果**：**T(n) = O(n^(log_2(2))) = O(n)** ✅

---

### 例子6：情况3示例 - 子问题少但处理复杂

```java
public void example2(int[] arr, int left, int right) {
    if (left >= right) return;
    
    int mid = (left + right) / 2;
    example2(arr, left, mid);        // 递归调用1次
    // 假设这里需要 O(n^2) 的时间处理
    for (int i = left; i <= right; i++) {
        for (int j = left; j <= right; j++) {
            // 一些操作
        }
    }
}
```

**分析**：
- **a = 1**：只有1个子问题
- **b = 2**：子问题规模是原问题的 1/2
- **d = 2**：双重循环是 O(n^2)

**套公式**：
```
T(n) = 1 × T(n/2) + O(n^2)
```

**计算**：
- log_2(1) = 0
- d = 2
- log_2(1) = 0 < 2 → **情况1**

**结果**：**T(n) = O(n^2)** ✅

---

## 四、快速记忆技巧

### 4.1 三步法

1. **识别参数**：找出 a、b、d
2. **计算 log_b(a)**：这是关键值
3. **比较大小**：
   - 如果 log_b(a) < d → O(n^d)
   - 如果 log_b(a) = d → O(n^d × log n)
   - 如果 log_b(a) > d → O(n^(log_b(a)))

### 4.2 常见log值

```
log_2(1) = 0
log_2(2) = 1
log_2(4) = 2
log_3(3) = 1
log_3(9) = 2
```

### 4.3 记忆口诀

> **"小等大"**
> - **小**：log_b(a) < d → O(n^d)
> - **等**：log_b(a) = d → O(n^d × log n)
> - **大**：log_b(a) > d → O(n^(log_b(a)))

---

## 五、实际应用：分析你的代码

### 5.1 分析 isScramble2 方法

```java
boolean f2(char[] s1, char[] s2, int l1, int l2, int len) {
    if (len == 1) {
        return s1[l1] == s2[l2];
    }
    // 第一个循环：尝试所有分割点
    for (int k = 1; k < len; k++) {
        if (f2(s1, s2, l1, l2, k) && f2(s1, s2, l1 + k, l2 + k, len - k)) {
            return true;
        }
    }
    // 第二个循环：交叉情况
    for (int i = l1 + 1, j = l2 + len - 1, k = 1; k < len; i++, j--, k++) {
        if (f2(s1, s2, l1, j, k) && f2(s1, s2, i, l2, len - k)) {
            return true;
        }
    }
    return false;
}
```

**分析**：
- 这个函数**不适用**Master公式！
- 原因：子问题规模**不固定**（k 从 1 到 len-1，不是固定的 n/b）
- 这是**多分支递归**，需要其他方法分析

**实际时间复杂度**：O(4^n) 或更高（指数级）

---

## 六、Master公式的局限性

### 6.1 不适用的情况

1. **子问题规模不相等**
   ```java
   // 例如：快速排序的最坏情况（pivot总是最小/最大）
   // 子问题规模是 0 和 n-1，不是 n/2
   ```

2. **子问题个数不固定**
   ```java
   // 例如：你的 isScramble 函数
   // 递归调用次数取决于循环次数，不是固定的 a
   ```

3. **递归深度不是 log n**
   ```java
   // 例如：斐波那契数列的递归版本
   // T(n) = T(n-1) + T(n-2) + O(1)
   // 不是 T(n) = a × T(n/b) 的形式
   ```

### 6.2 替代方法

对于不适用Master公式的情况，可以使用：
- **递归树法**：画出递归树，计算总节点数
- **代入法**：猜测复杂度，用数学归纳法证明
- **主定理扩展**：Akra-Bazzi定理（更通用）

---

## 七、练习题

### 练习1
```java
T(n) = 4 × T(n/2) + O(n)
```
**答案**：
- log_2(4) = 2
- d = 1
- 2 > 1 → **O(n^2)**

### 练习2
```java
T(n) = 3 × T(n/3) + O(n)
```
**答案**：
- log_3(3) = 1
- d = 1
- 1 = 1 → **O(n × log n)**

### 练习3
```java
T(n) = 2 × T(n/4) + O(n^2)
```
**答案**：
- log_4(2) = 0.5
- d = 2
- 0.5 < 2 → **O(n^2)**

### 练习4
```java
T(n) = 8 × T(n/2) + O(n^2)
```
**答案**：
- log_2(8) = 3
- d = 2
- 3 > 2 → **O(n^3)**

---

## 八、总结

### 8.1 核心要点

1. **Master公式适用于**：T(n) = a × T(n/b) + O(n^d) 形式的递归
2. **关键计算**：log_b(a) 与 d 的比较
3. **三种情况**：小、等、大 → 对应三种复杂度

### 8.2 快速判断表

| log_b(a) vs d | 时间复杂度 |
|--------------|-----------|
| < d | O(n^d) |
| = d | O(n^d × log n) |
| > d | O(n^(log_b(a))) |

### 8.3 常见模式

- **归并排序**：T(n) = 2T(n/2) + O(n) → O(n log n)
- **二分查找**：T(n) = T(n/2) + O(1) → O(log n)
- **快速排序（平均）**：T(n) = 2T(n/2) + O(n) → O(n log n)

---

**记住**：Master公式是工具，理解递归的本质更重要！ 🎯
