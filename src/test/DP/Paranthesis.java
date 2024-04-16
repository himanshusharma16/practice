package test.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paranthesis {

    public static void main(String[] args) {
        System.out.println(new Paranthesis().generateParenthesis(5));
    }
    public List<String> generateParenthesis(int n) {
        List<?>[] dp = new List[n+1];
        dp[0] = Arrays.asList("");
        dp[1] = Arrays.asList("()");

        for(int i = 2 ; i <= n ; i++){
            int low = 0;
            int hi = i-1;
            List<String> ans = new ArrayList<>();
            while(hi >= 0){
                for(var si : dp[low]){
                    for(var so : dp[hi]){
                        StringBuilder sb = new StringBuilder("");
                        sb.append("(");
                        sb.append(si);
                        sb.append(")");
                        sb.append(so);
                        ans.add(sb.toString());
                    }
                }
                low++; hi--;
            }
            dp[i] = ans;
        }
        return (List<String>) dp[n];
    }
}
