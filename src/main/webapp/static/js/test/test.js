/**
 * http://usejsdoc.org/
 */
$(document).ready(function(){
    
	$("#popAlert").hide();
	$("#smsBtn").click(function(){
		var phone = $("#phone").val().trim();
		var verification = $("#verification").val().trim();
		if(phone == "" || verification == "" || phone == "请输入用户名" || verification == "请输入密码"){
			alert("phone or verification is null");
		} else {
		    smsSend(phone, verification);
		}
	});
	
	$("#confirmBtn").click(function(){
		closeH5Page();
	});
});

/**
 * 调用test接口
 * 
 * @param name
 * @param passwd
 * @returns
 */
function smsSend(phone, verification){
	$.ajax({
	     url: rootpath + "/huaxin/sms/",
         type: "GET",
         dataType: "json",
         data: {
        	 phone: phone,
        	 verification:verification
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