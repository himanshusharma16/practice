package main.java.practice.array;

//10225104053816548

import java.util.*;
import test.practice.TreeNode;

public class ArrayTest {


    public int snakesAndLadders(int[][] board) {
        HashMap<Integer,int[]> map = new HashMap<>();
        populateMap(map,board.length);

        int step = 0, dest = board.length*board.length;
        Set<Integer> next = new HashSet<>();
        next.add(1);
        Set<Integer> visited = new HashSet<>();
        visited.add(1);

        while(!next.isEmpty()){
            if(next.contains(dest))
                return step;
            else {
                Set<Integer> nextMove = new HashSet<>();
                for(int curr : next){
                    for(int i = 1; i < 7; i++){
                        int nextStep = Math.min(i+curr,dest);
                        int[] coord = map.get(nextStep);
                        if(board[coord[0]][coord[1]] != -1)
                            nextStep = board[coord[0]][coord[1]];
                        if(visited.add(nextStep))
                            nextMove.add(nextStep);
                    }
                }
                step++;
                next = nextMove;
            }
        }
        return -1;
    }

    private void populateMap(HashMap<Integer,int[]> map, int n){
        for(int i = 1; i <= n*n;){
            for(int row = n-1; row >= 0;){
                for(int col = 0; col < n ; col++)
                    map.put(i++,new int[]{row,col});

                row--;
                if(row < 0 || i > n*n)
                    break;
                for(int col = n-1; col>= 0;col--)
                    map.put(i++,new int[]{row,col});
                row--;
            }
        }
    }

    public int totalStepsDp(int[] nums) {
        Set m = new HashSet();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i< nums.length; i++){
            int curr = nums[i];
            if(stack.isEmpty()){
                stack.push(i);
            } else {
                while(!stack.isEmpty() && nums[stack.peek()] <= curr){
                    int top = stack.pop();
                    int diff = i - top;
                    nums[top] = diff;
                }
                stack.push(i);
            }
        }

        while(!stack.isEmpty()){
            int top = stack.pop();
            nums[top] = nums.length - top -1;
        }

