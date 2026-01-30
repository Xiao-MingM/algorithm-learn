package practice;

/**
 * Performance Test: Compare Memoization, 3D DP, and 2D DP (Space Optimized)
 */
public class Class69_Code01_OnesAndZeroes_PerformanceTest {

    public int zero, one;

    public void countOneAndZero(char[] s) {
        zero = 0;
        one = 0;
        for (char c : s) {
            if (c == '0') {
                zero++;
            }
            if (c == '1') {
                one++;
            }
        }
    }

    // Memoization (Top-down DP)
    public int findMaxForm2(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 0; i <= len; i++) {
            for (int z = 0; z <= m; z++) {
                for (int o = 0; o <= n; o++) {
                    dp[i][z][o] = -1;
                }
            }
        }
        return f2(strs, 0, m, n, dp);
    }

    int f2(String[] strs, int i, int z, int o, int[][][] dp) {
        if (i == strs.length) {
            return 0;
        }
        if (dp[i][z][o] != -1) {
            return dp[i][z][o];
        }
        int p1 = f2(strs, i + 1, z, o, dp);
        countOneAndZero(strs[i].toCharArray());
        int p2 = 0;
        if (zero <= z && one <= o) {
            p2 = 1 + f2(strs, i + 1, z - zero, o - one, dp);
        }
        int ans = Math.max(p1, p2);
        dp[i][z][o] = ans;
        return ans;
    }

    // 3D DP (Bottom-up)
    public int findMaxForm3(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = len - 1; i >= 0; i--) {
            countOneAndZero(strs[i].toCharArray());
            for (int z = 0; z <= m; z++) {
                for (int o = 0; o <= n; o++) {
                    int p1 = dp[i + 1][z][o];
                    int p2 = 0;
                    if (zero <= z && one <= o) {
                        p2 = 1 + dp[i + 1][z - zero][o - one];
                    }
                    dp[i][z][o] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][m][n];
    }

    // 2D DP (Space Optimized)
    public int findMaxForm4(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            countOneAndZero(str.toCharArray());
            for (int z = m; z >= zero; z--) {
                for (int o = n; o >= one; o--) {
                    dp[z][o] = Math.max(dp[z][o], 1 + dp[z - zero][o - one]);
                }
            }
        }
        return dp[m][n];
    }

    // Generate test data
    public static String[] generateTestData(int len, int strLen) {
        String[] strs = new String[len];
        for (int i = 0; i < len; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < strLen; j++) {
                sb.append(Math.random() < 0.5 ? '0' : '1');
            }
            strs[i] = sb.toString();
        }
        return strs;
    }

    // Performance test
    public static void performanceTest() {
        Class69_Code01_OnesAndZeroes_PerformanceTest test = 
            new Class69_Code01_OnesAndZeroes_PerformanceTest();
        
        // Test case 1: Small scale
        System.out.println("========== Test Case 1: Small Scale ==========");
        String[] strs1 = generateTestData(20, 10);
        int m1 = 50, n1 = 50;
        testMethod(test, strs1, m1, n1, "Small");
        
        // Test case 2: Medium scale
        System.out.println("\n========== Test Case 2: Medium Scale ==========");
        String[] strs2 = generateTestData(50, 15);
        int m2 = 100, n2 = 100;
        testMethod(test, strs2, m2, n2, "Medium");
        
        // Test case 3: Large scale
        System.out.println("\n========== Test Case 3: Large Scale ==========");
        String[] strs3 = generateTestData(100, 20);
        int m3 = 150, n3 = 150;
        testMethod(test, strs3, m3, n3, "Large");
    }

    public static void testMethod(Class69_Code01_OnesAndZeroes_PerformanceTest test,
                                   String[] strs, int m, int n, String scale) {
        // Warm up JVM
        test.findMaxForm2(strs, m, n);
        test.findMaxForm3(strs, m, n);
        test.findMaxForm4(strs, m, n);
        
        // Test memoization
        long start = System.nanoTime();
        int result2 = test.findMaxForm2(strs, m, n);
        long time2 = System.nanoTime() - start;
        
        // Test 3D DP
        start = System.nanoTime();
        int result3 = test.findMaxForm3(strs, m, n);
        long time3 = System.nanoTime() - start;
        
        // Test 2D DP (space optimized)
        start = System.nanoTime();
        int result4 = test.findMaxForm4(strs, m, n);
        long time4 = System.nanoTime() - start;
        
        // Verify results consistency
        if (result2 != result3 || result2 != result4) {
            System.out.println("ERROR: Results inconsistent!");
            System.out.println("Memoization: " + result2);
            System.out.println("3D DP: " + result3);
            System.out.println("2D DP: " + result4);
            return;
        }
        
        // Print results
        System.out.println("Result: " + result2);
        System.out.printf("Memoization: %10.3f ms (baseline)\n", time2 / 1_000_000.0);
        System.out.printf("3D DP:       %10.3f ms (%.2fx faster)\n", 
            time3 / 1_000_000.0, (double)time2 / time3);
        System.out.printf("2D DP:       %10.3f ms (%.2fx faster)\n", 
            time4 / 1_000_000.0, (double)time2 / time4);
        
        // Memory usage estimation
        int len = strs.length;
        long mem3D = (len + 1L) * (m + 1) * (n + 1) * 4; // 3D array
        long mem2D = (m + 1L) * (n + 1) * 4; // 2D array
        System.out.printf("Memory - 3D DP: %d KB, 2D DP: %d KB (compression: %.1fx)\n",
            mem3D / 1024, mem2D / 1024, (double)mem3D / mem2D);
    }

    public static void main(String[] args) {
        System.out.println("Performance Comparison: Memoization vs 3D DP vs 2D DP (Space Optimized)\n");
        performanceTest();
        
        System.out.println("\n========== Performance Analysis ==========");
        System.out.println("1. Memoization (Top-down):");
        System.out.println("   - High function call overhead");
        System.out.println("   - Random memory access pattern");
        System.out.println("   - Low cache hit rate");
        System.out.println();
        System.out.println("2. 3D DP (Bottom-up):");
        System.out.println("   - No function call overhead");
        System.out.println("   - Sequential memory access");
        System.out.println("   - But array too large, cannot fit in CPU cache");
        System.out.println();
        System.out.println("3. 2D DP (Space Optimized):");
        System.out.println("   - No function call overhead");
        System.out.println("   - Sequential memory access");
        System.out.println("   - Small array, fully fits in CPU cache");
        System.out.println("   - Best performance!");
    }
}
