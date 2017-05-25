import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.cgnb.core.security.Encode;

public class ReadExcelEncryption {

	public static void main(String[] args) {
		// 读取文件自定义路径
		/*String filePath = "C:\\Users\\Bravowhale\\Documents\\Tencent Files\\1095254051\\FileRecv\\账号.txt";
		StringBuffer sb = new StringBuffer();
		for (String temp : getAccount(filePath)) {
			String result = Encode.encodePlainPass(temp, "111111", null);
			System.out.println(result);
			sb.append(result+"\r\n");
		}
		write(sb.toString());*/
		System.out.println("+++"+Encode.encodePlainPass("6213910100100003854", "888888")+"___");
	}

	/**
	 * 读取文件账号
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 账号List
	 */
	public static List<String> getAccount(String filePath) {
		List<String> result = new ArrayList<>();
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// if(!lineTxt.isEmpty()){
					// System.out.println(lineTxt);
					result.add(lineTxt);
					// }
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入文件到磁盘
	 * @param str 需要写入文件的内容
	 */
	public static void write(String str) {
		try {
			// 根据自定义路径写文件
			String path = "C:\\Users\\Bravowhale\\Desktop\\encryption.txt";
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true); //如果追加方式用true
		/*	StringBuffer sb = new StringBuffer();
			sb.append(str + "\r\n");*/
			out.write(str.getBytes("utf-8"));//注意需要转换对应的字符集
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
	}
}
