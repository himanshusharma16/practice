package test.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TesterImpl implements Tester{
    @Override
    public int getInt() {
        return 0;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int size = nums.length;//5
        TreeNode root = new TreeNode();
        if(size < 2){
            if(size == 1)
                root.val = nums[0];
            return root;
        } else {
            root.val = nums[size / 2];//0
            addLeftTree(root, getSubArray(nums, 0, size / 2));
            addRightTree(root, getSubArray(nums, size / 2 + 1, size));
            return root;
        }
    }

    public int[] getSubArray(int[] arr,int beg, int end){
        int[] array = arr;
        array = Arrays.copyOfRange(array, beg, end);
        return array;
    }

    public void addLeftTree(TreeNode parent , int[] nums) {
        int size = nums.length;//2
        TreeNode root = new TreeNode();
        if(size < 2){
            if(size == 1)
                root.val = nums[0];
            else
                return;
        } else {
                root.val = nums[size/2];
                addLeftTree(root,getSubArray(nums, 0, size/2));
                addRightTree(root,getSubArray(nums, size/2 +1, size));
            }
        parent.left = root;
    }

    public void addRightTree(TreeNode parent , int[] nums) {
        int size = nums.length;
        TreeNode root = new TreeNode();
        if(size < 2){
            if(size == 1)
                root.val = nums[0];
            else
                return;
        } else {
                root.val = nums[size/2];
                addLeftTree(root,getSubArray(nums, 0, size/2));
                addRightTree(root,getSubArray(nums, size/2 +1, size));
        }
        parent.right = root;
    }


    public void duplicateZeros(int[] arr){
        int len = arr.length;
        boolean swap = false;
        int next = 0;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]==0){
                for(int j=arr.length-1;j>i;j--){
                    arr[j]=arr[j-1];
                }
                i++;
            }

        }
        for(int val : arr){
            System.out.print(val+",");
        }
    }

    public void product(){
        int[] arr = new int[]{0,2,0,4};
        int product = 1;
        boolean isZero = false;
        for(int i : arr){
            if(i != 0)
                product = product * i;
            else
                isZero = true;
        }
        int[] result = new int[arr.length];
        for(int i = 0;i<arr.length ; i++){
            if(!isZero)
                result[i] = product/arr[i];
            else {
                if(arr[i] == 0)
                    result[i] = product;
                else
                    result[i] = 0;
            }
        }
        for(int i : result){
            System.out.println(i);
        }
    }
    public boolean getValue() {
        return Tester.super.getValue();
    }

    public String longestCommonPrefix(String[] strs) {
        String pre = "";
        int i =1;
        while(i<strs[0].length()){
            String temp = "";
            temp = strs[0].substring(0,i++);
            boolean present = true;
            for(int j = 1; j<strs.length; j++){
                if(!strs[j].startsWith(temp))
                    present = false;
            }
            if(present)
                pre = temp;
        }
        return pre;
    }

    public String countAndSay(int n) {
        String[] arr = new String[30];
        arr[1] = "1";
        arr[2] = "11";
        if(n<=2)
            return arr[n];
        return getn(arr,n-1);
    }

    public String getn(String[] arr, int n){
        if(null != arr[n] && !arr[n].isBlank())
            return say(arr[n]);
        arr[n-1] = getn(arr,n-1);
        return say(arr[n-1]);
    }

    public String simplifyPath(String path) {
        StringBuilder ss = new StringBuilder("");
        String[] arr = path.split("/");
        String last = "";
        for(String s:arr){
            if(s.isBlank())
                continue;
            else if(s.equals("."))
                continue;
            else if(s.equals("..")){
                if(ss.length() == 0)
                    continue;
                ss = new StringBuilder(ss.substring(0,ss.lastIndexOf("/")));
                last = s;
                continue;
            }
            ss.append("/");
            ss.append(s);
            last = s;
        }
        if(ss.toString().isEmpty())
            return "/";
        return ss.toString();
    }

    public String say(String str){
        char prev = str.charAt(0);
        int count = 1,i=0;
        StringBuffer result = new StringBuffer("");
        while(i<str.length()-1){
            char current = str.charAt(++i);
            if(current == prev)
                count++;
            else {
                result.append(count).append(Character.toString(prev));
                count = 1;
                prev = current;
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
      //  new TesterImpl().simplifyPath("/a/./b/../../c/");
        System.out.println( "10 rd bell number = "+new Solution().nthBellNumber(10));
       // System.out.println( "Binomial coefficient = "+new Solution().binomialCoefficient(5,2));

        /*new Solution().test();
        System.out.println(Tester.getName());
        List<String> list = new ArrayList<>();
        //testStream();
        //new Solution().findMissing(new int[]{1,4,5,6,2,7,0});
        //new TesterImpl().sortedArrayToBST(new int[]{-10,-3,0,5,9});//longestCommonPrefix(new String[]{"a"});
        *//*new TesterImpl().duplicateZeros(new int[]{0,0,1,0,3,4});
        List<Integer> res = new Solution().findAnagrams("cbaebabacd","abc");
        for(int i : res)
            System.out.println(i);*//*
        TreeNode four = new TreeNode(4,null,null);
        TreeNode foufour = new TreeNode(4,null,null);
        TreeNode three = new TreeNode(3,four,foufour);
        TreeNode threethree = new TreeNode(3,null,null);
        TreeNode two = new TreeNode(2,three,threethree);
        TreeNode twotwo = new TreeNode(2,null,null);
        TreeNode root = new TreeNode(1,two,twotwo);
       // System.out.println(new SolutionTree().spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
       // new Area().threeSumClosest(new int[]{1,1,1,0},-100);
                //.parseTags("<html><head>this is head</head><body><p>this is para 1</p><p>this is para 2</p>" +
          //      "<h1> this is heading 1</h1><p> this is para 3</p></body></html>");
        System.out.println(new Solution().reverseWords("a good   example"));*/
        System.out.println(new Solution().maxSum(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}));
    }

    private void testStream() {
        ArrayList<String> ll = new ArrayList<>();
        ll.add("male");
        ll.add("female");
        ll.add("male");
        ll.add("female");
        ll.add("male");
        ll.add("female");
        ll.add("male");
        ll.add("female");
        long mcount=0,fcount = 0;
        mcount = ll.stream().filter(i->i.equalsIgnoreCase("male")).count();
        fcount = ll.size()-mcount;
        Stream<Integer> s = ll.stream().map(i->i.length());
        Stream<Integer> s1 = s.filter(i->i<6);
        s1.distinct().collect(Collectors.toList());
        // ll1.stream().peek(ll1::add).collect(Collectors.toMap(TreeNode::getValue,c->c));
    }
}
