/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#startDate").val(CurentTime());
            $("#endDate").val(CurentTime());
            $("#getDeviceDataBtn").click(
                    function() {
                        getDeviceData();
                    });
            $("#getUserIdBtn").click(
                    function() {
                        getUserId();
                    });
        });

function getDeviceData(){
    var userId = $("#userId").val().trim();
    var deviceId = $("#deviceId").val().trim();
    var targetTypeId = $("#targetTypeId").val().trim();
    var targetId = $("#targetId").val().trim();
    var tasktId = $("#tasktId").val().trim();
    var startDate = $("#startDate").val().trim();
    var endDate = $("#endDate").val().trim();
    $.ajax({
        url : rootpath + "/dongya/getdevicedata/",
        type : "GET",
        dataType : "json",
        data : {
            userId : userId,
            deviceId : deviceId,
            targetTypeId : targetTypeId,
            targetId : targetId,
            tasktId : tasktId,
            startDate : startDate,
            endDate : endDate
        },
        success : function(data) {
        }
    });
}

function getUserId(){
    var phone = $("#phone").val().trim();
    $.ajax({
        url : rootpath + "/dongya/getUseridByPhone/",
        type : "GET",
        dataType : "json",
        data : {
            phone : phone
        },
        success : function(data) {
            $("#userId").val(data.result.userid)
        }
    });
}


//若要显示:当前日期加时间(如:2009-06-12 12:00)
function CurentTime()
{ 
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var clock = year + "";
    if(month < 10)
        clock += "0";
    clock += month + "";
    if(day < 10)
        clock += "0";
    clock += day + "";
//    if(hh < 10)
//        clock += "0";
//    clock += hh + ":";
//    if (mm < 10) clock += '0'; 
//    clock += mm; 
    return(clock); 
} 

