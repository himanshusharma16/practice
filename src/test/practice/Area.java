package test.practice;

import java.util.*;

class Area{

    public static int maxArea(int A[], int len)
    {
        int l = 0;
        int r = len -1;
        int area = 0;

        while (l < r)
        {
            // Calculating the max area
            area = Math.max(area,
                    Math.min(A[l], A[r]) * (r - l));

            if (A[l] < A[r])
                l += 1;

            else
                r -= 1;
        }
        return area;
    }

    public int findPairs(int[] nums, int k) {
        int count = 0;
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> zeroset = new HashSet<>();
        for(int i : nums){
            if(k == 0){
                if(set.contains(i) && zeroset.add(i))
                    count++;
            }
            set.add(i);
        }
        if(k == 0)
            return count;
        for(int i : nums){
            set.remove(i);
            int a = i-k;
            int b = i+k;
            if(set.remove(a))
                count++;
            if(set.remove(b))
                count++;
        }
        return count;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i<nums.length;i++){
            if(nums[i]==nums[i+1])
                continue;
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                if(nums[j]+nums[k]+nums[i]==0){
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                } else {
                    if(nums[j]+nums[k]+nums[i]>0)
                        k--;
                    else
                        j++;
                }
            }
        }
        return result;
    }

    public void parseTags(String htmlCode){
        String[] arr = htmlCode.split("body>");
        for(String s : arr) {
            if (s.endsWith("</"))
                System.out.println(s);//.replace("</",""));
        }
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);//--4,-1,1,2
        HashMap<Integer,Integer> map = new HashMap<>();
        int start = 0,sum=0,mindiff =  Integer.MAX_VALUE;
        int end = nums.length-1;
        for(int i = 0; i< nums.length;i++){
            start = i+1;end = nums.length-1;
            while(start<end){
                int diff = target-nums[i];//5
                if(nums[start]+nums[end]==diff)
                    return nums[i]+nums[start]+nums[end];
                if(Math.abs(mindiff) > Math.abs((diff-(nums[start]+nums[end])))){
                    mindiff = diff-(nums[start]+nums[end]);//5
                    sum = nums[i]+nums[start]+nums[end];//
                }
                if(diff-nums[start]-nums[end]>0)
                    start++;
                else
                    end--;
            }

        }
        return sum;
    }
}
