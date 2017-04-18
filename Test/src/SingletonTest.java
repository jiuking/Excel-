import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SingletonTest {
	private SingletonTest(){
		
	}
	private static class SingleTestHolder{
		private static final SingletonTest getSingleTest = new SingletonTest();
	}
	public static final SingletonTest getSingleTest(){
		return SingleTestHolder.getSingleTest;
	}
	public static void main(String[] args){
		for(SingletonEnum s : SingletonEnum.values()){
			System.out.println(s.name());
			System.out.println(s.eval());
			System.out.println(s.getName());
//			System.out.println(s.ordinal());
		}
		
		List<HashMap> map = new ArrayList<>();
		for(HashMap map1 : map){
			System.out.println("asdf");
		}
	}
}

class Singleton{
	private volatile static Singleton singleton;
	private Singleton(){
		
	}
	public static Singleton getSingleton(){
		if(singleton == null){
			synchronized (Singleton.class) {
				if(singleton == null){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}

enum SingletonEnum{
	INSTANCE("tom") {
		@Override
		public double eval() {
			// TODO Auto-generated method stub
			return 0;
		}
	},A("a") {
		@Override
		public double eval() {
			// TODO Auto-generated method stub
			return 1;
		}
	},B("b") {
		@Override
		public double eval() {
			// TODO Auto-generated method stub
			return 2;
		}
	};
	public abstract double eval();
	private final String name;
	private SingletonEnum(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
}