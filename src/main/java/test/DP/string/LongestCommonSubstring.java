package test.DP.string;

//Input : X = “zxabcdezy”, y = “yzabcdezx”
//    Output : 6
//    Explanation:
//    The longest common substring is “abcdez” and is of length 6.
public class LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.print(LongestCommonSubstring.lcs("OldSite:GeeksforGeeks.org", "NewSite:GeeksQuiz.com"));
    }

    private static int lcs(String s1, String s2) {
        int max = 0;
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }
}
