/**首页,登录页js脚本*/
$(function(){
	enterProccess("loginSubmit");
	if (!window.applicationCache) {
		$("#username").attr('placeholder','');
		$("#password").attr('placeholder','');
	}
});

/**
 * 登录事件
 * @returns {Boolean}
 */
function login(){
	if($("#username").val()==""){tipDialog("请输入用户名");return false;}
	if($("#password").val()==""){tipDialog("请输入密码");return false;}
	tipDialog("正在登录……");
	$.ajax({
		type: "POST",
		url: path+"/index/login.index",
		data: {username:$("#username").val(),password:$("#password").val()},
		dataType: "json",
		error: function(request) {
			tipDialog("登录失败，连接错误。请刷新页面重试。");
	    },success: function(re){
	    	if(re != null && re.userid != undefined){
	    		//把用户名写在cookie中
	    		$.cookie('realname', re.realname);//仅本次操作，浏览器关闭就失效。
	    		location.href = path +"/index/mainPage.do";
	    	}else{
	    		tipDialog("登录失败，用户名或密码错误");
	    	}
		}
	});
}

/**
 * 发送邮件事件
 */
function sendMail(){
	tipDialog("正在发送……");
	$.ajax({
		type: "POST",
		url: path+"/index/sendMail.index",
		data: {email:$("#email").val()},
		dataType: "json",
		error: function(request) {
			tipDialog("发送邮件失败，请刷新页面重试。");
	    },success: function(re){
	    	if(re != null && re.sendMailMsg != undefined){
	    		confirmDialog(re.sendMailMsg,basePath);
	    	}else{
	    		tipDialog("您填写的邮箱地址不存在");
	    	}
		}
	});
}

