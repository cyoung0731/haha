/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#startDate").val(CurentTime());
            $("#endDate").val(CurentTime());
            $("#getUserIdBtn").click(
                    function() {
                        getUserId();
                    });
            $("#getDeviceDataBtn").click(
                    function() {
                        getDeviceData();
                    });
            $("#getDongyaDeviceDataByUserTaskBtn").click(
                    function() {
                        getDongyaDeviceDataByUserTask();
                    });
            $("#getUsingDeviceDataBtn").click(
                    function() {
                        getUsingDeviceData();
                    });
            $("#setIndicatorDeviceBtn").click(
                    function() {
                        setIndicatorDevice();
                    });
            $("#setIndicatorDeviceDeleteBtn").click(
                    function() {
                        setIndicatorDeviceDelete();
                    });
        });

function getUserId(){
    var phone = $("#phone").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/getUseridByPhone/",
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

function getDeviceData(){
    var userId = $("#userId").val().trim();
    var deviceId = $("#deviceId").val().trim();
    var indicatorId = $("#indicatorId").val().trim();
    var startDate = $("#startDate").val().trim();
    var endDate = $("#endDate").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/getdevicedata/",
        type : "GET",
        dataType : "json",
        data : {
            userId : userId,
            deviceId : deviceId,
            indicatorId : indicatorId,
            startDate : startDate,
            endDate : endDate
        },
        success : function(data) {
            $("#getDevicesDataDiv").html(JSON.stringify(data));
        }
    });
}

function getUsingDeviceData(){
    var token = $("#token").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/device/get/using/devicedata/",
        type : "GET",
        dataType : "json",
        data : {
            token : token
        },
        success : function(data) {
            alert(1)
            $("#getDevicesDataDiv").html(JSON.stringify(data));
        }
    });
}

function getDongyaDeviceDataByUserTask(){
    var startDate = $("#startDate").val().trim();
    var endDate = $("#endDate").val().trim();
    $.ajax({
        url : rootpath + "/dongya/getdevicedatalog/",
        type : "GET",
        dataType : "json",
        data : {
            startDate : startDate,
            endDate : endDate
        },
        success : function(data) {
            alert("haha")
//            $("#getDevicesDataDiv").html(JSON.stringify(data));
        }
    });
}

function setIndicatorDevice(){
    var token = $("#token").val().trim();
    var indicatorId = $("#indicatorId").val().trim();
    var deviceId = $("#deviceId").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/device/indicator/update/",
        type : "POST",
        dataType : "json",
        data : {
            token : token,
            indicatorId : indicatorId,
            deviceId : deviceId
        },
        success : function(data) {
            $("#getDevicesDataDiv").html(JSON.stringify(data));
        }
    });
}

function setIndicatorDeviceDelete(){
    var token = $("#token").val().trim();
    var indicatorId = $("#indicatorId").val().trim();
    var deviceId = $("#deviceId").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/device/indicator/delete/",
        type : "POST",
        dataType : "json",
        data : {
            token : token,
            indicatorId : indicatorId,
            deviceId : deviceId
        },
        success : function(data) {
            $("#getDevicesDataDiv").html(JSON.stringify(data));
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

