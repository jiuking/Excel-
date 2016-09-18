package com.leetcode.solution;

import java.math.BigDecimal;

//Reverse Integer

/*Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321*/
public class Question7 {
	
	public int reverse(int x) {
		boolean is_pos = x > 0;  
        x = Math.abs(x);  
        BigDecimal result = new BigDecimal(0);  
        while (x >= 1) { //逐个地处理x的个位，十位，百位...  
            result = result.multiply(new BigDecimal(10)).add(new BigDecimal(x % 10)) ; //注意求个位可以用%，不用像上面那么麻烦。  
            x = x / 10;  
        }  
        if(result.compareTo(new BigDecimal(Integer.MAX_VALUE))  == 1 ||result.compareTo(new BigDecimal(Integer.MIN_VALUE)) ==-1)
            return 0;
        if (is_pos) return result.intValue();  
        else return -result.intValue();  
       
	}
	
	public static void main(String[] args){
		System.out.println(new Question7().reverse(1534236469));
//		System.out.println(Character.toChars(12));
	}
}
