package com.leetcode.solution;

import java.util.Arrays;

// ZigZag Conversion
public class Question6 {
	
	/*PAYPALISHIRING  
	P   A   H   N
	A P L S I I G
	Y   I   R 
	PAHNAPLSIIGYIR*/
	public String convert(String s, int numRows) {
		if(null == s||s.isEmpty()){
			return "";
		}
		if(numRows == 1){
			return s;
		}
		int len = s.length();
		String[] ans = new String[numRows];  
		int row = 0, delta = 1;
        Arrays.fill(ans, "");  
        for (int i = 0; i < len; i++) {  
            ans[row] += s.charAt(i);  
            row += delta;  
            if (row >= numRows) {  
                row = numRows-2;  
                delta = -1;  
            }  
            if (row < 0) {  
                row = 1;  
                delta = 1;  
            } 
        }
        StringBuffer ret = new StringBuffer();  
        for (int i = 0; i < numRows; i++) {  
            ret.append(ans[i]);  
        }  
        return ret.toString(); 
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Question6().convert("PAYPALISHIRING", 4));
	}

}
