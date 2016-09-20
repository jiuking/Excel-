package com.leetcode.solution;

//Sum of Two Integers
/*Calculate the sum of two integers a and b, 
but you are not allowed to use the operator + and -.*/
public class Question371 {
	
	public int getSum(int a, int b) {
		if(b == 0) return a;
		int sum = a ^ b;
        int carry = (a&b)<<1;
        return getSum(sum,carry);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Question371().getSum(1, 9));
		System.out.println(1^9);
		System.out.println((1&9)<<1);
	}

}
