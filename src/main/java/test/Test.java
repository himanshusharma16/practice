package test;

public class Test {

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i=grid.length-1;i>=0;i--){
            for(int j=grid[0].length-1;j>=0;j--){
                if(i==grid.length-1 && j== grid[0].length-1)
                    dp[i][j] = grid[i][j];
                else if (i == grid.length-1)
                    dp[i][j] = grid[i][j] + dp[i][j+1];
                else if (j == grid[0].length-1)
                    dp[i][j] = grid[i][j] + dp[i+1][j];
                else
                    dp[i][i] = Math.min(dp[i][j+1],dp[i+1][j])+ grid[i][j];
            }
        }
        return dp[0][0];
    }


    public int difference(String input1){
        if(input1 == null || input1 == "" || input1 == " ")
            return 0;
        return longestPalindromicSubstring(input1) - 1;
    }

    private int longestPalindromicSubstring(final String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        int maxlength = 1;
        for(int i = 0; i<n; i++)
            dp[i][i] = 1;
        for(int i =0;i<n-1;i++){
            if(str.charAt(i) == str.charAt(i+1)) {
                dp[i][i + 1] = 1;
                maxlength = 2;
            }
        }
        for(int k = 2;k<n;k++){
            for(int i = 0;i<n-k;i++){
                int j = i+k;
                if(dp[i+1][j-1] == 1 && str.charAt(i)==str.charAt(j)) {
                    dp[i][j] = 1;
                    maxlength = k+1;
                }
            }
        }
        return maxlength;
    }

//    public static void main(String[] args) {
//        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
//        System.out.println(new Test().minPathSum(grid));
//    }

    public static void main(String[] args) throws Exception {
        // write your code here
       // Scanner sc = new Scanner(System.in);
        int n = 4;//sc.nextInt();
//        String psf = "asd";
//        String a = psf+"fgh";
//        System.out.printf("psf = %s , a = %s",psf,a);
        int[] dp = new int[n+1];
        dp[0] = 0;
        if(n==0){
            System.out.print(0);
            return;
        }
        if( n == 1){
            System.out.print(1);
            return;
        }
        dp[1] = 1;
        if( n ==2 ){
            System.out.print(2);
            return;
        }
        dp[2] = 2;
        for(int i = 3;i<=n;i++){
            dp[i] = dp[n-1]+((n-1)*dp[n-2]);
        }
        System.out.print(dp[n]);
    }
}
