// �Ѵ��ڵġ��������⹦�ܡ������������Ǽ��еı�׼�ӿڵ���
class Adaptee{
	public void spcificRequest(){
		System.out.println("����������� ���⹦��...");
	}
}
//Ŀ��ӿڣ����Ϊ��׼�ӿ�
interface Target{
	public void request();
}
//����Ŀ���ֻ࣬�ṩ��ͨ����
class ConcreteTarget implements Target{
	@Override
	public void request() {
		// TODO Auto-generated method stub
		System.out.println("��ͨ�� ���� ��ͨ����...");
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
