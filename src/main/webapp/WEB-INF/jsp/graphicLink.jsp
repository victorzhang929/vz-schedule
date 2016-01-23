<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
  <head>
  	<%@ include file="include/head.jsp"%>
  	<link rel="stylesheet" href="<%=basePath%>css/subiframepage.css"/>
    <title>图形界面链接</title>
    <script type="text/javascript">
    	$(function(){
    		parent.setFrameHeight("box");
    	});
    	
    	/**
    	 * 侧边导航栏事件和顶部导航栏事件同步
    	 * @param id导航
    	 * @param pageUrl地址
    	 */
    	function clickNavMenu(id,url){
   			var parentId = id.substring(0,1);
   			$("#ul"+parentId+"Parent",window.parent.document).addClass("open");
   			id = id.substring(1,id.length);
   			parent.clickNavMenu(id,url);
    	}
    </script>
  </head>
  
<body>
	<div id="box">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="#" title="首页" class="tip-bottom"> <i class="icon-th"></i> 快速导航 </a>
			</div>
		</div>
		<div class="container-fluid">
    		<div class="quick-actions_homepage">
   				<ul class="quick-actions">
			        <li class="bg_lb"> <a href="javascript:void(0);" id="1userInfoUI" onclick="clickNavMenu(this.id,'<%=basePath%>user/userInfoUI.do')"> <i class="icon-user"></i> 用户信息 </a> </li>
			        <!-- 系统管理员，部门管理员权限 -->
			        <c:if test="${ sessionScope.roleid =='1'|| sessionScope.roleid =='2'}">
			        	<!-- 系统管理员权限 -->
			        	<c:if test="${ sessionScope.roleid =='1'}">
		        			<li class="bg_lg"><a href="javascript:void(0);" id="1departInfoUI" onclick="clickNavMenu(this.id,'<%=basePath%>depart/departInfoUI.do')"><i class="icon-reorder"></i>部门信息</a></li>
	        			</c:if>
		        		<li class="bg_ly"><a href="javascript:void(0);" id="1classesInfoUI" onclick="clickNavMenu(this.id,'<%=basePath%>classes/classesInfoUI.do')"><i class="icon-align-justify"></i>班级信息</a></li>
		        		<li class="bg_lo"><a href="javascript:void(0);" id="1teacherInfoUI" onclick="clickNavMenu(this.id,'<%=basePath%>user/teacherInfoUI.do')"><i class="icon-glass"></i> 教师信息</a></li>
		        		<li class="bg_ls"><a href="javascript:void(0);" id="1studentInfoUI" onclick="clickNavMenu(this.id,'<%=basePath%>user/studentInfoUI.do')"><i class="icon-user-md"></i> 学生信息</a></li>
			        </c:if>
			    </ul>
		    </div>
	    </div>
		<hr/>
		<div class="container-fluid">
    		<div class="quick-actions_homepage">
   				<ul class="quick-actions">
   					<!-- 系统管理员权限 -->
        			<c:if test="${ sessionScope.roleid =='1'|| sessionScope.roleid =='2'}">
	        			 <li class="bg_ls span4"> <a href="javascript:void(0);" id="2publishNBUI" onclick="clickNavMenu(this.id,'<%=basePath%>message/publishNBUI.do')"> <i class="icon-pencil"></i> 发布公告 </a> </li>
        			</c:if>
			        <li class="bg_lg span4"> <a href="javascript:void(0);" id="2noticeBoardUI" onclick="clickNavMenu(this.id,'<%=basePath%>message/noticeBoardUI.do')"> <i class="icon-table"></i> 公告栏</a> </li>
			    </ul>
		    </div>
	    </div>
		<hr/>
		<div class="container-fluid">
    		<div class="quick-actions_homepage">
   				<ul class="quick-actions">
   					<!-- 部门管理员权限 - -->
        			<c:if test="${ sessionScope.roleid =='2'}">
        				<li class="bg_lb span4"><a href="javascript:void(0);" id="3departlogUI" onclick="clickNavMenu(this.id,'<%=basePath%>log/departlogUI.do')"><i class="icon-book"></i>部门日志</a></li>
        			</c:if>
	        		<!-- 系统管理员-->
        			<c:if test="${ sessionScope.roleid =='1'}">
        				<li class="bg_ly span4"><a href="javascript:void(0);" id="3syslogUI" onclick="clickNavMenu(this.id,'<%=basePath%>log/syslogUI.do')"><i class="icon-wrench"></i> 系统日志 </a></li>
        			</c:if>
			    </ul>
		    </div>
	    </div>
		<hr/>
		      	
    </div>
</body>
</html>
