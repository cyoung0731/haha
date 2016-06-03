/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            var phone = $("#phone").val().trim();
            $("#getUseridByPhoneBtn").click(
                    function() {
                        getUseridByPhone(phone);
                    });
        });

/**
 * getUserid
 * 
 * @param name
 * @param passwd
 * @returns
 */
function getUseridByPhone(phone) {
    $.ajax({
        url : rootpath + "/dongya/getUseridByPhone/",
        type : "GET",
        dataType : "json",
        data : {
            phone : phone,
        },
        success : function(data) {
            if (data.status == 0) {
                
                $("#useridInput").val(data.result.userid);
            } else {
                alert("失败")
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
            if (data.status == 0) {
                $("#resultDiv").html(data.msg);
            } else {
                alert("失败")
                $("#resultDiv").html(data.msg);
            }
        }
    });
}