package com.wira.client.rest.exception;

public class Objects {

	public static String requireNonNull(String className, String valueIfNull){
		if(className==null){
			throw new IllegalArgumentException(valueIfNull);
		}
		
		return className;
	}
}
