package test.DP;

//Given a “2 x n” board and tiles of size “2 x 1”, count the number of ways to tile the given board
//    using the 2 x 1 tiles. A tile can either be placed horizontally i.e., as a 1 x 2 tile or
//    vertically i.e., as 2 x 1 tile.
//    Input: n = 4
//
//    Output: 5
//
//    Explanation:
//
//    For a 2 x 4 board, there are 5 ways
//
//    All 4 vertical (1 way)
//    All 4 horizontal (1 way)
//    2 vertical and 2 horizontal (3 ways)
//    Input: n = 3
//
//    Output: 3
//
//    Explanation:
//
//    We need 3 tiles to tile the board of size  2 x 3.
//
//    We can tile the board using following ways
//
//    Place all 3 tiles vertically.
//    Place 1 tile vertically and remaining 2 tiles horizontally (2 ways)

public class TilingProblem {
    public static void main(String[] args)
    {
        System.out.println(getNoOfWays(4));
        System.out.println(getNoOfWays(3));
    }

    private static int getNoOfWays(final int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i<n+1;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
