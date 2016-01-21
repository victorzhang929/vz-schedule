$(function(){
	parent.setFrameHeight("box");
});

/**
 * 提交用户信息
 * @returns
 */
function submitUserInfo(){
	//清空所有验证
	$("#userInfoForm div").removeClass("error");
	var param = {};
	param.username = $("#username").val();
	param.realname = $("#realname").val();
	param.usermobile = $("#usermobile").val();
	param.useridcard = $("#useridcard").val();
	param.usermail = $("#usermail").val();
	
	//前台验证
	if(param.username==""){$("#username").parent().parent().addClass("error");}
	if(param.realname==""){$("#realname").parent().parent().addClass("error");}
	var validateMobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(!validateMobile.test(param.usermobile)){$("#usermobile").parent().parent().addClass("error");}
	var validateIdcard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if(!validateIdcard.test(param.useridcard)){$("#useridcard").parent().parent().addClass("error");}
	var validateEmail = /[a-zA-Z0-9]{1,10}@[a-zA-Z0-9]{1,5}\.[a-zA-Z0-9]{1,5}/;
	if(!validateEmail.test(param.usermail)){$("#usermail").parent().parent().addClass("error");}
	
	//验证没有通过
	if($("#userInfoForm div").attr("class").indexOf("error")!=-1) return;
	//验证通过
	$.ajax({
		type:"POST",
		url:path+"/user/saveUserInfo.do",
		data:param,
		dataType: "json",
		error: function(request) {
			tipDialog("提交失败，连接错误。请刷新页面重试。");
	    },success: function(re){
	    	if(re != null){
	    		tipDialog(re.msg);
	    	}
		}
	});
}