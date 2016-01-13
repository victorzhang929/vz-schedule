// 排序的两个临时变量
var t_sort_col = "";
var t_sort_col_ob = "";

/* 创建分页div，必须 */
function tableDivPage() {
	$("#tableDivPage").html(
			"<div id='p_pageSelect' class='p_pageSelectClass'></div>"
					+ "<div id='p_countMsg' class='p_countMsgClass'></div>"
					+ "<div id='p_page' class='pagination alternate' style='width:83%;text-align:right;display:inline;float:right;margin-top:0px;'></div>");
	//width:48%;display:inline;float:right;margin-top:0px;margin-right:-25px;
	//width:48%;display:inline;margin-left:597px;
}

/* 页码选择，可选 */
function p_pageSelect(defRows, selectClass, pages) {
	if(isNaN(defRows)){defRows = 10;}
	var res = "<select id='p_pageSelect_id' style='width:50px;' onchange='load()'>";
	if (typeof (pages) == "undefined" || pages.length == 0) {
		pages = [ 5, 10, 15, 20, 25, 30 ];
	}
	for ( var a = 0; a < pages.length; a++) {
		res += "<option value='" + pages[a] + "'";
		if(defRows == pages[a]){ res += " selected=selected";}
		res += ">" + pages[a] + "</option>";
	}
	res += "</select>";
	$("#p_pageSelect").html(res);
}

/* 总记录数显示 */
function p_countMsg(count, countClass) {
	$("#p_countMsg").html("共 <font class='" + countClass + "'>" + count + "</font> 条记录");
}

/* 页码 */
function p_page(page, pageCount) {
	var res = "<ul>";
	if (page <= 1) {
		res+="<li class='disabled'><a href='javascript:void(0);'>首页</a></li>";
		res+="<li class='disabled'><a href='javascript:void(0);'>上一页</a></li>";
//		res += "首页&nbsp;&nbsp;上一页&nbsp;&nbsp;";
	} else {
		res +="<li><a href='javascript:void(0);' onclick='load(1)'>首页</a></li>";
		res +="<li><a href='javascript:void(0);' onclick='load("+(page - 1)+")'>上一页</a></li>";
		
//		res += "<a href='javascript:;' onclick='load(1)'>首页</a>&nbsp;&nbsp;<a href='javascript:;' onclick='load("
//				+ (page - 1) + ")'>上一页</a>&nbsp;&nbsp;";
	}

	var chang = 5;
	var begin = 0;var end = 0;

	if (pageCount <= chang) {
		begin = 1;
		end = pageCount;
	} else {
		if (page <= (chang+1)/2 ) {
			begin = 1;
			end = chang;
		} else if (page > (pageCount - (chang-1)/2)) {
			begin = pageCount - (chang-1);
			end = pageCount;
		} else {
			begin = page - (chang-1)/2;
			end = page + (chang-1)/2;
		}
	}

	for ( var v = begin; v <= end; v++) {
		if (v == page) {
			res +="<li class='active'><a href='javascript:void(0);' style='width:14px;text-align:center;'>"+v+"</a></li>";
			//res += v + "&nbsp;&nbsp;";
		} else {
			res +="<li><a href='javascript:void(0);' onclick='load(" + v + ")' style='width:14px;text-align:center;'>"+v+"</a></li>";
//			res += "<a href='javascript:;' onclick='load(" + v + ")'>" + v + "</a>&nbsp;&nbsp;";
		}
	}

	if (page >= pageCount) {
		res += "<li class='disabled'><a href='javascript:void(0);'>下一页</a></li>";
		res += "<li class='disabled'><a href='javascript:void(0);'>末页</a></li>";
		//res += "下一页&nbsp;&nbsp;末页&nbsp;&nbsp;";
	} else {
		res += "<li><a href='javascript:void(0);'  onclick='load("+(page+1)+")'>下一页</a></li>";
		res += "<li><a href='javascript:void(0);'  onclick='load("+(pageCount)+")'>末页</a></li>";
		
//		res += "<a href='javascript:;' onclick='load(" + (page + 1)
//				+ ")'>下一页</a>&nbsp;&nbsp;<a href='javascript:;' onclick='load(" + pageCount + ")'>末页</a>&nbsp;&nbsp;";
	}
	
	res += "<li style='margin-left:10px;'>第&nbsp;<input id='p_pageInput' style='text-align:center;width:35px;height:18px;line-height:18px; font-size:12px;' value='"
			+ page
			+ "' onfocus='enterProccess(\"p_pageInputButton\")'>&nbsp;页&nbsp;</li><li><button id='p_pageInputButton' class='btn btn-mini' onclick='p_pageInput()'>跳转</button></li>";
	res +="</ul>";
	$("#p_page").html(res);
}
function p_pageInput() {
	if (parseInt($("#p_pageInput").val())) {
		load($("#p_pageInput").val());
	}
}

