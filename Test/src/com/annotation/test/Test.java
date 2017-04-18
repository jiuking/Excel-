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
			return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "您导入的数据不能解析，请严格按照excel模板格式进行编辑数据。");
		} catch (ExcelReadException e) {
			e.printStackTrace();
			return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "您导入的数据不能解析，请严格按照excel模板格式进行编辑数据。");
		} catch (ValueFormatException e) {
			e.printStackTrace();
			return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "你导入的数据不能解析，"+e.getMessage());
		}	
		
		for(SalesOrderExcel salesOrderExcel:salesOrders){
			Product product = productDao.getProductByName(salesOrderExcel.getProductName());
			if(product ==null) {
				throw new ServiceException("产品名称\""+salesOrderExcel.getProductName()+"\"不存在"); 
			}
			
			User salesman = userDao.getUserByName(salesOrderExcel.getSalesManName());
			if(salesman ==null){
				throw new ServiceException("理财师\""+salesOrderExcel.getSalesManName()+"\"不存在"); 
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("customerName", salesOrderExcel.getCustomerName());
			map.put("salesmanId", salesman.getId());
			Customer customer = customerDao.getCustomerByName(map);
			if(customer ==null){
				throw new ServiceException("理财师"+salesman.getName()+"下不存在投资人\""+salesOrderExcel.getCustomerName()+"\""); 
			}
			
			//创建订单
			SalesOrder order = createSalesOrder(product,salesman.getId(),customer.getId(),salesOrderExcel);
			
			// ------------------------------创建订单份额------------------------------------
	        salesShareService.createSalesShare(order.getId(), product, salesman.getId());
	        
	        // -------------------------------保存投资人基本信息------------------------------------------
	        saveBaseData(order,customer,salesman,salesOrderExcel);
	        
	        //---------------------------------保存支付信息----------------------------------------------
	        transit(order, salesman, salesOrderExcel);
	        
	        //---------------------------------保存日志信息-----------------------------------------------
	        addTranscationLog(order.getId(), salesman.getId(), salesOrderExcel);
	        
	        //-----------------------------------添加审核失败原因----------------------------------------
	        if(NodeStatus.ORDER_STATUS_FAIL.equals(salesOrderExcel.getOrderStatus())){
	        	commentService.addComment(order,"审核不通过",order.getCreator_fk());//添加原因
	        }
	        
			
		}
		
		return new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "上传成功");
	}
}
*/