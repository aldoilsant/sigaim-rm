package org.sigaim.siie.utils;

import java.util.ArrayList;

public class Utils {
	public static Boolean classNameEquals(String className1, String className2) {
		return Utils.toUppercaseNotation(className1).toLowerCase().equals(Utils.toUppercaseNotation(className2).toLowerCase());
	}
	public static String toUppercaseNotation(String input) {
		StringBuilder fullPath=new StringBuilder(input);
		for(int i=0;i<fullPath.length();i++) {
			char c=fullPath.charAt(i);
			if(c=='_') {
				//Uppercase next character
				if(i+1<fullPath.length()) {
					fullPath.setCharAt(i+1,Character.toUpperCase(fullPath.charAt(i+1)));
					fullPath.deleteCharAt(i);
				}
			}
		}
		return fullPath.toString();
	}
	public static String toUnderscoreNotation(String input) {
		StringBuilder fullPath=new StringBuilder(input);
		for(int i=0;i<fullPath.length();i++) {
			char c=fullPath.charAt(i);
			if(Character.isUpperCase(c)) {
				fullPath.setCharAt(i, Character.toLowerCase(c));
				fullPath.insert(i, "_");
			}
		}
		return fullPath.toString();
	}
	public static String unquote(String string) {
		if(string.length()==0) return string;
		StringBuilder builder=new StringBuilder(string);
		if(string.charAt(0)=='"' && string.charAt(string.length()-1)=='"') {
			builder.deleteCharAt(0);
			builder.deleteCharAt(builder.length()-1);
		}
		return builder.toString();
	}
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	public static void ensureSize(ArrayList<?> list, int size) {
	    // Prevent excessive copying while we're adding
	    list.ensureCapacity(size);
	    while (list.size() < size) {
	        list.add(null);
	    }
	}
}
