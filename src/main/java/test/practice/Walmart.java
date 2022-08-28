package test.practice;

import java.util.Scanner;

class Walmart {
    public static int maxSubarraySum = 10000000;
    public static int minSubarraySum = 0;
    public static void solve(int a[], int n, int k,
                             int index, int sum, int maxsum,int minsum)
    {
        if (k == 1) {
            minsum = Math.min(minsum,sum);
            maxsum = Math.max(maxsum, sum);
            sum = 0;
            for (int i = index; i < n; i++) {
                sum += a[i];
            }
            maxsum = Math.max(maxsum, sum);
            minsum = Math.min(minsum , sum);
            maxSubarraySum = Math.min(maxSubarraySum, maxsum);
            minSubarraySum = Math.max(minSubarraySum , minsum);
            return;
        }
        sum = 0;
        for (int i = index; i < n; i++) {
            sum += a[i];
            maxsum = Math.max(maxsum, sum);
            minsum = Math.min(minsum , sum);
            solve(a, n, k - 1, i + 1, sum, maxsum,minsum);
        }
    }
    public static void main(String[] args)
    {
        /*Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int count = n;
        int arr[] = new int[n];
        int i =0;
        while(count-- > 0){
            arr[i++] = Integer.parseInt(sc.next());
        }*/
        int arr[] = {3,2,11,12,5,5,9};
        int n = 7;
        int k = 4;
        System.out.println(checkThis(arr));
        //solve(arr, n, k, 0, 0, 0,Integer.MAX_VALUE);
        System.out.println(maxSubarraySum-minSubarraySum+ "\n");
    }

    private static int checkThis(final int[] arr) {
        int[] arr1 = new int[2];
        return arr1[0]=5;
    }
}