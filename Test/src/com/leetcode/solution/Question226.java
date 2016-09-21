package com.leetcode.solution;

//Invert Binary Tree

/*Invert a binary tree.

    4
  /   \
 2     7
/ \   / \
1  3 6   9

 to
    4
  /   \
 7     2
/ \   / \
9  6 3   1*/
public class Question226 {
	public TreeNode226 invertTree(TreeNode226 root) {
//		TreeNode226 orgin = root;
		if(root == null) return root;
		TreeNode226 temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}
	
	public static void main(String[] args){
		TreeNode226 t1 = new TreeNode226(4);
		TreeNode226 t2 = new TreeNode226(2);
		TreeNode226 t3 = new TreeNode226(7);
		t1.left = t2;
		TreeNode226 t4 = new TreeNode226(1);
		TreeNode226 t5 = new TreeNode226(3);
		t2.left = t4;
		t2.right = t5;
		
		t1.right = t3;
		TreeNode226 t6 = new TreeNode226(6);
		TreeNode226 t7 = new TreeNode226(9);
		t3.left = t6;
		t3.right = t7;
		TreeNode226 t = new Question226().invertTree(t1);
		System.out.println(t.left.left.val);
	}
}

class TreeNode226{
	int val;
	TreeNode226 left;
	TreeNode226 right;

	TreeNode226(int x) {
		val = x;
	}
}