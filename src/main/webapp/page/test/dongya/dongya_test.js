/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#dakaJzcBtn").click(
                    function() {
                        dakaJzc();
                    });
        });

/**
 * 颈椎操打卡
 * 
 */
function dakaJzc(getDeviceUrl) {
    var token = $("#token").val().trim();
    $.ajax({
        url : rootpath + "/dongya/daka/jzc/",
        type : "POST",
        dataType : "json",
        data : {
            token : token
        },
        success : function(data) {
            if (data.status == 0) {
                $("#devicesDiv").html(JSON.stringify(data.result));
            } else {
                alert("失败")
            }
        }
    });
}