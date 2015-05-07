package com.ourchem.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex  {
	
	public static void main(String[]args){
//	    test1();
//		test2();
//		test3();
//		email();
		url();
	}

	//查找以Java开头,任意结尾的字符串
	public static void test1(){
		Pattern pattern = Pattern.compile("^java.*");
		Matcher matcher = pattern.matcher("java 你好");
		Matcher matcher1 = pattern.matcher("aa java 你好");
		
		System.out.println(matcher.matches());
		System.out.println(matcher1.matches());
	}
	
	//以多条件分割字符串时
	public static void test2(){
		Pattern pattern = Pattern.compile("[, |]+");
		String strs[] = pattern.split("Java Hello World  Java,Hello,,World|Sun");
		for(String str :strs){
			System.out.println(str);
		} 	 	
	}
	
	//文字替换（首次出现/全部字符）
	public static void test3(){
//		Pattern pattern = Pattern.compile("正则表达式");
//		Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World"); 
//		System.out.println(matcher.replaceAll("Java"));
				
		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
		    matcher.appendReplacement(sbr, "Java");
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());
	}
	
	public static void email(){
//	    合法E-mail地址：   
//	    1. 必须包含一个并且只有一个符号“@”   
//	    2. 第一个字符不得是“@”或者“.”   
//	    3. 不允许出现“@.”或者.@   
//	    4. 结尾不得是字符“@”或者“.”   
//	    5. 允许“@”前的字符中出现“＋”   
//	    6. 不允许“＋”在最前面，或者“＋@”   
//	      
//	    正则表达式如下：   
//	    -----------------------------------------------------------------------   
//	    ^(\w+((-\w+)|(\.\w+))*)\+\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$   
//	    -----------------------------------------------------------------------   
//	      
//	    字符描述：   
//	    ^ ：匹配输入的开始位置。   
//	    \：将下一个字符标记为特殊字符或字面值。   
//	    * ：匹配前一个字符零次或几次。   
//	    + ：匹配前一个字符一次或多次。   
//	    (pattern) 与模式匹配并记住匹配。   
//	    x|y：匹配 x 或 y。   
//	    [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。   
//	    \w ：与任何单词字符匹配，包括下划线。   
//	    $ ：匹配输入的结尾。  
	       
//		String patternStr = "^([a-zA-Z0-9_\\-\\.]+)"+
//	                        "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))" +
//				             "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		String patternStr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String str = "ff.f@qq.com";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
	}
	
	public static void url(){

		/* ***********************
		 * Pattern.CASE_INSENSITIVE 不区分大小写
		 * 仅仅以http://打头，不支持携带查询字符串
		 * [\\w-\\.] 限制域名和路径仅仅由a-z0-9_-.这么几个字符
		 * (?:/|(?:/[\\w\\.\\-]+)*(?:/[\\w\\.\\-]+\\.do))? 这个表示路径可以为空、/、和.do结尾
		 *************************/
	    //Pattern exp=Pattern.compile("^http://[\\w-\\.]+(?:/|(?:/[\\w\\.\\-]+)*(?:/[\\w\\.\\-]+\\.do))?$", Pattern.CASE_INSENSITIVE);
		
		Pattern exp=Pattern.compile("^http://[\\w-\\.]+(?:/|(?:/[\\w\\.\\-]+)*(?:/[\\w\\.\\-]+\\.do))?$", Pattern.CASE_INSENSITIVE);
		
		System.out.println(exp.matcher("http://202.199.160.62/validateCodeAction.do").matches());//true
		System.out.println(exp.matcher("http://202.199.160.62:").matches());//true
		System.out.println(exp.matcher("http://202.199.160.62/folder/validateCodeAction.do").matches());//true
		System.out.println(exp.matcher("http://www.baidu.com").matches());//true
		System.out.println(exp.matcher("http://www.baidu.com/").matches());//true
		System.out.println(exp.matcher("http://localhost/").matches());//true
		System.out.println(exp.matcher("http://localhost/vv.do").matches());//true
		

		System.out.println(exp.matcher("http://www.baidu.com/s").matches());//false

	}
}
