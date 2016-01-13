<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  	<!-- CSS载入 -->
  	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap-responsive.min.css" />
	<link rel="stylesheet" href="<%=basePath%>css/uniform.css" />
	<link rel="stylesheet" href="<%=basePath%>css/jquery.gritter.css" />
	
	<!-- select美化控件css -->
	<link rel="stylesheet" href="<%=basePath%>plugins/chosen/chosen.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-style.css" />
	<link rel="stylesheet" href="<%=basePath%>css/matrix-media.css" />
	<link rel="stylesheet" href="<%=basePath%>plugins/font-awesome/css/font-awesome.css"/>
	<!-- diaolg css -->
	<link rel="stylesheet" href="<%=basePath%>plugins/dialog/ui-dialog.css"/>
	<!-- 自己定义的css -->
	<link rel="stylesheet" href="<%=basePath%>css/victorzhang.css" />
	<link rel="shortcut icon" href="<%=basePath%>images/favicon.ico" type="image/x-icon">
	<!-- javascript载入 -->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.js"></script>
	<script src="<%=basePath%>js/jquery.ui.custom.js"></script> 
	<script src="<%=basePath%>js/bootstrap.min.js"></script> 
	<script src="<%=basePath%>js/jquery.uniform.js"></script> 
	<script src="<%=basePath%>js/jquery.dataTables.min.js"></script> 
	<script src="<%=basePath%>js/matrix.js"></script> 
	<script src="<%=basePath%>js/matrix.tables.js"></script>
	<!-- 日期控件 -->
  	<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
  	<!-- diaolg js -->
  	<script src="<%=basePath%>dialog/dialog.js" type="text/javascript"></script>
  	<script src="<%=basePath%>dialog/dialog-plus.js" type="text/javascript"></script>
  	<!-- select美化控件js -->
	<script src="<%=basePath%>plugins/chosen/chosen.jquery.min.js" type="text/javascript"></script>
	<!-- 自定义javascript -->
	<script type="text/javascript" src="<%=basePath%>js/gloab.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/table.js"></script>
	
	<!-- 表单验证 -->
	<script type="text/javascript" src="<%=basePath%>js/jquery.validate.js"></script>
  	
   	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var path = "<%=path%>";
	</script>