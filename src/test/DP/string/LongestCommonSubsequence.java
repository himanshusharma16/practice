package test.DP.string;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.Map;

//Given two sequences, print the longest subsequence present in both of them.
//
//    Examples:
//
//    LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
//    LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        new LongestCommonSubsequence().minWindow("ADOBECODEBANC","ABC");
        System.out.print(LongestCommonSubsequence.lcsdp("ABCDGEFGOH", "AEDFEOHR"));
        Random ranmd = new Random(6);

        var i = ranmd.nextInt();
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase().trim();
        if(s.isEmpty())
            return true;

        int left = 0 , right = s.length() - 1;
        while(left < right){
            char ll = ' ';
            while(left < right && (s.charAt(left) < 'a' || 'z' < s.charAt(left)))
                left++;
            ll = s.charAt(left);
            char rr = ' ';
            while(right > left && (s.charAt(right) < 'a' || 'z' < s.charAt(right)))
                right--;
            rr = s.charAt(right);
            if(left > right)
                return true;
            if(ll != rr)
                return false;
            right--; left++;
        }
        return true;
    }

    public String minWindow(String s, String t) {
        if(t.length() > s.length())
            return "";

        Map<Character,Integer> tmap = new HashMap<>();
        for(char c : t.toCharArray())
            tmap.put(c,tmap.getOrDefault(c,0)+1);

        int total = t.length();
        String min = "";

        for(char c : s.substring(0,t.length()).toCharArray()){
            if(tmap.get(c) != null){
                int val = tmap.get(c);
                if(val > 0)
                    total--;
                tmap.put(c,val-1);
            }
        }
        if(total == 0)
            return s.substring(0,t.length());

        int start = 0 , end = t.length();

        while(end < s.length()){
            char c = s.charAt(end);
            if(tmap.get(c) != null){
                int val = tmap.get(c);
                if(val > 0)
                    total--;
                tmap.put(c,val-1);
            }
            while(total == 0 && start < end){
                if(min.equals("") || min.length() > end-start)
                    min = s.substring(start,end+1);
                char cl = s.charAt(start++);
                if(tmap.get(cl) != null){
                    int val = tmap.get(cl) + 1;
                    if(val > 0)
                        total++;
                    tmap.put(cl,val);
                }
            }
            end++;
        }
        return min;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,Integer> indexMap = new HashMap<>();
        int i = 0;
        for(var ll : equations){
            if(indexMap.get(ll.get(0)) == null)
                indexMap.put(ll.get(0),i++);
            if(indexMap.get(ll.get(1)) == null)
                indexMap.put(ll.get(1),i++);;
        }
        double[][] val = populateValues(equations,indexMap,values);

        double[] ans = new double[queries.size()];
        for(i = 0 ; i < queries.size() ; i++){
            var eq = queries.get(i);
            if(indexMap.get(eq.get(0)) == null || indexMap.get(eq.get(1)) == null)
                ans[i] = -1.0;
            else {
                ans[i] = solve(indexMap.get(eq.get(0)), indexMap.get(eq.get(1)),indexMap,val,new HashSet<Integer>());
            }
        }

        return ans;
    }

    public int minEatingSpeed(int[] piles, int hours) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer,Integer> fmap = new HashMap<>();
        long total = 0 , h = hours;
        for(int i : piles){
            pq.offer(i);
            total += i;
            fmap.put(i,fmap.getOrDefault(i,0)+1);
        }

        if(h == piles.length)
            return pq.poll();

        /*if(piles.length == 1){
            return (piles[0] + h - 1) / h;
        }*/

        h = h - piles.length;

        int ps = 0;
        while(h > 0){
            int max = pq.poll();
            int freq = fmap.get(max);

            if(freq > h){
                ps = max;
                break;
            }

            int newm = (max+1)/2;

            h -= freq;
            fmap.put(max,0);
            fmap.put(newm, fmap.getOrDefault(newm,0) + freq*2);
            pq.offer(newm);
        }

        if(h == 0)
            ps = pq.poll();

        //check other values
        int low = 0 , hi = ps;
        int mid = 0;
        while(low < hi){
            mid = (hi+ low) / 2;
            if(mid * hours >= total){
                int t = 0;
                for(int i : piles){
                    if(i < mid)
                        t++;
                    else {
                        t += (i + mid - 1)/mid;
                    }
                }
                if(t <= hours) {
                    ps = mid;
                    hi = mid-1;
                } else
                    low = mid+1;
            } else {
                low = mid+1;
            }
        }
        return ps;
    }

    private double solve(int s, int d, Map<String,Integer> map, double[][] val, Set<Integer> visited){
        if(val[s][d] != 0.0)
            return val[s][d];
        else if (val[d][s] != 0.0)
            return 1.0 / val[d][s];
        else if (s == d)
            return 1.0;
        else {
            var dd = val[s];
            visited.add(s);
            for(int i = 0 ; i < dd.length; i++){
                int dest = i;
                if(dd[dest] != 0.0 && !visited.contains(dest)){
                    double ans = solve(dest,d,map,val,visited);
                    if(ans != -1.0)
                        return dd[i] * ans;
                }
            }
            visited.remove(s);
            return -1.0;
        }
    }

    private double[][] populateValues(List<List<String>> equations, Map<String,Integer> index, double[] values){
        double[][] val = new double[index.size()][index.size()];

        for(int i = 0 ; i < values.length; i++){
            var eq = equations.get(i);
            double v = values[i];
            val[index.get(eq.get(0))][index.get(eq.get(1))] = v;
            val[index.get(eq.get(1))][index.get(eq.get(0))] = 1.0 / v;
        }

        return val;
    }

    private static int lcs(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 == 0 || len2 == 0)
            return 0;
        if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1))
            return lcs(s1.substring(0, len1 - 1), s2.substring(0, len2 - 1)) + 1;
        else
            return Math.max(lcs(s1.substring(0, len1), s2.substring(0, len2 - 1)),
                lcs(s1.substring(0, len1 - 1), s2.substring(0, len2)));
    }

    private static int lcsdp(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();
        int[][] dp = new int[len1 + 1][len2 + 1];
        //get the length
        for(int i = 1;i<=len1;i++){
            for(int j = 1; j<=len2;j++){
                if(s1c[i-1] == s2c[j-1])
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        //get the subsequence
        String sb = "";
        int row = len1,col = len2;
        while(row>0 && col > 0){
            if(s1c[row-1]==s2c[col-1]){
                sb = s1c[row-1] + sb;
                row--; col--;
            } else {
                if(dp[row-1][col] > dp[row][col-1])
                    row--;
                else
                    col--;
            }
        }
        System.out.println(sb);
        return dp[len1][len2];
    }
}
