package com.leetcode.solution;

import java.math.BigDecimal;

public class Question8 {
	public int myAtoi(String str) {
		try{
            str = str.trim();
            char[] c = str.toCharArray();
            int i = c[0] == 45 || c[0] == 43 ?1:0;
            for(;i<c.length;i++){
            	/*if(c[0]==45||c[0]==43)
                    continue;*/
                if(c[i]<48||c[i]>57){
                    str = str.substring(0,i);
                    break;
                }
            }
            System.out.println(str);
            if(new BigDecimal(str).compareTo(new BigDecimal(Integer.MAX_VALUE)) == 1){
            	return Integer.MAX_VALUE;
            }
            if(new BigDecimal(str).compareTo(new BigDecimal(Integer.MIN_VALUE)) == -1){
            	return Integer.MIN_VALUE;
            }
            return Integer.valueOf(str);
        }catch(Exception e){
            return 0;
        }
//		for(int i = 0;i<c.length ;i++){
//			System.out.println(c[i]);
//			if(c[i] < 48||c[i]>57||c[i] != 45 ||c[i] != 43){
//				is = true;
//				break;
//			}
//			System.out.println(c[i] < 48||c[i]>57);
//		}
//		boolean fa = new BigDecimal(str).compareTo(new BigDecimal(Integer.MAX_VALUE)) == 1;
//		result = Integer.valueOf(str);
//		System.out.println(result);
    }
	public static void main(String[] args){
		int a = Integer.MAX_VALUE;
		System.out.println(new Question8().myAtoi("2147483648"));
		System.out.println('+' == 43);
	}
}
