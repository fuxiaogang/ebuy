<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function searchUser(){
		$("#dg").datagrid('load',{
			"s_user.userName":$("#s_userName").val()
		});
	}
	
	function deleteUser(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("user_deleteUser.action",{ids:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","数据已成功删除！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","数据删除失败！");
					}
				},"json");
			}
		});
		
	}
	
</script>
</head>
<body style="margin:1px;">
	<table id="dg" title="用户管理" class="easyui-datagrid"
	 fitColumns="true" pagination="true" rownumbers="true"
	 url="user_list.action" fit="true" toolbar="#tb">
	 <thead>
	 	<tr>
	 		<th field="cb" checkbox="true" align="center"></th>
	 		<th field="id" width="50" align="center">编号</th>
	 		<th field="trueName" width="100" align="center">真实姓名</th>
	 		<th field="userName" width="100" align="center">用户名</th>
	 		<th field="password" width="100" align="center">密码</th>
	 		<th field="sex" width="50" align="center">性别</th>
	 		<th field="birthday" width="100" align="center">出生日期</th>
	 		<th field="dentityCode" width="150" align="center">身份证</th>
	 		<th field="email" width="100" align="center">邮件</th>
	 		<th field="mobile" width="100" align="center">联系电话</th>
	 		<th field="address" width="100" align="center">收货地址</th>
	 	</tr>
	 </thead>
	</table>
	<div id="tb">
		<div>
			<a href="" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;用户名：&nbsp;<input type="text" id="s_userName" size="20" onkeydown="if(event.keyCode==13) searchUser()"/>
			<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
</body>
</html>