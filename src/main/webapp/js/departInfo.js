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
	param.dname = $("#dname").val();
	
	$.ajax({
        url: path+"/depart/queryDepartInfos.do",
		type : "POST",
		data : param,
		dataType : "json",
		success : function(res) {
			var mainTable = "<table class='table table-bordered table-striped' >"
				+ "<thead><tr>"
				+ "<th style='width:5%'>编号</th>"
				+ "<th style='width:25%'>学院名称</th>"
				+ "<th style='width:10%'>学院电话</th>"
				+ "<th style='width:25%'>学院地址</th>" 
				+ "<th style='width:10%'>联系人</th>" 
				+ "<th style='width:15%'>联系人电话</th>" 
				+ "<th style='width:15%'>操作</th>" 
				+ "</tr></thead>" + "<tbody id='trs'>";

		var datas = res.data;
		if (datas.length > 0) {
			for ( var i = 0; i < datas.length; i++) {
				var data = datas[i];
				mainTable += "<tr>"
					+ "<td>"+index(res.page,res.pageSize,i)+"</td>"
					+ "<td title='"+data.dname+"'>" + data.dname + "</td>" 
					+ "<td title='"+data.dphone+"'>" + data.dphone + "</td>"
					+ "<td title='"+data.address+"'>" + data.address + "</td>"
					+ "<td title='"+data.connperson+"'>" + data.connperson + "</td>"
					+ "<td title='"+data.connphone+"'>" + data.connphone + "</td>"
					+ "<td>"+ handle(data.departid) +"</td>"
					+ "</tr>";
			}
		} else {
			mainTable += "<tr><td colspan='7' style='height:40px;font-size:14px;font-size:16px;font-weight: 700;'>暂无数据!</td></tr>";
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
 * 表格操作（下属班级，修改，删除）
 * @param departid
 */
function handle(departid){
	 return "<a id='classes" + departid + "' class='bt bt-xs bt-info' onclick=getInfo('" + departid + "');>下属班级</a>" +
	 		"<a href='#modalDiv' data-toggle='modal' id='update" + departid + "' class='bt bt-xs bt-success' onclick=editInfoUI('" + departid + "');>修改</a>" +
	 		"<a id='delete" + departid + "' class='bt bt-xs bt-danger' onclick=deleteInfo('" + departid + "');>删除</a>";
}

/**
 * 获取当前学院下所有班级
 * @param departid
 */
function getInfo(departid){
	$.ajax({
        url: path+"/depart/queryClassesByDepartid.do",
		type : "POST",
		data : {departid:departid},
		dataType : "json",
		success : function(res) {
			var datas = res.data;
			if (datas.length > 0) {
				var dclasses = datas[0].cname;
				for ( var i = 1; i < datas.length; i++) {
					//两列放一行
					if(i%2){
						dclasses += "，" + datas[i].cname;
					}else{
						dclasses += "<br/>" + datas[i].cname;
					}
				}
				quickComfirmDialog("下属班级",dclasses);
			}else{
				quickComfirmDialog("下属班级","暂无任何班级");
			}
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 修改当前学院信息导航
 * @param departid
 */
function editInfoUI(departid){
	$("#msg").html("");
	$.ajax({
        url: path+"/depart/getDepartInfo.do",
		type : "POST",
		data : {departid:departid},
		dataType : "json",
		success : function(res) {
			$("#mdepartid").val(res.departid);
			$("#mdname").val(res.dname);
			$("#mdphone").val(res.dphone);
			$("#mdaddress").val(res.address);
			$("#mdconnperson").val(res.connperson);
			$("#mdconnphone").val(res.connphone);
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 更新当前学院信息
 * @returns {Boolean}
 */
function editInfo(){
	
	//获取填入信息
	var param = {};
	param.departid = $("#mdepartid").val();
	param.dname = $("#mdname").val();
	param.dphone = $("#mdphone").val();//座机
	param.address = $("#mdaddress").val();
	param.connperson = $("#mdconnperson").val();
	param.connphone = $("#mdconnphone").val();
	
	//前台验证
	if(param.dname==""){$("#msg").html("<span class='label label-important'>学院名称不能为空</span>");return false;}
	var validatePhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/; //座机验证表达式
	if(param.dphone!=""&&!validatePhone.test(param.dphone)){$("#msg").html("<span class='label label-important'>请输入正确的电话号码</span>");return false;}
	if(param.address==""){$("#msg").html("<span class='label label-important'>学院地址不能为空</span>");return false;}
	var validateMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(param.connphone!=""&&!validateMobile.test(param.connphone)){$("#msg").html("<span class='label label-important'>请输入正确的手机号码</span>");return false;}
	
	$.ajax({
        url: path+"/depart/editDepartInfo.do",
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
 * 删除当前部门
 * @param departid
 */
function deleteInfo(departid){
	var d = dialog({
		title:'提示',
		content:"删除部门后，所有下属部门，教师，学生信息都被删除！",
		okValue:'确定',
		ok:function(){
			$.ajax({
		        url: path+"/depart/deleteDepart.do",
				type : "POST",
				data : {departid:departid},
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
 * 添加学院显示模态框，清空信息
 */
function addDepartUI(){
	$("#msg2").html("");
	$("#m2dname").val("");
	$("#m2dphone").val("");
	$("#m2daddress").val("");
	$("#m2dconnperson").val("");
	$("#m2dconnphone").val("");
}

/**
 * 添加学院
 */
function addDepart(){
	var param = {};
	param.dname = $("#m2dname").val();
	param.dphone = $("#m2dphone").val();//座机
	param.address = $("#m2daddress").val();
	param.connperson = $("#m2dconnperson").val();
	param.connphone = $("#m2dconnphone").val();
	
	//前台验证
	if(param.dname==""){$("#msg2").html("<span class='label label-important'>学院名称不能为空</span>");return false;}
	var validatePhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/; //座机验证表达式
	if(param.dphone!=""&&!validatePhone.test(param.dphone)){$("#msg2").html("<span class='label label-important'>请输入正确的电话号码</span>");return false;}
	if(param.address==""){$("#msg2").html("<span class='label label-important'>学院地址不能为空</span>");return false;}
	var validateMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(param.connphone!=""&&!validateMobile.test(param.connphone)){$("#msg2").html("<span class='label label-important'>请输入正确的手机号码</span>");return false;}
	
	$.ajax({
        url: path+"/depart/addDepart.do",
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
