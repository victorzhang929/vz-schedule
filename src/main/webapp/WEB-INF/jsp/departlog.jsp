<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="include/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/subiframepage.css"/>
<script type="text/javascript" src="<%=basePath%>js/departlog.js"></script>
<title>部门日志</title>
</head>

<body>
<div id="box">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="javascript:void(0);" title="日志中心" class="tip-bottom"> <i class="icon-cogs"></i> 日志中心
			</a> <a href="javascript:void(0);" title="部门日志" class="tip-right"> 部门日志 </a>
		</div>
	</div>

	<!-- 表格start -->
	<div class="container-fluid">
		<!-- <hr> -->
		<div class="row-fluid">
			<div class="span12">
				<!-- 查询条件 -->
				<div class="widget-box">
					<div class="widget-content nopadding">
						<div class="controls controls-row">
							<select id="querytype" class="span2 m-wrap select2-container select2-container-active select2-dropdown-open">
							</select>
							<input type="text" id="querystadate" class="span2 m-wrap" placeholder="开始日期" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'queryenddate\')||\'2099-12-31\'}'})" >
							<input type="text" id="queryenddate" class="span2 m-wrap" placeholder="结束日期" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'querystadate\')}',maxDate:'2099-12-31'})" >
							<button type="submit" id="query" class="span1 btn btn-success" onclick="load()"><i class="icon-search"></i>查询</button>
						</div>
					</div>
				</div>

				<!-- 表格2 -->
				<div class="widget-box">
					<div class="widget-content ">
						<div id="tableDiv"></div>
						<div id="tableDivPage"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
