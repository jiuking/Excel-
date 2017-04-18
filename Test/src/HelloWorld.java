import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

class Super{
	
    static{
    	m = 22;
        System.out.println("÷¥––¡Àsuper¿‡æ≤Ã¨”Ôæ‰øÈ");
    }
    public static int m = 11;
}
 
class Father extends Super{
//    public static int m = 33;
    static{
        System.out.println("÷¥––¡À∏∏¿‡æ≤Ã¨”Ôæ‰øÈ");
    }
}
 
class Child extends Father{
    static{
        System.out.println("÷¥––¡À◊”¿‡æ≤Ã¨”Ôæ‰øÈ");
    }
}
 class HelloWorld1 {
	private static String a = "asdf";
	
	public static void main1(String[] args){
		String b = a.intern();
		System.out.println(a==b);
		a = "asd";
		System.out.println(a);
		System.out.println(a == b);
		System.out.println(b);
		System.out.println(Child.m);
		System.out.println("Hello World!");
		
	}
}




class Instance{
	private Instance(){
		
	}
	private static class InstanceHolder{
		private final static Instance instance = new Instance();
	}
	public final static Instance getInstance(){
		return InstanceHolder.instance;
	}
}
class Upper {
	String upperString;

	public Upper() {
		Initializer.initialize(this);
	}
}

public class HelloWorld extends Upper {
	 
	 String lowerString = null;
	 static double[] temps = { 98.6, 32.0, 100.0, 212.0, 53.5 };
	 static double[] tempsa = temps.clone();
	 public HelloWorld() {
	  super();
	  System.out.println("Upper:  " + upperString);
	  System.out.println("Lower:  " + lowerString);
	 }
	 
	 public static void main(final String[] args)throws Exception{
//	  new HelloWorld();
//		 HashSet set = new HashSet();
//		 set.add("1");
//		
//		 System.out.println( set.add("1"));
		 
//		 for(int i = 0;i<tempsa.length;i++)
//			 System.out.println(tempsa[i]);
//		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		 System.out.println(formatter.format(formatter.parse("null")));
		 String a = "0.012  +∏°∂Ø";
		 String[] a1 = a.split("[+]");
		 System.out.println(a.trim());
		 System.out.println(a1[0]);
//		  BigDecimal decimal = new BigDecimal(a1[0].trim());
//	        System.out.println(decimal);
//	        BigDecimal setScale = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
//	        System.out.println(setScale.toString());
	 }
	}
class Initializer {
	 static void initialize(final Upper anUpper) {
	  if (anUpper instanceof HelloWorld) {
		  HelloWorld lower = (HelloWorld) anUpper;
	   lower.lowerString = "lowerInited";
	  }
	  anUpper.upperString = "upperInited";
	 }
	}
