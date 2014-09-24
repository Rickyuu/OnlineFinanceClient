<%@page import="edu.nju.onlinefinance.factory.DaoFactory"%>
<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<%@page import="java.util.List"%>
<%@page import="edu.nju.onlinefinance.model.FinanceApply"%>
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
    	// 查看申请表
        $(document).on("click", "#financelisttable .btn", function(){
        	viewId = $(this).parent().parent().children("td").get(0).innerText;
        	var map = new ParamMap();
        	map.put("viewId", viewId);
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/UserViewApplyServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {
                    	var block = document.getElementById("block");
                    	block.innerHTML = xmlHttp.responseText;
                    }     
                }        
            });              
        });

    	// 添加新的申请表
        $(document).on("click", "#addButton", function(){ 
        	var map = parseForm2ParamMap("projectId");
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/AddFinanceApplyServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {   
                    	var block = document.getElementById("block");
                    	block.innerHTML = xmlHttp.responseText;                     	
                    }     
                }        
            });   
        });
    	
    	
     	// 添加申请条目
        $(document).on("click", "#addButton2", function(){ 
        	var map = parseForm2ParamMap("newYear", "newMonth", "newDay", "describe", "availMoney");
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/AddApplyItemServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {   
                    	$("#applytable").append(xmlHttp.responseText);                     	
                    }     
                }        
            });   
        });
    	
     	// 返回项目列表的监听
        $(document).on("click", "#backButton", function(){ 
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/BackUserFinanceServlet", "", function(){     
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
    <li class="active"><a>报销情况</a></li>
    <li><a href="/OnlineFinanceClient/UserProjectServlet">参与项目</a></li>
    <li><a href="/OnlineFinanceClient/UserInfoServlet">个人信息</a></li>
  </ul>
</div>


<div id="block">

<table class="table table-striped" id="financelisttable">
<tr>
	<th>申请表编号</th>
	<th>项目编号</th>
	<th>项目名称</th>
	<th>申请日期</th>
	<th>申请状态</th>
	<th>查看</th>
	</tr>
	<%   		
	List<FinanceApply> applies = DaoFactory.getFinanceApplyDao().getAllApplies();	      
    Long userid = (Long)session.getAttribute("userid");
    for(int i=0; i<applies.size(); i++){
    	if(applies.get(i).getUserid().equals(userid)){
    		FinanceApply apply = applies.get(i);
    		out.print("<tr class='viewline'>");
    		out.print("<td class='viewcolumn'>"+apply.getApplyid()+"</td>");
    		out.print("<td>"+apply.getProjectid()+"</td>");
    		out.print("<td>"+DaoFactory.getProjectDao().getProjectById(apply.getProjectid()).getProjectname()+"</td>");
    	//	Date currentDate = new java.sql.Date(System.currentTimeMillis());
    		out.print("<td>"+apply.getApplydate()+"</td>");
    		out.print("<td>"+apply.getApplystate()+"</td>");
    		out.print("<td>");
    		out.print("<div class='btn'><i class='icon-camera'></i></div>");
    		out.print("</td>");
    		out.print("</tr>");
    	}
    }
    
	%>
	

	
</table>

<div class="form-inline">
  <input type="text" class="input-medium" placeholder="项目编号" name="projectId">
  <button type="submit" class="btn" id="addButton">添加</button>
</div>

</div>

  </body>
</html>