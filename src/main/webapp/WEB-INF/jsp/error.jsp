<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Error Page</title>
<style>
body {
   background-color: #fff; 
   background-image: url("img/error.png");
   background-position: right bottom; 
   background-repeat: no-repeat;
   font-family: "Lucida Console", Monaco, monospace;
}
.error {
	width:800px;
	height: 300px;
	border: 1px, solid, black;
	position: relative;
	top:200px;
	left: 100px;
	font-size:25px;
	line-height: 40px;
}
</style>
</head>
<body>
<div class="error">	
<h2>Error ${pageContext.errorData.statusCode}</h2>
<ul>
<li>Request from ${pageContext.errorData.requestURI} is failed</li>
<li>Servlet name or type: ${pageContext.errorData.servletName}</li>
<li>Exception: ${pageContext.errorData.throwable}</li>
</div>
</body></html>