/* 排序 */
function t_sort(col) {
	if (col == t_sort_col) {
		if (t_sort_col_ob == "asc") {
			t_sort_col_ob = "desc";
		} else {
			t_sort_col_ob = "asc";
		}
	} else {
		t_sort_col = col;
		t_sort_col_ob = "asc";
	}

	load();

}
/* 排序改造表头 */
function sortHead() {
	if (t_sort_col != "") {
		//var jt = "&nbsp;&nbsp;↓";
		var jt = "&nbsp;&nbsp;<i class='icon-sort-down'></i>";
		if (t_sort_col_ob == "asc") {
			//jt = "&nbsp;&nbsp;↑";
			jt = "&nbsp;&nbsp;<i class='icon-sort-up'></i>";
		}
		$("#head_" + t_sort_col).html($("#head_" + t_sort_col).html() + jt);
	}
}

/* 全选按钮 */
function ckall(check) {
	// $("input[name='ck']").attr("checked",check);
	var s = $("input[name='ck']");
	for ( var v = 0; v < s.length; v++) {
		ck(check, s[v].value);
		s[v].checked = check;
	}
}
/* 复选框 */
function ck(check, id, auto) {
	if (!auto) {
		tr_cbox(id);
	}
	if (check) {
		$("#tr" + id).css("background-color", "#dfe8f6");
	} else {
		$("#tr" + id).css("background-color", "");
	}
}
/* 复选框和行关联 */
function tr_cbox(id) {
	if(document.getElementById("ck" + id)!=null){
		if (document.getElementById("ck" + id).checked) {
			$("input[id='ck" + id + "']").attr("checked", false);
		} else {
			$("input[id='ck" + id + "']").attr("checked", true);
		}
		ck(document.getElementById("ck" + id).checked, id, true);
	}
}
/* 隔行变色 */
function t_bs(tbodyId) {
	var $trs = $("#"+tbodyId+">tr"); // 选择所有行
	$trs.filter(":odd").addClass("odd"); // 给奇数行添加odd样式
	$trs.filter(":even").addClass("even");// 给偶数行添加odd样式

	/*
	 * //点击行,添加变色样式 $trs.click(function(e) {
	 * $(this).addClass("selected").siblings().removeClass("selected"); })
	 */

	// 划过变色
	$trs.hover(function() {
		$(this).addClass("hover"); // 鼠标经过添加hover样式
	}, function() {
		$(this).removeClass("hover"); // 鼠标离开移除hover样式
	});

}
/* 表格ajax调用前显示读取中 */
function beforeSend(){
	var loadHei = 30;
	if($("#tableDiv").height() + $("#tableDivPage").height() >= loadHei){loadHei = $("#tableDiv").height() + $("#tableDivPage").height();}
	$("#tableDiv").html("<div style='height:40px;width:100%;text-align:center;padding-top:"+(loadHei/2-20)+"px;padding-bottom:"+(loadHei/2-20)+"px;'><img src='../images/tload.gif'>&nbsp;数据读取中……</div>");// 读取中的信息
}

/* 表格序号 */
function index(page,pageSize,i){
	var a = parseInt(page-1)*parseInt(pageSize) + 1;
	var b = parseInt(i);
	return (a+b);
}