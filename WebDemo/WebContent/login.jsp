<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>用户登录</title>
</head>
<body> 
   <%
      if(request.getAttribute("is_error")!=null){
    	  out.println("用户名或密码错误");
      }
   %>
   <p>
   <form method="post" action="login"> 
      <table>
         <tr>
           <td>用户名</td>
           <td>
               <input type="text" id="username" name="username"/>
           </td>
         </tr>
         <tr>
           <td>密码</td>
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