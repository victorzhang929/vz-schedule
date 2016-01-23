<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="include/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/subiframepage.css" />
<script type="text/javascript" src="<%=basePath%>js/message.js"></script>
<title>发布公告</title>
</head>

<body>
	<div id="box">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="javascript:void(0);" title="信息中心" class="tip-bottom"> <i class="icon-envelope"></i> 信息中心
				</a> <a href="javascript:void(0);" title="发布公告" class="tip-right"> 发布公告 </a>
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
							<h5>发布公告</h5>
						</div>
						<div class="widget-content">
					        <div class="control-group">
					          <form>
					            <div class="controls">
					              <textarea id="msgContent" class="textarea_editor span12" rows="6" placeholder="请输入发布信息"></textarea>
					            </div>
					            <div class="form-actions">
										<a href="javascript:void(0);" class="btn btn-success" onclick="publishMsg()">发布</a>
									</div>
					          </form>
					        </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
