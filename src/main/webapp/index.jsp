<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
    
	<head>
        <title>登录页--课表安排系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="css/matrix-login.css" />
        <link href="plugins/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
        <script src="js/jquery-1.8.2.js"></script>  
        <script src="js/matrix.login.js"></script>
        <script src="js/gloab.js"></script>
        <script src="js/index.js"></script>
        <script src="js/jCookie.js"></script>
        
        <script type="text/javascript">
        	var basePath = "<%=basePath%>";
    		var path = "<%=path%>";
        </script>
        
        <!-- dialog插件 -->
    	<link rel="stylesheet" href="plugins/dialog/ui-dialog.css">
    	<script src="plugins/dialog/dialog-min.js" type="text/javascript"></script>
        
        <link rel="shortcut icon" href="images/favicon.ico" /> 
	</head>
    <body>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="http://themedesigner.in/demo/matrix-admin/index.html">
				<div class="control-group normal_text"> <h3 style="font-family:'黑体';">课表安排系统</h3></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input id="username" type="text" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input id="password" type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a class="flip-link btn btn-info" id="to-recover">忘记密码?</a></span>
                    <span class="pull-right"><a type="submit" id="loginSubmit" class="btn btn-success" onclick="login()">登录</a></span>
                </div>
            </form>
            <form id="recoverform" class="form-vertical">
				<p class="normal_text">在下方输入您的邮箱地址，我们将会给您的邮箱发送找到密码的邮件，请注意查收！</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="邮箱地址" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a class="flip-link btn btn-success" id="to-login"><i class=" icon-chevron-left"></i> 返回登陆页面</a></span>
                    <span class="pull-right"><a class="btn btn-info" onclick="sendMail()"/>发送邮件</a></span>
                </div>
            </form>
        </div>
    </body>

</html>
