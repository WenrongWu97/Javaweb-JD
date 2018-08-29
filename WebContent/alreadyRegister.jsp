<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册失败</title>
</head>
<body>
	<img src="../images/logo.png" />
	<h1 style="color:red">${param.userName }，已被注册，请更换用户名重新注册。</h1>
	<a href="../register.html">重新注册</a>
</body>
</html>