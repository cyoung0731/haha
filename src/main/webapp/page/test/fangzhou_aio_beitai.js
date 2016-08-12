/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#report_time").val(CurentTime());
            $("#report_id").val('BT'+CurentTime()+'00000000000001461656839000');
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
    var user_phone = $("#user_phone").val().trim();
    var user_idcard = $("#user_idcard").val().trim();
    var report_id = $("#report_id").val().trim();
    var report_time = $("#report_time").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/aio/beitai/scan/",
        type : "GET",
        dataType : "json",
        data : {
            token : token,
            user_phone : user_phone,
            user_idcard : user_idcard,
            report_id : report_id,
            appid : appid,
            report_time : report_time
        },
        success : function(data) {
            $("#bindDevicesDiv").html(JSON.stringify(data));
        }
    });
}


function upload() {
    var token = $("#token").val().trim();
    var user_phone = $("#user_phone").val().trim();
    var user_idcard = $("#user_idcard").val().trim();
    var report_id = $("#report_id").val().trim();
    var report_time = $("#report_time").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/aio/beitai/upload/",
        type : "GET",
        dataType : "json",
        data : {
            token : token,
            user_phone : user_phone,
            user_idcard : user_idcard,
            report_id : report_id,
            report_time : report_time
        },
        success : function(data) {
        }
    });
}



//若要显示:当前日期加时间(如:20090612120000)
function CurentTime()
{ 
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();  //秒
    var clock = year + "";
    if(month < 10)
        clock += "0";
    clock += month + "";
    if(day < 10)
        clock += "0";
    clock += day + "";
    if(hh < 10)
        clock += "0";
    clock += hh ;
    if (mm < 10) clock += '0'; 
    clock += mm; 
    if (ss < 10) clock += '0';
    clock += ss;
    return(clock); 
} 
