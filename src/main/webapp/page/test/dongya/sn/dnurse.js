/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#time").val(CurentTime());
            $("#sendDataNeiwangBtn").click(
                    function() {
                    	sendData(dynwhttpaddr);
                    });
            $("#sendDataWaiwangBtn").click(
                    function() {
                    	sendData(dygwhttpaddr);
                    });
            $("#sendDataLocalBtn").click(
                    function() {
                    	sendData(dybdhttpaddr);
                    });
        });

function sendData(httpaddr){
    var imei = $("#imei").val().trim();
    var time = get_unix_time($("#time").val().trim());
    var value = $("#value").val().trim();
    var phase = $("#phase").val().trim();
    var url = httpaddr+"/device/upload/bloodsugar/dnurse";
    var paramstr = "imei="+imei+"&time="+time+"&value="+value+"&phase="+phase;
    $.ajax({
        url : rootpath + "/test/rest/",
        type : "POST",
        dataType : "json",
        data : {
            url : url,
            type : "post",
            paramstr : paramstr
        },
        success : function(data) {
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
    var ss = now.getSeconds(); //秒
    var clock = year + "";
    if(month < 10)
        clock += "0";
    clock += month + "";
    if(day < 10)
        clock += "0";
    clock += day + "";
    if(hh < 10)
        clock += "0";
    clock += hh;
    if (mm < 10) clock += '0'; 
    clock += mm; 
    if (ss < 10) clock +='0';
    clock += ss;
    return(clock); 
} 

//获取当前时间戳到毫秒
function CurrentTimestamp()
{
	var timestamp = (new Date()).valueOf();
	return timestamp;
}

//时间字符串转换成时间戳
function get_unix_time(dateStr)
{
	var newstr = dateStr.substr(0,4)+"/"+dateStr.substr(4,2)+"/"+dateStr.substr(6,2)+" "+dateStr.substr(8,2)+":"+dateStr.substr(10,2)+":"+dateStr.substr(12,2);
    var date =  new Date(newstr); 
    var time_str = date.getTime().toString();
    return time_str.substr(0, 10);
}
