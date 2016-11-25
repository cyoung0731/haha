/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#bindBtn").click(
                    function() {
                        bind();
                    });
        });

function bind(){
    var token = $("#token").val().trim();
    var code = $("#code").val().trim();
    var device_id = 1217001;
    var bind_type = 1;
    var url = dyhttpaddr+"/device/bind/";
    var paramstr = "token="+token+"&code="+code+"&device_id="+device_id+"&bind_type="+bind_type;
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
        	alert(JSON.stringify(data))
        	$("#resultDiv").val(JSON.stringify(data));
        }
    });
}
