package test.DP.arraySum;

public class MaxSumNo2Together {

    public static void main(String args[])
    {

        // Creating the array
        int[] arr = {3, 2, 5, 10, 7};
        int N = arr.length;

        // Function call
        System.out.println(findMaxSum(arr, N));
    }

    private static int findMaxSum(final int[] arr, final int len) {
        int[] dp = new int[len+1];
        dp[0] = 0;
        dp[1] = arr[0];
        int max =  0;
        for(int i = 2;i<len+1;i++){
            dp[i] = Math.max(dp[i-1],dp[i-2]+arr[i-1]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }


}
