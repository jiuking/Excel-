package com.annotation.test;

import java.util.Date;

public interface ICell {
	public Date getDate() throws ValueFormatException;
	public String getString() throws ValueFormatException;
	public Integer getInteger() throws ValueFormatException;
	public Boolean getBoolean() throws ValueFormatException;
	public Double getDouble() throws ValueFormatException;
	public Long getLong() throws ValueFormatException;
	public Short getShort()throws ValueFormatException;
}
