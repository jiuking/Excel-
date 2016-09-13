import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
		System.out.println(System.currentTimeMillis());
		service.scheduleAtFixedRate(runnable, 0, 2, TimeUnit.SECONDS);
		System.out.println(System.currentTimeMillis());
		/*ExecutorService  exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();   
		results.add(exec.submit(callable));
		for (Future<String> fs : results) {  
            if (fs.isDone()) {  
                System.out.println(fs.get());  
            } else {  
                System.out.println("Future result is not yet complete");  
            }  
        }  */
	}

}
class RunnableTest implements Runnable{
	List<String> list = Collections.synchronizedList(new ArrayList<String>());
	
	String error = "";
	boolean isEnd = true;//是否都更新成功！
	@Override
	public void run() {
		// TODO Auto-generated method stub
		toDoSelectDb();//查询出对应符合自动续约的产品
		/*try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//new ArrayList 参数 若是失败则再次调用该方法即可or 返回是否更新成功，与失败list再次重新调用callable即可直至全部成功。
		Callable<String> callable = (Callable)new RunnableTest().new CallableTest(new ArrayList<String>());
		ExecutorService  exec = Executors.newCachedThreadPool();
		ArrayList<Future<String>> results = new ArrayList<Future<String>>();   
		results.add(exec.submit(callable));
		for (Future<String> fs : results) {  
            if (fs.isDone()) {  
                try {
					System.out.println(fs.get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
            } else {  
                System.out.println("Future result is not yet complete"+list.size());  
            }  
        }  
	}
	
	protected void toDoSelectDb() {
		for(int i = 0;i<5;i++){
			list.add(i+"");
			
		}
		
		System.out.println("hellow11212" + list.size());
	}
	class CallableTest implements Callable<Object>{

		private Object object;
		public CallableTest(){
			
		}
		public CallableTest(Object object){
			this.object = object;
		}
		@Override
		public Object call() throws Exception {
			// TODO Auto-generated method stub
			//use object使用初始化参数
			for(int i = 0;i<list.size();i++){
			try{
				System.out.println("添加及修改数据库数据");
				
					System.out.println(list.get(i));
					if(i == 2){
						int b = i/0;
					}
			}catch(Exception e){
				System.out.println("error is happen!");
				error = "error"+i;
				continue;
			}
			}
			return error;
		}
		
	}
}
