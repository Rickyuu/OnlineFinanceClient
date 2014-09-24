<%@page import="edu.nju.onlinefinance.factory.DaoFactory"%>
<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<%@page import="java.util.List"%>
<%@page import="edu.nju.onlinefinance.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>财务报销系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    
	<style type="text/css">
		body{
			position: absolute;
			left: 80px;
			right: 80px;
		}
    </style>
    
  </head>
  <body>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/ajax.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>     
    
    
    
    <div class="tabbable"> <!-- Only required for left/right tabs -->
  <ul class="nav nav-tabs">
    <li><a href="/OnlineFinanceClient/HostManageProjectServlet">项目管理</a></li>
    <li><a href="/OnlineFinanceClient/HostManageFinanceServlet">经费审核</a></li>
    <li class="active"><a>个人信息</a></li>
  </ul>
</div>


<div id="block">

<table class="table table-striped">
<tr>
	<th>卡号</th>
	<th>姓名</th>
	<th>身份</th>
	<th>余额</th>
	<th>注册日期</th>
	</tr>
	<%   	
	Long userid = (Long)session.getAttribute("userid");
	User user = DaoFactory.getUserDao().getUserById(userid);
    out.print("<tr class='viewline'>");
    out.print("<td class='viewcolumn'>"+user.getUserid()+"</td>");
    out.print("<td>"+user.getUserName()+"</td>");
    out.print("<td>"+user.getIdentity()+"</td>");
    out.print("<td>"+user.getUsermoney()+"</td>");
    out.print("<td>"+user.getCreateDate()+"</td>");
    out.print("</tr>");
    
	%>
	

	
</table>

</div>

  </body>
</html>