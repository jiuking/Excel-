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
		// 第一步，创建一个webbook，对应一个Excel文件  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	    HSSFSheet sheet = wb.createSheet("客户资料表");  
	    //设置总体样式 合并单元格 
	    mergeCell(sheet);
	    //设置标题
	    setDefaultTitle(sheet,wb);
	    //第二行
	    HSSFCellStyle line2Sytle = wb.createCellStyle();  
	    HSSFRow row2 = sheet.createRow(1);//第二行
	    Font font2 = wb.createFont();
	    font2.setFontName("宋体");
	    font2.setFontHeightInPoints((short) 11);//字体(大小,类型,加粗，颜色) 边框  背景 
	    font2.setColor(HSSFColor.RED.index);
	    line2Sytle.setFont(font2);
//	    contentStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
//	    contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
//	    contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
//	    contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型
	    HSSFCell cell = row2.createCell(0);  //第2行第1列
	    cell.setCellValue("到港日期");

	    cell.setCellStyle(line2Sytle);  
	    
	    cell = row2.createCell(2);  
	    cell.setCellValue("时间");  
	    HSSFCellStyle contentStyle1 = wb.createCellStyle();  
	    contentStyle1.setFont(font2);
	    cell.setCellStyle(contentStyle1); 
	    
	    HSSFCellStyle secondLineStyle = wb.createCellStyle();
	    secondLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框类型 
	    secondLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
	    secondLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
	    secondLineStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
	   
//	    HSSFRow row2 = sheet.createRow(1);//第二行
        
//    	for(int column = 0;column<7;column++){
//    		HSSFCell cellTemp = row2.createCell(column);  
//    		cellTemp.setCellStyle(secondLineStyle); 
//    	}
    	HSSFCell cell2_7 = row2.createCell(7);//第二行7列
    	HSSFCellStyle secondSpeciaRLineStyle = wb.createCellStyle();
    	secondSpeciaRLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框类型 
    	secondSpeciaRLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
    	secondSpeciaRLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
    	secondSpeciaRLineStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框类型
    	cell2_7.setCellStyle(secondSpeciaRLineStyle);
    	
	    
//	    setFontStyle();
	    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
//	    HSSFRow row1 = sheet.createRow(0);  

//	    HSSFow row = sheet.create
	    // 第四步，创建单元格，并设置值表头 设置表头居中  
//	    HSSFCellStyle titleStyle = wb.createCellStyle();  
	    //设置边框
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
	
	//设置标题
	static void setDefaultTitle(HSSFSheet sheet,HSSFWorkbook wb){
		//标题
	    HSSFRow row1 = sheet.createRow(0);
	    HSSFCell cell = row1.createCell(0);  
	    HSSFCellStyle titleStyle = wb.createCellStyle(); 
	    titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 标题创建一个居中格式
	    titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    Font font = wb.createFont();
	    font.setFontName("宋体");//字体类型
	    font.setFontHeightInPoints((short) 20);//字体大小
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    titleStyle.setFont(font);
	    cell.setCellStyle(titleStyle);
	    cell.setCellValue("客户资料登记表");  
	}
	static void setBorder(HSSFWorkbook wb,HSSFSheet sheet){
		//设置边框 
	    HSSFCellStyle defaultStyle = wb.createCellStyle();  
	    defaultStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框类型
	    defaultStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
	    defaultStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
	    defaultStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型  
	    //有边框加粗
	    HSSFCellStyle specialRightStyle = wb.createCellStyle();  
	    specialRightStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框类型
	    specialRightStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
	    specialRightStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框类型
	    specialRightStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型
	    
	    for(int rowNum = 1;rowNum<120;rowNum++){
	        HSSFRow row = sheet.createRow(rowNum);//第二行
	        
	    	for(int column = 0;column<8;column++){
	    		HSSFCell cell = row.createCell(column);
	    		if(column == 7){
	    			cell.setCellStyle(specialRightStyle); 
	    		}else{
	    			cell.setCellStyle(defaultStyle);
	    		}
	    	}
	    }
	    //第二行上下右边框加粗
	    HSSFCellStyle secondLineStyle = wb.createCellStyle();
	    secondLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框类型 
	    secondLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
	    secondLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
	    secondLineStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
	   
	    HSSFRow row2 = sheet.createRow(1);//第二行
        
    	for(int column = 0;column<7;column++){
    		HSSFCell cell = row2.createCell(column);  
    	    cell.setCellStyle(secondLineStyle); 
    	}
    	HSSFCell cell2_7 = row2.createCell(7);//第二行7列
    	HSSFCellStyle secondSpeciaRLineStyle = wb.createCellStyle();
    	secondSpeciaRLineStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);//上边框类型 
    	secondSpeciaRLineStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
    	secondSpeciaRLineStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
    	secondSpeciaRLineStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框类型
    	cell2_7.setCellStyle(secondSpeciaRLineStyle);
	}
	static void mergeCell(HSSFSheet sheet){
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7)); //标题合并单元格title第一行
	    
	    sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));//第2行
	    
	    sheet.addMergedRegion(new CellRangeAddress(2, 2, 0 ,7));//第3行
	    
	    sheet.addMergedRegion(new CellRangeAddress(4, 4, 1 ,3));//第5行
	    sheet.addMergedRegion(new CellRangeAddress(4, 4, 4 ,5));//第5行
	    sheet.addMergedRegion(new CellRangeAddress(4, 4, 6 ,7));//第5行
	    
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1 ,3));//第6行
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 4 ,5));//第6行
	    sheet.addMergedRegion(new CellRangeAddress(5, 5, 6 ,7));//第6行
	    
	    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1 ,7));//第7行
	    
	    sheet.addMergedRegion(new CellRangeAddress(7, 7, 1 ,7));//第8行
	    
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 1 ,3));//第9行
	    sheet.addMergedRegion(new CellRangeAddress(8, 8, 5 ,7));//第9行
	    
	    sheet.addMergedRegion(new CellRangeAddress(9, 9, 1 ,3));//第10行
	    
	    sheet.addMergedRegion(new CellRangeAddress(10, 10, 1 ,7));//第11行
	    
	    sheet.addMergedRegion(new CellRangeAddress(11, 11, 1 ,3));//第12行
	    
	    sheet.addMergedRegion(new CellRangeAddress(12, 12, 0 ,7));//第13行
	    
	    sheet.addMergedRegion(new CellRangeAddress(14, 14, 1 ,3));//第15行
	    sheet.addMergedRegion(new CellRangeAddress(14, 14, 4 ,5));//第15行
	    
	    sheet.addMergedRegion(new CellRangeAddress(15, 15, 1 ,3));//第16行
	    sheet.addMergedRegion(new CellRangeAddress(15, 15, 4 ,5));//第16行
	    
	    sheet.addMergedRegion(new CellRangeAddress(16, 16, 1 ,7));//第17行
	    
	    sheet.addMergedRegion(new CellRangeAddress(17, 17, 1 ,7));//第18行
	    
	    sheet.addMergedRegion(new CellRangeAddress(18, 18, 1 ,3));//第19行
	    sheet.addMergedRegion(new CellRangeAddress(18, 18, 5 ,7));//第19行
	    
	    sheet.addMergedRegion(new CellRangeAddress(19, 19, 1 ,3));//第20行
	    
	    sheet.addMergedRegion(new CellRangeAddress(20, 20, 1 ,7));//第21行
	    
	    sheet.addMergedRegion(new CellRangeAddress(21, 21, 1 ,3));//第22行
	    
	    sheet.addMergedRegion(new CellRangeAddress(22, 22, 0 ,7));//第23行

	    sheet.addMergedRegion(new CellRangeAddress(23, 23, 1 ,3));//第24行
	    sheet.addMergedRegion(new CellRangeAddress(23, 23, 4 ,5));//第24行
	    sheet.addMergedRegion(new CellRangeAddress(23, 23, 6 ,7));//第24行
	    
	    int row = 24;//第25行。
	    int rowSum = row + 2;//被保人所有现行生效或申请中之保险保障总数
	    for(;row<rowSum;row++){//
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,3));//第25行
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 4 ,5));//第25行
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 6 ,7));//第25行
	    }
	    
