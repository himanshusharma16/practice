package test.DP.string;

import java.util.Arrays;

//Given two sequences, print the longest subsequence present in both of them.
//
//    Examples:
//
//    LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
//    LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.print(LongestCommonSubsequence.lcsdp("ABCDGEFGOH", "AEDFEOHR"));
    }

    private static int lcs(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0 || len2 == 0)
            return 0;
        if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1))
            return lcs(s1.substring(0, len1 - 1), s2.substring(0, len2 - 1)) + 1;
        else
            return Math.max(lcs(s1.substring(0, len1), s2.substring(0, len2 - 1)),
                lcs(s1.substring(0, len1 - 1), s2.substring(0, len2)));
    }

    private static int lcsdp(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        //get the length
        for(int i = 1;i<=len1;i++){
            for(int j = 1; j<=len2;j++){
                if(s1c[i-1] == s2c[j-1])
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        //get the subsequence
        String sb = "";
        int row = len1,col = len2;
        while(row>0 && col > 0){
            if(s1c[row-1]==s2c[col-1]){
                sb = s1c[row-1] + sb;
                row--; col--;
            } else {
                if(dp[row-1][col] > dp[row][col-1])
                    row--;
                else
                    col--;
            }
        }
        System.out.println(sb);
        return dp[len1][len2];
    }
}
