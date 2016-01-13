/**
 * 提示信息弹出框
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
 * 回车是假
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