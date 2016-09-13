import java.lang.reflect.Field;
import java.util.Hashtable;

public class Test implements Cloneable{
	public static void main(String[] args) throws Exception{
		String hello_world = "hello world";
		Field value = String.class.getDeclaredField("value");
		value.setAccessible(true);
		String hello = hello_world.substring(0,5);
		System.out.println(hello);
		System.out.println(value.get(hello));
		System.out.println(value.get(hello_world));
		System.setSecurityManager(new SecurityManager(){
			@Override
			public void checkExit(int status) {
				// TODO Auto-generated method stub
				throw new ThreadDeath();
			}
		});
		Hashtable<Integer,Integer> hash = new Hashtable<Integer,Integer>();
		/*try{
			System.exit(0);
		}finally{
			System.out.println("In the finnaly block");
		}*/
		System.out.println(Singleton1.getSingleton().count1);
		System.out.println(Singleton1.getSingleton().count2);
	}
	
}

class Singleton1{
	public static int count2 = 0;
	public static int count1 = 2;
//	private static Singleton1 singleton1= new Singleton1();
	
	
	private Singleton1(){
		count1 = count1 +1 ;
		count2++;
	}
	public static Singleton1 getSingleton(){
		return new Singleton1();
	}
}
