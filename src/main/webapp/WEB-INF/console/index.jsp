<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>院外药房管理系统</title>
</head>
<frameset rows="72,*" frameborder="0" border="0" framespacing="0">
	<frame src="<%=basePath%>/control/top.do" name="topFrame" noresize="noresize" id="leftFrame" />
	<frame src="<%=basePath%>/control/main.do" name="mainFrame" id="mainFrame" />
</frameset>
<noframes><body></body></noframes>
</html>
