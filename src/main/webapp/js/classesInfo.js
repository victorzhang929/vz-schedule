$(function(){
	tableDivPage();//生成分页信息div(必须)
	p_pageSelect();//页码
	queryAllDepartment('querytype');//初始化所有学院名称
	load();//读取数据
});

function load(pge){
	if(isNaN(pge)){pge=1;}//没传入页码，置为1
	
	// 查询参数
	var param = {};
	param._page = pge;
	param._pageSize = $("#p_pageSelect_id").val();//分页
	
	// 搜索字段
	param.dname = $("#querytype").val();
	param.cname = $("#queryname").val();
	
	$.ajax({
        url: path+"/classes/queryClassesInfos.do",
		type : "POST",
		data : param,
		dataType : "json",
		success : function(res) {
			var mainTable = "<table class='table table-bordered table-striped' >"
				+ "<thead><tr>"
				+ "<th style='width:5%'>编号</th>"
				+ "<th style='width:25%'>班级名称</th>"
				+ "<th style='width:25%'>所在学院</th>"
				+ "<th style='width:10%'>教师人数</th>"
				+ "<th style='width:25%'>学生人数</th>" 
				+ "<th style='width:15%'>操作</th>" 
				+ "</tr></thead>" + "<tbody id='trs'>";

		var datas = res.data;
		if (datas.length > 0) {
			for ( var i = 0; i < datas.length; i++) {
				var data = datas[i];
				mainTable += "<tr>"
					+ "<td>"+index(res.page,res.pageSize,i)+"</td>"
					+ "<td title='"+data.cname+"'>" + data.cname + "</td>" 
					+ "<td title='"+data.dname+"'>" + data.dname + "</td>" 
					+ "<td title='"+data.teaNum+"'>" + data.teaNum + "</td>"
					+ "<td title='"+data.stuNum+"'>" + data.stuNum + "</td>"
					+ "<td>"+ handle(data.classid) +"</td>"
					+ "</tr>";
			}
		} else {
			mainTable += "<tr><td colspan='6' style='height:40px;font-size:14px;font-size:16px;font-weight: 700;'>暂无数据!</td></tr>";
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

/**
 * 表格操作（修改，删除）
 * @param classid
 */
function handle(classid){
	 return "<a href='#modalDiv' data-toggle='modal' id='update" + classid + "' class='bt bt-xs bt-success' onclick=editInfoUI('" + classid + "');>修改</a>" +
	 		"<a id='delete" + classid + "' class='bt bt-xs bt-danger' onclick=deleteInfo('" + classid + "');>删除</a>";
}

/**
 * 班级信息修改导航
 * @param classid
 */
function editInfoUI(classid){
	$("#msg").html("");
	$.ajax({
        url: path+"/classes/getClassesInfo.do",
		type : "POST",
		data : {classid:classid},
		dataType : "json",
		success : function(res) {
			$("#mclassid").val(res.classid);
			$("#mcname").val(res.cname);
			queryAllDepartment('mdepart',res.dname);//初始化所有学院名称
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 班级信息修改
 */
function editInfo(){
	//获取填入信息
	var param = {};
	param.classid = $("#mclassid").val();
	param.cname = $("#mcname").val();
	param.dname = $("#mdepart").val();//学院
	
	//前台验证
	if(param.cname==""){$("#msg").html("<span class='label label-important'>班级名称不能为空</span>");return false;}
	
	$.ajax({
        url: path+"/classes/editClassesInfo.do",
		type : "POST",
		data : param,
		dataType : "json",
		success : function(res) {
			if(res != null){
				$("#msg").html("<span class='label label-important'>"+res.msg+"</span>");
				if(res.msg=='保存成功'){
		    		setTimeout(function(){$("#modalDiv").modal('hide');load();},2000);
		    	}
			}
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 删除当前班级
 * @param classid
 */
function deleteInfo(classid){
	var d = dialog({
		title:'提示',
		content:"删除班级后，教师，学生信息都被删除！",
		okValue:'确定',
		ok:function(){
			$.ajax({
		        url: path+"/depart/deleteClass.do",
				type : "POST",
				data : {classid:classid},
				dataType : "json",
				success : function(res) {
					if(res!=null){
						alert(res.msg);
					}
					load();
				},error : function(request) {
					tipDialog("读取失败");
				}
			});
		},
		cancelValue:'取消',
		cancel:function(){}
	});
	d.showModal();
}

/**
 * 添加班级显示模态框，清空信息
 */
function addClassesUI(){
	$("#msg2").html("");
	$("#m2cname").val("");
	queryAllDepartment('querytype');//初始化所有学院名称
}

/**
 * 添加班级
 */
function addClasses(){
	var param = {};
	param.cname = $("#m2cname").val();
	param.dname = $("#m2depart").val();//学院
	
	//前台验证
	if(param.cname==""){$("#msg2").html("<span class='label label-important'>班级名称不能为空</span>");return false;}
	if(param.dname=="所属学院"){$("#msg2").html("<span class='label label-important'>请选择学院</span>");return false;}
	
	$.ajax({
        url: path+"/depart/addClasses.do",
		type : "POST",
		data : param,
		dataType : "json",
		success : function(res) {
			if(res != null){
				$("#msg2").html("<span class='label label-important'>"+res.msg+"</span>");
				if(res.msg=='添加成功'){
		    		setTimeout(function(){$("#modalDiv2").modal('hide');load();},2000);
		    	}
			}
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