        return Arrays.stream(nums).max().getAsInt() - 1;
    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] cl = new int[s.length()];
        char[] sarr = s.toCharArray();
        Stack<Integer> stack = new Stack();
        int count = 0;
        int tillNow = 0;
        for(int i = 0 ; i< sarr.length; i++){
            if(sarr[i] == '*') {
                count++;
                stack.push(i);
            } else {
                tillNow = count;
                cl[i] = count;
                while(!stack.isEmpty())
                    cl[stack.pop()] = count;
            }
        }
        while(!stack.isEmpty())
            cl[stack.pop()] = tillNow;

        int[] ans = new int[queries.length];
        int c = 0;
        for(var q : queries){
            ans[c++] = cl[q[1]] - cl[q[0]] < 0 ? 0 : cl[q[1]] - cl[q[0]];
        }
        return ans;
    }

    public TreeNode recoverFromPreorder(String traversal) {

        if(traversal == null || traversal == "")
            return new TreeNode();
        int ind = traversal.indexOf('-');
        TreeNode root = new TreeNode(Integer.parseInt(traversal.substring(0,ind)));
        TreeNode curr = root;
        boolean left = false, right = false;
        for(int i = 1 ; i < traversal.length() ; i++){
            char c = traversal.charAt(i);
            if(c == '-' && traversal.charAt(i+1) != '-'){
                if(curr.left == null)
                    left = true;
                else
                    right = true;
            } else if (c == '-' && traversal.charAt(i+1) == '-'){
                if(curr.right != null)
                    curr = curr.right;
                else
                    curr = curr.left;
            } else {
                int cint = (int)c-'0';
                while(i+1 < traversal.length() && traversal.charAt(i+1) != '-'){
                    cint = cint*10 + ((int)traversal.charAt(++i) - '0');
                }
                if(left) {
                    curr.left = new TreeNode(cint);
                    left = false;
                } else {
                    curr.right = new TreeNode(cint);
                    right = false;
                }
                curr = root;
            }
        }
        return root;
    }

    public boolean equalFrequency(String word) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(var c : word.toCharArray()){
            map.put(c , map.getOrDefault(c,0)+1);
        }
        return check(map, word.charAt(0));
    }

    private boolean check(Map<Character,Integer> map, char c){

        HashMap<Integer,Integer> set = new HashMap<>();
        for(var e : map.entrySet()){
            set.put(e.getValue(),set.getOrDefault(e.getValue(),0)+1);
        }
        if(set.size() > 2)
            return false;

        if(set.size() == 1 && map.get(c) != 1)
            return false;

        int count = 0;
        int onetype = 0 , twotype = 0;
        for(var e : set.entrySet()){
            if(e.getValue() > 1)
                count++;
            if(onetype == 0)
                onetype = e.getKey();
            else if (twotype == 0)
                twotype = e.getKey();
        }

        if(count > 1){
            return false;
        } else {
            return Math.abs(onetype-twotype) == 1;
        }

    }

    public int[] sortDutchFlag(int[] arr){
        int lo = 0 , mid = 0 , hi = arr.length-1;
        while(mid <= hi){
            if(arr[mid] == 0){
                int temp = arr[mid];
                arr[mid] = arr[lo];
                arr[lo] = temp;
                lo++;
            } else if(arr[mid] == 1){
                mid++;
            } else {
                int temp = arr[mid];
                arr[mid] = arr[hi];
                arr[hi] = temp;
                hi--;
            }
        }
        return arr;
    }

    public int largestRectangleArea(int[] heights) {
        int[] smer = new int[heights.length];

        Stack<Integer> stack = new Stack<Integer>();

        stack.push(heights.length-1);
        smer[heights.length-1] = heights.length;

        for(int i = heights.length-2; i >= 0 ; i--){
            int curr = heights[i];
            while(!stack.isEmpty() && heights[stack.peek()] > curr){
                stack.pop();
            }
            if(stack.isEmpty())
                smer[i] = heights.length;
            else
                smer[i] = stack.peek();

            stack.push(i);
        }
        stack.clear();

        int[] smel = new int[heights.length];
        smel[0] = -1;

        for(int i = 1 ; i < heights.length; i++){
            int curr = heights[i];
            while(!stack.isEmpty() && heights[stack.peek()] > curr)
                stack.pop();

            if(stack.isEmpty())
                smer[i] = -1;
            else
                smer[i] = stack.peek();

            stack.push(i);
        }

        int max = 0;
        for(int i = 0; i < heights.length ; i++){
            int leftBndry = smel[i];
            int rghtBndry = smer[i];
            int area = heights[i] * (rghtBndry - leftBndry -1);
            max = Math.max(max,area);
        }
        return max;
    }

    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        int[] histo = new int[matrix[0].length];
        for(int i = 0; i< matrix.length; i++){
            for(int j = 0 ; j < matrix[0].length; j++){
                histo[j] += Integer.parseInt(String.valueOf(matrix[i][j]));
            }
            max = Math.max(max,largestRectangleHistogram(histo));
        }
        return max;
    }

    public int largestRectangleHistogram(int[] heights) {
        int[] smer = new int[heights.length];

        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < heights.length; i++) {
            int curr = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > curr)
                smer[stack.pop()] = i;
            stack.push(i);
        }
        while (!stack.isEmpty())
            smer[stack.pop()] = heights.length;

        int[] smel = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            int curr = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > curr)
                smel[stack.pop()] = i;
            stack.push(i);
        }
        while (!stack.isEmpty())
            smel[stack.pop()] = -1;

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int leftBndry = smel[i];
            int rghtBndry = smer[i];
            int area = heights[i] * (rghtBndry - leftBndry - 1);
            max = Math.max(max, area);
        }
        return max;
    }

        class Node{
        int data;
        Node next;
        Node prev;
        Node(int d){
            this.data = d;
        }
    }

    public int maxSubarraySumCircular(int[] nums) {
        int [] num = new int[nums.length*2];
        for(int i = 0 ; i < num.length; i++)
            num[i] = nums[i%nums.length];

        int len = 1 , max = num[0], maxTillNow = num[0], start = 0;
        for(int i = 1 ; i < num.length; i++){
            if(len == nums.length){
                int rem = num[start++];
                maxTillNow -= rem;
                len--;
                while(start < i) {
                    rem = num[start++];
                    if(rem < 0) {
                        maxTillNow -= rem;
                        len--;
                    }
                    else {
                        start--;
                        break;
                    }
                }
            }

            int curr = num[i];
            maxTillNow += curr;
            if(maxTillNow < curr ){
                start = i;
                maxTillNow = curr;
                len = 0;
            }
            len++;
            max = Math.max(max,maxTillNow);
        }
        return max;
    }

    public int[][] merge(int[][] intervals){
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0]; int end = intervals[0][1];
        for(int i = 1 ; i < intervals.length; i++){
            int prevEnd = intervals[i-1][1];
            int thisStart = intervals[i][0];
            if(thisStart <= prevEnd){
                end = Math.max(intervals[i][1],end); //this End
            } else {
                list.add(new int[]{start,end});
                start = thisStart;
                end = intervals[i][1]; //this end
            }
        }
        list.add(new int[]{start,end});
        int[][] ans = new int[list.size()][2];
        return list.toArray(ans);
    }
    public static void main(String[] args) {
        int[][] intervals = new int[][]{
                {2,3},{4,7},{8,9},{1,10}
        };
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b){
                return a[0]-b[0];
            }
        });
        System.out.println(new ArrayTest().snakesAndLadders(new int[][]{{-1,4,-1},{6,2,6},{-1,3,-1}}));
        System.out.print(new ArrayTest().fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"},16));
        System.out.println(new ArrayTest().totalStepsDp(new int[]{7,14,4,14,13,2,6,13}));
    }
    public int totalSteps(int[] nums) {
        LinkedList<Node> list = new LinkedList<>();
        convertToList(list,nums);

        boolean changes = false; int count = 0;
        while(!changes){
            Node curr = list.getLast();
            while(curr != null){
                if(curr.prev != null && curr.data < curr.prev.data){
                    curr.prev.next = curr.next;
                    if(curr.next != null)
                        curr.next.prev = curr.prev;
                    list.remove(curr);
                    changes = true;
                }
                curr = curr.prev;
            }
            if(changes){
                count++;
                changes = false;
            } else
                changes = true;
        }
        return count;
    }

    private void convertToList(LinkedList<Node> list, int[] nums) {
        Node last = null;
        for(int i : nums){

            Node node = new Node(i);
            if(last != null)
                last.next = node;
            node.prev = last;
            list.addLast(node);
            last = node;
        }
    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        int totalSum = 0;
        boolean isNegative = false;
        for(int i : arr){
            if( i < 0)
                isNegative = true;
            totalSum += i;
        }

        if(!isNegative)
            return totalSum * k ;

        int maxSum = 0;
        if(k > 1){
            int[] arrarr = new int[arr.length * 2];
            int i = 0;
            for(int j = 0 ; j < arr.length; j++,i++)
                arrarr[i] = arr[j];
            for(int j = 0 ; j < arr.length; j++,i++)
                arrarr[i] = arr[j];
            maxSum = getMaxSum(arrarr);
        } else {
            maxSum = getMaxSum(arr);
        }

        return Math.max(maxSum , totalSum * k);
    }

    public int firstMissingPositive(int[] nums) {

        for(int i = 0 ; i< nums.length; i++){
            if(nums[i] < 0 || nums[i] > nums.length)
                nums[i] = 0;
        }
        for(int i = 0 ; i< nums.length; i++){
            int curr = nums[i];
            if(curr == 0)
                continue;

            curr = Math.abs(curr);
            if(nums[curr-1] > 0)
                nums[curr-1] *= -1;
        }

        for(int i =0 ; i< nums.length; i++){
            if(nums[i] > 0)
                return i+1;
        }
        return nums.length+1;
    }

    private int getMaxSum(int[] arr){
        int s = 0 , e = 1, sum = arr[0], maxsum = arr[0] > 0 ? arr[0] : 0;
        while(e < arr.length) {
            int n = arr[e];
            if(sum + n > 0){
                sum += n;
                e++;
            } else {
                sum -= arr[s++];
            }
            if(s == e)
                e++;
            maxsum = Math.max(maxsum , sum);
        }
        return maxsum;
    }

    public String reverseWords(String s) {
        String[] arr = s.trim().split(" ");
        for(int i = 0 ; i < arr.length; i++){
            String temp = arr[i];
            arr[i] = arr[arr.length -1 - i];
            arr[arr.length - 1 - i] = temp;
        }

        return makeString(arr);
    }

    public String makeString(String[] arr){
        StringBuilder sb = new StringBuilder("");
        for(int i = 0 ; i < arr.length; i++){
            if(arr[i].trim() != "")
                sb.append(arr[i].trim()).append(" ");
        }

        return sb.toString().trim();
    }

    public int findMinArrowShots1(int[][] points) {
        Arrays.sort(points, (a,b) -> (a[1] - b[1]));

        int count = 0 , start = points[0][0] , end = points[0][1];
        for(int i = 1 ; i < points.length; i++){
            if(points[i][0] <= end){
                start = Math.max(start,points[i][0]);
                end = Math.min(end,points[i][1]);
            } else {
                count++;
                start = points[i][0];
                end = points[i][1];
            }
        }
        return count+1;
    }

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a,b)->(Integer.compare(a[0],b[0])));

        int c = 1, last = points[0][1];
        for(int i = 1 ; i < points.length ; i++){
            if(points[i][0] <= last){
                continue;
            } else {
                last = points[i][1];
                c++;
            }
        }
        return c;
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int count = 0;
        int wordCount = 0;
        int startWord = 0;
        for(int i = 0 ; i < words.length; i++){
            String word = words[i];

            if(count+word.length() > maxWidth){
                i--;
                StringBuilder sb = new StringBuilder("");
                List<String> event = new ArrayList<>();
                for(;wordCount > 0;wordCount--){
                    event.add(words[startWord++]+" ");
                }
                int j = 0;
                while(count<maxWidth){
                    int index = j % event.size();
                    event.set(index,event.get(index)+" ");
                    count++;
                    j++;
                }
                event.stream().forEach(sb::append);
                res.add(sb.toString());
                wordCount = 0;
                count = 0;
            } else {
                count += word.length()+1;
                wordCount++;
            }
        }
        if(wordCount > 1) {
            StringBuilder sb = new StringBuilder("");
            List<String> event = new ArrayList<>();
            for(;wordCount > 0;wordCount--){
                event.add(words[startWord++]+" ");
            }
            int i = 0;
            while(count<maxWidth){
                int index = i % event.size();
                event.set(index,event.get(index)+" ");
                count++;
                i++;
            }
            event.stream().forEach(sb::append);
            res.add(sb.toString());
        } else if (wordCount == 1) {
            StringBuilder sb = new StringBuilder(words[words.length -1]);
            for(int j = 0 ; j < maxWidth ; j++){
                sb.append(" ");
            }
            res.add(sb.substring(0,maxWidth));
        }

        return res;
    }
}
