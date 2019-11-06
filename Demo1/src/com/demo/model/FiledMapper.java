package com.demo.model;

import java.lang.annotation.Documented;   
import java.lang.annotation.Inherited;   
import java.lang.annotation.Retention;   
import java.lang.annotation.Target;   
import java.lang.annotation.ElementType;   
import java.lang.annotation.RetentionPolicy;   
@Target(ElementType.FIELD)   
@Retention(RetentionPolicy.RUNTIME)   
@Documented 
@Inherited 

public @interface FiledMapper {
	//public int id(); 
	public String filed();

}
