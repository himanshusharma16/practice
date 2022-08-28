package test.DP;

//Given a value N, if we want to make change for N cents, and we have infinite supply of
//    each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change?
//    The order of coins doesn’t matter.
//    For example, for N = 4 and S = {1,2,3}, there are four solutions:
//    {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.
//    For N = 10 and S = {2, 5, 3, 6}, there are 5 solutions: {2,2,2,2,2},{2,2,3,3},{2,2,6},{2,3,5},{5,5}.

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        int[] S = { 1,2};
        int n = 8; //sum
        int[][] dp = new int[n+1][S.length+1];
        for(int i = 0;i <=n ; i++)
            Arrays.fill(dp[i],-1);
        for(int i = 0;i <=n ; i++)
            dp[i][0] = 0;
        Arrays.fill(dp[0],1);
        System.out.println(CoinChange.calculateSum(S,n,S.length,dp));
    }

    private static int calculateSum(int[] s, int n , int len, int[][] dp) {
        if(dp[n][len] != -1)
            return dp[n][len];
        if(n >= s[len-1])
            dp[n][len] = calculateSum(s,n-s[len-1],len,dp)+calculateSum(s,n,len-1,dp);
        else
            dp[n][len] = calculateSum(s,n,len-1,dp);
        return dp[n][len];

    }
}
