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
	// 第一步，创建一个webbook，对应一个Excel文件  
    HSSFWorkbook wb = new HSSFWorkbook();  
    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
    HSSFSheet sheet = wb.createSheet("客户资料表");  

    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
    HSSFRow row1 = sheet.createRow(0);  
    // 第四步，创建单元格，并设置值表头 设置表头居中  
    HSSFCellStyle titleStyle = wb.createCellStyle();  
    sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7)); //标题合并单元格
    titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 标题创建一个居中格式  
    titleStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
    titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
    titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
    titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型
    Font font = wb.createFont();
    font.setFontName("宋体");//字体类型
    font.setFontHeightInPoints((short) 20);//字体大小
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
    cell.setCellValue("客户资料登记表");  
    cell.setCellStyle(titleStyle);  
  
    HSSFCellStyle contentStyle = wb.createCellStyle();  
    HSSFRow row2 = sheet.createRow(1);//第二行
    Font font2 = wb.createFont();
    font2.setFontName("宋体");
    font2.setFontHeightInPoints((short) 11);//字体(大小,类型,加粗，颜色) 边框  背景 
    font2.setColor(HSSFColor.RED.index);
    contentStyle.setFont(font2);
//    contentStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);//下边框类型
//    contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
//    contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
//    contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型
    cell = row2.createCell(0);  
    cell.setCellValue("现在居住地址(通讯地址)");

    cell.setCellStyle(contentStyle);  
    
    cell = row2.createCell(2);  
    cell.setCellValue("时间");  
    
    font2.setFontName("宋体");
    font2.setFontHeightInPoints((short) 11);//字体大小
    font2.setColor(HSSFColor.RED.index);
    HSSFCellStyle contentStyle1 = wb.createCellStyle();  
    contentStyle1.setFont(font2);
    contentStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框类型
    contentStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
    contentStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
    contentStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型    
    cell.setCellStyle(contentStyle1); 
    sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 7));
    cell = row2.createCell(3);
    cell.setCellValue("啊阿斯顿发顺丰的阿斯顿发三大佛 阿斯顿发发大水发沙发大师傅大是大非 ");
    cell.setCellStyle(contentStyle1);
//    cell.setCellStyle(style);  
//    cell = row2.createCell(3);  
//    cell.setCellValue("生日");  
//    cell.setCellStyle(style);  

    // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
//    List list = CreateSimpleExcelToDisk.getStudent();  

/*    for (int i = 0; i < 3; i++)  
    {  
        row = sheet.createRow((int) i + 1);  
//        Student stu = (Student) list.get(i);  
        // 第四步，创建单元格，并设置值  
        row.createCell(0).setCellValue((double) i);  
        row.createCell(1).setCellValue("张"+i);  
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
    // 第六步，将文件存到指定位置  
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
