package test.practice;

import java.util.*;
import test.practice.TreeNode;

public class SolutionTree {
    HashMap<TreeNode, Integer> depthMap = new HashMap<TreeNode, Integer>();
    Stack<TreeNode> stack = new Stack<TreeNode>();

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        stack.push(root);
        if (root.left != null)
            addToStack(root.left);
        if (root.right != null)
            addToStack(root.right);
        boolean isBalanced = true;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int depth = updateDepth(node);
            if (depth > 1 || -1 > depth) {
                isBalanced = false;
                break;
            }
        }
        return isBalanced;
    }

    public void addToStack(TreeNode node) {
        stack.push(node);
        if (node.left != null)
            addToStack(node.left);
        if (node.right != null)
            addToStack(node.right);
    }

    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> stack = new LinkedList<TreeNode>();
        stack.add(root);
        stack.add(null);
        int counter = 1;
        while(!stack.isEmpty()){
            TreeNode current = stack.poll();
            if(current == null){
                counter++;
                if(!stack.isEmpty())
                    stack.add(null);
                continue;
            }
            if(current.left == null && current.right == null)
                return counter;
            if(current.left != null)
                stack.add(current.left);
            if(current.right != null)
                stack.add(current.right);
        }
        return counter;
    }

    public List<Integer> getRow(int rowIndex) {
        Integer[][] pascal = new Integer[rowIndex+1][rowIndex+1];
        for(int i=0; i<=rowIndex;i++){
            pascal[rowIndex][i] = getValue(rowIndex,i,pascal);
        }
        return Arrays.asList(pascal[rowIndex]);
    }

    public int getValue(int row, int column, Integer[][] pascal){
        if(row == 0 || column == 0)
            return 1;
        if(row == column)
            return 1;
        if(row<column)
            return 0;
        if(pascal[row][column] == null){
            pascal[row][column] = getValue(row-1,column-1,pascal)+getValue(row-1,column,pascal);
        }
        return pascal[row][column];
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase().trim().replace(" ","");
        char[] arr = s.toCharArray();
        s = "";
        for(char c : arr){
            if('a'<=c && c<='z'){
                s = s.concat(Character.toString(c));
            }
        }
        StringBuffer sb = new StringBuffer(s);
        return sb.reverse().toString().equals(s);
    }

    public synchronized void deadlock2(){
        //assigning somthing

    }

    public int longestValidParentheses(String s) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            else {
                stack.pop();
                if (stack.isEmpty())
                    stack.push(i);
                else
                    answer = Math.max(answer, i - stack.peek());
            }
        }
        return answer;
    }

    public int updateDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (depthMap.get(root) != null)
            return depthMap.get(root);
        int leftDepth = updateDepth(root.left);
        int rightDepth = updateDepth(root.right);
        if(leftDepth > rightDepth)
            depthMap.put(root, leftDepth+1);
        else
            depthMap.put(root,rightDepth+1);
        return leftDepth - rightDepth;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        List<Integer> ll = new ArrayList<>();
        int i=0,j=0;
        while(m>0 && n>0){
            for(;j<m;j++)
                ll.add(matrix[i][j]);
            j--;
            m--;
            i++;
            for(;i<n;i++)
                ll.add(matrix[i][j]);
            i--;
            n--;
            for(int k=0;k<m;k++)
                ll.add(matrix[i][--j]);
            m--;
            n--;
            for(int k=0;k<n;k++)
                ll.add(matrix[--i][j]);
            j++;
        }
        return ll;
    }
}