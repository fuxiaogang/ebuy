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

	//������Java��ͷ,�����β���ַ���
	public static void test1(){
		Pattern pattern = Pattern.compile("^java.*");
		Matcher matcher = pattern.matcher("java ���");
		Matcher matcher1 = pattern.matcher("aa java ���");
		
		System.out.println(matcher.matches());
		System.out.println(matcher1.matches());
	}
	
	//�Զ������ָ��ַ���ʱ
	public static void test2(){
		Pattern pattern = Pattern.compile("[, |]+");
		String strs[] = pattern.split("Java Hello World  Java,Hello,,World|Sun");
		for(String str :strs){
			System.out.println(str);
		} 	 	
	}
	
	//�����滻���״γ���/ȫ���ַ���
	public static void test3(){
//		Pattern pattern = Pattern.compile("������ʽ");
//		Matcher matcher = pattern.matcher("������ʽ Hello World,������ʽ Hello World"); 
//		System.out.println(matcher.replaceAll("Java"));
				
		Pattern pattern = Pattern.compile("������ʽ");
		Matcher matcher = pattern.matcher("������ʽ Hello World,������ʽ Hello World ");
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
		    matcher.appendReplacement(sbr, "Java");
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());
	}
	
	public static void email(){
//	    �Ϸ�E-mail��ַ��   
//	    1. �������һ������ֻ��һ�����š�@��   
//	    2. ��һ���ַ������ǡ�@�����ߡ�.��   
//	    3. ��������֡�@.������.@   
//	    4. ��β�������ַ���@�����ߡ�.��   
//	    5. ����@��ǰ���ַ��г��֡�����   
//	    6. ��������������ǰ�棬���ߡ���@��   
//	      
//	    ������ʽ���£�   
//	    -----------------------------------------------------------------------   
//	    ^(\w+((-\w+)|(\.\w+))*)\+\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$   
//	    -----------------------------------------------------------------------   
//	      
//	    �ַ�������   
//	    ^ ��ƥ������Ŀ�ʼλ�á�   
//	    \������һ���ַ����Ϊ�����ַ�������ֵ��   
//	    * ��ƥ��ǰһ���ַ���λ򼸴Ρ�   
//	    + ��ƥ��ǰһ���ַ�һ�λ��Ρ�   
//	    (pattern) ��ģʽƥ�䲢��סƥ�䡣   
//	    x|y��ƥ�� x �� y��   
//	    [a-z] ����ʾĳ����Χ�ڵ��ַ�����ָ�������ڵ��κ��ַ�ƥ�䡣   
//	    \w �����κε����ַ�ƥ�䣬�����»��ߡ�   
//	    $ ��ƥ������Ľ�β��  
	       
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
		 * Pattern.CASE_INSENSITIVE �����ִ�Сд
		 * ������http://��ͷ����֧��Я����ѯ�ַ���
		 * [\\w-\\.] ����������·��������a-z0-9_-.��ô�����ַ�
		 * (?:/|(?:/[\\w\\.\\-]+)*(?:/[\\w\\.\\-]+\\.do))? �����ʾ·������Ϊ�ա�/����.do��β
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
