/*package com.annotation.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
//	@Transactional(rollbackFor=Exception.class)
//	public ResultEntity importSalesOrderByExcel(InputStream inputStream,HttpServletRequest httpRequest) throws ServiceException, UtilException {
		XSSFWorkbook workbook;
//		ExcelReader<SalesOrderExcel> reader;
//		List<SalesOrderExcel> salesOrders=null;
		try {
			workbook = new XSSFWorkbook(inputStream);
			reader = new ExcelReader<SalesOrderExcel>(SalesOrderExcel.class,new DefaultSheet(workbook.getSheetAt(0)));
			salesOrders=reader.readRows();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "����������ݲ��ܽ��������ϸ���excelģ���ʽ���б༭���ݡ�");
		} catch (ExcelReadException e) {
			e.printStackTrace();
			return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "����������ݲ��ܽ��������ϸ���excelģ���ʽ���б༭���ݡ�");
		} catch (ValueFormatException e) {
			e.printStackTrace();
			return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "�㵼������ݲ��ܽ�����"+e.getMessage());
		}	
		
		for(SalesOrderExcel salesOrderExcel:salesOrders){
			Product product = productDao.getProductByName(salesOrderExcel.getProductName());
			if(product ==null) {
				throw new ServiceException("��Ʒ����\""+salesOrderExcel.getProductName()+"\"������"); 
			}
			
			User salesman = userDao.getUserByName(salesOrderExcel.getSalesManName());
			if(salesman ==null){
				throw new ServiceException("���ʦ\""+salesOrderExcel.getSalesManName()+"\"������"); 
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("customerName", salesOrderExcel.getCustomerName());
			map.put("salesmanId", salesman.getId());
			Customer customer = customerDao.getCustomerByName(map);
			if(customer ==null){
				throw new ServiceException("���ʦ"+salesman.getName()+"�²�����Ͷ����\""+salesOrderExcel.getCustomerName()+"\""); 
			}
			
			//��������
			SalesOrder order = createSalesOrder(product,salesman.getId(),customer.getId(),salesOrderExcel);
			
			// ------------------------------���������ݶ�------------------------------------
	        salesShareService.createSalesShare(order.getId(), product, salesman.getId());
	        
	        // -------------------------------����Ͷ���˻�����Ϣ------------------------------------------
	        saveBaseData(order,customer,salesman,salesOrderExcel);
	        
	        //---------------------------------����֧����Ϣ----------------------------------------------
	        transit(order, salesman, salesOrderExcel);
	        
	        //---------------------------------������־��Ϣ-----------------------------------------------
	        addTranscationLog(order.getId(), salesman.getId(), salesOrderExcel);
	        
	        //-----------------------------------������ʧ��ԭ��----------------------------------------
	        if(NodeStatus.ORDER_STATUS_FAIL.equals(salesOrderExcel.getOrderStatus())){
	        	commentService.addComment(order,"��˲�ͨ��",order.getCreator_fk());//���ԭ��
	        }
	        
			
		}
		
		return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "�ϴ��ɹ�");
	}
}
*/