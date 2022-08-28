package test.DP;

public class RodCutting
{
    /* Returns the best obtainable price for a rod of
       length n and price[] as prices of different pieces */
    /* Driver program to test above functions */
    public static void main(String args[]) {
        int arr[] = new int[] { 3, 5, 8, 9, 10, 17, 17, 20 };
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is " +
            cutRod(arr, size));
    }

    private static int cutRod(int[] arr, int size) {
        int[] dp = new int[size+1];
        dp[0] = 0;
        for(int i=1; i<=size;i++){
            int price = arr[i-1];
            for(int j = 0;j<i-1;j++){
                price = Math.max(price,arr[j]+dp[i-j-1]);
            }
            dp[i] = price;
        }
        return dp[size];
    }
}