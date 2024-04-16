package test.DP.arraySum;

import java.util.Arrays;

//Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
//    Output: True
//    There is a subset (4, 5) with sum 9.
//
//    Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 30
//    Output: False
//    There is no subset that add up to 30.
public class SubsetSum {

    public static void main(String args[])
    {
        int set[] = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        int n = set.length;
        if (isSubsetSum(set, n, sum) == true)
            System.out.println("Found a subset"
                + " with given sum");
        else
            System.out.println("No subset with"
                + " given sum");
    }

    private static boolean isSubsetSum(int[] set, final int n, final int sum) {
        boolean[][] dp = new boolean[n+1][sum+1];
        Arrays.fill(dp[0],false);
        for(int i=0;i<=n;i++)
            dp[i][0] = true;
        for(int i=0;i<n;i++){
            for(int j =1;j<sum+1;j++){
                if(j == set[i])
                    dp[i+1][j] = true;
                else{
                    if(set[i] < j)
                        dp[i+1][j] = dp[i][j-set[i]] || dp[i][j];
                    else
                        dp[i+1][j] = dp[i][j];
                }
            }
        }
        return dp[n][sum];
    }

}
