/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#smsBtn").click(
                    function() {
                        var phone = $("#phone").val().trim();
                        var verification = $("#verification").val().trim();
                        if (phone == "" || verification == ""
                                || phone == "请输入手机号"
                                || verification == "请输入验证码") {
                            alert("phone or verification is null");
                        } else {
                            smsSend(phone, verification);
                        }
                    });

            $("#voiceBtn").click(
                    function() {
                        var phone = $("#phone").val().trim();
                        var verification = $("#verification").val().trim();
                        if (phone == "" || verification == ""
                                || phone == "请输入手机号"
                                || verification == "请输入验证码") {
                            alert("phone or verification is null");
                        } else {
                            voiceSend(phone, verification);
                        }
                    });
        });

/**
 * 调用发送短信接口
 * 
 * @param name
 * @param passwd
 * @returns
 */
function smsSend(phone, verification) {
    $.ajax({
        url : rootpath + "/huaxin/sms/",
        type : "GET",
        dataType : "json",
        data : {
            phone : phone,
            verification : verification
        },
        success : function(data) {
            if (data.status == 0) {
                $("#resultDiv").html(data.msg);
            } else {
                alert("失败")
                $("#resultDiv").html(data.msg);
            }
        }
    });
}

/**
 * 调用发送语音验证码接口
 * 
 * @param phone
 * @param verification
 */
function voiceSend(phone, verification) {
    $.ajax({
        url : rootpath + "/huaxin/voice/",
        type : "GET",
        dataType : "json",
        data : {
            phone : phone,
            verification : verification
        },
        success : function(data) {
                $("#resultDiv").html(data.msg);
            }
    });
}