/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#scanBtnDy").click(
                    function() {
                        scan(1);
                    });
            $("#scanBtnFz").click(
                    function() {
                        scan(2);
                    });
            $("#uploadBtn").click(
                    function() {
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
    var dn = $("#dn").val().trim();
    var tag2 = $("#tag2").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/aio/hufeng/scan/",
        type : "GET",
        dataType : "json",
        data : {
            token : token,
            dn : dn,
            tag2 : tag2,
            appid : appid
        },
        success : function(data) {
            alert(data);
            $("#bindDevicesDiv").html(data);
        }
    });
}


function upload() {
    var token = $("#token").val().trim();
    var dn = $("#dn").val().trim();
    var tag2 = $("#tag2").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/aio/hufeng/upload/",
        type : "GET",
        dataType : "json",
        data : {
            dn : dn,
            tag2 : tag2
        },
        success : function(data) {
        }
    });
}
