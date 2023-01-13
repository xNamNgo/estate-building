package com.laptrinhjavaweb.utils;

public class NumberUtils {
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch(NumberFormatException ex) {
			return false;
		}
	}
}
