import redis.clients.jedis.Jedis;

public class RedisJava {
	public static void main(String[] args){//contract_code1 contract_code1 contract_code1
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connetion to server sucessfully");
		System.out.println("Server is running:"+jedis.ping());
		System.out.println("contract_code1".equals("contract_code1"));
	}
}
