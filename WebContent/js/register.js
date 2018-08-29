// JavaScript Document

function loadDialog() {
	var dialog = document.getElementById("dialog");
	dialog.style.display = "block";
	var bg = document.getElementById("bg");
	bg.style.display = "block";
}

function cancel() {
	window.location = "login.html";
}

function agree() {
	var dialog = document.getElementById("dialog");
	dialog.style.display = "none";
	var bg = document.getElementById("bg");
	bg.style.display = "none";
	var userName = document.getElementById("userName");
	userName.focus();
}

function validate() {
	var userName = document.getElementById("userName");
	var userNameMsg = document.getElementById("userNameMsg");
	var userNameSuccess = document.getElementById("userNameSuccess");
	if (!/^.+$/.test(userName.value)) {
		// if(userName.value == ""){
		// alert("用户名不能为空");
		userNameMsg.innerHTML = "<img src='images/!.png' alt='' />用户名不能为空";
		userName.focus();
		return false;
	} else if (!/^.{4,20}$/.test(userName.value)) {
		// if(userName.value.length < 4 || userName.value.length > 20){
		// alert("长度只能在4-20个字符之间");
		userNameMsg.innerHTML = "<img src='images/!.png' alt='' />长度只能在4-20个字符之间";
		userName.focus();
		return false;
	} else if (/^\d+$/.test(userName.value)) {
		// if(!isNaN(userName.value)){
		// alert("用户名不能是纯数字，请重新输入！");
		userNameMsg.innerHTML = "<img src='images/!.png' alt='' />用户名不能是纯数字，请重新输入！";
		userName.focus();
		return false;
	} else if (!/^[\u4e00-\u9fa5\w-]+$/.test(userName.value)) {
		userNameMsg.innerHTML = "<img src='images/!.png' alt='' />格式错误，仅支持汉字、字母、数字、“-”、“_”的组合";
		userName.focus();
		return false;
	} else {
		// alert("用户名格式正确");
		userNameSuccess.style.display = "inline";
		userNameMsg.innerHTML = "&nbsp;";
	}

	var password = document.getElementById("password");
	var passwordMsg = document.getElementById("passwordMsg");
	var passwordSuccess = document.getElementById("passwordSuccess");
	if (!/^.+$/.test(password.value)) {
		// if(password.value == ""){
		// alert("密码不能为空");
		passwordMsg.innerHTML = "<img src='images/!.png' alt='' />密码不能为空";
		password.focus();
		return false;
	} else if (!/^.{6,20}$/.test(password.value)) {
		// if(password.value.length < 6 || password.value.length > 20){
		// alert("长度只能在6-20个字符之间");
		passwordMsg.innerHTML = "<img src='images/!.png' alt='' />长度只能在6-20个字符之间";
		password.focus();
		return false;
	} else if (/^(\d+|[a-zA-Z]+|[^0-9a-zA-Z]+)$/.test(password.value)) {
		passwordMsg.innerHTML = "<img src='images/ruo.png' alt='' />有被盗风险，建议使用字母、数字和符号两种以上组合";
		password.focus();
		return false;
	} else if (/^(?!\D+$)(?![^a-zA-Z]+$)(?![0-9a-zA-Z]+$)/.test(password.value)) {
		passwordMsg.innerHTML = "<img src='images/qiang.png' alt='' />你的密码很安全";
		passwordSuccess.style.display = "inline";
		passwordMsg.style.color = "#43C75A";
	} else if (/^(?!\d+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z]+$)/.test(password.value)) {
		passwordMsg.innerHTML = "<img src='images/zhong.png' alt='' />安全强度适中，可以使用三种以上的组合来提高安全强度";
		passwordSuccess.style.display = "inline";
		passwordMsg.style.color = "#999999";
	}

	var repassword = document.getElementById("repassword");
	var repasswordMsg = document.getElementById("repasswordMsg");
	var repasswordSuccess = document.getElementById("repasswordSuccess");
	if (!/^.+$/.test(repassword.value)) {
		// if(repassword.value == ""){
		// alert("确认密码不能为空");
		repasswordMsg.innerHTML = "<img src='images/!.png' alt='' />确认密码不能为空";
		repassword.focus();
		return false;
	} else if (password.value !== repassword.value) {
		// alert("两次密码输入不一致");
		repasswordMsg.innerHTML = "<img src='images/!.png' alt='' />两次密码输入不一致";
		repassword.focus();
		return false;
	} else {
		// alert("确认密码格式正确");
		repasswordSuccess.style.display = "inline";
		repasswordMsg.innerHTML = "&nbsp;";
	}

	var phone = document.getElementById("phone");
	var phoneMsg = document.getElementById("phoneMsg");
	var phoneSuccess = document.getElementById("phoneSuccess");
	if (!/^.+$/.test(phone.value)) {
		// if(phone.value == ""){
		// alert("手机不能为空");
		phoneMsg.innerHTML = "<img src='images/!.png' alt='' />手机不能为空";
		phone.focus();
		return false;
	} else if (!/^.{11}$/.test(phone.value)) {
		// if(phone.value.length != 11){
		// alert("手机号必须为11位");
		phoneMsg.innerHTML = "<img src='images/!.png' alt='' />手机号必须为11位";
		phone.focus();
		return false;
	} else if (!/^\d+$/.test(phone.value)) {
		// if(isNaN(phone.value)){
		// alert("手机号必须由数字组成");
		phoneMsg.innerHTML = "<img src='images/!.png' alt='' />手机号必须由数字组成";
		phone.focus();
		return false;
	} else if (!/^1[38]\d{9}|15[012356789]\d{8}|14[57]\d{8}|17[678]\d{8}$/
			.test(phone.value)) {
		phoneMsg.innerHTML = "<img src='images/!.png' alt='' />手机号格式错误";
		phone.focus();
		return false;
	} else {
		// alert("手机格式正确");
		phoneSuccess.style.display = "inline";
		phoneMsg.innerHTML = "&nbsp;";
	}

	var validateCode = document.getElementById("validateCode");
	var validateCodeMsg = document.getElementById("validateCodeMsg");
	if (!/^.+$/.test(validateCode.value)) {
		// if(validateCode.value == ""){
		// alert("验证码不能为空");
		validateCodeMsg.innerHTML = "<img src='images/!.png' alt='' />验证码不能为空";
		validateCode.focus();
		return false;
	} else if (!/^.{4}$/.test(validateCode.value)) {
		// if(validateCode.value.length != 4){
		// alert("手机验证码必须为4位");
		validateCodeMsg.innerHTML = "<img src='images/!.png' alt='' />验证码必须为4位";
		validateCode.focus();
		return false;
	} else if (!/^[0-9a-zA-Z]+$/.test(validateCode.value)) {
		// if(isNaN(validateCode.value)){
		// alert("验证码必须由数字和字母组成");
		validateCodeMsg.innerHTML = "<img src='images/!.png' alt='' />验证码必须由数字和字母组成";
		return false;
	} else {
		// alert("验证码格式正确");
		// validateCodeMsg.innerHTML = "<img src='images/√.png' alt='' />验证码格式正确";
		// validateCodeMsg.style.color = "#43C75A";
	}
	return true;
}

function getValidateCode() {
	var validateCodeImg = document.getElementById("validateCodeImg");
	validateCodeImg.setAttribute("src", "maintain/ValidateCodeServlet?" + new Date().getTime());
}