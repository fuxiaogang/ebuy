<%@ page language="java" contentType="text/html; charset=gb2312"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/base.css" rel="stylesheet" type="text/css" />
<title>�û���¼</title> 

</head>
<body > 
   <%
      if(request.getAttribute("is_error")!=null){
    	  out.println("�û������������");
      }
   %>
   <p>
   <form method="post" action="login" class="bordered" style="width:300px;"> 
      <table>
         <tr>
           <td class="tabtitle">�û���</td>
           <td>
               <input type="text" id="username" name="username" style="background-color:#fff;border:1px solid #c5c5c5;width:90%;"/>
           </td>
         </tr>
         <tr>
           <td  class="tabtitle">����</td>
           <td>
              <input type="password" id="password" name="password" style="background-color:#fff;border:1px solid #c5c5c5;width:90%;"/>
           </td>
         </tr>
         <tr>
           <td colspan="2">
               <input type="submit" name="" value="��½"/>
           </td>
         </tr> 
      </table>  
   </form> 
   
<script>
   

</script>   
   
</body>
</html>