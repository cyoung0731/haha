/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
    alert(1)
	$("#popAlert").hide();
	$("#connectBtn").click(function(){
		var name = $("#name").val().trim();
		var passwd = $("#passwd").val().trim();
		if(name == "" || passwd == "" || name == "请输入用户名" || passwd == "请输入密码"){
			showWeakAlert("用户名和密码不能为空");
		} else {
			connectDevice(name, passwd);
		}
	});
	
	$("#confirmBtn").click(function(){
		closeH5Page();
	});
});

/**
 * 连接设备
 * 
 * @param name
 * @param passwd
 * @returns
 */
function connectDevice(name, passwd){
	$.ajax({
	     url: "/test/",
         type: "GET",
         dataType: "json",
         data: {
        	 username: name,
        	 passwd:passwd
         },
         success: function (data) {
             alert(data)
             if(data.status === 0){
                 alert(1)
             }else{
            	 alert(2)
             }
         }
	});
}

// 显示弱提示
function showWeakAlert(msg){
    var html = "<div class=\"popup-box\"><div class=\"weak-alert\"><div class=\"hint\">" +  msg + "</div></div></div>";
    $("#weakAlert").append(html);

    setTimeout(function(){
        $("#weakAlert").empty();
    }, 1500);
}

// 显示正在注册
function showLoading(){
    var html = "<div class=\"popup-box\"><div class=\"weak-alert\"><div class=\"hint\">注册中...</div></div></div>";
    $("#weakAlert").append(html);
}