//	    sheet.addMergedRegion(new CellRangeAddress(24, 24, 1 ,3));//第25行
//	    sheet.addMergedRegion(new CellRangeAddress(25, 25, 1 ,3));//第26行
//	    sheet.addMergedRegion(new CellRangeAddress(24, 24, 4 ,5));//第25行
//	    sheet.addMergedRegion(new CellRangeAddress(25, 25, 4 ,5));//第26行
//	    sheet.addMergedRegion(new CellRangeAddress(24, 24, 6 ,7));//第25行
//	    sheet.addMergedRegion(new CellRangeAddress(25, 25, 6 ,7));//第26行
	    
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//第27行
	    row++;//第28行
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,3));//第28行
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 4 ,5));//第28行
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 6 ,7));//第28行
	    row++;
	    rowSum = row + 2;//被保人所有现行生效或申请中之保险保障总数
	    for(;row<rowSum;row++){//
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,3));//第29行与30行
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 4 ,5));//第25行与30行
		    sheet.addMergedRegion(new CellRangeAddress(row, row, 6 ,7));//第25行与30行
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//第31行
	    row++;//第32行
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 2 ,3));//第32行
	    row++;
	    rowSum = row + 3;//欲申请购买保险保障
	    for(;row<rowSum;row++){
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 2 ,3));//第33行到35行
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//第36行
	    row++;//第37行
	    rowSum = row + 3;
	    for(;row<rowSum;row++){
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 1 ,2));//第37行到39行
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 3 ,4));//第37行到39行
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 5 ,7));//第37行到39行
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//第40行
	    row++;//41行
	    rowSum = row+4;//41到44行
	    for(;row<rowSum;row++){
	    	sheet.addMergedRegion(new CellRangeAddress(row, row, 2 ,4));//第41行到44行
	      	sheet.addMergedRegion(new CellRangeAddress(row, row, 5 ,6));//第41行到44行
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row, row, 0 ,7));//第45行
//	    row++;
//	    rowSum = row + 37;//46-82行
	    for(int i = 0;i<37;i++){
	    	row++;
	    	if(i==26||i==28){
	    		sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));
	    		continue;
	    	}
	    	sheet.addMergedRegion(new CellRangeAddress(row,row,0,6));
	    }
	    sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));//第82行
	    row++;
	    sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));//第83行
//	    row++;
	    /*rowSum = row + 37;//84-120行
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
	    sheet.addMergedRegion(new CellRangeAddress(row,row,0,7));//第120行
	}
}
