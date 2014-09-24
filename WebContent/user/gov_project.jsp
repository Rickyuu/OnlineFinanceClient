<%@page import="edu.nju.onlinefinance.factory.DaoFactory"%>
<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<%@page import="java.util.List"%>
<%@page import="java.sql.Date"%>
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
    <li class="active"><a>本月新增项目</a></li>
    <li><a href="/OnlineFinanceClient/GovUserServlet">本月新增用户</a></li>
  </ul>
</div>


<div id="block">
<table class="table table-striped" id="accountfinancetable">
<tr>
	<th>项目编号</th>
	<th>项目名称</th>
	<th>主持卡号</th>
	<th>项目金额</th>
	<th>截止日期</th>
	</tr>
	<%
	List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
    Date currentDate = new Date(System.currentTimeMillis());
    int year = currentDate.getYear();
    int month = currentDate.getMonth();
    for(int i=0; i<projects.size(); i++){
    	Project project = projects.get(i);
    	Date createDate = project.getCreateDate();
    	int itemYear = createDate.getYear();
    	int itemMonth = createDate.getMonth();
    	if(year == itemYear && month == itemMonth){
    		out.print("<tr class='viewline'>");
       		out.print("<td class='viewcolumn'>"+project.getProjectid()+"</td>");
       		out.print("<td>"+project.getProjectname()+"</td>");
       		out.print("<td>"+project.getHostid()+"</td>");
       		out.print("<td>"+project.getProjectmoney()+"</td>");
       		out.print("<td>"+project.getDeadline()+"</td>");
       		out.print("</tr>");
    	}
    }
    
	%>
</table>

</div>

  </body>
</html>