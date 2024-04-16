package test.DP.numbers;

//Ugly numbers are the numbers with only 2,3,5 as prime factors
public class UglyNumbers {

    public static void main(String[] args) {
        System.out.println(UglyNumbers.printUglyNumber(150));
    }

    static int getNthUglyNo(int n)
    {
        // To store ugly numbers
        int ugly[] = new int[n];
        int i2 = 0, i3 = 0, i5 = 0;
        int next_multiple_of_2 = 2;
        int next_multiple_of_3 = 3;
        int next_multiple_of_5 = 5;
        int next_ugly_no = 1;

        ugly[0] = 1;

        for (int i = 1; i < n; i++)
        {
            next_ugly_no
                = Math.min(next_multiple_of_2,
                Math.min(next_multiple_of_3,
                    next_multiple_of_5));

            ugly[i] = next_ugly_no;
            if (next_ugly_no == next_multiple_of_2)
            {
                i2 = i2 + 1;
                next_multiple_of_2 = ugly[i2] * 2;
            }
            if (next_ugly_no == next_multiple_of_3)
            {
                i3 = i3 + 1;
                next_multiple_of_3 = ugly[i3] * 3;
            }
            if (next_ugly_no == next_multiple_of_5)
            {
                i5 = i5 + 1;
                next_multiple_of_5 = ugly[i5] * 5;
            }
        }

        // End of for loop (i=1; i<n; i++)
        return next_ugly_no;
    }

    private static int printUglyNumber(int n) {
        if(n == 0)
            return 1;
        int[] dp = new int[n+1];
        dp[1]=1;
        int i2=1,i3=1,i5=1;
        int next2 = 2,next3 = 3,next5 = 5;
        for(int i=2;i<=n;i++){
            int next = Math.min(next2,Math.min(next3,next5));
            dp[i] = next;
            if(next == next2) {
                next2 = 2 *dp[++i2];
            }
            if(next == next3) {
                next3 = 3 *dp[++i3];
            }
            if(next == next5) {
                next5 = 5 *dp[++i5];
            }
        }
        return dp[n];
    }
}
