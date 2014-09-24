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
    <li><a href="/OnlineFinanceClient/AccountViewApplyServlet">经费审核</a></li>
    <li class="active"><a>用户信息</a></li>
    <li><a href="/OnlineFinanceClient/AccountViewProjectServlet">项目查看</a></li>
  </ul>
</div>


<div id="block">
<table class="table table-striped" id="accountfinancetable">
<tr>
	<th>卡号</th>
	<th>姓名</th>
	<th>身份</th>
	<th>金额</th>
	</tr>
	<%
	List<User> users = DaoFactory.getUserDao().getAllUsers();
    for(int i=0; i<users.size(); i++){
    		User user = users.get(i);
    		out.print("<tr class='viewline'>");
       		out.print("<td class='viewcolumn'>"+user.getUserid()+"</td>");
       		out.print("<td>"+user.getUserName()+"</td>");
       		out.print("<td>"+user.getIdentity()+"</td>");
       		out.print("<td>"+user.getUsermoney()+"</td>");
       		out.print("</tr>");
    }
    
	%>
</table>

</div>

  </body>
</html>