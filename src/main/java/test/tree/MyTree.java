package test.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

class Node {
	int data;
	Node right;
	Node left;
	int val;

	public Node(int d, Node r, Node l) {
		data = d;
		right = r;
		left = l;
		val =d;
	}

	public Node(int d) {
		data = d;
		val = d;
	}
}

public class MyTree {
	Node root;
	
	public Node[] findPath(Node n){
		Node root = this.root;
		int height = getHeight(root);
		Node[] path = new Node[height];
		int now = 0;
		if(root == null)
			System.out.println("no nodes available");
		path[now] = root;
		Node top = root;
		boolean proceed = top==n? true:false;
		while(!proceed){
			if(top.left != null)
				proceed = checkPath(top.left,n,path,now+1);
			if(top.right != null && !proceed)
				proceed = checkPath(top.right,n,path,now+1);
		}
		print(path,n);
		return path;
	}

	private static boolean checkPath(Node top, Node n, Node[] path, int now) {
		boolean proceed = false;
		path[now] = top;
		if(top == n)
			return true;
		else{
			if(top.left != null)
				proceed = checkPath(top.left,n,path,now+1);
			if(top.right != null && !proceed)
				proceed = checkPath(top.right,n,path,now+1);
			return proceed;
		}

	}
	
	public static Node[] findPathStatic(Node root , Node n){
		int height = getHeight(root);
		Node[] path = new Node[height];
		int now = 0;
		if(root == null)
			System.out.println("no nodes available");
		path[now] = root;
		Node top = root;
		boolean proceed = top==n? true:false;
		while(!proceed){
			if(top.left != null)
				proceed = checkPath(top.left,n,path,now+1);
			if(top.right != null && !proceed)
				proceed = checkPath(top.right,n,path,now+1);
		}
		return path;
	}
	
	public static void spiral(Node root){
		boolean ltr = true;
		Queue<Node> q = new LinkedList<Node>();
		Stack<Node> s = new Stack<Node>();
		q.offer(root);
		q.offer(null);
		while(!q.isEmpty()){
			Node curr = q.poll();
			if(curr== null){
				if(q.isEmpty()){
					if(s.isEmpty())
						System.out.println("");
					else{
						printNodes(s);
						ltr = !ltr;
					}
				} else {
					if(!ltr)
						printNodes(s);
					q.add(null);
					ltr = !ltr;
					continue;
				}
			} else {
				if(ltr){
					System.out.print(curr.data+" ");
					if(curr.left != null)
						q.add(curr.left);
					if(curr.right != null)
						q.add(curr.right);
				} else {
					s.push(curr);
					if(curr.left != null)
						q.add(curr.left);
					if(curr.right != null)
						q.add(curr.right);
				}
			}
		}
	}
	
	private static void printNodes(Stack<Node> s) {
		s.stream().map(c->c.data).forEach(System.out::println);
		while(!s.isEmpty())
			System.out.print(s.pop().data+" ");
		
	}
	
	public static void kDistanceNodes(Node root , Node target , int k){
		if(root == target)
			kDistantNodeFromRoot(root, k , null);
		else {
			int dist = 0;
			Queue<Node> q = new LinkedList<Node>();
			q.add(root);
			q.add(null);
			while(!q.isEmpty()){
				Node n = q.poll();
				if(n == target){
					kDistantNodeFromRoot(n, k , null);
					break;
					}
				else if(n == null){
					q.add(null);
					dist++;
				} else {
					if(n.left != null)
						q.add(n.left);
					if(n.right != null)
						q.add(n.right);
				}
			}
			if(dist > 0){
				Node[] path = findPathStatic(root, target);
				for(int i=dist-1; i>0;i--){
					
				}
				kDistantNodeFromRoot(root, k-dist, target);
			}
		}
	}
	
