import java.awt.Color;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Font;  

public class ExcelTest {

	public static void main(String[] args){
	// ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
    HSSFWorkbook wb = new HSSFWorkbook();  
    // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
    HSSFSheet sheet = wb.createSheet("�ͻ����ϱ�");  

    // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
    HSSFRow row1 = sheet.createRow(0);  
    // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
    HSSFCellStyle titleStyle = wb.createCellStyle();  
    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7)); //����ϲ���Ԫ��
    titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ���ⴴ��һ�����и�ʽ  
    titleStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//�±߿�����
    titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
    titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
    titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����
    Font font = wb.createFont();
    font.setFontName("����");//��������
    font.setFontHeightInPoints((short) 20);//�����С
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    titleStyle.setFont(font);
    HSSFCell cell1 = row1.createCell(1);
    cell1.setCellStyle(titleStyle);
    HSSFCell cell2 = row1.createCell(2);
    cell2.setCellStyle(titleStyle);
    HSSFCell cell3 = row1.createCell(3);
    cell3.setCellStyle(titleStyle);
    HSSFCell cell4 = row1.createCell(4);
    cell4.setCellStyle(titleStyle);
    HSSFCell cell5 = row1.createCell(5);
    cell5.setCellStyle(titleStyle);
    HSSFCell cell6 = row1.createCell(6);
    cell6.setCellStyle(titleStyle);
    HSSFCell cell7 = row1.createCell(7);
    cell7.setCellStyle(titleStyle);
    
    HSSFCell cell = row1.createCell(0);  
    cell.setCellValue("�ͻ����ϵǼǱ�");  
    cell.setCellStyle(titleStyle);  
  
    HSSFCellStyle contentStyle = wb.createCellStyle();  
    HSSFRow row2 = sheet.createRow(1);//�ڶ���
    Font font2 = wb.createFont();
    font2.setFontName("����");
    font2.setFontHeightInPoints((short) 11);//����(��С,����,�Ӵ֣���ɫ) �߿�  ���� 
    font2.setColor(HSSFColor.RED.index);
    contentStyle.setFont(font2);
//    contentStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//�±߿�����
//    contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
//    contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
//    contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����
    cell = row2.createCell(0);  
    cell.setCellValue("���ھ�ס��ַ(ͨѶ��ַ)");

    cell.setCellStyle(contentStyle);  
    
    cell = row2.createCell(2);  
    cell.setCellValue("ʱ��");  
    
    font2.setFontName("����");
    font2.setFontHeightInPoints((short) 11);//�����С
    font2.setColor(HSSFColor.RED.index);
    HSSFCellStyle contentStyle1 = wb.createCellStyle();  
    contentStyle1.setFont(font2);
    contentStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//�±߿�����
    contentStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
    contentStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
    contentStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����    
    cell.setCellStyle(contentStyle1); 
    sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));
    cell = row2.createCell(3);
    cell.setCellValue("����˹�ٷ�˳��İ�˹�ٷ������ ��˹�ٷ�����ˮ��ɳ����ʦ�����Ǵ�� ");
    cell.setCellStyle(contentStyle1);
//    cell.setCellStyle(style);  
//    cell = row2.createCell(3);  
//    cell.setCellValue("����");  
//    cell.setCellStyle(style);  

    // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���  
//    List list = CreateSimpleExcelToDisk.getStudent();  

/*    for (int i = 0; i < 3; i++)  
    {  
        row = sheet.createRow((int) i + 1);  
//        Student stu = (Student) list.get(i);  
        // ���Ĳ���������Ԫ�񣬲�����ֵ  
        row.createCell(0).setCellValue((double) i);  
        row.createCell(1).setCellValue("��"+i);  
        row.createCell(2).setCellValue((double)i);  
        cell = row.createCell(3);  
        cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(new Date()));  
    }  */
    sheet.autoSizeColumn(0,true);
   // sheet.autoSizeColumn(1,true);
//    sheet.autoSizeColumn(2,true);
    sheet.autoSizeColumn(3);
    sheet.autoSizeColumn(4);
    sheet.autoSizeColumn(5);
    sheet.autoSizeColumn(6);
    sheet.autoSizeColumn(7);
//    sheet.autoSizeColumn(8,true);
    // �����������ļ��浽ָ��λ��  
    try  
    {  
        FileOutputStream fout = new FileOutputStream("E:/students.xls");  
        wb.write(fout);  
        fout.close();  
    }  
    catch (Exception e)  
    {  
        e.printStackTrace();  
    }  
}  
}
