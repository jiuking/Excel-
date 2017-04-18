import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;

public class Excel3Test {
	public static void main(String[] args) throws Exception {
		
			//读取excel：
			InputStream is = new FileInputStream("originalexcel\\客户资料表.xls");
			POIFSFileSystem fs = new POIFSFileSystem(is);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			
			HSSFCellStyle titleStyle = wb.createCellStyle(); 
		    titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 标题创建一个居中格式
		    titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFRow  row;
			//读取sheet，读取sheet里面第一行第一列内容
			HSSFSheet sheet = wb.getSheetAt(0);
			row = sheet.getRow(3);
			System.out.println(row.getCell(0).getStringCellValue());
			HSSFCell hsCell11 = row.createCell(1);
			hsCell11.setCellValue("43saf");
			hsCell11.setCellStyle(titleStyle);
			int currentRowNum = 25;//如有下移的起始位置 被保人所有现行生效或申请中之保险保障
			int totleRowNum = 120;//总行数
//			int currentRowNum = 25;//目前所在行数
			//下移1行 这儿1为变量，依据数据库查询条件增加。无或者为0；
			int addInvesterInsureType = 2; 
//			for(int i = 0;i<addInvesterInsureType;i++){
			sheet.shiftRows(currentRowNum,totleRowNum - 1,addInvesterInsureType);
//				totleRowNum += i;//下移一行加总行数加1
//				System.out.println(totleRowNum); 121
//			}

//			currentRowNum += 1;//当前行号加1
		
			//需要下移的行号
			row = sheet.createRow(currentRowNum);
			sheet.addMergedRegion(new CellRangeAddress(currentRowNum, currentRowNum, 1 ,3));//第25行
		    sheet.addMergedRegion(new CellRangeAddress(currentRowNum, currentRowNum, 4 ,5));//第25行
		    sheet.addMergedRegion(new CellRangeAddress(currentRowNum, currentRowNum, 6 ,7));//第25行
//			sheet.addMergedRegion(region.copy()); 
			row.setHeight((short)450);
//			copyRow(wb,row,newRow,true);
			
//			currentRowNum += addInvesterInsureType;
			System.out.println("1---"+currentRowNum);
			HSSFCellStyle defaultStyle = wb.createCellStyle();  
		    defaultStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框类型
		    defaultStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
		    defaultStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框类型
		    defaultStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型  
			
		    HSSFCellStyle specialRightStyle = wb.createCellStyle();  
		    specialRightStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框类型
		    specialRightStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框类型
		    specialRightStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//右边框类型
		    specialRightStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框类型
		    
			HSSFCell bCell =row.createCell(0);
			bCell.setCellValue("afds");
			bCell.setCellStyle(defaultStyle);
//			hsCell11.setCellValue("TESTVALUE");
			HSSFCell bCell1 =row.createCell(1);
			bCell1.setCellValue("aaa");
			HSSFCell cCell = row.createCell(6);
			cCell.setCellValue("2016/12/10");
			HSSFCell lastCell = row.createCell(7);
			lastCell.setCellStyle(specialRightStyle);
			
			row = sheet.createRow(currentRowNum+1);
			HSSFCell cCell1 = row.createCell(0);
			cCell1.setCellValue("as12343");
			
			currentRowNum += addInvesterInsureType;//此项的末尾添加
			
//			添加 被保人 所有现行生效或申请中之保险保障 一项 之后 行号 需要调整 +4 为默认的重疾保险其中包括 上一项的 请选择一行。
			currentRowNum += 4;
			System.out.println(currentRowNum);
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
			//请选择这行向下移动 还需加判断  如果有 投保人所有现行生效或申请中之保险保障 一项
			totleRowNum += addInvesterInsureType;
			//假设只存在1项
			addInvesterInsureType = 1;
//			currentRowNum += 1;
			System.out.println("totalRow---"+totleRowNum);
			
			sheet.shiftRows(currentRowNum,totleRowNum - 1,addInvesterInsureType);
			row = sheet.createRow(currentRowNum);
			HSSFCell cell1 = row.createCell(0);
			cell1.setCellValue("asdf");
			cell1.setCellStyle(sheet.getRow(currentRowNum-1).getCell(0).getCellStyle());
//			cell1.setCellStyle(sheet.get);
			currentRowNum += addInvesterInsureType;
//			欲申请购买保险保障 上一项包含 请选择一行
			currentRowNum += 5;
			totleRowNum += addInvesterInsureType;
			//请选择这行向下移动 还需加判断  如果有 投保人 所有现行生效或申请中之保险保障 一项 
			//假设只存在1项
			addInvesterInsureType = 2;
//			currentRowNum += addInvesterInsureType;
			System.out.println("3---"+currentRowNum);
			System.out.println("totalRow111---"+totleRowNum);
			sheet.shiftRows(currentRowNum,totleRowNum - 1 ,addInvesterInsureType);
			row = sheet.createRow(currentRowNum);
			HSSFCell cell2 = row.createCell(0);
			cell2.setCellValue("asdf");
			totleRowNum += addInvesterInsureType;
			System.out.println(totleRowNum+"---last");
			//健康资料申报
			currentRowNum += 4;
			//被保人
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
			//持有人
			currentRowNum += 1;
			System.out.println(sheet.getRow(currentRowNum).getCell(0));

			//收益人资料 如果有的话
			currentRowNum += 3;
			int receiptorSum = 1;//查询数据库 假设最多3个。假设存在1个
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
			currentRowNum += 1;
			//假设收益人只有一个只有1个
			currentRowNum += 3 - receiptorSum;
			currentRowNum += 1;
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
//			sheet.addMergedRegion(new CellRangeAddress(26, 26, 1 ,3));//第25行
//		    sheet.addMergedRegion(new CellRangeAddress(26, 26, 4 ,5));//第25行
//		    sheet.addMergedRegion(new CellRangeAddress(26, 26, 6 ,7));//第25行
			//上一一格
//			for(int i = 25;i<121;i++){
//				sheet.shiftRows(i,i,1);
//			}
//			ExcelTest1.mergeCell(sheet);
			
//			sheet.addMergedRegion(new CellRangeAddress(27, 27, 1 ,3));//第25行
//		    sheet.addMergedRegion(new CellRangeAddress(27, 27, 4 ,5));//第25行
//		    sheet.addMergedRegion(new CellRangeAddress(27, 27, 6 ,7));//第25行
			FileOutputStream fos = new FileOutputStream("e:\\test3.xls");
			wb.write(fos);
			fos.close();
	}
	/** 
     * 复制一个单元格样式到目的单元格样式 
     * @param fromStyle 
     * @param toStyle 
     */  
    public static void copyCellStyle(HSSFCellStyle fromStyle,  
            HSSFCellStyle toStyle) {  
        toStyle.setAlignment(fromStyle.getAlignment());  
        //边框和边框颜色  
        toStyle.setBorderBottom(fromStyle.getBorderBottom());  
        toStyle.setBorderLeft(fromStyle.getBorderLeft());  
        toStyle.setBorderRight(fromStyle.getBorderRight());  
        toStyle.setBorderTop(fromStyle.getBorderTop());  
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());  
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());  
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());  
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());  
          
        //背景和前景  
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());  
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());  
          
        toStyle.setDataFormat(fromStyle.getDataFormat());  
        toStyle.setFillPattern(fromStyle.getFillPattern());  
