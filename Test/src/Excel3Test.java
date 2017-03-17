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
	//��ȡexcel��
			InputStream is = new FileInputStream("e:\\�ͻ����ϱ�.xls");
			POIFSFileSystem fs = new POIFSFileSystem(is);
			HSSFWorkbook wb = new HSSFWorkbook(fs);

			//��ȡsheet����ȡsheet�����һ�е�һ������
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow  row = sheet.getRow(25);
			HSSFCell cell = row.getCell(0);
			String  s = cell.getStringCellValue();

			System.out.println(s);
			FileOutputStream fos = new FileOutputStream("e:\\test3.xls");

//			HSSFWorkbook wb2 = new HSSFWorkbook();
//			HSSFSheet hsSheet = wb.createSheet("TestReport");
			row = sheet.getRow(25);//����
			HSSFCell hsCell11 = row.createCell(1);
			row.createCell(0);
//			row = sheet.createRow(26);
			HSSFRow newRow = sheet.createRow(26);
//			sheet.addMergedRegion(new CellRangeAddress(26, 26, 1 ,3));//��25��
//			sheet.addMergedRegion(new CellRangeAddress(26, 26, 4 ,5));//��25��
//			sheet.addMergedRegion(new CellRangeAddress(26, 26, 6 ,7));//��25��
			CellRangeAddress region = sheet.getMergedRegion(24); 
//			region.copy();
//			CellRangeAddress newRegion = region.copy();  
//            newRegion.setFirstRow(26);  
//            newRegion.setFirstColumn(7);  
//            newRegion.setLastRow(26);  
//            newRegion.setLastColumn(7);  
//            currentSheet.addMergedRegion(newRegion);  
			sheet.addMergedRegion(region.copy()); 
			newRow.setHeight((short)450);
//			copyRow(wb,row,newRow,true);
			
//			HSSFCell aCell = row.createCell(0);
//			aCell.setCellValue("asdfsa11");
			
			HSSFCell bCell =newRow.createCell(3);
			bCell.setCellValue("1232");
			hsCell11.setCellValue("TESTVALUE");
			wb.write(fos);
			fos.close();
	}
	/** 
     * ����һ����Ԫ����ʽ��Ŀ�ĵ�Ԫ����ʽ 
     * @param fromStyle 
     * @param toStyle 
     */  
    public static void copyCellStyle(HSSFCellStyle fromStyle,  
            HSSFCellStyle toStyle) {  
        toStyle.setAlignment(fromStyle.getAlignment());  
        //�߿�ͱ߿���ɫ  
        toStyle.setBorderBottom(fromStyle.getBorderBottom());  
        toStyle.setBorderLeft(fromStyle.getBorderLeft());  
        toStyle.setBorderRight(fromStyle.getBorderRight());  
        toStyle.setBorderTop(fromStyle.getBorderTop());  
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());  
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());  
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());  
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());  
          
        //������ǰ��  
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());  
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());  
          
        toStyle.setDataFormat(fromStyle.getDataFormat());  
        toStyle.setFillPattern(fromStyle.getFillPattern());  
//      toStyle.setFont(fromStyle.getFont(null));  
        toStyle.setHidden(fromStyle.getHidden());  
        toStyle.setIndention(fromStyle.getIndention());//��������  
        toStyle.setLocked(fromStyle.getLocked());  
        toStyle.setRotation(fromStyle.getRotation());//��ת  
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());  
        toStyle.setWrapText(fromStyle.getWrapText());  
          
    }  
	public static void copyCell(HSSFWorkbook wb,HSSFCell srcCell, HSSFCell distCell,  
            boolean copyValueFlag) {  
        HSSFCellStyle newstyle=wb.createCellStyle();  
        copyCellStyle(srcCell.getCellStyle(), newstyle);  
//        distCell.setEncoding(srcCell.getEncoding());  
        //��ʽ  
        distCell.setCellStyle(newstyle);  
        //����  
        if (srcCell.getCellComment() != null) {  
            distCell.setCellComment(srcCell.getCellComment());  
        }  
        // ��ͬ�������ʹ���  
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
