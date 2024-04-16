package test.practice;

import java.util.*;

public class DynamicProgramming {

    static int collectGold(int[][] gold, int x, int y,
        int n, int m, int[][] dp) {

        // Base condition.
        if ((x < 0) || (x == n) || (y == m)) {
            return 0;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // Right upper diagonal
        int rightUpperDiagonal
            = collectGold(gold, x - 1, y + 1, n, m, dp);

        // right
        int right = collectGold(gold, x, y + 1, n, m, dp);

        // Lower right diagonal
        int rightLowerDiagonal
            = collectGold(gold, x + 1, y + 1, n, m, dp);

        // Return the maximum and store the value
        dp[x][y] = gold[x][y]
            + Math.max(Math.max(rightUpperDiagonal,
                rightLowerDiagonal),
            right);
        return dp[x][y];
    }

    static int getMaxGold(int[][] gold, int n, int m) {
        int maxGold = 0;
        int[][] dp = new int[n][m];
        for (int row = 0; row < n; row++) {
            Arrays.fill(dp[row], -1);
        }
        for (int i = 0; i < n; i++) {

            // Recursive function call for ith row.
            int goldCollected
                = collectGold(gold, i, 0, n, m, dp);
            maxGold = Math.max(maxGold, goldCollected);
        }

        return maxGold;
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> q = new LinkedList<>();
        List<Integer> ll = null;
        q.add(entrance);
        maze[entrance[0]][entrance[1]] = '+';
        q.add(null);

        int count = 0;

        while(!q.isEmpty()){
            var curr = q.poll();

            //check if border
            if(curr == null){
                count++;
                if(!q.isEmpty())
                    q.add(null);
                continue;
            } else {
                if(maze[curr[0]][curr[1]] == '.' && (curr[0] == 0 || curr[1] == 0 || curr[0] == maze.length - 1 || curr[1] == maze[0].length - 1))
                    return count;
                else {
                    maze[curr[0]][curr[1]] = '+';
                }
            }

            boolean added = false;

            // try up
            if(curr[0] != 0 && maze[curr[0]-1][curr[1]] == '.'){
                q.add(new int[]{curr[0]-1,curr[1]});
                added = true;
            }

            //try down
            if(curr[0] != maze.length - 1 && maze[curr[0]+1][curr[1]] == '.') {
                q.add(new int[]{curr[0]+1,curr[1]});
                added = true;
            }

            //try right
            if(curr[1] != maze[0].length - 1 && maze[curr[0]][curr[1]+1] == '.') {
                q.add(new int[]{curr[0],curr[1]+1});
                added = true;
            }

            //try left
            if(curr[1] != 0 && maze[curr[0]][curr[1]-1] == '.') {
                q.add(new int[]{curr[0],curr[1]-1});
                added = true;
            }

            //add dummy
            if(added)
                q.add(null);
        }

        return -1;
    }

    /*public static void main(String[] args) {
        int[][] gold = { { 1, 3, 1, 5 },
            { 2, 2, 4, 1 },
            { 5, 0, 2, 3 },
            { 0, 6, 1, 2 } };
        int m = 4, n = 4;
        System.out.println(getMaxGold(gold, n, m));
    }*/

    static long countWays(int S[], int m, int n)
    {

        long[] table = new long[n+1];

        Arrays.fill(table, 0);   //O(n)

        table[0] = 1;

        for (int i=0; i<m; i++)
            for (int j=S[i]; j<=n; j++)
                table[j] += table[j-S[i]];

        return table[n];
    }

    static int getWays(int s[] , int sum, int num, int arr[][]){
        if(sum == 0)
            return arr[num][0] = 1;
        if(sum < 0)
            return 0;
        if(num <= 0)
            return 0;
        if(arr[num][sum] != -1)
            return arr[num][sum];
        return arr[num][sum] = getWays(s,sum-s[num-1],num,arr)+getWays(s,sum,num-1,arr);
    }

    static int binomialCoeff(int n, int k)
    {

        // Base Cases
        if (k > n)
            return 0;
        if (k == 0 || k == n)
            return 1;

        // Recur
        return binomialCoeff(n - 1, k - 1)
            + binomialCoeff(n - 1, k);
    }

    static int combination (int n , int k){
        int[] fact = new int[n+1];
        fact[0] = 1;
        fact[1] = 1;
        for(int i = 2; i< n+1;i++)
            fact[i] = i * fact[i-1];
        return fact[n]/(fact[k]*fact[n-k]);
    }

    static int countWays(int n)
    {
        int []A = new int[n+1];
        int []B = new int[n+1];
        A[0] = 1; A[1] = 0;
        B[0] = 0; B[1] = 1;
        for (int i = 2; i <= n; i++)
        {
            A[i] = A[i - 2] + 2 * B[i - 1];
            B[i] = A[i - 1] + B[i - 2];
        }

        return A[n];
    }

    public static void main(String args[])
    {
        new DynamicProgramming().nearestExit(
                new char[][]{{'+','+','+'},
                           {'.','.','.'},
                           {'+','+','+'}},new int[]{1,0});
        System.out.print(countWays(9));
        int set[] = {4, 1, 10, 12, 5, 2};//{ 2, 4, 8, 7 };
        int sum = 9;
        int n = set.length;
        if (isSubsetSum(set, n, sum) == true)
            System.out.println("Found a subset"
                + " with given sum");
        else
            System.out.println("No subset with"
                + " given sum");
       // System.out.println(binomialCoeff(9,5));
       // System.out.print(combination(9,5));
        /*int arr[] = {1,2,3};
        int m = arr.length;
        int n = 4;
        System.out.println(countWays(arr, m, n));
        int[][] ways = new int[m+1][n+1];
        for(int i =0; i<=m;i++)
            Arrays.fill(ways[i],-1);
        System.out.println(getWays(arr, n, m,ways));*/

    }
//    n s 0   1   2   3   4   5   6
//    0   t   f   f   f   f   f   f
// 2  1   t   f   t   f   f   f   f
// 4  2   t   f   t   f   t   f   t
// 8  3   t   f   t   f   t   f   t
// 7  4   t   f   t   f   t   f   T

    static boolean isSubsetSum(int[] set, int n , int sum){
        boolean[][] dp = new boolean[n+1][sum+1];
        if(sum == 0)
            return dp[n][sum] = true;
        if(n == 0)
            return dp[n][sum] = false;
        for(int i=0;i<=n;i++)
            dp[i][0] = true;
        for(int i=1;i<=sum;i++){
            dp[0][i] = false;
        }
        for(int i = 1;i<=n;i++){
            for(int j = 1; j<=sum; j++){
                if(set[i-1] == j)
                    dp[i][j] = true;
                else {
                    if(set[i-1] < j)
                        dp[i][j] = dp[i-1][j] || dp[i-1][j-set[i-1]];
                    else
                        dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];

    }

}
