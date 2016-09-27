package com.leetcode.solution;

//Sum of Left Leaves

/*Find the sum of all left leaves in a given binary tree.
   3
  / \
 9  20
    / \
   15  7
    5
   / \
  3   4
 / \  /
1   2 1   
There are two left leaves in the binary tree, 
with values 9 and 15 respectively. Return 24.*/
//[1,2,3,4,5]
public class Question404 {
	int sumResult = 0;
	public int sumOfLeftLeaves(TreeNode404 root) {
		if(root == null || root.left == null) return 0;
		
		sumResult = sumResult + root.left.val;
		sumOfLeftLeaves(root.left);
		sumOfLeftLeaves(root.right);
		return sumResult;
    }
	
	public static void main(String[] args) {
		TreeNode404 root = new TreeNode404(5);
		root.left = new TreeNode404(3);
		root.right = new TreeNode404(4);
		root.right.left = new TreeNode404(1);
		root.left.left = new TreeNode404(1);
		root.left.right = new TreeNode404(2);
		System.out.println(new Question404().sumOfLeftLeaves(root));
	}

}

class TreeNode404 {
	int val;
	TreeNode404 left;
	TreeNode404 right;
	TreeNode404(int x) { val = x; }
}
