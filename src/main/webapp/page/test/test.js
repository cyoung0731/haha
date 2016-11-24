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

    var imei = "imeivalue";
    var value = 8.5;
    var phase = 1;
 alert(rootpath);
alert(dyhttpaddr)
    var url = dyhttpaddr+"/device/upload/bloodsugar/dnurse";
    var paramstr = "imei="+imei+"&time="+CurrentTimestamp()/1000+"&value="+value+"&phase="+phase;

    $.ajax({
        url : rootpath + "/test/rest/",
        type : "POST",
        dataType : "json",
        data : {
            url : url,
            type : "post",
            params : paramstr
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

//获取当前时间戳到毫秒
function CurrentTimestamp()
{
	var timestamp = (new Date()).valueOf();
	return timestamp;
}

