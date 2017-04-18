// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
class Adaptee{
	public void spcificRequest(){
		System.out.println("被适配类具有 特殊功能...");
	}
}
//目标接口，或称为标准接口
interface Target{
	public void request();
}
//具体目标类，只提供普通功能
class ConcreteTarget implements Target{
	@Override
	public void request() {
		// TODO Auto-generated method stub
		System.out.println("普通类 具有 普通功能...");
	}
}
class Adapter extends Adaptee implements Target{

	@Override
	public void request() {
		// TODO Auto-generated method stub
		spcificRequest();
	}
	
}
public class AdapterTest {

}
