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
			InputStream is = new FileInputStream("originalexcel\\�ͻ����ϱ�.xls");
			POIFSFileSystem fs = new POIFSFileSystem(is);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			
			HSSFCellStyle titleStyle = wb.createCellStyle(); 
		    titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ���ⴴ��һ�����и�ʽ
		    titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			
			HSSFRow  row;
			//��ȡsheet����ȡsheet�����һ�е�һ������
			HSSFSheet sheet = wb.getSheetAt(0);
			row = sheet.getRow(3);
			System.out.println(row.getCell(0).getStringCellValue());
			HSSFCell hsCell11 = row.createCell(1);
			hsCell11.setCellValue("43saf");
			hsCell11.setCellStyle(titleStyle);
			int currentRowNum = 25;//�������Ƶ���ʼλ�� ����������������Ч��������֮���ձ���
			int totleRowNum = 120;//������
//			int currentRowNum = 25;//Ŀǰ��������
			//����1�� ���1Ϊ�������������ݿ��ѯ�������ӡ��޻���Ϊ0��
			int addInvesterInsureType = 2; 
//			for(int i = 0;i<addInvesterInsureType;i++){
			sheet.shiftRows(currentRowNum,totleRowNum - 1,addInvesterInsureType);
//				totleRowNum += i;//����һ�м���������1
//				System.out.println(totleRowNum); 121
//			}

//			currentRowNum += 1;//��ǰ�кż�1
		
			//��Ҫ���Ƶ��к�
			row = sheet.createRow(currentRowNum);
			sheet.addMergedRegion(new CellRangeAddress(currentRowNum, currentRowNum, 1 ,3));//��25��
		    sheet.addMergedRegion(new CellRangeAddress(currentRowNum, currentRowNum, 4 ,5));//��25��
		    sheet.addMergedRegion(new CellRangeAddress(currentRowNum, currentRowNum, 6 ,7));//��25��
//			sheet.addMergedRegion(region.copy()); 
			row.setHeight((short)450);
//			copyRow(wb,row,newRow,true);
			
//			currentRowNum += addInvesterInsureType;
			System.out.println("1---"+currentRowNum);
			HSSFCellStyle defaultStyle = wb.createCellStyle();  
		    defaultStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//�±߿�����
		    defaultStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
		    defaultStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//�ұ߿�����
		    defaultStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����  
			
		    HSSFCellStyle specialRightStyle = wb.createCellStyle();  
		    specialRightStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//�±߿�����
		    specialRightStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//��߿�����
		    specialRightStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);//�ұ߿�����
		    specialRightStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//�ϱ߿�����
		    
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
			
			currentRowNum += addInvesterInsureType;//�����ĩβ���
			
//			��� ������ ����������Ч��������֮���ձ��� һ�� ֮�� �к� ��Ҫ���� +4 ΪĬ�ϵ��ؼ��������а��� ��һ��� ��ѡ��һ�С�
			currentRowNum += 4;
			System.out.println(currentRowNum);
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
			//��ѡ�����������ƶ� ������ж�  ����� Ͷ��������������Ч��������֮���ձ��� һ��
			totleRowNum += addInvesterInsureType;
			//����ֻ����1��
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
//			�����빺���ձ��� ��һ����� ��ѡ��һ��
			currentRowNum += 5;
			totleRowNum += addInvesterInsureType;
			//��ѡ�����������ƶ� ������ж�  ����� Ͷ���� ����������Ч��������֮���ձ��� һ�� 
			//����ֻ����1��
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
			//���������걨
			currentRowNum += 4;
			//������
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
			//������
			currentRowNum += 1;
			System.out.println(sheet.getRow(currentRowNum).getCell(0));

			//���������� ����еĻ�
			currentRowNum += 3;
			int receiptorSum = 1;//��ѯ���ݿ� �������3�����������1��
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
			currentRowNum += 1;
			//����������ֻ��һ��ֻ��1��
			currentRowNum += 3 - receiptorSum;
			currentRowNum += 1;
			System.out.println(sheet.getRow(currentRowNum).getCell(0));
//			sheet.addMergedRegion(new CellRangeAddress(26, 26, 1 ,3));//��25��
//		    sheet.addMergedRegion(new CellRangeAddress(26, 26, 4 ,5));//��25��
//		    sheet.addMergedRegion(new CellRangeAddress(26, 26, 6 ,7));//��25��
			//��һһ��
//			for(int i = 25;i<121;i++){
//				sheet.shiftRows(i,i,1);
//			}
//			ExcelTest1.mergeCell(sheet);
			
//			sheet.addMergedRegion(new CellRangeAddress(27, 27, 1 ,3));//��25��
//		    sheet.addMergedRegion(new CellRangeAddress(27, 27, 4 ,5));//��25��
//		    sheet.addMergedRegion(new CellRangeAddress(27, 27, 6 ,7));//��25��
			FileOutputStream fos = new FileOutputStream("e:\\test3.xls");
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
