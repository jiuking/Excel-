package com.annotation.test;

public class ValueFormatException extends Exception{
	
	private static final long serialVersionUID = 8689153131957489882L;

	private int row;
	private String name;
	private String reason;
	
	public ValueFormatException(int row,String name,String reason)
	{
		super();
		this.row=row;
		this.name=name;
		this.reason=reason;
	}
	
	public ValueFormatException(String msg)
	{
		super();
		reason=msg;	
	}
	
	public ValueFormatException()
	{
		super();
	}
	
	public String getMessage()
	{
		String msg="";
		if(row>0)
			msg+="��ȡ��"+row+"�����ݴ���";
		else
			msg+="��ȡ���ݴ���";
		
		if(name!=null)
			msg+=name;
		
		if(reason!=null)
			msg+=reason;
		return msg;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
