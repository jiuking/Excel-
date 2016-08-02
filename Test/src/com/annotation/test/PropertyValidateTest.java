package com.annotation.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * excel reader
 * @author TontoZhou
 *
 */
public class PropertyValidateTest<T> {
		
	private Map<String,FieldSet> fieldCacheMap;
	private Map<Class<?>,PropertyConvert<?>> convertCacheMap;
	private Map<Class<?>,PropertyValidate> validateCacheMap;
	
	private ISheet sheet;
	private int currentRowIndex;
	private int lastRowIndex;
	
	private List<FieldSet> validFieldSetList;
	private Class<T> clazz;
	
	/**�Ƿ��ڶ�ȡһ�м�¼�����ݴ��������¼�����һ��*/
	private boolean continueIfDataError=false;
	/**
	 * 
	 * @param clazz ��Ҫ����ȡӳ�䵽��Class
	 * @param sheet excel��Դ
	 * @throws ExcelReadException ���ܴ���{@link PropertyConvert}ʵ��ʱ���׳�
	 */
	public PropertyValidateTest(Class<T> clazz,ISheet sheet) throws ExcelReadException
	{	
		this(clazz,sheet,-1);		
	}
	
	public PropertyValidateTest(Class<T> clazz,ISheet sheet,int titleRowIndex) throws ExcelReadException
	{
		fieldCacheMap=new HashMap<String,FieldSet>();
		
		
		if(sheet==null)
			throw new ExcelReadException("Sheet����ΪNull");
		if(clazz==null)
			throw new ExcelReadException("Class����ΪNull");
		
		this.clazz=clazz;
		this.sheet=sheet;
		
		lastRowIndex=sheet.getLastRowNum();
		currentRowIndex=0;
		
		// ��������Ҳɨ��
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Property property = field.getAnnotation(Property.class);
			if (property != null) {
				// ��������
				field.setAccessible(true);

			
				String name = property.name();
				
				if ("".equals(name)) {
					name = field.getName();
				}
				
				FieldSet fieldset=new FieldSet();
				fieldset.field=field;
				fieldset.type=property.type();
				fieldset.nullable=property.nullable();
				
				if(property.minLength()>0)
					fieldset.minLength=property.minLength();
				
				if(property.maxLength()>0)
					fieldset.maxLength=property.maxLength();
				
				String regex=property.regex();
				if(regex!=null&&!"".equals(regex))
					fieldset.pattern=Pattern.compile(regex);
				
				Convert convert=field.getAnnotation(Convert.class);
				if(convert !=null)
				{
					Class<?> cla=convert.convert();
					
					if(convertCacheMap==null)
						convertCacheMap=new HashMap<Class<?>,PropertyConvert<?>>();
					
					PropertyConvert<?> propertyconvert=convertCacheMap.get(cla);
					
					if(propertyconvert==null)
					{
						try {
							propertyconvert=(PropertyConvert<?>) cla.newInstance();
						} catch (Exception e) {
							e.printStackTrace();
							throw new ExcelReadException("���ܴ���"+cla.getName()+"��ʵ��");
						}
						
						convertCacheMap.put(cla, propertyconvert);
					}	
					
					fieldset.convert=propertyconvert;
				}
				
				Validate validate=field.getAnnotation(Validate.class);
				if(validate !=null)
				{
					Class<?> cla=validate.validate();
					
					if(validateCacheMap==null)
						validateCacheMap=new HashMap<Class<?>,PropertyValidate>();
					
					PropertyValidate valueValidate=validateCacheMap.get(cla);
					
					if(valueValidate==null)
					{
						try {
							valueValidate=(PropertyValidate) cla.newInstance();
						} catch (Exception e) {
							e.printStackTrace();
							throw new ExcelReadException("���ܴ���"+cla.getName()+"��ʵ��");
						}
						
						validateCacheMap.put(cla, valueValidate);
					}
					
					fieldset.validate=valueValidate;
				}
				
				fieldCacheMap.put(name, fieldset);
			}
		}	
		
		int size=fieldCacheMap.keySet().size();
		
		if(size<=0)
			throw new ExcelReadException("û���κ���Ҫ��ȡ������");
		
		validFieldSetList=new ArrayList<FieldSet>(size);
		
		boolean result=false;
		
		if(titleRowIndex>0&&titleRowIndex<lastRowIndex)
		{
			result=readColumn(titleRowIndex);
			if(result)
				currentRowIndex=++titleRowIndex;
		}
		
		if(!result)
		{
			result=readColumn();
		}
		
		if(!result)
			throw new ExcelReadException("û���ҵ��κζ�Ӧ��������");
		
