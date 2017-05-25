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
		// ��ȡ�ļ��Զ���·��
		/*String filePath = "C:\\Users\\Bravowhale\\Documents\\Tencent Files\\1095254051\\FileRecv\\�˺�.txt";
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
	 * ��ȡ�ļ��˺�
	 * 
	 * @param filePath
	 *            �ļ�·��
	 * @return �˺�List
	 */
	public static List<String> getAccount(String filePath) {
		List<String> result = new ArrayList<>();
		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// ���ǵ������ʽ
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
				System.out.println("�Ҳ���ָ�����ļ�");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * д���ļ�������
	 * @param str ��Ҫд���ļ�������
	 */
	public static void write(String str) {
		try {
			// �����Զ���·��д�ļ�
			String path = "C:\\Users\\Bravowhale\\Desktop\\encryption.txt";
			File file = new File(path);
			if (!file.exists())
				file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true); //���׷�ӷ�ʽ��true
		/*	StringBuffer sb = new StringBuffer();
			sb.append(str + "\r\n");*/
			out.write(str.getBytes("utf-8"));//ע����Ҫת����Ӧ���ַ���
			out.close();
		} catch (IOException ex) {
			System.out.println(ex.getStackTrace());
		}
	}
}
