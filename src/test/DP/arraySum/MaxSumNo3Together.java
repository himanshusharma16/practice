package test.DP.arraySum;

//Given a sequence of positive numbers, find the maximum sum that can be formed which has no three consecutive elements present.
//    Examples :
//
//    Input: arr[] = {1, 2, 3}
//    Output: 5
//    We can't take three of them, so answer is
//    2 + 3 = 5
//
//    Input: arr[] = {3000, 2000, 1000, 3, 10}
//    Output: 5013
//    3000 + 2000 + 3 + 10 = 5013
//
//    Input: arr[] = {100, 1000, 100, 1000, 1}
//    Output: 2101
//    100 + 1000 + 1000 + 1 = 2101
public class MaxSumNo3Together {

    //3 cases
    //sum[3] = max(sum[2],sum[1]+arr[3],sum[0]+arr[2]+arr[3]);
}
