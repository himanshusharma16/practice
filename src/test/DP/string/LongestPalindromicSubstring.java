package test.DP.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Input: Given string :"forgeeksskeegfor",
//    Output: "geeksskeeg"
//
//    Input: Given string :"Geeks",
//    Output: "ee"
public class LongestPalindromicSubstring {

    public static void main(String[] args)
    {

        String str = "mississiippi";
        System.out.println("Length is: " + new LongestPalindromicSubstring().addBinary("11","1"));
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

    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        int c = 0;
        char[] sarr = startGene.toCharArray();
        char[] earr = endGene.toCharArray();
        if(!set.contains(endGene))
            return -1;
        return minMutation(sarr,earr,set,endGene);
    }

    public String addBinary(String a, String b) {
        char[] arra = a.toCharArray();
        char[] arrb = b.toCharArray();

        int l = a.length() >= b.length() ? a.length() : b.length();
        StringBuilder sb = new StringBuilder("");
        int carry = 0, al = a.length()-1, bl = b.length()-1;
        for(int i = l-1 ; i <=0 ; i--){
            int c1 = al >= 0 ? arra[al--]-'0' : 0;
            int c2 = bl >= 0 ? arrb[bl--]-'0' : 0;

            int sum = c1+c2+carry;
            if(sum == 0 || sum == 1){
                char c = sum == 0 ? '0' : '1';
                sb = sb.append(c);
                carry = 0;
            } else if ( sum == 2){
                sb = sb.append('0');
                carry = 1;
            } else if ( sum == 3){
                sb = sb.append('1');
                carry = 1;
            }
        }

        if(carry == 0)
            return sb.reverse().toString();
        else {
            sb = sb.append('1');
            return sb.reverse().toString();
        }
    }

    public int minMutation(char[] sarr, char[] earr, Set<String> set, String end) {
        int c = 0;

        for(int i = 0; i < sarr.length; i++){
            if(sarr[i] != earr[i]){
                char temp = sarr[i];
                sarr[i] = earr[i];
                String ss = String.valueOf(sarr);
                if(ss.equals(end))
                    return 1;
                if(set.contains(ss)){
                    set.remove(ss);
                    return minMutation(sarr,earr,set,end)+1;
                } else {
                    sarr[i] = temp;
                }
            }
        }

        return -1;
    }

}
