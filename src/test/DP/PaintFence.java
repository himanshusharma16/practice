package test.DP;

//Given a fence with n posts and k colors, find out the number of ways of painting the fence such that at
// most 2 adjacent posts have the same color. Since the answer can be large return it modulo 10^9 + 7.
//    Examples:
//
//    Input : poles = 2 colors = 4
//    Output : 16
//    Explaination: We have 4 colors and 2 posts.
//    Ways when both posts have same color : 4
//    Ways when both posts have diff color :4(choices for 1st post) * 3(choices for 2nd post) = 12
//
//    Input : poles = 3 colors = 2
//    Output : 6

import java.util.Arrays;

public class PaintFence {

    public static void main(String[] args)
    {
        int poles = 7, colors = 7;
        System.out.println(countWaysToPaint(poles, colors));

    }

    private static int countWaysToPaint(final int poles, final int colors) {
        int[][] dp = new int[3][poles];
        //int[0] - same color
        //int[1] - different color
        //int[2] - total ways
        dp[0][0] = dp[1][0] = dp[2][0] = colors;
        if(poles > 1){
            dp[0][1] = colors;
            dp[1][1] = colors * (colors-1);
            dp[2][1] = dp[0][1] + dp[1][1];
        }
        for(int i = 2;i<poles;i++){
            dp[0][i] = dp[1][i-1];
            dp[1][i] = dp[2][i-1] * (colors-1);
            dp[2][i] = dp[0][i] + dp[1][i];
        }
        return dp[2][poles-1];
    }

}