		if(fieldCacheMap.size()!=validFieldSetList.size())
			throw new ExcelReadException("û���ҵ�ȫ����Ӧ��������");
	}
	
	/**
	 * ˳���ȡ��
	 * @return
	 */
	private boolean readColumn()
	{
		for(;currentRowIndex<=lastRowIndex;)
		{
			if(readColumn(currentRowIndex++))
				return true;
		}
		return false;
	}
	
	/**
	 * ����ҵ�һ��Cell������ƥ�䵽���ԣ���ȷ�϶�ȡ���������У����Ұ���������õ�FieldSet��
	 * @param rowIndex
	 * @return
	 */
	private boolean readColumn(int rowIndex)
	{
		boolean result=false;
		IRow row=sheet.getRow(rowIndex);
		if(row != null)
		{
			for(int i=0;i<row.getLastCellNum();i++)
			{
				ICell cell=row.getCell(i);
				if(cell==null)
					continue;
				String name;
				try {
					name = cell.getString();
					FieldSet fieldset=fieldCacheMap.get(name);
					if(fieldset!=null)
					{
						fieldset.name=name;
						fieldset.cellIndex=i;
						validFieldSetList.add(fieldset);
						result=true;
					}
				} catch (ValueFormatException e) {
					e.printStackTrace();
				}
				
			}	
		}
		return result;
	}
	
	/***
	 * 
	 * @return
	 * @throws ExcelReadException	��������ʱδָ������������
	 * @throws ValueFormatException cell��ֵ��ʽ����ʱ���׳�
	 */
	public T readRow() throws ExcelReadException, ValueFormatException
	{
		int rowindex=currentRowIndex++;
		
		IRow row=sheet.getRow(rowindex);
		
		if(row==null)
			return null;
		
		T obj=null;
		
		try {
			obj=clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelReadException("�޷�����ת����ʵ����ȷ���Ƿ����޲����Ĺ��캯�����ڣ�");
		} 
		
		for(int i=0;i<validFieldSetList.size();i++)
		{
			FieldSet fieldset=validFieldSetList.get(i);
			
			ICell cell=row.getCell(fieldset.cellIndex);
			
			Object value=null;
			
			if(cell!=null)
			{
				if(fieldset.convert!=null)
				{
					try{
						value=fieldset.convert.convert(cell.getString());				
					}
					catch(ValueFormatException e)
					{
						throw new ValueFormatException(rowindex,fieldset.name,e.getReason()); 
					}
				}
				else
				{
					PropertyType type=fieldset.type;
					if(type==PropertyType.DATE)
						value=cell.getDate();
					else if(type==PropertyType.DOUBLE)
						value=cell.getDouble();
					else if(type==PropertyType.INTEGER)
						value=cell.getInteger();
					else if(type==PropertyType.LONG)
						value=cell.getLong();
					else if(type==PropertyType.BOOLEAN)
						value=cell.getBoolean();
					else if(type==PropertyType.SHORT)
					    value=cell.getShort();
					else 
						value=cell.getString();	
				}
			}
			
			if(value==null)
			{
				if(!fieldset.nullable)
					throw new ValueFormatException(rowindex,fieldset.name,"����Ϊ��");
				continue;				
			}
			
			if(value instanceof String)
			{
				String s=(String) value;
				int size=s.length();
				if(fieldset.minLength!=null&&size<fieldset.minLength)
				{
					throw new ValueFormatException(rowindex,fieldset.name,"���Ȳ�������"+fieldset.minLength);
				}	
				
				if(fieldset.maxLength!=null&&size>fieldset.maxLength)
				{
					throw new ValueFormatException(rowindex,fieldset.name,"���Ȳ��ܳ���"+fieldset.maxLength);
				}
			}
			
			if(fieldset.pattern!=null)
			{
				if(!fieldset.pattern.matcher(value.toString()).matches())
					throw new ValueFormatException(rowindex,fieldset.name,"��ʽ����"); 
			}

			
			try {
				fieldset.field.set(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ExcelReadException("�޷���"+fieldset.name+"��ֵ��ֵ��ʵ����");
			} 
		}
		
		for(int i=0;i<validFieldSetList.size();i++)
		{
			FieldSet fieldset=validFieldSetList.get(i);
			
			if(fieldset.validate!=null)
			{
				Object value;
				try {
					value = fieldset.field.get(obj);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ExcelReadException("�޷���ȡ��ʵ��������:"+fieldset.name+"��ֵ");
				} 
				
				if(!fieldset.validate.validate(obj,value))
					throw new ValueFormatException(rowindex,fieldset.name,"��ʽ����");
				
			}
		}
		
		
		return obj;
	}
	
	public List<T> readRows() throws ExcelReadException, ValueFormatException
	{
		List<T> resultList=new ArrayList<T>(lastRowIndex-currentRowIndex+1);
		for(;currentRowIndex<=lastRowIndex;)
		{
			T obj=null;
			
			
			try {
				obj = readRow();
			}  catch (ValueFormatException e) {				
				if(!continueIfDataError)
					throw e;
				else
				{
					e.printStackTrace();
					continue;
				}
			}
				
			if(obj!=null)
				resultList.add(obj);
		}
		
		return resultList;
	}
	
	/**
	 * �Ƿ��ڶ�ȡһ�м�¼�����ݴ��������¼�����һ��
	 */
	public boolean isContinueIfDataError() {
		return continueIfDataError;
	}
	/**
	 * �Ƿ��ڶ�ȡһ�м�¼�����ݴ��������¼�����һ��
	 */
	public void setContinueIfDataError(boolean continueIfDataError) {
		this.continueIfDataError = continueIfDataError;
	}
	
	class FieldSet{
		//��Ӧexcel�ϵ�����
		String name;
		//��Ӧʵ���е�����
		Field field;
		//��������
		PropertyType type;
		//�Ƿ��Ϊ��
		boolean nullable=true;
		//��Ӧexcel�������
		int cellIndex=-1;
		//����У��ֵ
		Pattern pattern;
		//ת��ֵ����
		PropertyConvert<?> convert;
		//У��ֵ����
		PropertyValidate validate;
		
		//���Ϊ�ַ����Ļ����ַ�����С���ȣ�null�����
		Integer minLength;
		//���Ϊ�ַ����Ļ����ַ�����󳤶ȣ�null�����
		Integer maxLength;
	}
	
}
