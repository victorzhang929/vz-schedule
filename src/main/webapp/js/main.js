$(function(){//初始化未读消息
	initUnreadMsg('myMsgUnread','unreadSum');
});

function initUnreadMsg(msId,numId){
	$.ajax({
		type: "POST",
		url: path+"/message/getUnreadMsg.do",
		dataType: "json",
		error: function(request) {
			tipDialog("提交失败，连接错误。请刷新页面重试。");
	    },success: function(re){
	    	var msg = "";
	    	var unreadSum = 0;
    		var datas = re.data;
    		unreadSum = re.unreadNum;
    		if (datas.length > 0) {
    			for ( var i = 0; i < datas.length; i++) {
    				msg += "<li><a href='javascript:void(0);' title='' onclick=showNews('"+datas[i].msid+"','"+datas[i].ms+"') ><i class='icon-envelope-alt'></i>"+datas[i].ms+"</a></li>";
    			}
    		}
    		msg += "<li><a href='javascript:void(0);' title='全部消息' onclick=clickNavMenu('this.id','"+path+"/message/getAllMsg.do') ><i class='icon-bell'></i> 全部消息 >></a></li>";
	    	$("#"+msId).html(msg);
	    	$("#"+numId).html(unreadSum);
		}
	});
}

/**
 * 阅读未读信息
 * @param msid
 * @param ms
 */
function showNews(msid,ms){
	dialog({
		title:'详细消息内容',
		width:320,
		fixed: true,
	    content: "<textarea disabled style='width:100%'>"+ms+"</textarea>",
    	okValue: '确定',
        ok: function () {
            this.title('提交中…');
            $.ajax({
        		type: "POST",
        		url: path+"/message/doReadMsg.do",
        		data:{msid:msid},
        		error: function(request) {
        			tipDialog("提交失败，连接错误。请刷新页面重试。");
        	    },success: function(res){
        	    	tipDialog("已阅读");
        	    	initUnreadMsg('myMsgUnread','unreadSum');
        	    }
            });
        }
	}).show();
}

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
	//清空验证信息
	$("#msg").html("");
	
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
 	var h1 = iframe.contents().find("#"+(id||"box")).height();//box高度
 	var h2 = document.body.clientHeight-110;//body高度
 	if(h1!=null){
 		if(h1>h2){
 			iframe.height(h1+25);
 			$("#content").height(h1);
 		}else{
 			iframe.height(h2+25);
 			$("#content").height(h2);
 		}
 	}else{
 		iframe.height(h2+25);
 		$("#content").height(h2);
 	}
}