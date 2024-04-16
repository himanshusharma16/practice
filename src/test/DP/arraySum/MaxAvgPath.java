package test.DP.arraySum;

//Input : Matrix = [1, 2, 3
//                  4, 5, 6
//                  7, 8, 9]
//    Output : 5.8
//    Path with maximum average is, 1 -> 4 -> 7 -> 8 -> 9
//    Sum of the path is 29 and average is 29/5 = 5.8
public class MaxAvgPath {

    public static void main(String[] args)
    {
        int cost[][] = {{1, 2, 3},
                        {6, 5, 4},
                        {7, 3, 9}};

        System.out.println(maxAverageOfPath(cost, 3)); //o/p - 5.2
    }

    private static double maxAverageOfPath(final int[][] cost, final int n) {
        double[][] dp = new double[n][n];
        dp[n-1][n-1] = cost[n-1][n-1];
        for(int i=n-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i == n-1 && j== n-1)
                    continue;
                if(i==(n-1))
                    dp[i][j] = dp[i][j+1]+cost[i][j];
                else if (j == (n-1))
                    dp[i][j] = dp[i+1][j]+cost[i][j];
                else {
                    dp[i][j] = Math.max(dp[i][j+1],dp[i+1][j])+cost[i][j];
                }
            }
        }
        final long steps = 2 * n -1;
        return dp[0][0]/steps;
    }
}
