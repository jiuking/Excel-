import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ImportDBTable2Excel {
	
	/**
	 * ��ȡ���������ݿ���txt�ļ�
	 * @return ��Ҫ���������ݿ��ṹ���������ݿ��ֶ����ƣ���𣬳��ȣ����壩
	 */
	private static List<String> readNeedTable() {
		List<String> result = new ArrayList<>();
		try {
			File file = new File("");
			if (!file.isFile() && file.exists()) {
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
				BufferedReader br = new BufferedReader(reader);
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					result.add(lineTxt);
				}
				br.close();
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println("��Ӧ�ļ�������");
		} catch (IOException ioe) {
			System.out.println("��ȡio�쳣");
		}
		return result;
	}

	public static void main(String[] args) {
		List<String> tables = readNeedTable();
		for(String table : tables){
			
		}
	}
	
	private static void connection(){
		
	}
	
	private static void writeTableExcel(){
		
	}
}
