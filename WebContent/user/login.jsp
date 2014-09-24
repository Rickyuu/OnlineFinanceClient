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
    	body {
    		background-color: gray;
    		background-repeat: no-repeat;
    		background-attachment: fixed;
    		text-align: center;
    	}
    	.form-inline {
    		position: relative;
    		top: 300px;
    	}
    </style>
    
  </head>
<body>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <form class="form-inline" method="post" action="<%=request.getContextPath()+"/LoginServlet" %>">
  		<input type="text" class="input-medium" name="userid">
  		<button type="submit" class="btn">登录</button>
	</form>
  </body>
</html>