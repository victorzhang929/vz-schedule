<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="include/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/subiframepage.css" />
<script type="text/javascript" src="<%=basePath%>js/userInfo.js"></script>
<title>用户信息</title>
<style type="text/css">
	.validateSpan{display:none;}
</style>
</head>

<body>
	<div id="box">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="javascript:void(0);" title="个人中心" class="tip-bottom"> <i class="icon-cogs"></i> 个人中心
				</a> <a href="javascript:void(0);" title="用户信息" class="tip-bottom"> 用户信息 </a>
			</div>
		</div>

		<!-- 表格start -->
		<div class="container-fluid">
			<!-- <hr> -->
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
							<div class="widget-title">
								<span class="icon"> <i class="icon-align-justify"></i>
								</span>
								<h5>用户信息</h5>
							</div>
							<div class="widget-content nopadding">
								<form method="get" class="form-horizontal" id="userInfoForm">
									<div class="control-group">
										<label class="control-label">用户名 :</label>
										<div class="controls">
											<input type="text" id="username" class="span11" placeholder="用户名不能为空" value="${username}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">真实姓名 :</label>
										<div class="controls">
											<input type="text" id="realname" class="span11" placeholder="真实姓名不能为空" value="${realname}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">角色 :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="角色" value="${rolename}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">所属学院 :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="所属学院" value="${dname}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">所属班级 :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="所属班级" value="${cname}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">手机号 :</label>
										<div class="controls">
											<input type="text" id="usermobile" class="span11" placeholder="请输入正确手机号" value="${usermobile}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">身份证号 :</label>
										<div class="controls">
											<input type="text" id="useridcard" class="span11" placeholder="请输入身份证号" value="${useridcard}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">邮箱地址 :</label>
										<div class="controls">
											<input type="text" id="usermail" class="required span11" placeholder="请输入邮箱地址" value="${usermail}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">录入人 :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="录入人" value="${inputusername}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">录入时间 :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="录入时间" value="${inputdate}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">录入人IP :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="录入IP" value="${inputip}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">最后修改人 :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="最后修改人" value="${updateusername}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">最后修改时间  :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="最后修改时间" value="${updatedate}" disabled="disabled"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">最后修改人IP :</label>
										<div class="controls">
											<input type="text" class="span11" placeholder="最后修改人IP" value="${updateip}" disabled="disabled" />
										</div>
									</div>
									<div class="form-actions">
										<a href="javascript:void(0);" class="btn btn-success" onclick="submitUserInfo()">保存</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
</body>
</html>
