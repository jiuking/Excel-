import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Task3 {

	public static void main(String[] args) throws Exception{
		List<Integer> list = new ArrayList<>();
		Runnable runnable = new RunnableTest();
		RunnableTest rtest = new RunnableTest();
		Callable<String> callable = (Callable)rtest.new CallableTest();
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		ScheduledFuture<String> scheduledFutrue = service.schedule(callable, 2, TimeUnit.SECONDS);
		System.out.println(scheduledFutrue.get());
		
	}

}
class RunnableTest implements Runnable{
	List<String> list = new ArrayList<>();
	List<String> errorList = new ArrayList<>();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		toDoSelectDb();
	}
	
	protected void toDoSelectDb() {
		for(int i = 0;i<5;i++){
			list.add(i+"");
			
		}
		
		System.out.println("hellow11212");
	}
	class CallableTest implements Callable<Object>{

		@Override
		public Object call() throws Exception {
			// TODO Auto-generated method stub
			for(int i = 0;i<list.size();i++){
			try{
				System.out.println("添加及修改数据库数据");
				
					System.out.println(list.get(i));
					if(i == 2){
						int b = i/0;
					}
			}catch(Exception e){
				errorList.add(list.get(i));
				System.out.println("error is happen!");
				continue;
			}
			}
			return null;
		}
		
	}
}
