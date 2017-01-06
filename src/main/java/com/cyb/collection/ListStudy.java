package com.cyb.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListStudy {
 public static void main(String[] args) {
	List<String> lst = new ArrayList<String>();
	List<String> lst1 = new ArrayList<String>();
	lst.add("1");lst.add("2");
	lst1.add("a");lst1.add("b");
	//lst.addAll(lst1);
	lst1.addAll(lst);
	Pattern pat = Pattern.compile("^[a-zA-Z]");
	Matcher mat = pat.matcher("AH9800");
	boolean rs = mat.find();
	for(int i=1;i<=mat.groupCount();i++){
	System.out.println(mat.group(i));
	}
 }
}
