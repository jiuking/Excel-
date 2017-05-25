import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
		System.out.println(getAgeByBirthDay(new Date(), "511029201301010013")+"岁");
		/*int a = getInteger();
		System.out.println(a);*/
		System.out.println(new BigDecimal(10).compareTo(new BigDecimal(11))<0);
	}
	


private static Integer getInteger() {
		// TODO Auto-generated method stub
		return null;
	}



private static Integer getAgeByBirthDay(Date birth,String idNum){//根据身份证计算年龄
	Date now = new Date();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	try{
		birth = sdf.parse(idNum.substring(6, 14));
	}catch(ParseException e){
		return null;
	}
	Calendar birthCal = Calendar.getInstance();
	Calendar nowCal = Calendar.getInstance();
	birthCal.setTime(birth);
	nowCal.setTime(now);
	int year = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
	int month = nowCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
	int day = nowCal.get(Calendar.DAY_OF_MONTH) - birthCal.get(Calendar.DAY_OF_MONTH);
	if(month > 0)
		year += 1;
	else if(month == 0 && day >0)
		year += 1;
	return year;
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
