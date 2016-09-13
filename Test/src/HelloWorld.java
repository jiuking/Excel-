class Super{
	
    static{
    	m = 22;
        System.out.println("执行了super类静态语句块");
    }
    public static int m = 11;
}
 
class Father extends Super{
//    public static int m = 33;
    static{
        System.out.println("执行了父类静态语句块");
    }
}
 
class Child extends Father{
    static{
        System.out.println("执行了子类静态语句块");
    }
}
public class HelloWorld {
	public static void main(String[] args){
		System.out.println(Child.m);
		System.out.println("Hello World!");
		
	}
}
