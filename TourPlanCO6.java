import java.util.*;

public class TourPlanCO6 {

    // Greedy Activity Selection
    static void activitySelection(int[] start, int[] end, int n) {
        Arrays.sort(end);
        System.out.println("Selected activities (Greedy):");
        int lastEnd = -1;
        for (int i = 0; i < n; i++) {
            if (start[i] >= lastEnd) {
                System.out.print("(" + start[i] + "," + end[i] + ") ");
                lastEnd = end[i];
            }
        }
        System.out.println();
    }

    // 0/1 Knapsack DP
    static int knapsack(int W, int wt[], int val[], int n) {
        int[][] dp = new int[n+1][W+1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) dp[i][w] = 0;
                else if (wt[i-1] <= w)
                    dp[i][w] = Math.max(val[i-1] + dp[i-1][w-wt[i-1]], dp[i-1][w]);
                else dp[i][w] = dp[i-1][w];
            }
        }
        return dp[n][W];
    }

    // Longest Common Subsequence DP
    static int LCS(String X, String Y) {
        int m = X.length(), n = Y.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else if (X.charAt(i-1) == Y.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }

    // Matrix Chain Multiplication DP
    static int matrixChainOrder(int[] p, int n) {
        int[][] dp = new int[n][n];
        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n-L+1; i++) {
                int j = i+L-1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int q = dp[i][k] + dp[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < dp[i][j]) dp[i][j] = q;
                }
            }
        }
        return dp[1][n-1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Greedy Activity Selection
        System.out.print("Enter number of activities: ");
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];
        System.out.println("Enter start and end times:");
        for (int i = 0; i < n; i++) {
            start[i] = sc.nextInt();
            end[i] = sc.nextInt();
        }
        activitySelection(start, end, n);

        // Knapsack
        System.out.print("\nEnter number of items: ");
        int items = sc.nextInt();
        int[] wt = new int[items];
        int[] val = new int[items];
        System.out.println("Enter weights and values:");
        for (int i = 0; i < items; i++) {
            wt[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }
        System.out.print("Enter capacity of knapsack: ");
        int W = sc.nextInt();
        System.out.println("Max value in Knapsack = " + knapsack(W, wt, val, items));

        // LCS
        System.out.print("\nEnter first itinerary string: ");
        String X = sc.next();
        System.out.print("Enter second itinerary string: ");
        String Y = sc.next();
        System.out.println("Length of LCS = " + LCS(X, Y));

        // Matrix Chain Multiplication
        System.out.print("\nEnter number of matrices: ");
        int m = sc.nextInt();
        int[] p = new int[m+1];
        System.out.println("Enter dimensions array:");
        for (int i = 0; i <= m; i++) p[i] = sc.nextInt();
        System.out.println("Minimum multiplications = " + matrixChainOrder(p, m+1));

        sc.close();
    }
}
