/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#bindDevicesBtn").click(
                    function() {
                        bindDevice();
                    });
            $("#bindDeviceSnBtn").click(
                    function() {
                        bindDeviceSn();
                    });
            $("#deleteUserDeviceBtn").click(
                    function() {
                        deleteUserDevice();
                    });
            $("#bindDeviceBluetoothBtn").click(
                    function() {
                        bindDeviceBluetooth();
                    });
        });

/**
 * 绑定设备
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


/**
 * 绑定设备-SN设备
 */
function bindDeviceBluetooth() {
    var token = $("#token").val().trim();
    var mac_addr = $("#mac_addr").val().trim();
    var device_id = $("#device_id").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/bind/bluetooth/",
        type : "GET",
        dataType : "json",
        data : {
            token : token,
            mac_addr : mac_addr,
            device_id : device_id
        },
        success : function(data) {
            $("#bindDevicesDiv").html(JSON.stringify(data));
        }
    });
}

/**
 * 绑定设备-蓝牙设备
 */
function bindDeviceSn() {
    var token = $("#token").val().trim();
    var sn = $("#sn").val().trim();
    var device_id = $("#device_id").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/bind/sn/",
        type : "GET",
        dataType : "json",
        data : {
            token : token,
            sn : sn,
            device_id : device_id
        },
        success : function(data) {
            $("#bindDevicesDiv").html(JSON.stringify(data));
        }
    });
}

function deleteUserDevice(){
    var device_id = $("#device_id").val().trim();
    var token = $("#token").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/unbind/",
        type : "POST",
        dataType : "json",
        data : {
            device_id : device_id,
            token : token
        },
        success : function(data) {
            $("#bindDevicesDiv").html(JSON.stringify(data));
        }
    });
}
