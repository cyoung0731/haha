/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#bindDevicesBtn").click(
                    function() {
                        bindDevice("/fangzhou/getdevices/");
                    });
            $("#deleteUserDeviceBtn").click(
                    function() {
                        deleteUserDevice();
                    });
        });

/**
 * 获取设备列表
 * 
 * @param name
 * @param passwd
 * @returns
 */
function bindDevice() {
    var token = $("#token").val().trim();
    var code = $("#code").val().trim();
    var device_id = $("#device_id").val().trim();
    var relogin = $("#relogin").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/bind/",
        type : "GET",
        dataType : "json",
        data : {
            token : token,
            code : code,
            device_id : device_id,
            relogin : relogin
        },
        success : function(data) {
            $("#bindDevicesDiv").html(JSON.stringify(data));
        }
    });
}

function deleteUserDevice(){
    var user_id = $("#user_id").val().trim();
    var device_id = $("#device_id").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/unbind/",
        type : "POST",
        dataType : "json",
        data : {
            device_id : device_id,
            user_id : user_id
        },
        success : function(data) {
            alert(JSON.stringify(data))
        }
    });
}
