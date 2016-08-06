/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#getReportListBtn").click(
                    function() {
                        getReportList();
                    });
            $("#sendReportListBtn").click(
                    function() {
                        sendReportList();
                    });
        });


function getReportList() {
    var token = $("#token").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/checkup/report/list/",
        type : "GET",
        dataType : "json",
        data : {
            token : token
        },
        success : function(data) {
            $("#devicesDiv").html(JSON.stringify(data));
        }
    });
}

function sendReportList() {
    $.ajax({
        url : rootpath + "/fangzhou/checkup/send/report/list/",
        type : "GET",
        dataType : "json",
        data : {
        },
        success : function(data) {
            $("#devicesDiv").html(JSON.stringify(data));
        }
    });
}
