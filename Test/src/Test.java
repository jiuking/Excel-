import java.lang.reflect.Field;

public class Test implements Cloneable{
	public static void main(String[] args) throws Exception{
		String hello_world = "hello world";
		Field value = String.class.getDeclaredField("value");
		value.setAccessible(true);
		String hello = hello_world.substring(0,5);
		System.out.println(hello);
		System.out.println(value.get(hello));
		System.out.println(value.get(hello_world));
	}
}
