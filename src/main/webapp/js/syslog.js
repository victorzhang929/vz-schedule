$(function(){
	tableDivPage();//生成分页信息div(必须)
	p_pageSelect();//页码
	queryAllLogLx('querytype','1');//初始化日志类型
	queryAllDepartment('querydepart');//初始化所有学院
	load();//读取数据
});

function load(pge){
	if(isNaN(pge)){pge=1;}//没传入页码，置为1
	
	// 查询参数
	var param = {};
	param._page = pge;
	param._pageSize = $("#p_pageSelect_id").val();//分页
	
	// 搜索字段
	param.loglx = $("#querytype").val();
	param.dname = $("#querydepart").val();
	param.stadate = $("#querystadate").val();
	param.enddate = $("#queryenddate").val();
	//系统日志1，部门日志2，用户为3
	param.roleType = "1";
	
	$.ajax({
        url: path+"/log/querylogpage.do",
		type : "POST",
		data : param,
		dataType : "json",
		success : function(res) {
			var mainTable = "<table class='table table-bordered table-striped' >"
				+ "<thead><tr>"
				+ "<th style='width:5%'>编号</th>"
				+ "<th style='width:8%'>操作类型</th>"
				+ "<th style='width:8%'>用户名</th>"
				+ "<th style='width:8%'>真实姓名</th>"
				+ "<th style='width:51%'>操作说明</th>"
				+ "<th style='width:10%'>操作日期</th>" 
				+ "<th style='width:10%'>IP地址</th>" 
				+ "</tr></thead>" + "<tbody id='trs'>";

		var datas = res.data;
		if (datas.length > 0) {
			for ( var i = 0; i < datas.length; i++) {
				var data = datas[i];
				mainTable += "<tr>"
					+ "<td>"+index(res.page,res.pageSize,i)+"</td>"
					+ "<td title='"+data.loglx+"'>" + data.loglx + "</td>" 
					+ "<td title='"+data.username+"'>" + data.username + "</td>" 
					+ "<td title='"+data.realname+"'>" + data.realname + "</td>" 
					+ "<td title='"+data.logms+"'>" + data.logms + "</td>"
					+ "<td title='"+data.userdate+"'>" + data.userdate + "</td>"
					+ "<td title='"+data.userip+"'>" + data.userip + "</td>"
					+ "</tr>";
			}
		} else {
			mainTable += "<tr><td colspan='7'>暂无数据!</td></tr>";
		}
		
		mainTable += "</tbody></table>";

		p_countMsg(res.count);// 显示总记录数
		p_page(res.page, res.pageSum,res.count);// 刷新页码区域

		$("#tableDiv").html(mainTable);// 显示表格
		t_bs("trs");// 隔行变色
		
		parent.setFrameHeight("box");
		},beforeSend : function(){
			beforeSend();
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}