/**
 * http://usejsdoc.org/
 */
$(document).ready(
        function() {
            $("#testBtn").click(
                    function() {
                        test();
                    });
            $("#searchpageBtn").click(
                    function() {
                        searchpage();
                    });
            $("#getDataBtn").click(
                    function() {
                        getData();
                    });
            $("#getHtmlBtn").click(
                    function() {
                        getHtml();
                    });
            $("#getReportListBtn").click(
                    function() {
                        getReportList();
                    });
        });

/**
 *接口验证
 * 
 * @param thname 用户名
 * @param license 授权码
 * @returns
 */
function test() {
    var thname = $("#thname").val().trim();
    var license = $("#license").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/minzhong/test/",
        type : "GET",
        dataType : "json",
        data : {
            thname : thname,
            license : license
        },
        success : function(data) {
            $("#bindDevicesDiv").html(JSON.stringify(data));
        }
    });
}

/**
 * 查询页面
 * 
 * @param code 身份证（客户体检下号码）
 * @param barcode 条形码（客户体检条形码）
 * @returns
 */
function searchpage() {
    var code = $("#code").val().trim();
    var barcode = $("#barcode").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/minzhong/searchpage/",
        type : "GET",
        dataType : "json",
        data : {
            code : code,
            barcode : barcode
        },
        success : function(data) {
        }
    });
}

/**
 * 获取体检报告数据json
 * 
 * @param code 身份证（客户体检下号码）
 * @param barcode 条形码（客户体检条形码）
 * @returns
 */
function getData() {
    var barcode = $("#barcode").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/minzhong/get/data/",
        type : "GET",
        dataType : "json",
        data : {
            barcode : barcode
        },
        success : function(data) {
        }
    });
}

/**
 * 获取体检报告html
 * 
 * @param code 身份证（客户体检下号码）
 * @param barcode 条形码（客户体检条形码）
 * @returns
 */
function getHtml() {
    var barcode = $("#barcode").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/minzhong/get/html/",
        type : "GET",
        dataType : "json",
        data : {
            barcode : barcode
        },
        success : function(data) {
        }
    });
}

/**
 * 获取体检报告列表
 * 
 * @param code 身份证（客户体检下号码）
 * @param barcode 条形码（客户体检条形码）
 * @returns
 */
function getReportList() {
    var code = $("#code").val().trim();
    $.ajax({
        url : rootpath + "/fangzhou/minzhong/get/report/list/",
        type : "GET",
        dataType : "json",
        data : {
            code : code
        },
        success : function(data) {
        }
    });
}
