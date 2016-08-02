package com.annotation.test;

public interface PropertyConvert<T> {
	public T convert(Object obj) throws ValueFormatException;
}
