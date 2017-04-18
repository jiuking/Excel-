import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelTest1 {
	
	public static void main(String[] args)throws Exception{
		// ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	    // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
	    HSSFSheet sheet = wb.createSheet("�ͻ����ϱ�");  
	    //����������ʽ �ϲ���Ԫ�� 
	    mergeCell(sheet);
	    //���ñ���
	    setDefaultTitle(sheet,wb);
	    //�ڶ���
	    HSSFCellStyle line2Sytle = wb.createCellStyle();  
	    HSSFRow row2 = sheet.createRow(1);//�ڶ���
	    Font font2 = wb.createFont();
	    font2.setFontName("����");
	    font2.setFontHeightInPoints((short) 11);//����(��С,����,�Ӵ֣���ɫ) �߿�  ���� 
	    font2.setColor(HSSFColor.RED.index);
	    line2Sytle.setFont(font2);
//	    contentStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//�±߿�����
//	    contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
//	    contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
//	    contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����
	    HSSFCell cell = row2.createCell(0);  //��2�е�1��
	    cell.setCellValue("��������");

	    cell.setCellStyle(line2Sytle);  
	    
	    cell = row2.createCell(2);  
	    cell.setCellValue("ʱ��");  
	    HSSFCellStyle contentStyle1 = wb.createCellStyle();  
	    contentStyle1.setFont(font2);
	    cell.setCellStyle(contentStyle1); 
	    
	    HSSFCellStyle secondLineStyle = wb.createCellStyle();
	    secondLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//�ϱ߿����� 
	    secondLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//�±߿�����
	    secondLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
	    secondLineStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
	   
//	    HSSFRow row2 = sheet.createRow(1);//�ڶ���
        
//    	for(int column = 0;column<7;column++){
//    		HSSFCell cellTemp = row2.createCell(column);  
//    		cellTemp.setCellStyle(secondLineStyle); 
//    	}
    	HSSFCell cell2_7 = row2.createCell(7);//�ڶ���7��
    	HSSFCellStyle secondSpeciaRLineStyle = wb.createCellStyle();
    	secondSpeciaRLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//�ϱ߿����� 
    	secondSpeciaRLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//�±߿�����
    	secondSpeciaRLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
    	secondSpeciaRLineStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//�ұ߿�����
    	cell2_7.setCellStyle(secondSpeciaRLineStyle);
    	
	    
//	    setFontStyle();
	    // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
//	    HSSFRow row1 = sheet.createRow(0);  

//	    HSSFow row = sheet.create
	    // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
//	    HSSFCellStyle titleStyle = wb.createCellStyle();  
	    //���ñ߿�
//	    setBorder(wb,sheet);
    	Date date = new Date();
    	String birth = "1991-04-13";
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	
      	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
       	Date d1 = sdf1.parse("513029199102213255".substring(6, 14));
    	
//    	Date d1 = sdf.parse(birth);
    	Date d2 = date;
    	Calendar c1 = Calendar.getInstance();
    	Calendar c2 = Calendar.getInstance();
    	c1.setTime(d1);
    	c2.setTime(d2);
    	int a = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
    	int b = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
    	int c = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
    	if(b > 0)
    		a += 1;
    	else if(b == 0 && c >0)
    		a += 1;
    	System.out.println(a+"---"+b+"----"+c);
       	System.out.println(sdf.format(date));
       	System.out.println(sdf1.format(d1));
//       	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
//       	System.out.println(sdf1.parse("513029199012313255".substring(6, 14)));
	    /*try  
	    {  
	        FileOutputStream fout = new FileOutputStream("E:/students1.xls");  
	        wb.write(fout);  
	        fout.close();  
	    }  
	    catch (Exception e)  
	    {  
	        e.printStackTrace();  
	    }  */
	}
	
	private int getAgeByBirthDay(Date birth)throws ParseException{
		Date now = new Date();
    	Calendar birthCal = Calendar.getInstance();
    	Calendar nowCal = Calendar.getInstance();
    	birthCal.setTime(birth);
    	nowCal.setTime(now);
    	int year = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
    	int month = nowCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
    	int day = nowCal.get(Calendar.DAY_OF_MONTH) - birthCal.get(Calendar.DAY_OF_MONTH);
    	
		return 1;
	}
	
	//���ñ���
	static void setDefaultTitle(HSSFSheet sheet,HSSFWorkbook wb){
		//����
	    HSSFRow row1 = sheet.createRow(0);
	    HSSFCell cell = row1.createCell(0);  
	    HSSFCellStyle titleStyle = wb.createCellStyle(); 
	    titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ���ⴴ��һ�����и�ʽ
	    titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    Font font = wb.createFont();
	    font.setFontName("����");//��������
	    font.setFontHeightInPoints((short) 20);//�����С
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    titleStyle.setFont(font);
	    cell.setCellStyle(titleStyle);
	    cell.setCellValue("�ͻ����ϵǼǱ�");  
	}
	static void setBorder(HSSFWorkbook wb,HSSFSheet sheet){
		//���ñ߿� 
	    HSSFCellStyle defaultStyle = wb.createCellStyle();  
	    defaultStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//�±߿�����
	    defaultStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
	    defaultStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
	    defaultStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����  
	    //�б߿�Ӵ�
	    HSSFCellStyle specialRightStyle = wb.createCellStyle();  
	    specialRightStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//�±߿�����
	    specialRightStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
	    specialRightStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//�ұ߿�����
	    specialRightStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����
	    
	    for(int rowNum = 1;rowNum<120;rowNum++){
	        HSSFRow row = sheet.createRow(rowNum);//�ڶ���
	        
	    	for(int column = 0;column<8;column++){
	    		HSSFCell cell = row.createCell(column);
	    		if(column == 7){
	    			cell.setCellStyle(specialRightStyle); 
	    		}else{
	    			cell.setCellStyle(defaultStyle);
	    		}
	    	}
	    }
	    //�ڶ��������ұ߿�Ӵ�
	    HSSFCellStyle secondLineStyle = wb.createCellStyle();
	    secondLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//�ϱ߿����� 
	    secondLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//�±߿�����
	    secondLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
	    secondLineStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
	   
	    HSSFRow row2 = sheet.createRow(1);//�ڶ���
        
    	for(int column = 0;column<7;column++){
    		HSSFCell cell = row2.createCell(column);  
    	    cell.setCellStyle(secondLineStyle); 
    	}
    	HSSFCell cell2_7 = row2.createCell(7);//�ڶ���7��
    	HSSFCellStyle secondSpeciaRLineStyle = wb.createCellStyle();
    	secondSpeciaRLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//�ϱ߿����� 
    	secondSpeciaRLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//�±߿�����
    	secondSpeciaRLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
    	secondSpeciaRLineStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//�ұ߿�����
    	cell2_7.setCellStyle(secondSpeciaRLineStyle);
	}
	static void mergeCell(HSSFSheet sheet){
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7)); //����ϲ���Ԫ��title��һ��
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));//��2��
	    
	    sheet.addMergedRegion(new CellRangeAddress(2, 2, 0 ,7));//��3��
	    
	    sheet.addMergedRegion(new CellRangeAddress(4, 4, 1 ,3));//��5��
	    sheet.addMergedRegion(new CellRangeAddress(4, 4, 4 ,5));//��5��
	    sheet.addMergedRegion(new CellRangeAddress(4, 4, 6 ,7));//��5��
	    
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1 ,3));//��6��
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 4 ,5));//��6��
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 6 ,7));//��6��
	    
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1 ,7));//��7��
	    
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1 ,7));//��8��
	    
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1 ,3));//��9��
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 5 ,7));//��9��
	    
	    sheet.addMergedRegion(new CellRangeAddress(9, 9, 1 ,3));//��10��
	    
	    sheet.addMergedRegion(new CellRangeAddress(10, 10, 1 ,7));//��11��
	    
	    sheet.addMergedRegion(new CellRangeAddress(11, 11, 1 ,3));//��12��
	    
	    sheet.addMergedRegion(new CellRangeAddress(12, 12, 0 ,7));//��13��
	    
	    sheet.addMergedRegion(new CellRangeAddress(14, 14, 1 ,3));//��15��
	    sheet.addMergedRegion(new CellRangeAddress(14, 14, 4 ,5));//��15��
	    
	    sheet.addMergedRegion(new CellRangeAddress(15, 15, 1 ,3));//��16��
	    sheet.addMergedRegion(new CellRangeAddress(15, 15, 4 ,5));//��16��
	    
	    sheet.addMergedRegion(new CellRangeAddress(16, 16, 1 ,7));//��17��
	    
	    sheet.addMergedRegion(new CellRangeAddress(17, 17, 1 ,7));//��18��
	    
	    sheet.addMergedRegion(new CellRangeAddress(18, 18, 1 ,3));//��19��
	    sheet.addMergedRegion(new CellRangeAddress(18, 18, 5 ,7));//��19��
	    
	    sheet.addMergedRegion(new CellRangeAddress(19, 19, 1 ,3));//��20��
	    
	    sheet.addMergedRegion(new CellRangeAddress(20, 20, 1 ,7));//��21��
	    
	    sheet.addMergedRegion(new CellRangeAddress(21, 21, 1 ,3));//��22��
	    
	    sheet.addMergedRegion(new CellRangeAddress(22, 22, 0 ,7));//��23��

	    sheet.addMergedRegion(new CellRangeAddress(23, 23, 1 ,3));//��24��
	    sheet.addMergedRegion(new CellRangeAddress(23, 23, 4 ,5));//��24��
	    sheet.addMergedRegion(new CellRangeAddress(23, 23, 6 ,7));//��24��
	    
	    int row = 24;//��25�С�
	    int rowSum = row + 2;//����������������Ч��������֮���ձ�������
	    for(;row<rowSum;row++){//
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,3));//��25��
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 4 ,5));//��25��
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 6 ,7));//��25��
	    }
	    
