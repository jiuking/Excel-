package com.annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
	public String name() default "";
	public PropertyType type() default PropertyType.STRING; 
	public boolean nullable() default false;
	public String regex() default "";
	public int minLength() default -1;
	public int maxLength() default -1;
	public String format() default "";
}
