<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.6.4.min.js" ></script>
<link href="css/base.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
  $(function(){ 
    
  });
  function back(){
	 history.go(-1); 
  }
  function save(){
	  $('#formid').submit();
  }
</script>
</head>
<body> 
  <input type="button" value="返回" onclick="back();"/>
  <input type="button" value="保存" onclick="save();"/>
  <p>
   <form id="formid" action="bizController">
      <input type="hidden" name="oper" value="save"/>
      <table class="bordered" style="width:600px;">
         <tr>
           <td>内容</td>
           <td><textarea  rows="5" cols="20" name="title" id="title" style="width:90%"/></textarea></td>
         </tr>
          <tr>
           <td>部门</td>
           <td><input type="text" name="dept" style="width:90%"/></td>
         </tr>
      </table>
   </form>
</body>

</html>