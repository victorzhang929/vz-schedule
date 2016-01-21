<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<%@ include file="include/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/subiframepage.css"/>
<script type="text/javascript" src="<%=basePath%>js/departInfo.js"></script>
<title>部门信息</title>
</head>

<body>
<div id="box">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="javascript:void(0);" title="个人中心" class="tip-bottom"> <i class="icon-cogs"></i> 个人中心
			</a> <a href="javascript:void(0);" title="学院信息" class="tip-right"> 学院信息 </a>
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
							<input type="text" id="dname" class="span2 m-wrap" placeholder="学院名称">
							<a id="query" class="span1 btn btn-success" onclick="load()"><i class="icon-search"></i>查询</a>
							<a id="add" href="#modalDiv2" data-toggle='modal' class="span1 btn btn-primary" onclick="addDepartUI()"><i class="icon-plus"></i>添加部门</a>
							<a id="upload" herf="javascript:void(0);" class="span1 btn btn-warning" onclick=""><i class="icon-upload"></i>上传Excel</a>
							<a id="download" herf="javascript:void(0);" class="span1 btn btn-info" onclick=""><i class="icon-download"></i>下载Excel</a>
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

<!-- 修改部门信息模态框 -->
<div id="modalDiv" class="modal hide">
	<!-- 表头div -->
	<div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>修改学院信息</h3>
	</div>
	<!-- 表单div -->
	<div class="modal-body">
  		<form class="form-horizontal" method="post" name="basic_validate">
  			<input id="mdepartid" type="hidden" />
   			<div class="control-group">
   				<label class="control-label">学院名称:</label>
   				<div class="controls">
     				<input id="mdname" type="text" style="height:28px;" />
   				</div>
   			</div>
   			<div class="control-group">
       			<label class="control-label">学院电话:</label>
         		<div class="controls">
         			<input id="mdphone" type="text" style="height:28px;" />
       			</div>
   			</div>
   			<div class="control-group">
   				<label class="control-label">学院地址:</label>
   				<div class="controls">
       				<input id="mdaddress" type="text" style="height:28px;" />
   				</div>
   			</div>
   			<div class="control-group">
   				<label class="control-label">联系人:</label>
   				<div class="controls">
       				<input id="mdconnperson" type="text" style="height:28px;" />
   				</div>
   			</div>
   			<div class="control-group">
   				<label class="control-label">联系人电话:</label>
   				<div class="controls">
       				<input id="mdconnphone" type="text" style="height:28px;" />
   				</div>
   			</div>
		</form>
        </div>
	<!-- 底部按钮div -->
	<div class="modal-footer"> 
		<div id='msg'></div>
		<a data-dismiss="modal" class="btn" href="javascript:void(0);"> <i class="icon-remove"></i>取消</a> 
		<a class="btn btn-success" href="javascript:void(0);" onclick="editInfo()"> <i class="icon-ok"></i>保存</a> 
	</div>
</div>

<div id="modalDiv2" class="modal hide">
	<!-- 表头div -->
	<div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h3>添加学院</h3>
	</div>
	<!-- 表单div -->
	<div class="modal-body">
  		<form class="form-horizontal" method="post">
   			<div class="control-group">
   				<label class="control-label">学院名称:</label>
   				<div class="controls">
     				<input id="m2dname" type="text" style="height:28px;" />
   				</div>
   			</div>
   			<div class="control-group">
   				<label class="control-label">学院电话:</label>
   				<div class="controls">
     				<input id="m2dphone" type="text" style="height:28px;" />
   				</div>
   			</div>
   			<div class="control-group">
   				<label class="control-label">学院地址:</label>
   				<div class="controls">
     				<input id="m2daddress" type="text" style="height:28px;" />
   				</div>
   			</div>
   			<div class="control-group">
   				<label class="control-label">学院联系人:</label>
   				<div class="controls">
     				<input id="m2dconnperson" type="text" style="height:28px;" />
   				</div>
   			</div>
   			<div class="control-group">
   				<label class="control-label">联系人电话:</label>
   				<div class="controls">
     				<input id="m2dconnphone" type="text" style="height:28px;" />
   				</div>
   			</div>
		</form>
    </div>
	<!-- 底部按钮div -->
	<div class="modal-footer"> 
		<div id='msg2'></div>
		<a data-dismiss="modal" class="btn" href="javascript:void(0);"> <i class="icon-remove"></i>取消</a> 
		<a class="btn btn-success" href="javascript:void(0);" onclick="addDepart()"> <i class="icon-ok"></i>添加</a> 
	</div>
</div>
</body>
</html>
