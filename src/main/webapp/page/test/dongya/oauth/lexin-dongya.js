/**
 * http://usejsdoc.org/
 */
$(document).ready(function() {
    $("#getUserIdBtn").click(function() {
        getUserId();
    });
    $("#bindlocalBtn").click(function() {
        bind(dybdhttpaddr);
    });
    $("#bindneiwangBtn").click(function() {
        bind(dynwhttpaddr);
    });
    $("#bindgongwangBtn").click(function() {
        bind(dygwhttpaddr);
    });
    $("#getSteplocalBtn").click(function() {
        getStep(dybdhttpaddr,1000);
    });
    $("#getSleeplocalBtn").click(function() {
        getStep(dybdhttpaddr,1005);
    });
    $("#sendSteplocalBtn").click(function() {
        sendStep(dybdhttpaddr);
    });
    $("#startdate").val(CurentDate());
    $("#enddate").val(CurentDate());
    getUserId();
});

// 绑定
function bind(dyhttpaddr) {
    var token = $("#token").val().trim();
    var code = $("#code").val().trim();
    var device_id = $("#device_id").val().trim();
    var bind_type = 1;
    var url = dyhttpaddr + "/device/bind/";
    var paramstr = "token=" + token + "&code=" + code + "&device_id=" + device_id + "&bind_type=" + bind_type;
    $.ajax({
        url: rootpath + "/test/rest/",
        type: "POST",
        dataType: "json",
        data: {
            url: url,
            type: "post",
            paramstr: paramstr
        },
        success: function(data) {
            $("#resultDiv").val(JSON.stringify(data));
        }
    });
}

// 获取数据
function getStep(httpaddr,targetTypeId) {
    var userId = $("#userid").val().trim();
    var deviceId = $("#device_id").val().trim();
    var targetId = 40000;
    var tasktId = 3000;
    var startDate = $("#startdate").val().trim();
    var endDate = $("#enddate").val().trim();
    var url = httpaddr + "/device/getdata/";
    var paramstr = "userId=" + userId + "&deviceId=" + deviceId + "&targetTypeId=" + targetTypeId + "&targetId=" + targetId + "&startDate=" + startDate + "&endDate=" + endDate;
    $.ajax({
        url: rootpath + "/test/rest/",
        type: "POST",
        dataType: "json",
        data: {
            url: url,
            type: "get",
            paramstr: paramstr
        },
        success: function(data) {
            $("#resultDiv").val(JSON.stringify(data));
        }
    });
}

// 发送本地步数数据
function sendStep(httpaddr) {
    var userId = $("#userid").val().trim();
    var deviceId = $("#device_id").val().trim();
    var targetTypeId = 1000;
    var targetId = 40000;
    var tasktId = 3000;
    var startDate = $("#startdate").val().trim();
    var endDate = $("#enddate").val().trim();
    var url = httpaddr + "/device/send/data/";
    var paramstr = "userId=" + userId + "&deviceId=" + deviceId + "&targetTypeId=" + targetTypeId + "&targetId=" + targetId + "&startDate=" + startDate + "&endDate=" + endDate;
    $.ajax({
        url: rootpath + "/test/rest/",
        type: "POST",
        dataType: "json",
        data: {
            url: url,
            type: "get",
            paramstr: paramstr
        },
        success: function(data) {
            $("#resultDiv").val("已发送消息，看日志");
        }
    });
}

// 根据手机号获取userId
function getUserId() {
    var phone = $("#phone").val().trim();
    $.ajax({
        url: rootpath + "/dongya/getUserByPhone/",
        type: "GET",
        dataType: "json",
        data: {
            phone: phone
        },
        success: function(data) {
            $("#userid").val(data.result.userid);
            $("#token").val(data.result.token);
        }
    });
}
