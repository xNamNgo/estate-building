package com.laptrinhjavaweb.utils;

import java.util.Map;

public class MapUtils {
	public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass) {
		Object obj = params.getOrDefault(key, null);
		
		// khi != null, sẽ còn 1 TH nữa là "" 
		// mà "" thì không thể parse sang Long,Integer -> NumberFormatException.
		if (obj != null) {
			if(tClass.getTypeName().equals("java.lang.Long")) {
				obj = (obj != "") ? Long.valueOf(obj.toString()) : null;
			} else if (tClass.getTypeName().equals("java.lang.String")) {
				obj = (obj != "") ? String.valueOf(obj.toString()) : null;
			} else if(tClass.getTypeName().equals("java.lang.Integer")) {
				obj = (obj != "") ? Integer.valueOf(obj.toString()) : null;
			}
			return tClass.cast(obj);
		}
		return null;
	}

}