	public static void kDistanceNodes2(Node root , Node target , int k){
		if(root == target)
			kDistantNodeFromRoot(root, k , null);
		else{
			String where = "";
			Map<Integer,ArrayList<Node>> mapper = new TreeMap<Integer,ArrayList<Node>>();
			ArrayList<Node> list = new ArrayList<Node>();
			list.add(root);
			mapper.put(0,list);
			int index = 0;
				index = findNode(root.left,index-1,target);
			if(index == 0){
				index = findNode(root.right, index+1,target);
			}
			else
				where = "left";
			int low = index-k;
			int high = index+k;
			if(where == "left"){
				mapNodes(root.left,-1,mapper,low,high);
				kDistantNodeFromRoot(root.right,k+index-1,target);
			}
			else{
				mapNodes(root.right,1,mapper,low,high);
				kDistantNodeFromRoot(root.left,k-index-1,target);
			}
			list = mapper.get(low);
			if(list != null){
			for(Node n : list)
				System.out.print(n.data+" ");
			}
			list = mapper.get(high);
			if(list != null){
			for(Node n : list)
				System.out.print(n.data+" ");
			}
		}	
	}
	
	private static void mapNodes(Node curr, int i, Map<Integer, ArrayList<Node>> mapper, int low, int high) {
		if(i>high || i<low){
			return;
		} else if(curr == null){
			return;
		} else {
			if(curr != null){
				ArrayList<Node> list = mapper.get(i) != null ? mapper.get(i) :new ArrayList<Node>();
				list.add(curr);
				mapper.put(i, list);
			}
			mapNodes(curr.left,i-1,mapper,low,high);
			mapNodes(curr.right,i+1,mapper,low,high);
		}
	}

	private static int findNode(Node curr, int i, Node target) {
		if(curr == null)
			return 0;
		if(target == curr)
			return i;
		int index = 0;
			index = findNode(curr.left,i-1,target);
		if(index == 0)
			index = findNode(curr.right, i+1,target);
		return index;
	}

	public static void kDistantNodeFromRoot(Node root , int k, Node target){
		if(root == target)
			return;
		if(root != null){
			if(k==0){
				System.out.print(root.data+" ");
				return;
			}
			kDistantNodeFromRoot(root.left, k-1, target);
			kDistantNodeFromRoot(root.right, k-1,target);
		}
	}
	public static int getMaximumSum(Node root){
		ArrayList<Integer> max = new ArrayList<Integer>();
		max.add(0);
		int left = getMaximumSumRecursive(root.left , max);
		int right = getMaximumSumRecursive(root.right,max);
		if(left+right+root.data > max.get(0))
			return left+right+root.data;
		else
			return max.get(0);
	}
	
	private static int getMaximumSumRecursive(Node node, ArrayList<Integer> max) {
		int left = 0;
		int right = 0;
		if(node == null)
			return 0;
		else{
			if(node.left != null)
				left = getMaximumSumRecursive(node.left , max);
			if(node.right != null)
				right = getMaximumSumRecursive(node.right , max);
			if(left+right+node.data > max.get(0))
				max.set(0, left+right+node.data);
			if(left>right)
				return left+node.data;
			else
				return right+node.data;
		}
	}

	public static Node lowestCommonAncestor(Node root, Node a, Node b) {
		if(root == null)
			return null;
		if(root.data == a.data || root.data == b.data )
			return root;
 
		Node left=lowestCommonAncestor(root.left,a,b);
		Node right=lowestCommonAncestor(root.right,a,b);
 
		// If we get left and right not null , it is lca for a and b
		if(left!=null && right!=null)
			return root;
		if(left== null)
			return right;
		else
			return left;
	}
	
	private void print(Node[] path,Node n) {
		for(Node i:path){
			if(i != null)
				System.out.print(i.data+" ");
			if(i==n)
				break;
		}
		System.out.println();
	}

	public void getVerticalSum(Node root) {
		TreeMap<Integer, Integer> sum = new TreeMap<Integer, Integer>();
		Node curr = root;
		int column = 0;
		if (curr != null) {
			int val = sum.get(column) != null ? sum.get(column) : 0;
			sum.put(column, val + curr.data);
			addToSum(curr.left, column - 1,sum);
			addToSum(curr.right, column + 1, sum);
		}
		for(Integer i: sum.keySet()){
			System.out.print(sum.get(i)+" ");
		}
	}

