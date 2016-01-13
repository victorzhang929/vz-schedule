<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<%@ include file="include/head.jsp"%>
	<title>课表安排系统</title>
	<script src="<%=basePath%>js/jCookie.js"></script>
	<script src="<%=basePath%>js/main.js"></script>
	<link rel="stylesheet" href="<%=basePath%>css/main.css" />
</head>

<body>
	<div id="header">
  		<h1></h1>
	</div>
	
	<div id="user-nav" class="navbar navbar-inverse">
  		<ul class="nav" >
	    	<li class="dropdown" id="menu-messages">
	    		<a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
	    			<i class="icon icon-envelope"></i> 
	    			<span class="text">消息</span>
	    			<span class="label label-important" id='wdMessSum'>0</span> <b class="caret"></b>
	    		</a>
		      	<ul class="dropdown-menu">
		      		<li><a href='javascript:void(0);' class='sTrash' title='' id='meAll' ><i class='icon-bell'></i> 全部未读消息 >></a></li>
		      	</ul>
	    	</li>
    
    		<li  class="dropdown" id="profile-messages" >
    			<a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
    				<i class="icon icon-user"></i> <span class="text" id='realname'></span><b class="caret"></b>
    			</a>
      			<ul class="dropdown-menu">
	        		<li><a href="javascript:void(0);" onclick="clickNavMenu(this.id,'<%=basePath%>user/userslogUI.do')" id="userslog"><i class="icon-book"></i> 操作日志</a></li>
			        <li class="divider"></li>
			        <li><a href="#modalDiv" data-toggle='modal' onclick="changePasswordUI()"><i class="icon-pencil"></i> 修改密码</a></li>
			        <li class="divider"></li>
			        <li><a href="javascript:void(0);" onclick="exit()"><i class="icon-signout"></i>注销</a></li>
      			</ul>
    		</li>
    		<li>
    			<a title="注销登陆当前账户" href="javascript:void(0);" onclick="exit()"><i class="icon-off"></i> 退出</a>
    		</li>
  		</ul>
	</div>
	
	<div id="sidebar">
		<a href="javascript:void(0);" class="visible-phone" onclick="clickNavMenu('graphicLinkUI','<%=basePath%>index/graphicLinkUI.do')"><i class="icon icon-home"></i></a>
  		<ul>
    		<li class="submenu">
    			<a href="javascript:void(0);" >
		    		<i class="icon-link"></i> <span>快速导航</span>
		    		<i class="icon-chevron-right"></i>
	    		</a> 
    			<ul>
    				<li><a href="javascript:void(0);" id="graphicLinkUI" onclick="clickNavMenu(this.id,'<%=basePath%>index/graphicLinkUI.do')">快速导航</a></li>
      			</ul>
    		</li>
    
    		<li class="submenu"> 
    			<a href="javascript:void(0);">
    				<i class="icon-cogs"></i> <span>个人中心</span>
    				<i class="icon-chevron-right"></i>
    			</a>
    			<ul id="ul2">
	        		<li><a href="javascript:void(0);" id="userInfoUI" onclick="clickNavMenu(this.id,'<%=basePath%>user/userInfoUI.do')">用户信息</a></li>
	        		<li><a href="javascript:void(0);">部门信息</a></li>
	        		<li><a href="javascript:void(0);">班级信息</a></li>
	        		<li><a href="javascript:void(0);">教师信息</a></li>
	        		<li><a href="javascript:void(0);">学生信息</a></li>
	    		</ul> 
    		</li>
    		<li class="submenu"> 
    			<a href="javascript:void(0);">
    				<i class="icon-envelope"></i> <span>信息中心</span>
    				<i class="icon-chevron-right"></i>
    			</a>
    			<ul id="ul2">
	        		<li><a href="javascript:void(0);">公告栏</a></li>
	        		<li><a href="javascript:void(0);">信息箱</a></li>
	    		</ul> 
    		</li>
    		<li class="submenu"> 
    			<a href="javascript:void(0);">
    				<i class="icon-list"></i> <span>日志中心</span>
    				<i class="icon-chevron-right"></i>
    			</a>
    			<ul id="ul2">
	        		<li><a href="javascript:void(0);">用户日志</a></li>
	        		<li><a href="javascript:void(0);">部门日志</a></li>
	        		<li><a href="javascript:void(0);">系统日志</a></li>
	    		</ul> 
    		</li>
  		</ul>
	</div>
	
	<div id="content">
		<iframe id="iframepage" onload="setFrameHeight(this.id)" marginheight="0" marginwidth="0"
			frameborder="0" scrolling="auto" height="100%" width="100%" src="<%=basePath%>index/graphicLinkUI.do"></iframe>
	</div>
	
	<div class="row-fluid">
  		<div id="footer" class="span12"> 2016 &copy; 课表安排系统 victorzhang </div>
	</div>
	
	
	<div id="modalDiv" class="modal hide">
		<!-- 表头div -->
		<div class="modal-header">
	        <button data-dismiss="modal" class="close" type="button">×</button>
	        <h3>修改密码</h3>
		</div>
		<!-- 表单div -->
		<div class="modal-body">
       		<form class="form-horizontal" method="post" name="basic_validate">
	       			<div class="control-group">
	       				<label class="control-label">原密码:</label>
	       				<div class="controls">
	         				<input id="oldpassword" type="password" name="oldpassword" style="height:28px;" />
	       				</div>
	       			</div>
	       			<div class="control-group">
	           			<label class="control-label">新密码:</label>
	             		<div class="controls">
	             			<input id="password" type="password" name="password" style="height:28px;" />
	           			</div>
	       			</div>
	       			<div class="control-group">
	       				<label class="control-label">重复密码:</label>
	       				<div class="controls">
	           				<input id="password2" type="password" name="password2" style="height:28px;" />
	       				</div>
	       			</div>
			</form>
         </div>
		<!-- 底部按钮div -->
		<div class="modal-footer"> 
			<div id='msg'></div>
			<a data-dismiss="modal" class="btn" href="javascript:void(0);"> <i class="icon-remove"></i>取消</a> 
			<a class="btn btn-success" href="javascript:void(0);" onclick="changePassword()"> <i class="icon-ok"></i>确定</a> 
		</div>
	</div>
</body>
</html>
