<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册成功</title>
</head>
<body>
	<img src="../images/logo.png" />
	<h1>${param.userName }注册成功！</h1>
	<h1>姓名：${param.userName }</h1>
	<h1>手机：${param.phone }</h1>
	<h1>验证码：${param.validateCode }</h1>
</body>
</html>