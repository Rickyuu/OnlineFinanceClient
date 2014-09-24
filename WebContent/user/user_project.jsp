<%@page import="edu.nju.onlinefinance.factory.DaoFactory"%>
<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<%@page import="java.util.List"%>
<%@page import="edu.nju.onlinefinance.model.ProjectUser"%>
<%@page import="edu.nju.onlinefinance.model.Project"%>
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
    <li><a href="/OnlineFinanceClient/UserFinanceServlet">报销情况</a></li>
    <li class="active"><a>参与项目</a></li>
    <li><a href="/OnlineFinanceClient/UserInfoServlet">个人信息</a></li>
  </ul>
</div>


<div id="block">

<table class="table table-striped" id="financelisttable">
<tr>
	<th>项目编号</th>
	<th>项目名称</th>
	<th>已用金额</th>
	<th>剩余金额</th>
	<th>截止日期</th>
	</tr>
	<%   		
	
	Long userid = (Long)session.getAttribute("userid");
	List<ProjectUser> projectUsers = DaoFactory.getProjectUserDao().getProjectsByUser(userid);      
    for(int i=0; i<projectUsers.size(); i++){
    		ProjectUser projectUser = projectUsers.get(i);
    		out.print("<tr>");
    		out.print("<td>"+projectUser.getProjectid()+"</td>");
    		out.print("<td>"+DaoFactory.getProjectDao().getProjectById(projectUser.getProjectid()).getProjectname()+"</td>");
    		out.print("<td>"+projectUser.getSpendmoney()+"</td>");
    		out.print("<td>"+projectUser.getRemainmoney()+"</td>");
    		out.print("<td>"+DaoFactory.getProjectDao().getProjectById(projectUser.getProjectid()).getDeadline()+"</td>");
    		out.print("</tr>");
    }
    
	%>	
</table>

</div>

  </body>
</html>