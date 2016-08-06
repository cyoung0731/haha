/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            var yesterdar = CurentTime() - 1
            $("#value").val("[{dt:"+CurentTime()+", steps:1234},{dt:"+yesterdar+", steps:5678}]");
            $("#record_date").val(CurentTime());
            
            $("#uploadDeviceDataBtn").click(
                    function() {
                        uploadDeviceData();
                    });
            $("#uploadStepDataBtn").click(
                    function() {
                        uploadStepData();
                    });
            $("#uploadBloodSugarDataBtn").click(
                    function() {
                        uploadBloodSugarData();
                    });
            $("#uploadBloodPressureDataBtn").click(
                    function() {
                        uploadBloodPressureData();
                    });
        });

function uploadDeviceData(){
    var token = $("#token").val().trim();
    var device_id = $("#device_id").val().trim();
    var indicator_id = $("#indicator_id").val().trim();
    var value = $("#value").val().trim();
    var derive_data = $("#derive_data").val().trim();
    var record_date = $("#record_date").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/device/upload/",
        type : "POST",
        dataType : "json",
        data : {
            token : token,
            device_id : device_id,
            indicator_id : indicator_id,
            value : value,
            derive_data : derive_data,
            record_date : record_date
        },
        success : function(data) {
            alert("haha")
        }
    });
}

function uploadStepData(){
    var token = $("#token").val().trim();
    var device_id = $("#device_id").val().trim();
    var value = $("#value").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/device/upload/step/",
        type : "POST",
        dataType : "json",
        data : {
            token : token,
            device_id : device_id,
            steps : value
        },
        success : function(data) {
            alert("haha")
        }
    });
}

function uploadBloodSugarData(){
    var value = $("#value").val().trim();
    var time_type = $("#derive_data").val().trim();
    var date = $("#record_date").val().trim();
    var device_id = $("#device_id").val().trim();
    var token = $("#token").val().trim();
    
    $.ajax({
        url : rootpath + "/fangzhou/device/upload/bloodsugar/",
        type : "POST",
        dataType : "json",
        data : {
            token : token,
            device_id : device_id,
            blood_glucose : value,
            time_type : time_type,
            date : date
        },
        success : function(data) {
            alert("haha")
        }
    });
}

function uploadBloodPressureData(){
    var token = $("#token").val().trim();
    var device_id = $("#device_id").val().trim();
    var high_pressure = $("#high_pressure").val().trim();
    var low_pressure = $("#low_pressure").val().trim();
    var heart_rate = $("#heart_rate").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/device/upload/bloodpressure/",
        type : "POST",
        dataType : "json",
        data : {
            token : token,
            device_id : device_id,
            high_pressure : high_pressure,
            low_pressure : low_pressure,
            heart_rate : heart_rate
        },
        success : function(data) {
            alert("haha")
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