//	    sheet.addMergedRegion(new CellRangeAddress(24, 24, 1 ,3));//��25��
//	    sheet.addMergedRegion(new CellRangeAddress(25, 25, 1 ,3));//��26��
//	    sheet.addMergedRegion(new CellRangeAddress(24, 24, 4 ,5));//��25��
//	    sheet.addMergedRegion(new CellRangeAddress(25, 25, 4 ,5));//��26��
//	    sheet.addMergedRegion(new CellRangeAddress(24, 24, 6 ,7));//��25��
//	    sheet.addMergedRegion(new CellRangeAddress(25, 25, 6 ,7));//��26��
	    
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//��27��
	    row++;//��28��
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,3));//��28��
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 4 ,5));//��28��
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 6 ,7));//��28��
	    row++;
	    rowSum = row + 2;//����������������Ч��������֮���ձ�������
	    for(;row<rowSum;row++){//
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,3));//��29����30��
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 4 ,5));//��25����30��
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 6 ,7));//��25����30��
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//��31��
	    row++;//��32��
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 2 ,3));//��32��
	    row++;
	    rowSum = row + 3;//�����빺���ձ���
	    for(;row<rowSum;row++){
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 2 ,3));//��33�е�35��
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//��36��
	    row++;//��37��
	    rowSum = row + 3;
	    for(;row<rowSum;row++){
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,2));//��37�е�39��
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 3 ,4));//��37�е�39��
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 5 ,7));//��37�е�39��
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//��40��
	    row++;//41��
	    rowSum = row+4;//41��44��
	    for(;row<rowSum;row++){
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 2 ,4));//��41�е�44��
	      	sheet.addMergedRegion(new CellRangeAddress(row, row, 5 ,6));//��41�е�44��
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//��45��
//	    row++;
//	    rowSum = row + 37;//46-82��
	    for(int i = 0;i<37;i++){
	    	row++;
	    	if(i==26||i==28){
	    		sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));
	    		continue;
	    	}
	    	sheet.addMergedRegion(new CellRangeAddress(row,row,0,6));
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));//��82��
	    row++;
	    sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));//��83��
//	    row++;
	    /*rowSum = row + 37;//84-120��
	    for(;row<rowSum;row++){
	    	sheet.addMergedRegion(new CellRangeAddress(row,row,0,6));
	    }*/
	    for(int i = 0;i<37;i++){
	    	row++;
	    	if(i==26||i==28){
	    		sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));
	    		continue;
	    	}
	    	sheet.addMergedRegion(new CellRangeAddress(row,row,0,6));
	    }
//	    row++;
	    sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));//��120��
	}
}
