package test;

import java.util.Stack;

public class LinkedList {

    static class Node{
        Node next;
        int data;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private static void printNextGreater(int[] arr){ //13-7-6-12
        Stack<Integer> stack = new Stack<>();
        int size = arr.length;
        if(size == 0)
            return;
        stack.push(arr[0]);//13
        for(int i = 1;i<size;i++){
            int curr = arr[i];//7
            int top = stack.peek();
            if(curr <= top)
                stack.push(curr);
            else {
                while(!stack.isEmpty() && stack.peek() < curr){
                    top = stack.pop();
                    System.out.println(top +"->"+curr);
                }
                stack.push(curr);
            }
        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop()+"-> -1");
        }
    }

    static Node result = null;

    private static Node reverseRecursion(Node head){
        if(head == null || head.next == null) {
            result = head;
            return head;
        }
        reverseRecursion(head.next).next = head;
        return head;
    }

    private static Node reverse(Node head){
        reverseRecursion(head);
        head.next = null;
        return result;
    }

    static Node tt = null,th = null;
    //1-2-3-4-5-6-7-8
    private  static Node reverseInK(Node head, int k){
        Node oh = null;
        Node ot = null;
        Node curr = head;
        while(curr != null){
            tt = null;
            th = null;
            int i = 0;
            while(curr != null && i++ < k) {
                Node fow = curr.next;
                curr.next=null;
                addToHead(curr);
                curr = fow;
            }
            if(oh == null){
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }
        }
        return oh;
    }

    private static void addToHead(Node curr) {
        if(th == null){
            th = tt = curr;
        } else {
            curr.next = th;
            th = curr;
        }
    }

    private static Node reverse1(Node head){
        if(head == null || head.next == null)
            return head;
        Node curr = head;//1
        Node prev = null;
        Node forw = null;
        while(curr != null){
            forw = curr.next;//4
            curr.next = prev;//3-2-1-null
            prev = curr;//2
            curr = forw;//3
        }
        return prev;
    }

    public static void main(String[] args) {
       /* int[] arr = {11,13,3,21};
        printNextGreater(arr);*/
        Node eight = new Node(8,null);
        Node seven = new Node(7,eight);
        Node six = new Node(6,seven);
        Node five = new Node(5,six);
        Node four = new Node(4,five);
        Node three = new Node(3,four);
        Node two = new Node(2,three);
        Node one = new Node(1,two);
//        Node result = reverse(one);
//        printList(result);
//        System.out.println();
        Node rev = reverseInK(one,3);
        printList(rev);
    }

    private static void printList(Node result) {
        while(result != null) {
            System.out.print(result.data+"->");
            result = result.next;
        }
    }
}
