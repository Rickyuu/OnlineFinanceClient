<%@page import="edu.nju.onlinefinance.factory.DaoFactory"%>
<%String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<%@page import="java.util.List"%>
<%@page import="edu.nju.onlinefinance.model.Project"%>
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
        $(document).on("click", "#accountfinancetable .btn", function(){
        	viewId = $(this).parent().parent().children("td").get(0).innerText;
        	var map = new ParamMap();
        	map.put("viewId", viewId);
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/AccountViewFinanceServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {
                    	var block = document.getElementById("block");
                    	block.innerHTML = xmlHttp.responseText;
                    }     
                }        
            });              
        });

    	// 同意申请表按钮
        $(document).on("click", "#agreeButton", function(){ 
        	var map = new ParamMap();
        	map.put("viewId",viewId);         
            doPost("/OnlineFinanceClient/AccountAgreeFinanceServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {   
                    	var block = document.getElementById("block");
                    	block.innerHTML = xmlHttp.responseText;     
                    }     
                }        
            });   
        });
    	
     // 不同意申请表按钮
        $(document).on("click", "#disagreeButton", function(){ 
        	var map = new ParamMap();
        	map.put("viewId",viewId);        
            doPost("/OnlineFinanceClient/AccountDisagreeFinanceServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {   
                    	var block = document.getElementById("block");
                    	block.innerHTML = xmlHttp.responseText;                     	
                    }     
                }        
            });   
        });
    	
     	// 返回申请表列表的
        $(document).on("click", "#backButton", function(){          
            doPost("/OnlineFinanceClient/BackAccountFinanceServlet", "", function(){     
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
    <li class="active"><a>经费审核</a></li>
    <li><a href="/OnlineFinanceClient/AccountViewUserServlet">用户信息</a></li>
    <li><a href="/OnlineFinanceClient/AccountViewProjectServlet">项目查看</a></li>
  </ul>
</div>


<div id="block">
<table class="table table-striped" id="accountfinancetable">
<tr>
	<th>申请表编号</th>
	<th>项目编号</th>
	<th>项目名称</th>
	<th>申请人卡号</th>
	<th>申请人名称</th>
	<th>申请日期</th>
	<th>查看</th>
	</tr>
	<%   		
	List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
    for(int i=0; i<projects.size(); i++){
    		Project project = projects.get(i);
    		Long projectid = project.getProjectid();
    		// 获得这个项目所有的申请表，并列出其中“提交财务”的部分
    		List<FinanceApply> applies = DaoFactory.getFinanceApplyDao().getAllApplies();
    		for(int j=0; j<applies.size(); j++){
    			// 是这个项目,并且是"提交财务"
    			if(applies.get(j).getProjectid().equals(projectid) && applies.get(j).getApplystate().equals("提交财务")){
    				FinanceApply apply = applies.get(j);
    				out.print("<tr class='viewline'>");
    	    		out.print("<td class='viewcolumn'>"+apply.getApplyid()+"</td>");
    	    		out.print("<td>"+project.getProjectid()+"</td>");
    	    		out.print("<td>"+project.getProjectname()+"</td>");
    	    		out.print("<td>"+apply.getUserid()+"</td>");
    	    		out.print("<td>"+DaoFactory.getUserDao().getUserById(apply.getUserid()).getUserName()+"</td>");
    	    		out.print("<td>"+apply.getApplydate()+"</td>");
    	    		out.print("<td>");
    	    		out.print("<div class='btn'><i class='icon-camera'></i></div>");
    	    		out.print("</td>");
    	    		out.print("</tr>");
    			}
    		}
    }
    
	%>
</table>

</div>

  </body>
</html>