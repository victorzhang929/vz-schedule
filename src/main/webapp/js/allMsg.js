$(function(){
	tableDivPage();//生成分页信息div(必须)
	p_pageSelect();//页码
	load();//读取数据
});

function load(pge){
	if(isNaN(pge)){pge=1;}//没传入页码，置为1
	
	// 查询参数
	var param = {};
	param._page = pge;
	param._pageSize = $("#p_pageSelect_id").val();//分页
	
	// 搜索字段
	param.isread = $("#querytype option:selected").val();
	param.stadate = $("#querystadate").val();
	param.enddate = $("#queryenddate").val();
	
	$.ajax({
        url: path+"/message/querymsgpage.do",
		type : "POST",
		data : param,
		dataType : "json",
		success : function(res) {
			var mainTable = "<table class='table table-bordered table-striped' >"
				+ "<thead><tr>"
				+ "<th style='width:5%'>编号</th>"
				+ "<th style='width:50%'>消息内容</th>"
				+ "<th style='width:15%'>发送人</th>"
				+ "<th style='width:15%'>发送日期</th>" 
				+ "<th style='width:15%'>发送人IP</th>" 
				+ "</tr></thead>" + "<tbody id='trs'>";

		var datas = res.data;
		if (datas.length > 0) {
			for ( var i = 0; i < datas.length; i++) {
				var data = datas[i];
				mainTable += "<tr>"
					+ "<td>"+index(res.page,res.pageSize,i)+"</td>"
					+ "<td title='"+data.ms+"'>" + data.ms + "</td>" 
					+ "<td title='"+data.realname+"'>" + data.realname + "</td>"
					+ "<td title='"+data.seuserdate+"'>" + data.seuserdate + "</td>"
					+ "<td title='"+data.seuserip+"'>" + data.seuserip + "</td>"
					+ "</tr>";
			}
		} else {
			mainTable += "<tr><td colspan='5'>暂无数据!</td></tr>";
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