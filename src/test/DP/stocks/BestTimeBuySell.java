package test.DP.stocks;

import java.util.Stack;

public class BestTimeBuySell {

    public static void main(String[] args) {
        int[] prices = { 20,18,17,14,11,9 };
        System.out.println("Maximum profit value is : " + getMaxProfit(prices));
        System.out.println("Maximum profit value is : " + getMaxProfitStack(prices));
    }

    private static int getMaxProfit(final int[] prices) {
        int[] profit = new int[prices.length];
        profit[0] = Integer.MIN_VALUE;
        int min = prices[0];
        int maxp = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                profit[i] = prices[i] - min;
            } else {
                profit[i] = prices[i] - min;
                min = prices[i];
            }
            maxp = Math.max(maxp, profit[i]);
        }
        return maxp;
    }

    private static int getMaxProfitStack(final int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int maxp = Integer.MIN_VALUE;
        for (int curr : prices) {
            if (stack.isEmpty())
                stack.push(curr);
            else {
                int top = stack.peek();
                if (curr - top > maxp)
                    maxp = curr - top;
                if (curr < top) {
                    stack.pop();
                    stack.push(curr);
                }
            }
        }
        return maxp;
    }
}
