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
 * 必须将页面内容用#box的div包裹或传递参数id,否则缺省使用body的高度.但这样的话将不能自动缩小高度
 * @param id
 */
function setFrameHeight(id){ 
 	setTimeout(function(){
	 	var iframe = $("#iframepage");
	 	var h = iframe.contents().find("#"+(id||"box")).height() //参数id优先
		 		 || iframe.contents().height(); //缺省值为body的高度
		 	
		 	var h1 = iframe.contents().find("#"+(id||"box")).height();
		 	var h2 = document.body.clientHeight - 110;
		 	
		 	if(h1 != null){
		 		if(h1>h2){
		 			iframe.height(h1);
		 		}else{
		 			iframe.height(h2);
		 		}
		 	}else{
		 		iframe.height(h2);
		 	}
		 },200); //延时
 }