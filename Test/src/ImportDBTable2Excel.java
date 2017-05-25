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
	 * 读取仅包含数据库表的txt文件
	 * @return 需要导出的数据库表结构（包含数据库字段名称，类别，长度，含义）
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
			System.out.println("对应文件不存在");
		} catch (IOException ioe) {
			System.out.println("读取io异常");
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
