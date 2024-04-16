package test.DP.string;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static void main(String[] args)
    {
        int arr[] = {50, 3, 10, 7, 40, 80,30};
        int n = arr.length;
        System.out.println("Length of lis is " + lis(arr, n)
            + "\n");
        int a[] = {1, 101, 2, 3, 100, 4, 5};
        int index = 2, k = 5;
        System.out.println(maxSumIncSubsequence(a, index, k));
    }


    private static int maxSumIncSubsequence(int arr[] , int k, int last){
        int maxElement = arr[last];
        int[] dp = new int[k+1];
        dp[0] = arr[0] < maxElement ? arr[0] : 0;
        for(int i=1; i<=k;i++){
            if(arr[i] >= maxElement)
                dp[i] = dp[i-1];
            else {
                int max = 0;
                for(int j=0;j<i;j++){
                    if(arr[j] < arr[i] && dp[j] > max)
                        max = dp[j];
                }
                dp[i] = max+arr[i];
            }
        }
        return maxElement+dp[k];
    }

    private static int lis(final int[] arr, final int n) {
        int[] lis = new int[n];
        lis[0] = 1;
        int maxi = 0;
        for(int i = 1;i<n;i++){
            int max = 0;
            int curr = arr[i];
            for(int j = 0; j<i;j++){
                if(arr[j] < curr && lis[j] > max)
                    max = lis[j];
            }
            lis[i] = max+1;
        }
        int index = 0;
        for(int i = 0;i<n;i++) {
            if(maxi < lis[i]) {
                maxi = lis[i];
                index = i;
            }
        }
        List<Integer> licList = new ArrayList<>();
        licList.add(arr[index]);
        while(maxi > 0){
            maxi--;
            for(int i = index-1;i>0;i--){
                if(lis[i] == maxi && arr[index] > arr[i]){
                    index = i;
                    licList.add(arr[index]);
                    break;
                }
            }
        }
        return licList.size();

    }


}
