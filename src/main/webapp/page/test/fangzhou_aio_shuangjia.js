/**
 * http://usejsdoc.org/
 */
$(document).ready(function() {
	$("#time").val(CurentTime());
	$("#scanBtnDy").click(function() {
		scan(1);
	});
	$("#scanBtnFz").click(function() {
		scan(2);
	});
	$("#uploadBtn").click(function() {
		upload();
	});
});

/**
 * 琥蜂扫描二位码
 * 
 * @param name
 * @param passwd
 * @returns
 */
function scan(appid) {
	var token = $("#token").val().trim();
	var phone = $("#phone").val().trim();
	var id = $("#id").val().trim();
	var time = $("#time").val().trim();
	$.ajax({
		url : rootpath + "/fangzhou/aio/shuangjia/scan/",
		type : "GET",
		dataType : "json",
		data : {
			token : token,
			phone : phone,
			id : id,
			appid : appid,
			time : time
		},
		success : function(data) {
		}
	});
}

function upload() {
	var token = $("#token").val().trim();
	var phone = $("#phone").val().trim();
	var id = $("#id").val().trim();
	var time = $("#time").val().trim();
	var bmi = $("#bmi").val().trim();
	var hp = $("#hp").val().trim();
	var lp = $("#lp").val().trim();
	var xl = $("#xl").val().trim();
	$.ajax({
		url : rootpath + "/fangzhou/aio/shuangjia/upload/",
		type : "GET",
		dataType : "json",
		data : {
			token : token,
			phone : phone,
			id : id,
			time : time,
			bmi : bmi,
			hp : hp,
			lp : lp,
			xl : xl
		},
		success : function(data) {
		}
	});
}

// 若要显示:当前日期加时间(如:20090612120000)
function CurentTime() {
	var now = new Date();
	var year = now.getFullYear(); // 年
	var month = now.getMonth() + 1; // 月
	var day = now.getDate(); // 日
	var hh = now.getHours(); // 时
	var mm = now.getMinutes(); // 分
	var ss = now.getSeconds(); // 秒
	var clock = year + "";
	if (month < 10)
		clock += "0";
	clock += month + "";
	if (day < 10)
		clock += "0";
	clock += day + "";
	if (hh < 10)
		clock += "0";
	clock += hh;
	if (mm < 10)
		clock += '0';
	clock += mm;
	if (ss < 10)
		clock += '0';
	clock += ss;
	return (clock);
}
