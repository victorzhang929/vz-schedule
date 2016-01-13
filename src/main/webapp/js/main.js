/**
 * main.jsp主页js支持
 */
jQuery(document).ready(function() {
	$("#realname").html("<b>"+$.cookie('realname')+"</b>");//从cookie中取出用户名，射入
});

/**
 * 退出按钮事件
 */
function exit(){
	cfmcclDialog("确定退出系统吗？",basePath+"index/exit.do");
}

/**
 * 侧边导航栏事件和顶部导航栏事件同步
 * @param id导航
 * @param pageUrl地址
 */
function clickNavMenu(id,pageUrl){
	$("#user-nav li").removeClass("active");
	$("#sidebar ul li").removeClass("active");
	$("#"+id).parent().addClass("active");
	$("#iframepage").attr("src",pageUrl);
}

/**
 * 修改密码弹出框之前清空输入框内容
 */
function changePasswordUI(){
	$("#oldpassword").val("");
	$("#password").val("");
	$("#password2").val("");
}

/**
 * 修改密码
 */
function changePassword(){
	//前台验证
	if($("#oldpassword").val()==""){$("#msg").html("<span class='label label-important'>原密码不能为空</span>");return false;}
	if($("#password").val()==""){$("#msg").html("<span class='label label-important'>新密码不能为空</span>");return false;}
	if($("#password2").val()==""){$("#msg").html("<span class='label label-important'>重复密码不能为空</span>");return false;}
	if($("#password").val()!=$("#password2").val()){$("#msg").html("<span class='label label-important'>两次密码不一致</span>");return false;}
	
	var param = {};
	param.oldpassword = $("#oldpassword").val();
	param.password = $("#password").val();
	param.password2 = $("#password2").val();
	$.ajax({
		type: "POST",
		url: path+"/user/changePassword.do",
		data: param,
		dataType: "json",
		error: function(request) {
			tipDialog("提交失败，连接错误。请刷新页面重试。");
	    },success: function(re){
	    	$("#msg").html("<span class='label label-important'>"+re.msg+"</span>");
	    	if(re.msg=='修改密码成功'){
	    		setTimeout(function(){location.href=path+"/index/exit.do";},2000);
	    	}
		}
	});
}

/**
 * 必须将页面内容用#box的div包裹或传递参数id,否则缺省使用body的高度.但这样的话将不能自动缩小高度
 */
function setFrameHeight(id){ 
 	var iframe = $("#iframepage");
 	var h = iframe.contents().find("#"+(id||"box")).height() //参数id优先
 		 || iframe.contents().height(); //缺省值为body的高度
 	iframe.height((h>460) ? (h+50) : 460);  //575 565
}