class Super{
	
    static{
    	m = 22;
        System.out.println("ִ����super�ྲ̬����");
    }
    public static int m = 11;
}
 
class Father extends Super{
//    public static int m = 33;
    static{
        System.out.println("ִ���˸��ྲ̬����");
    }
}
 
class Child extends Father{
    static{
        System.out.println("ִ�������ྲ̬����");
    }
}
public class HelloWorld {
	public static void main(String[] args){
		System.out.println(Child.m);
		System.out.println("Hello World!");
		
	}
}
