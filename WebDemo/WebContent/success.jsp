<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery-1.6.4.min.js" ></script>
<link href="css/base.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
  $(function(){ 
	  $.ajax({
		  url      : 'bizController', 
		  data     : 'oper=listdata',
		  dataType : 'json',
		  success  : function(data){
			  for(i in data){  
				 var tr = "<tr>" +
				             "<td>" + i + "</td>" +
				             "<td>" + data[i].title + "</td>" +
				             "<td>" + data[i].dept + "</td>" +
				             "<td><a href='#'>编辑</a> &nbsp<a href='bizController?oper=delete&id="+data[i].id+"'>删除</a></td>" +
				           "</tr>";
				 $('#tab').append(tr);
			  }
		  }
	  });
  });
</script>
</head>
<body> 

<input type="button" value="新增"  onclick="javascript:location.href='bizController?oper=add'"/> <p>
<table id="tab" class="bordered">
  
</table>

</body>

</html>