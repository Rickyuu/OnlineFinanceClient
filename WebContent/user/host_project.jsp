<%@page import="edu.nju.onlinefinance.factory.DaoFactory"%>
<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<%@page import="java.util.List"%>
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
    <script type="text/javascript"> 
    window.onload = function(){  
    	var viewId;
    	// 查看项目按钮监听
        $(document).on("click", "#hostprotable .btn", function(){
        	viewId = $(this).parent().parent().children("td").get(0).innerText;
        	var map = new ParamMap();
        	map.put("viewId", viewId);
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/HostViewProjectServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {
                    	var block = document.getElementById("block");
                    	block.innerHTML = xmlHttp.responseText;
                    }     
                }        
            });              
        });

    	// 添加新的项目参与者的监听
        $(document).on("click", "#addButton", function(){ 
        	var map = parseForm2ParamMap("userId", "availMoney");
        	map.put("viewId",viewId);
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/AddProjectUserServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {   
                    	$("#putable").append(xmlHttp.responseText);                     	
                    }     
                }        
            });   
        });
    	
     	// 返回项目列表的监听
        $(document).on("click", "#backButton", function(){ 
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/BackProjectUserServlet", "", function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {                       	  
                    	var block = document.getElementById("block");
                    	block.innerHTML = xmlHttp.responseText;
                    }     
                }        
            });   
        });
    	
        
    };   // 最后一个
    
    

    
    </script> 
    
    
    
    <div class="tabbable"> <!-- Only required for left/right tabs -->
  <ul class="nav nav-tabs">
    <li class="active"><a>项目管理</a></li>
    <li><a href="/OnlineFinanceClient/HostManageFinanceServlet">经费审核</a></li>
    <li><a href="/OnlineFinanceClient/HostInfoServlet">个人信息</a></li>
  </ul>
</div>


<div id="block">
<table class="table table-striped" id="hostprotable">
<tr>
	<th>项目编号</th>
	<th>项目名称</th>
	<th>项目金额</th>
	<th>截止日期</th>
	<th>查看</th>
	</tr>
	<%   		
	List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
	Long hostId = (Long)session.getAttribute("userid");
    for(int i=0; i<projects.size(); i++){
    	if(projects.get(i).getHostid().equals(hostId)){
    		Project project = projects.get(i);
    		out.print("<tr class='viewline'>");
    		out.print("<td class='viewcolumn'>"+project.getProjectid()+"</td>");
    		out.print("<td>"+project.getProjectname()+"</td>");
    		out.print("<td>"+project.getProjectmoney()+"</td>");
    		out.print("<td>"+project.getDeadline()+"</td>");
    		out.print("<td>");
    		out.print("<div class='btn'><i class='icon-camera'></i></div>");
    		out.print("</td>");
    		out.print("</tr>");
    	}
    }
    
	%>
</table>

</div>

  </body>
</html>