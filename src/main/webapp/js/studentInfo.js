$(function(){
	tableDivPage();//生成分页信息div(必须)
	p_pageSelect();//页码
	queryAllDepartment('querydepart');
	$("#queryclasses").html("<option>所属班级</option>");
	$("#querydepart").change(function(){//学院发生变化
		queryAllClassesByDname('querydepart','queryclasses');//初始化所有学院名称
	});
	load();//读取数据
});

function load(pge){
	if(isNaN(pge)){pge=1;}//没传入页码，置为1
	
	// 查询参数
	var param = {};
	param._page = pge;
	param._pageSize = $("#p_pageSelect_id").val();//分页
	
	// 搜索字段
	param.dname = $("#querydepart").val();
	param.cname = $("#queryclasses").val();
	param.roleid = "4";//学生
	
	$.ajax({
        url: path+"/user/getUserInfos.do",//查询学生信息
		type : "POST",
		data : param,
		dataType : "json",
		success : function(res) {
			var mainTable = "<table class='table table-bordered table-striped' >"
				+ "<thead><tr>"
				+ "<th style='width:5%'>编号</th>"
				+ "<th style='width:25%'>真实姓名</th>" 
				+ "<th style='width:25%'>所在学院</th>"
				+ "<th style='width:10%'>所在班级</th>"
				+ "<th style='width:15%'>操作</th>" 
				+ "</tr></thead>" + "<tbody id='trs'>";

		var datas = res.data;
		if (datas.length > 0) {
			for ( var i = 0; i < datas.length; i++) {
				var data = datas[i];
				mainTable += "<tr>"
					+ "<td>"+index(res.page,res.pageSize,i)+"</td>"
					+ "<td title='"+data.realname+"'>" + data.realname + "</td>" 
					+ "<td title='"+data.dname+"'>" + data.dname + "</td>" 
					+ "<td title='"+data.cname+"'>" + data.cname + "</td>"
					+ "<td>"+ handle(data.userid) +"</td>"
					+ "</tr>";
			}
		} else {
			mainTable += "<tr><td colspan='5' style='height:40px;font-size:14px;font-size:16px;font-weight: 700;'>暂无数据!</td></tr>";
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
function handle(userid){
	return "<a id='classes" + userid + "' class='bt bt-xs bt-info' onclick=getInfo('" + userid + "');>详细</a>" +
			"<a href='#modalDiv' data-toggle='modal' id='update" + userid + "' class='bt bt-xs bt-success' onclick=editInfoUI('" + userid + "');>修改</a>" +
	 		"<a id='delete" + userid + "' class='bt bt-xs bt-danger' onclick=deleteInfo('" + userid + "');>删除</a>";
}

/**
 * 获取当前用户信息(填充到模态框(详情和添加共用))
 * @param userid
 */
function getInfo(userid){
	$("#msg2").html("");
	$.ajax({
        url: path+"/user/getUserInfoByUserid.do",
		type : "POST",
		data : {userid:userid},
		dataType : "json",
		success : function(res) {
			$("#modalDiv2").modal('show');;
			$("#m2dname").html("<option>"+res.dname+"</option>").attr("disabled","disabled");
			$("#m2cname").html("<option>"+res.cname+"</option>").attr("disabled","disabled");
			$("#m2username").val(res.username).attr("disabled","disabled");
			$("#m2realname").val(res.realname).attr("disabled","disabled");
			$("#m2usermobile").val(res.usermobile).attr("disabled","disabled");
			$("#m2usermail").val(res.usermail).attr("disabled","disabled");
			$("#m2btnComfirm").attr("disabled","disabled");
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 用户信息修改导航
 * @param userid
 */
function editInfoUI(userid){
	$("#msg").html("");
	$.ajax({
		url: path+"/user/getUserInfoByUserid.do",
		type : "POST",
		data : {userid:userid},
		dataType : "json",
		success : function(res) {
			$("#muserid").val(userid);
			queryAllDepartment('mdname',res.dname);
			initClassesByDname('mdname','mcname',res.cname);
			$("#mdname").change(function(){//学院发生变化
				queryAllClassesByDname('mdname','mcname',res.cname);
			});
			$("#mrealname").val(res.realname);
		},error : function(request) {
			tipDialog("读取失败");
		}
	});
}

/**
 * 信息修改
 */
function editInfo(){
	//获取填入信息
	var param = {};
	param.userid = $("#muserid").val();
	param.dname = $("#mdname").val();
	param.cname = $("#mcname").val();
	param.realname = $("#mrealname").val();
	//前台验证
	if(param.dname=="所属学院"){$("#msg").html("<span class='label label-important'>请选择学院</span>");return false;}
	if(param.cname=="所属班级"){$("#msg").html("<span class='label label-important'>请选择班级</span>");return false;}
	if(param.realname==""){$("#msg").html("<span class='label label-important'>真实姓名不能为空</span>");return false;}
	
	$.ajax({
        url: path+"/user/doUpdateStuTeaInfo.do",
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
 * 删除该用户
 * @param userid
 */
function deleteInfo(userid){
	var d = dialog({
		title:'提示',
		content:"确定删除该用户？",
		okValue:'确定',
		ok:function(){
			$.ajax({
		        url: path+"/user/deleteUserInfo.do",
				type : "POST",
				data : {userid:userid},
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
 * 添加用户显示模态框，清空信息
 */
function addStudentUI(){
	$("#msg2").html("");
	$("#m2dname").removeAttr("disabled");
	$("#m2cname").removeAttr("disabled");
	queryAllDepartment('m2dname');
	$("#m2cname").html("<option>所属班级</option>");
	$("#m2dname").change(function(){//学院发生变化
		queryAllClassesByDname('m2dname','m2cname');//初始化所有学院名称
	});
	
	$("#m2username").removeAttr("disabled").val("");
	$("#m2realname").removeAttr("disabled").val("");
	$("#m2usermobile").removeAttr("disabled").val("");
	$("#m2usermail").removeAttr("disabled").val("");
}

/**
 * 添加班级
 */
function addStudent(){
	var param = {};
	param.dname = $("#m2dname").val();
	param.cname = $("#m2cname").val();
	param.username = $("#m2username").val();
	param.realname = $("#m2realname").val();
	param.usermobile = $("#m2usermobile").val();
	param.usermail = $("#m2usermail").val();
	param.roleid = "4";
	
	//前台验证
	if(param.dname=="所属学院"){$("#msg2").html("<span class='label label-important'>请选择所属学院</span>");return false;}
	if(param.cname=="所属班级"){$("#msg2").html("<span class='label label-important'>请选择所属班级</span>");return false;}
	if(param.username==""){$("#msg2").html("<span class='label label-important'>用户名不能为空</span>");return false;}
	if(param.realname==""){$("#msg2").html("<span class='label label-important'>真实姓名不能为空</span>");return false;}
	var validateMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(param.usermobile!=""&&!validateMobile.test(param.usermobile)){$("#msg2").html("<span class='label label-important'>请输入正确的手机号</span>");return false;}
	var validateEmail = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	if(param.usermail!=""&&!validateEmail.test(param.usermail)){$("#msg2").html("<span class='label label-important'>请输入正确的邮箱地址</span>");return false;}
	
	$.ajax({
        url: path+"/user/addUserInfo.do",
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
