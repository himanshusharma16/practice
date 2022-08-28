package test.DP.string;

//Given an array of n size, the task is to find the longest subsequence such that difference between
// adjacents is one.
//
//    Examples:
//
//    Input :  arr[] = {10, 9, 4, 5, 4, 8, 6}
//    Output :  3
//    As longest subsequences with difference 1 are, "10, 9, 8",
//    "4, 5, 4" and "4, 5, 6"
//
//    Input :  arr[] = {1, 2, 3, 2, 3, 7, 2, 1}
//    Output :  7
//    As longest consecutive sequence is "1, 2, 3, 2, 3, 2, 1"
public class LongestSubsequenceWithDiffOne {

    public static void main(String[] args)
    {
        // Longest subsequence with one
        // difference is
        // {1, 2, 3, 4, 3, 2}
        int arr[] = { 1, 2, 3, 4, 5, 3, 2 };
        int n = arr.length;
        System.out.println(longestSubseqWithDiffOne(arr, n));
    }

    private static int longestSubseqWithDiffOne(final int[] arr, final int n) {
        int[] dp = new int[n];
        int max = 0;
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        dp[0] = 1;
        for(int i = 1; i<n; i++){
            int m = 0;
            for(int j = 0; j< i; j++) {
                if (Math.abs(arr[j] - arr[i]) == 1 && dp[j] > m)
                    m = dp[j];
            }
            dp[i] = m+1;
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}
