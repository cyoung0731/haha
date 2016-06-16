/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#getDevicesBtn").click(
                    function() {
                        getDevices("/fangzhou/getdevices/");
                    });
            $("#getDongyatTestBtn").click(
                    function() {
                        getDevices("/dongya/getdevices/test22/");
                    });
            getDongyatTestBtn
            
        });

/**
 * 获取设备列表
 * 
 * @param name
 * @param passwd
 * @returns
 */
function getDevices(getDeviceUrl) {
    var token = $("#token").val().trim();
    var task_indicator_id = $("#task_indicator_id").val().trim();
    var os = $("#os").val().trim();
    $.ajax({
        url : rootpath + getDeviceUrl,
        type : "GET",
        dataType : "json",
        data : {
            token : token,
            task_indicator_id : task_indicator_id,
            os : os
        },
        success : function(data) {
            if (data.status == 0) {
                $("#devicesDiv").html(JSON.stringify(data.result));
                var tableid="#deivcesTable"
                var json=data.result
                var htmls=['<table>']; 
//                htmls.push('<tr>')  
//                for(var k in data.result) htmls.push('<td>'+k+'</td>');
//                htmls.push('</tr>');
                for(var i=0,L=json.length;i<L;i++){
                  htmls.push('<tr>');
                  htmls.push('<td>'+json[i].task_type_id+'</td>');
                  htmls.push('<td>'+json[i].task_type_name+'</td>');
                  
                  for(var k in json[i].device) {
                          htmls.push('<td>'+json[i].device[k].device_id+'</td>');  
                  }
                  htmls.push('</tr>');
                }
                htmls.push('</table>');
                $(tableid).html(htmls.join(''));
                $("#deivcesTable").html(htmls.join(''));
                
            } else {
                alert("失败")
            }
        }
    });
}