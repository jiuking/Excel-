
public class Triangle {
	int a,b,c;
	public Triangle(){
		a = 1;
		b = 1;
		c = 1;
	}
	public Triangle(int a,int b,int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public boolean isTriangle(){
		if(a<0||b<0||c<0)
			return false;
		if(a+b>c&&b+c>a&&a+c>b)
			return true;
		return false;
	}
	public String isTypeTriangle(){
		if(a==b&&b==c&&a==c){
			return "等边三角形";
		}
		if((a==b&&b!=c)||(a==c&&c!=b)||(b==c&&c!=a)){
			return "等腰三角形";
		}
		return "普通三角形";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//s1 = ["hoqq", "bbllkw", "oox", "ejjuyyy", "plmiis", "xxxzgpsssa", "xxwwkktt", "znnnnfqknaz", "qqquuhii", "dvvvwz"]
		//s2 = ["cccooommaaqqoxii", "gggqaffhhh", "tttoowwwmmww"]
		
		Triangle triangle = new Triangle();
		if(triangle.isTriangle()){
			System.out.println(triangle.isTypeTriangle());
		}else{
			System.out.println("不能构成三角形");
		}
		triangle = new Triangle(3,2,3);
		if(triangle.isTriangle()){
			System.out.println(triangle.isTypeTriangle());
		}else{
			System.out.println("不能构成三角形");
		}
		triangle = new Triangle(2,3,4);
		if(triangle.isTriangle()){
			System.out.println(triangle.isTypeTriangle());
		}else{
			System.out.println("不能构成三角形");
		}
	}

}
