package com.annotation.test;

import java.util.concurrent.ConcurrentHashMap;

public class TestThread extends Thread {
	public   static volatile int  n  =   0 ;  
    public   void  run()  
    {  
         for  ( int  i  =   0 ; i  <   10 ; i ++ )  
             try   
        {  
                n  =  n  +   1 ;  
                sleep( 3 );  //  Ϊ��ʹ���н����������ӳ�3����   
  
            }  
             catch  (Exception e)  
            {  
            }  
    }  
  
     public   static   void  main(String[] args)  throws  Exception  
    {  
  
        Thread threads[]  =   new  Thread[ 100 ];  
         for  ( int  i  =   0 ; i  <  threads.length; i ++ )  
             //  ����100���߳�   
            threads[i]  =   new  TestThread();  
         for  ( int  i  =   0 ; i  <  threads.length; i ++ )  
             //  ���иղŽ�����100���߳�   
            threads[i].start();  
         for  ( int  i  =   0 ; i  <  threads.length; i ++ )  
             //  100���̶߳�ִ��������   
            threads[i].join();  
        System.out.println( " n= "   +  TestThread.n);  
    }  
}   

class Singleton{
	private Singleton(){
		
	}
	private static class SingletonHoler{
		private static final Singleton instance = new Singleton();
	}
	public static Singleton getInstance(){
		return Singleton.SingletonHoler.instance;
	}
}
class Singleton1{
	private Singleton1(){
		
	}
	private volatile static Singleton1 instance = null;
	
	public static Singleton1 getInstance(){
		if(instance == null){
			synchronized (Singleton1.class) {
				if(instance == null)
					instance = new Singleton1();
			}
		}
		return instance;
			
	}
}
