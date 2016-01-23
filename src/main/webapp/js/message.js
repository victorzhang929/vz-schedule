function publishMsg(){
	var ms = $("#msgContent").val();
	if(ms==""){
		tipDialog("请输入发布信息");return false;
	}else if(ms.length>2000){
		tipDialog("消息内容超过指定长度");return false;
	}
	$.ajax({
		type:"POST",
		url:path+"/message/doPublishMsg.do",
		data:{ms:ms},
		dataType: "json",
		error: function(request) {
			tipDialog("提交失败，连接错误。请刷新页面重试。");
	    },success: function(re){
	    	if(re != null){
	    		tipDialog(re.msg);
	    		if(re.msg=='发布成功')
	    			setTimeout(function(){location.href=path+"/user/userInfoUI.do";},2000);
	    	}
		}
	});
	
}