	private void addToSum(Node curr, int column, Map<Integer, Integer> sum) {
		if (curr != null) {
			int val = sum.get(column) != null ? sum.get(column) : 0;
			sum.put(column, val + curr.data);
			addToSum(curr.left, column - 1, sum);
			addToSum(curr.right, column + 1, sum);
		}
	}

	public static int getHeight(Node root){
		//int height = 0;
		if(root == null)
			return 0;
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		if(leftHeight > rightHeight)
			return leftHeight + 1;
		else
			return rightHeight + 1;
	}
	
	public int getHeightIterative(Node root){
		int height = 0;
		if(root == null)
			return 0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		q.add(null);
		while(!q.isEmpty()){
			Node top = q.poll();
			if(top == null){
				if(q.peek() != null)
					q.add(null);
				height++;
			} else {
				if(top.left != null)
					q.add(top.left);
				if(top.right != null)
					q.add(top.right);
			}
		}
		return height;
	}
	
	/*public static void main(String... s) {
		MyTree mt = new MyTree();
		mt.root = new Node(1);
		mt.root.left = new Node(2);
		mt.root.right = new Node(3);
		mt.root.left.left = new Node(7);
		mt.root.left.right = new Node(4);
		mt.root.right.left = new Node(5);
		mt.root.right.right = new Node(6);
		mt.root.right.right.right = new Node(12);
		mt.root.right.left.right = new Node(11);
		mt.root.right.left.right.left = new Node(14);
		mt.root.right.right.right.left = new Node(13);
		mt.root.left.left.left = new Node(8);
		mt.root.left.left.left.right = new Node(9);
		mt.root.left.right.right = new Node(15);
		mt.root.left.right.left = new Node(10);
		System.out.println("Maximum sum is :"+getMaximumSum(mt.root));
		System.out.println("Spiral is :");
		spiral(mt.root);
		*//*System.out.println("Vertical sum is");
		mt.getVerticalSum(mt.root);
		System.out.println();
		System.out.println("3 distant nodes :");
		kDistanceNodes(mt.root,mt.root.left.right.left, 5);*//*
		//BigDecimal bd = new BigDecimal(123);
		//System.out.println(bd.toPlainString().equals("123"));
		//System.out.println("Height is :"+mt.getHeight(mt.root));
		//System.out.println("Height is :"+mt.getHeightIterative(mt.root));
		//mt.findPath(mt.root.left.left.left.left);
		System.out.println("Pre order");
		mt.preOrder();
		System.out.println();
		System.out.println("Post order");
		mt.postOrder();
		System.out.println();
		System.out.println("In order");
		mt.inOrder();
		System.out.println();
		System.out.println("In order recursive");
		mt.inOrderRecursive(mt.root);
		System.out.println();
		System.out.println("Post order recursive");
		mt.postOrderRecursive(mt.root);
		System.out.println();
		System.out.println("LCA :"+ mt.lowestCommonAncestor(mt.root, new Node(4), new Node(8)).data);
	}*/

	private void postOrder() {
		Node root = this.root;
		if(root == null)
			return;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		Node prev = null;
		while(!stack.isEmpty()){
			Node top = stack.peek();
			if(top != null){
				if(top.left != null && top.left != prev && top.right != prev){
					stack.push(top.left);
				} else{
					if(top.right == null){
						System.out.print(top.data+" ");
						stack.pop();
					} else if (top.right == prev){
						System.out.print(top.data+" ");
						stack.pop();
					} else if(top.right != null)
						stack.push(top.right);
					}
				}
			prev = top;
			}
	}
		
