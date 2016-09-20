package com.leetcode.solution;


//Maximum Depth of Binary Tree

/*Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along 
the longest path from the root node down to the farthest leaf node.*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
		val = x;
	}
}
public class Quesiton104 {
	
	public int maxDepth(TreeNode root) {
		if(root == null) return 0;
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
        return leftDepth>rightDepth?leftDepth+1:rightDepth+1;
    }
	
	public static void main(String[] args){
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		
		t1.left = t2;
		t2.right = t3;
		t3.right = t4;
		System.out.println(new Quesiton104().maxDepth(t1));
		System.out.println((1&1)<<1);
	}
}
