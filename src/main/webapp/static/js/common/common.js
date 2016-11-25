 //获取web应用上下文
var rootpath="/"+document.location.href.substring(document.location.href.indexOf('//')+2,document.location.href.length).substring(document.location.href.substring(document.location.href.indexOf('//')+2,document.location.href.length).indexOf('/')+1,document.location.href.substring(document.location.href.indexOf('//')+2,document.location.href.length).length).substring(0,document.location.href.substring(document.location.href.indexOf('//')+2,document.location.href.length).substring(document.location.href.substring(document.location.href.indexOf('//')+2,document.location.href.length).indexOf('/')+1,document.location.href.substring(document.location.href.indexOf('//')+2,document.location.href.length).length).indexOf('/'));
var dybdhttpaddr="http://localhost:8081";
var dynwhttpaddr="http://192.168.18.25/rest/3.0"
var dygwhttpaddr="http://dongya.rocedar.com//rest/3.0"
	
/////////////////////////////////////////

var dongya = {
	upyunPre: "http://img.dongya.rocedar.com",
    urlPre: "" //访问前缀 
};

/**
 * 添加又拍云前缀
 * @param pathName
 * @returns {String}
 */
function appendUpyun(pathName){
	return  "http://img.dongya.rocedar.com"+ pathName;
}

/**
 * 获取参数值
 * @param name
 * @returns {*}
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r !== null)
        return decodeURIComponent(r[2]);
    return null; //返回参数值
}

/**
 * 将字符串转换成日期
 * @param dt
 * @returns
 */
function convertDateByDt(dt){
	var dts = dt.toString();
	var result = "未知";
	if(dts.length == 8){
		result = dts.substr(0, 4) + "-" + dts.substr(4, 2) + "-" + dts.substr(6, 2);
	}else if(dts.length == 14){
		result = dts.substr(0, 4) + "-" + dts.substr(4, 2) + "-" + dts.substr(6, 2) 
		+ " " + dts.substr(8, 2) + ":" + dts.substr(10, 2) + ":" + dts.substr(12, 2);
	}
	return result;
}
/**
 * 是否是号码
 * @param phone
 * @returns {Boolean}
 */
function checkPhone(phone){
	var patern = /^(13[0-9]{9})|(15[0-9]{9})|(17[0-9]{9})|(18[0-9]{9})$/;
	if (!patern.test(phone)) {
        return false;
    }
    else{
        return true;
    }
}

/**
 * 检测字符串长度
 * @param value
 * @param min
 * @param max
 */
function checkLength(valueStr, min, max){

	if(valueStr ===  undefined || valueStr.trim().length < min || valueStr.trim().length > max){
		return false;
	}
	return true;
}

