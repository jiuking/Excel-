import java.util.UUID;

public class UUIDFactory {
	public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //È¥µô¡°-¡±·ûºÅ 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	private static String[] getNumUUID(int number){
		if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++){ 
            ss[i] = getUUID(); 
        } 
        return ss; 
	}
 	public static void main(String[] args){
 		String[] uuid = getNumUUID(1);
		for(String u : uuid){
			System.out.println(u);
		}
		System.out.println(uuid);
	}
}
