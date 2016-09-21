package com.leetcode.solution;

//Same Tree

/*Given two binary trees, write a function to check if they are equal or not.
[10,5,15]
   10
   / \
  5   15
     10
    / \
   5   null
  / \
 null 15 
[10,5,null,null,15]
Two binary trees are considered equal if they are structurally identical
and the nodes have the same value.*/

public class Question100 {
	
	public boolean isSameTree(TreeNode100 p, TreeNode100 q) {
		
		if(p == null && q == null){
			return true;
		}
		if(q == null) return false;
		if(p == null) return false;
		if(p.val != q.val){
			return false;
		}
//		isSameTree(p.left, q.left);
//		isSameTree(p.right,q.right);
		return isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode100 t1 = new TreeNode100(10);
		TreeNode100 t2 = new TreeNode100(10);
		TreeNode100 tl1 = new TreeNode100(5);
		TreeNode100 tr1 = new TreeNode100(15);
		t1.left = tl1;
		t1.right = tr1;
		t2.left = tl1;
//		t2.right = tr1;
//		TreeNode100 tl2 = new TreeNode100(15);
		TreeNode100 tr2 = new TreeNode100(15);
//		tl1.left = null;
//		tl1.right = null;
		tl1.right = tr2;
		
		
		System.out.println(new Question100().isSameTree(new TreeNode100(10), new TreeNode100(10)));
	}
}
class TreeNode100 {
	int val;
	TreeNode100 left;
	TreeNode100 right;
	TreeNode100(int x) { val = x; }
}
