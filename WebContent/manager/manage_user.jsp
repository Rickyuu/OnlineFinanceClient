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
    <script type="text/javascript"> 
    window.onload = function(){  
    	/*
    	var tdNodes = $("td");
    	tdNodes.click(tdClick);
    	*/
    	$(document).on("click", "#usertable .column",tdClick);
        
        $(document).on("click", "#addButton", function(){ 
        	var map = parseForm2ParamMap("newId", "newName", "newIdentity", "newMoney");     
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/AddUserServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {    
                        addLine();    
                    }     
                }        
            });   
        });
        
        $(document).on("click", "#usertable .btn", function(){
        	$(this).parents(".line").remove(); 
        	var delId = $(this).parent().parent().children("td").get(0).innerText;
        	var map = new ParamMap();
        	map.put("delId", delId);
            //doPost("input", map.toString(), callback); //默认Alert出信息            
            doPost("/OnlineFinanceClient/DelUserServlet", map.toString(), function(){     
            	if(xmlHttp.readyState == 4) {     
                    if(xmlHttp.status == 200) {
                    	// 成功返回
                    }     
                }        
            });
        	
               
        });
        
    };   
    
 	// 给表格添加行
    function addLine(){
    	var tests1 = document.getElementsByName('newId');
        var tests2 = document.getElementsByName('newName');
        var tests3 = document.getElementsByName('newIdentity');
        var tests4 = document.getElementsByName('newMoney');
    	$("#usertable").append("<tr class='line'>"
    			+ "<td>"+tests1[0].value+"</td>"
    			+ "<td class='column'>"+tests2[0].value+"</td>"
    			+ "<td class='column'>"+tests3[0].value+"</td>"
    			+ "<td class='column'>"+tests4[0].value+"</td>"
    			+ "/tr");
    }
 	
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
            doPost("/OnlineFinanceClient/ChangeUserServlet", map.toString(), function(){     
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
        /*
        var changeId = $(this).parent().children("td").get(0).innerText;
        var changeName = $(this).parent().children("td").get(1).innerText;
        var changeIdentity = $(this).parent().children("td").get(2).innerText;
        var changeMoney = $(this).parent().children("td").get(3).innerText;
        alert(changeId+" "+changeName+" "+changeIdentity+" "+changeMoney);
        */
    }
    
    </script> 
    
    <div class="tabbable"> <!-- Only required for left/right tabs -->
  <ul class="nav nav-tabs">
    <li class="active"><a>用户管理</a></li>
    <li><a href="/OnlineFinanceClient/ManageProjectServlet">项目管理</a></li>
  </ul>
</div>



<table class="table table-striped" id="usertable">
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
		out.print("<tr class='line'>");
		out.print("<td>"+user.getUserid()+"</td>");
		out.print("<td class='column'>"+user.getUserName()+"</td>");
		out.print("<td class='column'>"+user.getIdentity()+"</td>");
		out.print("<td class='column'>"+user.getUsermoney()+"</td>");
		out.print("</tr>");
	}
	%>
</table>

<div class="form-inline">
  <input type="text" class="input-medium" placeholder="卡号" name="newId">
  <input type="text" class="input-medium" placeholder="姓名" name="newName">
  <select class="input-medium" name="newIdentity">
  <option>普通用户</option>
  <option>项目主持</option>
  <option>财务审核</option>
  <option>财务主管</option>
</select>
  
  <input type="text" class="input-medium" placeholder="金额" name="newMoney">
  <button type="submit" class="btn" id="addButton">添加</button>
</div>

  </body>
</html>