package com.asm.cglib.test;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

class TestClass{
	public void doSome(){
		System.out.println("====>ßÞÑ½ßÞÑ½Î¹");
	}
}
public class CglibTest {

	static class MethodInterceptorImpl implements MethodInterceptor{

		@Override
		public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
			// TODO Auto-generated method stub
			System.out.println(arg1);
			System.out.println("start!");
			arg3.invokeSuper(arg0, arg2);
			System.out.println("end!");
			return null;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(TestClass.class);
		enhancer.setCallback(new MethodInterceptorImpl());
		TestClass my = (TestClass)enhancer.create();
		my.doSome();
	}

}
