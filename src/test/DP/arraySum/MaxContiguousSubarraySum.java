package test.DP.arraySum;

import java.util.Arrays;

public class MaxContiguousSubarraySum {

    public static void main (String[] args)
    {
        int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is " +
            maxSubArraySum(a));
    }

    private static int maxSubArraySum(final int[] a) {
        int totalSum = Arrays.stream(a).sum();
        int i=0,j=a.length-1;
        int maxsum = totalSum;
        int start=i,end=j;
        while(i!=j){
            if(a[i] > a[j])
                totalSum -= a[j--];
            else
                totalSum -= a[i++];
            if(maxsum != Math.max(maxsum,totalSum)){
                maxsum = Math.max(maxsum,totalSum);
                start = i;
                end = j;
            }
        }
        System.out.println(String.format("Max sum subarray start at %d and ends at %d",start,end));
        return maxsum;
    }

}