	private void postOrder1() {
		// TODO Auto-generated method stub
		Node root = this.root;
		ArrayList<Node> visited = new ArrayList<Node>();
		if(root == null)
			return;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			boolean added = false;
			Node top = stack.peek();
			if(top != null && !visited.contains(top)){
				if(top.right != null && !visited.contains(top.right)){
					stack.push(top.right);
					added = true;
				}
				if(top.left != null && !visited.contains(top.left)){
					stack.push(top.left);
					added = true;
				}
				if(!added){
						System.out.print(top.data+" ");
						visited.add(top);
						stack.pop();
				}
			} else {
				stack.pop();
			}
		}
		
	}
	
	private void postOrderRecursive(Node r) {
		// TODO Auto-generated method stub
		Node curr = r;
		if(curr == null)
			return;
		postOrderRecursive(curr.left);
		postOrderRecursive(curr.right);
		System.out.print(curr.data+" ");
	}

	private void preOrder() {
		Node root = this.root;
		if(root == null)
			return;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node top = stack.pop();
			if(top != null){
				System.out.print(top.data+" ");
				if(top.right != null)
					stack.push(top.right);
				if(top.left != null)
					stack.push(top.left);
			}
		}
	}
	
	private void inOrderRecursive(Node r) {
		// TODO Auto-generated method stub
		Node curr = r;
		if(curr == null)
			return;
		inOrderRecursive(curr.left);
		System.out.print(curr.data+" ");
		inOrderRecursive(curr.right);
	}

	private void inOrder() {
		// TODO Auto-generated method stub
		Node top = this.root;
		if(root == null)
			return;
		Stack<Node> stack = new Stack<Node>();
		do{
			while(top != null){
					stack.push(top);
					top = top.left;
			}
			top = stack.pop();
			System.out.print(top.data+" ");
		//	stack.push(top.right);
			top = top.right;
		}while(top != null || !stack.isEmpty());
	}

	public static List<List<Integer>> pathSum(Node root, int targetSum) {
		List<List<Integer>> result = new ArrayList<>();
		if(root == null)
			return result;
		List<Integer> currentPath = new ArrayList<>();
		int remSum = targetSum - root.val;
		currentPath.add(root.val);
		if(remSum == 0 && root.left == null && root.right == null)
			result.add(currentPath);
		checkTree(root.left,remSum,result,currentPath);
		checkTree(root.right,remSum,result,currentPath);
		return result;
	}

	private static void checkTree(Node root ,int remsum, List result, List<Integer> path){
		if(root == null)
			return;
		int remSum = remsum - root.val;
		path.add(root.val);
		if(remSum == 0 && root.left == null && root.right == null){
			result.add(new ArrayList(path));
			path.remove(path.size()-1);
			return;
		}
		checkTree(root.left,remSum,result,path);
		checkTree(root.right,remSum,result,path);
		path.remove(path.size()-1);
	}

	public static void printLeftView(Node root){
		if(root == null)
			return;
		Stack<Integer> stack = new Stack<>();
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		Node prev = root;
		while(!q.isEmpty()){
			Node node = q.poll();
			if(node == null){
				if(q.peek() != null){
					q.add(null);
				} else
					break;
			} else {
				if(prev == null)
					stack.push(node.val);
				if(node.left != null)
					q.add(node.left);
				if(node.right != null)
					q.add(node.right);
			}
			prev = node;
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop()+" ");
	}

	public static void printRightView(Node root){
		if(root == null)
			return;
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		q.add(null);
		while(!q.isEmpty()){
			Node node = q.poll();
			if(node == null){
				if(q.peek() != null){
					q.add(null);
				} else
					break;
			} else {
				if(q.peek() == null){
					System.out.print(node.val+" ");
				}
				if(node.left != null)
					q.add(node.left);
				if(node.right != null)
					q.add(node.right);
			}
		}
	}

	public static void main(String[] args) {
		MyTree mt = new MyTree();
		mt.root = new Node(5);
		mt.root.left = new Node(4);
		mt.root.right = new Node(8);
		mt.root.left.left = new Node(11);
		mt.root.right.left = new Node(13);
		mt.root.right.right = new Node(4);
		mt.root.right.right.right = new Node(1);
		mt.root.right.right.left = new Node(5);
		mt.root.left.left.left = new Node(7);
		mt.root.left.left.left.right = new Node(20);
		mt.root.left.left.right = new Node(2);
		mt.postOrderRecursive(mt.root);
		printLeftView(mt.root);
		printRightView(mt.root);
	}
//				5
//			4		8
//		11		13		4
//	7       2		 5		1
//	  20
}
