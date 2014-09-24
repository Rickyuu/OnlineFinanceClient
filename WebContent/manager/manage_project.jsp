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
    	// 修改项目监听
    	$(document).on("click", "#projecttable .column",tdClick);

        // 添加项目按钮监听
        $(document).on("click", "#addButton", function(){ 
        	var map = parseForm2ParamMap("newProjectName", "newHostId", "newProjectMoney", "newYear", "newMonth", "newDay");     
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/AddProjectServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {   
                    	$("#projecttable").append(xmlHttp.responseText);                      	
                    }     
                }        
            });   
        });
    	
    	// 删除项目按钮监听
        $(document).on("click", "#projecttable .btn", function(){
        	$(this).parents(".line").remove(); 
        	var delId = $(this).parent().parent().children("td").get(0).innerText;
        	var map = new ParamMap();
        	map.put("delId", delId);
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/DelProjectServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {
                    	// 成功返回
                    }     
                }        
            });              
        });
    	
        //td的点击事件
        function tdClick(){
            //将td的文本内容保存
            var td = $(this);
            
            var whichRow = $(this).parents("tr").find("td").index($(this));
            var changeId = $(this).parent().children("td").get(0).innerText;
            
            var tdText = td.text();
            //将td的内容清空
            td.empty();
            //新建一个输入框
            var input = $("<input>");
            //将保存的文本内容赋值给输入框
            input.attr("value",tdText);       
            //将输入框添加到td中
            td.append(input);
            //给输入框注册事件，当失去焦点时就可以将文本保存起来
            input.blur(function(){
                //将输入框的文本保存
                var input = $(this);
                var inputText = input.val();
                //将td的内容，即输入框去掉,然后给td赋值
                var td = input.parent("td");                   
                td.html(inputText);
                //让td重新拥有点击事件       
                td.click(tdClick);
                // 将ID，索要修改的项， 修改项的值返回给客户端
                
            	var map = new ParamMap();
            	map.put("changeId", changeId);
            	map.put("whichRow", whichRow);
            	map.put("inputText", inputText);  
                doPost("/OnlineFinanceClient/ChangeProjectServlet", map.toString(), function(){     
                	if(xmlHttp.readyState == 4) {     
                        if(xmlHttp.status == 200) {
                        	// 成功返回
                        }     
                    }        
                });
                
               // alert(whichRow+":"+inputText+"   "+changeId);
            });
            //将输入框中的文本高亮选中
            //将jquery对象转化为DOM对象      
            var inputDom = input.get(0);
            inputDom.select();
            inputDom.focus();
            //将td的点击事件移除
            td.unbind("click");
        }
    	
        
    };   
    

    
    </script> 
    
    <div class="tabbable"> <!-- Only required for left/right tabs -->
  <ul class="nav nav-tabs">
    <li><a href="/OnlineFinanceClient/ManageUserServlet">用户管理</a></li>
    <li class="active"><a>项目管理</a></li>
  </ul>
</div>



<table class="table table-striped" id="projecttable">
<tr>
	<th>项目编号</th>
	<th>项目名称</th>
	<th>主持卡号</th>
	<th>项目金额</th>
	<th>截止日期</th>
	</tr>
	<% 
	List<Project> projects = DaoFactory.getProjectDao().getAllProjects();
	for(int i=0; i<projects.size(); i++){
		Project project = projects.get(i);
		out.print("<tr class='line'>");
		out.print("<td>"+project.getProjectid()+"</td>");
		out.print("<td class='column'>"+project.getProjectname()+"</td>");
		out.print("<td class='column'>"+project.getHostid()+"</td>");
		out.print("<td class='column'>"+project.getProjectmoney()+"</td>");
		out.print("<td class='column'>"+project.getDeadline()+"</td>");
		out.print("</tr>");
	}
	%>
</table>

<div class="form-inline">
  <input type="text" class="input-medium" placeholder="项目名称" name="newProjectName">
  <input type="text" class="input-medium" placeholder="主持卡号" name="newHostId">
  <input type="text" class="input-medium" placeholder="项目金额" name="newProjectMoney">
  <input type="text" class="input-mini" name="newYear">年
  <input type="text" class="input-mini" name="newMonth">月
  <input type="text" class="input-mini" name="newDay">日
  <button type="submit" class="btn" id="addButton">添加</button>
</div>

  </body>
</html>