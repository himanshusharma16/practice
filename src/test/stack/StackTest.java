package test.stack;

import java.util.Stack;

public class StackTest {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for(int i : asteroids){
            if(stack.isEmpty()){
                stack.push(i);
            } else {
                if(i < 0){
                    if(stack.peek() > 0){
                        while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() + i < 0)
                            stack.pop();
                        if(stack.isEmpty() || stack.peek() < 0){
                            stack.push(i);
                        } else if (stack.peek() + i == 0)
                            stack.pop();
                    } else {
                        stack.push(i);
                    }
                } else if(i >= 0){
                        stack.push(i);
                }
            }
        }

        int i = stack.size() - 1;
        int[] arr = new int[stack.size()];
        while(!stack.isEmpty()){
            arr[i--] = stack.pop();
        }
        return arr;
    }

    Stack<Integer> stack = new Stack<>();
    Integer min = null;

    public void push(int val) {
        int[][] arr = null;

        if(min == null)
            min = val;

        if(val >= min)
            stack.push(val);
        else {
            int newval = val - (min - val);
            min = val;
            stack.push(newval);
        }
    }

    public String decodeString(String s) {
        Stack<Object> stack = new Stack<>();
        StringBuilder curr = new StringBuilder("");
        int num = 0;
        for(char c : s.toCharArray()){
            if(c == '['){
                stack.push(num);
                num = 0;
            } else if (c >= 'a' && c <= 'z'){
                curr.append(c);
            } else if (c >= '0' && c <= '9'){
                if(curr.toString() != ""){
                    stack.push(curr);
                    curr = new StringBuilder("");
                }
                num = num*10 + (int)(c-'0');
            } else if (c == ']'){
                while(!stack.isEmpty()){
                    var obj = stack.pop();
                    if(obj instanceof Integer){
                        int val = (int) obj;
                        StringBuilder sb = new StringBuilder(curr);
                        while(val-- > 1){
                            sb = sb.append(curr);
                        }
                        curr = sb;
                        break;
                    } else {
                        curr = ((StringBuilder)obj).append(curr);
                    }
                }
            }
        }
        while(!stack.isEmpty()){
            curr = ((StringBuilder)stack.pop()).append(curr);
        }
        return curr.toString();
    }

    public static void main(String[] args) {
        new StackTest().decodeString("3[a]2[bc]");
        var st = new StackTest();
        st.push(2147483647);
        st.push(-2147483648);
    }

}
