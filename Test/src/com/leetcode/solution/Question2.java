package com.leetcode.solution;

//Add Two Numbers 2016-09-14
/*Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8*/

class ListNode{
	int val;
	ListNode next;
	public ListNode(){
		
	}
	public ListNode(int val){
		this.val = val;
	}
}
public class Question2 {
	//根据定义对象的索引关联对象
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		
		ListNode result = l1;//返回结果对象
		ListNode lastNode = new ListNode(0);//添加解决进位问题
		int flag = 0;
		while(l1 != null && l2 != null){
			l1.val = l1.val + l2.val +flag;
			flag = l1.val/10;
			l1.val = l1.val%10;
			lastNode = l1;
			l1 = l1.next;
			l2 = l2.next;
		}
		if(l2 != null){
			lastNode.next = l2;
			l1 = l2;
		}
		while(l1 != null){
			l1.val = l1.val + flag;
			flag = l1.val / 10;
			l1.val = l1.val % 10;
			lastNode = l1;
			l1 = l1.next;
		}
		if(flag >0){
			ListNode listNode = new ListNode(1);
			lastNode.next = listNode;
		}
		return result;
	}
	public static void main(String[] args){
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
//		l1.next.next.next = new ListNode(9);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(6);
		l2.next.next.next = new ListNode(9);
		ListNode listNode = new Question2().addTwoNumbers(l1, l2);
		System.out.println(listNode.val);
		System.out.println(listNode.next.val);
		System.out.println(listNode.next.next.val);
		System.out.println(listNode.next.next.next.val);
		System.out.println(listNode.next.next.next.next.val);
	}
}
