<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�û���¼</title>
</head>
<body> 
   <%
      if(request.getAttribute("is_error")!=null){
    	  out.println("�û������������");
      }
   %>
   <p>
   <form method="post" action="login"> 
      <table>
         <tr>
           <td>�û���</td>
           <td>
               <input type="text" id="username" name="username"/>
           </td>
         </tr>
         <tr>
           <td>����</td>
           <td>
              <input type="password" id="password" name="password"/>
           </td>
         </tr>
         <tr>
           <td colspan="2">
               <input type="submit"/>
           </td>
         </tr> 
      </table>  
   </form> 
</body>
</html>