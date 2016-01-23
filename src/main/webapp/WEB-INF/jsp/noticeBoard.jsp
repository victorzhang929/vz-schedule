<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="include/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/subiframepage.css" />
<script type="text/javascript" src="<%=basePath%>js/message.js"></script>
<title>公告栏</title>
<script type="text/javascript">
$(function(){
	$.ajax({
        url: path+"/message/getNewestMsg.do",
		type : "POST",
		dataType : "json",
		success : function(res) {
			if(res!=null)
				$("#newestMsg").html(res.ms);	
		},beforeSend : function(){
			beforeSend();
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
});
</script>
</head>

<body>
	<div id="box">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="javascript:void(0);" title="信息中心" class="tip-bottom"> <i class="icon-envelope"></i> 信息中心
				</a> <a href="javascript:void(0);" title="公告栏" class="tip-right"> 公告栏 </a>
			</div>
		</div>

		<!-- 表格start -->
		<div class="container-fluid">
			<!-- <hr> -->
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-list"></i> </span>
            				<h5>最新公告消息</h5>
          				</div>
          				<div class="widget-content" id="newestMsg">
          				</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
