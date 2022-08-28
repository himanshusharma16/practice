package test.DP.string;

//Input: Given string :"forgeeksskeegfor",
//    Output: "geeksskeeg"
//
//    Input: Given string :"Geeks",
//    Output: "ee"
public class LongestPalindromicSubstring {

    public static void main(String[] args)
    {

        String str = "mississiippi";
        System.out.println("Length is: " + longestPalSub(str));
        System.out.println("Length is: " + longestPalSubstrRecursive(str));

    }

    private static int longestPalSub(String str){
        int len = str.length();
        boolean[][] dp = new boolean[len][len];
        int max = 0;
        for(int i=0;i<len;i++){
            dp[i][i] = true;
        }
        //length 2
        for(int i = 0;i<len-1;i++){
            if(str.charAt(i) == str.charAt(i+1)) {
                dp[i][i + 1] = true;
                max = 2;
            }
        }
        //length > 2
        for(int i = 2;i<len;i++){
            for(int j = 0; j<len-i;j++){
                int k = j+i-1;
                if(dp[j+1][k] == true && str.charAt(j) == str.charAt(k+1)) {
                    dp[j][k + 1] = true;
                    max = i+1;
                }
            }
        }
        return max;
    }

    private static int longestPalSubstr(final String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        int maxlength = 1,start=0;
        //for length 1
        for(int i = 0; i<n; i++)
            dp[i][i] = 1;
        //for length 2
        for(int i =0;i<n-1;i++){
            if(str.charAt(i) == str.charAt(i+1)) {
                dp[i][i + 1] = 1;
                start = i;
                maxlength = 2;
            }
        }
        //for length >= 3
        for(int k = 2;k<n;k++){
            for(int i = 0;i<n-k;i++){
                int j = i+k;
                if(dp[i+1][j-1] == 1 && str.charAt(i)==str.charAt(j)) {
                    dp[i][j] = 1;
                    start = i;
                    maxlength = k+1;
                }
            }
        }

        System.out.println("Palindrome is - "+str.substring(start,start+maxlength));
        return maxlength;
    }

    private static int longestPalSubstrRecursive(final String str) {
        if(isPalindrome(str)) {
            System.out.println("Palindrome is - "+str);
            return str.length();
        }
        for(int i = 1;i<str.length();i++){
            for(int j=0;j<=i;j++){
                final String substring = str.substring(0 + j, str.length() - i + j);
                if(isPalindrome(substring)) {
                    System.out.println("Palindrome is - "+ substring);
                    return str.length() - i;
                }
            }
        }
        return 1;
    }

    private static boolean isPalindrome(String s){
        StringBuilder s1 = new StringBuilder(s);
        s1 = s1.reverse();
        return s1.toString().equals(s);
    }


}