//      toStyle.setFont(fromStyle.getFont(null));  
        toStyle.setHidden(fromStyle.getHidden());  
        toStyle.setIndention(fromStyle.getIndention());//首行缩进  
        toStyle.setLocked(fromStyle.getLocked());  
        toStyle.setRotation(fromStyle.getRotation());//旋转  
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());  
        toStyle.setWrapText(fromStyle.getWrapText());  
          
    }  
	public static void copyCell(HSSFWorkbook wb,HSSFCell srcCell, HSSFCell distCell,  
            boolean copyValueFlag) {  
        HSSFCellStyle newstyle=wb.createCellStyle();  
        copyCellStyle(srcCell.getCellStyle(), newstyle);  
//        distCell.setEncoding(srcCell.getEncoding());  
        //样式  
        distCell.setCellStyle(newstyle);  
        //评论  
        if (srcCell.getCellComment() != null) {  
            distCell.setCellComment(srcCell.getCellComment());  
        }  
        // 不同数据类型处理  
        int srcCellType = srcCell.getCellType();  
        distCell.setCellType(srcCellType);  
        if (copyValueFlag) {  
            if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {  
                if (HSSFDateUtil.isCellDateFormatted(srcCell)) {  
                    distCell.setCellValue(srcCell.getDateCellValue());  
                } else {  
                    distCell.setCellValue(srcCell.getNumericCellValue());  
                }  
            } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {  
                distCell.setCellValue(srcCell.getRichStringCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {  
                // nothing21  
            } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {  
                distCell.setCellValue(srcCell.getBooleanCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {  
                distCell.setCellErrorValue(srcCell.getErrorCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {  
                distCell.setCellFormula(srcCell.getCellFormula());  
            } else { // nothing29  
            }  
        }  
    }  
	
    public static void copyRow(HSSFWorkbook wb,HSSFRow fromRow,HSSFRow toRow,boolean copyValueFlag){  
    	 int i = 0;
    	for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext();) {  
            HSSFCell tmpCell = (HSSFCell) cellIt.next();  
            HSSFCell newCell = toRow.createCell(tmpCell.getCellNum());  
            copyCell(wb,tmpCell, newCell, copyValueFlag);  
           
            System.out.println(i);
            i++;
        }            
    }  
}
