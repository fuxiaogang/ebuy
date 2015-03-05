<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	  $.ajax({
		  url : 'listInfo', 
		  dataType : 'json',
		  success : function(data){
			  for(i in data){ 
				 var tr = "<tr><td>"+i+"</td><td>" + data[i].title + '</td></tr>';
				 $('#tab').append(tr);
			  }
		  }
	  });
  });
</script>
</head>
<body> 

<table id="tab" class="bordered">
  
</table>

</body>

</html>