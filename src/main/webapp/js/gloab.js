/**
 * 提示信息弹出框（2秒自动消失）
 * @param message
 */
function tipDialog(message){
	var d = dialog({
		content: message
	});
	d.showModal();
	setTimeout(function () {
	    d.close().remove();
	}, 2000);
}

/**
 * 点击确认消失
 * @param message
 */
function quickComfirmDialog(title,message){
	var d = dialog({
		title: title,
	    content: message,
	});
	d.show();
}


/**
 * 确认弹出框(只有确认按钮)
 * @param message提示信息
 * @param url跳转页面
 */
function confirmDialog(message,url){
	var d = dialog({
	    title: '提示',
	    content: message,
	    okValue: '确定',
	    ok: function () {
	    	this.title('提交中…');
	    	location.href=url;
	    },
	});
	d.show();
}

/**
 * 确认弹出框（确认取消事件）
 * @param message
 */
function cfmcclDialog(message,url){
	var d = dialog({
		title:'提示',
		content:message,
		okValue:'确定',
		ok:function(){
			this.title('提交中…');
			location.href = url;
		},
		cancelValue:'取消',
		cancel:function(){}
	});
	d.showModal();
}

/**
 * 回车事件
 */
var activeEl = null;
function enterProccess(id) {
	activeEl = document.activeElement;
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if (e && (e.keyCode == 13) && document.activeElement == activeEl) {
			setTimeout(function() {
				document.getElementById(id).click();
			}, 50);
		}
	};
}

/**
 * 所有学院名称
 * @param id学院下拉框id
 */
function queryAllDepartment(id,dname){
	$.ajax({
		async : false,
        url: path+"/depart/queryDepartInfos.do",
		type : "POST",
		dataType : "json",
		success : function(res) {
			var mes = "";
			if(dname==undefined){
				mes = "<option>所属学院</option>";
			}else{
				mes = "<option>"+dname+"</option>";
			}
			if (res!= null) {
				var datas = res.data;
			 	for(var i = 0; i < datas.length; i++){
			 		var data = datas[i];
			 		if(dname!=data.dname){
			 			mes = mes + "<option value="+data.dname+">"+data.dname+"</option>";
			 		}
				}
			}
			$("#"+id).html(mes);
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 学院名称班级联动
 * @param departid学院下拉框id
 * @param classid班级下拉框id
 */
function queryAllClassesByDname(departid,classid){
	var dname = $("#"+departid).val();//部门名称
	$.ajax({
		async : false,
        url: path+"/classes/queryAllClassesByDname.do",//查询部门名称，班级名称
		type : "POST",
		data : {dname:dname},
		dataType : "json",
		success : function(res) {
			var mes = "<option>所属班级</option>";//班级下拉框
			if (res!= null) {
			 	for(var i = 0; i < res.length; i++){
			 		var data = res[i];
			 		mes = mes + "<option value="+data.cname+">"+data.cname+"</option>";
			 	}
			}
			$("#"+classid).html(mes);
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 根据学院名称初始化班级
 * @param departid
 * @param classid
 */
function initClassesByDname(departid,classid,cname){
	var dname = $("#"+departid).val();//部门名称
	$.ajax({
		async : false,
		url: path+"/classes/queryAllClassesByDname.do",//查询部门名称，班级名称
		type : "POST",
		data : {dname:dname},
		dataType : "json",
		success : function(res) {
			var mes = "<option>"+cname+"</option>";//班级下拉框
			if (res!= null) {
				for(var i = 0; i < res.length; i++){
					var data = res[i];
					//去除重复
					if(data.cname!=cname){
						mes = mes + "<option value="+data.cname+">"+data.cname+"</option>";
					}
				}
			}
			$("#"+classid).html(mes);
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 日志类型
 * @param id日志下拉框id
 */
function queryAllLogLx(id,roleType){
	$.ajax({
        url: path+"/log/queryAllLogLx.do",
		type : "POST",
		data:{roleType:roleType},
		dataType : "json",
		success : function(res) {
			var mes = "<option>操作类型</option>";
			if (res!= null) {
			 	for(var i = 0; i < res.length; i++){
		 			mes = mes + "<option value="+res[i].loglx+">"+res[i].loglx+"</option>";
				}
			}
			$("#"+id).html(mes);
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}
