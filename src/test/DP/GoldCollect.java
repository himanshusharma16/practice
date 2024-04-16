package test.DP;

//Given a gold mine of n*m dimensions. Each field in this mine contains a positive integer which
//    is the amount of gold in tons. Initially the miner is at first column but can be at any row.
//    He can move only (right->,right up /,right down\) that is from a given cell, the miner can move
//    to the cell diagonally up towards the right or right or diagonally down towards the right.
//    Find out maximum amount of gold he can collect.

//Input : mat[][] = {{1, 3, 3},
//                  {2, 1, 4},
//                  {0, 6, 4}};
//    Output : 12
//    {(1,0)->(2,1)->(1,2)}
//
//    Input: mat[][] = { {1, 3, 1, 5},
//                      {2, 2, 4, 1},
//                      {5, 0, 2, 3},
//                      {0, 6, 1, 2}};
//    Output : 16
//    (2,0) -> (1,1) -> (1,2) -> (0,3) OR
//    (2,0) -> (3,1) -> (2,2) -> (2,3)
//
//    Input : mat[][] = {{10, 33, 13, 15},
//                      {22, 21, 04, 1},
//                      {5, 0, 2, 3},
//                      {0, 6, 14, 2}};
//    Output : 83


public class GoldCollect {
    public static void main(String[] args)
    {
        int[][] gold = {{10, 33, 13, 15},
                              {22, 21, 04, 1},
                              {5, 0, 2, 3},
                              {0, 6, 14, 2}};
        int m = 4, n = 4;
        System.out.println(getMaxGold(gold, n, m));
    }

    private static int getMaxGold(int[][] gold, int row, int column) {
        int[][] dp = new int[row][column];
        for(int j=column-1;j>=0;j--){
            for(int i = 0;i<row;i++){
                if(j == column-1)
                    dp[i][j] = gold[i][j];
                else if (i == 0){
                    dp[i][j] = gold[i][j] + Math.max(dp[i][j+1],dp[i+1][j+1]);
                } else if (i == row-1)
                    dp[i][j] = gold[i][j] + Math.max(dp[i][j+1],dp[i-1][j+1]);
                else
                    dp[i][j] = gold[i][j] + Math.max(dp[i][j+1],Math.max(dp[i-1][j+1],dp[i+1][j+1]));
            }
        }
        int maxGold = 0;
        for(int i = 0; i< row;i++)
            maxGold = maxGold < dp[i][0]  ? dp[i][0] : maxGold;
        return maxGold;
    }
}
