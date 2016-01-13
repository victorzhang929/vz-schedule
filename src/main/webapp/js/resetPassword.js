/**
 * 重置密码
 */
$(function(){
	enterProccess("resetSubmit");
	if (!window.applicationCache) {
		$("#password").attr('placeholder','');
		$("#repassword").attr('placeholder','');
	}
});

/**
 * 清空密码输入框
 */
function cancelInput(){
	$("#password").val('');
	$("#repassword").val('');
}

/**
 * 重置密码
 */
function doResetPassword(){
	//判断是否输入框不为空
	if($("#password").val()==""){tipDialog("请输入密码");return false;}
	if($("#repassword").val()==""){tipDialog("请重复输入密码");return false;}
	//判断两次输入一致
	if($("#password").val()!=$("#repassword").val()){tipDialog("两次密码输入不一致");return false;}
	
	var _path = path;//jquery.url.js 获取当前相对路径
	tipDialog("正在提交……");
	var param = {};
	param.username = jQuery.url.param('username');
	param.checkcode = jQuery.url.param('checkcode');
	param.password = $("#password").val();
	param.repassword = $("#repassword").val();
	
	$.ajax({
		type: "POST",
		url: _path+"/index/doResetPassword.index",
		data: param,
		dataType: "json",
		error: function(request) {
			tipDialog("提交失败，连接错误。请刷新页面重试。");
	    },success: function(re){
	    	if(re.successMsg != undefined){
	    		confirmDialog(re.successMsg,basePath);
	    	}else{
	    		tipDialog(re.errorMsg);
	    	}
		}
	});
}