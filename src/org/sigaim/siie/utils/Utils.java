package org.sigaim.siie.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	//Class comparison for different conventions
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
	public static boolean stringIsISO136065Date(String value) {
	
		 String pattern = "[1-2][0-9]{3,3}(((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9]|3[0-1])(([0-1][0-9]|2[0-3])([0-5][0-9]([0-5][0-9](\\.[0-9]{1,4})?)?)?)?)?)?([+\\-](0[0-9]|1[0-3])([0-5][0-9]))?";


	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(value);
	      return m.find();
	}
	public static String longestCommonPrefix(String s, String s2) {
        String small,large;
         if(s.length() > s2.length()) 
            {small = s2;large = s;}
          else
            {small = s;large = s2;}
        int index = 0;    
        for(char c: large.toCharArray())
        {
            if(index==small.length()) break;
            if(c != small.charAt(index)) break;
            index++;
        }
        if(index==0)
        	return "";
        else
          return large.substring(0,index);
	}
}
