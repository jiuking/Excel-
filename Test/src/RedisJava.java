import redis.clients.jedis.Jedis;

public class RedisJava {
	public static void main(String[] args){
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connetion to server sucessfully");
		System.out.println("Server is running:"+jedis.ping());
	}
}
