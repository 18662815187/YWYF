<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>product-left</title>
<link href="./css/admin.css" rel="stylesheet" type="text/css" />
<link href="./css/theme.css" rel="stylesheet" type="text/css" />
<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css" />
<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css" />
<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css" />

<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/jquery.form.js" type="text/javascript"></script>
<script src="./js/itcast.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
</head>
<body class="lbody">
	<div class="left">
		<%@ include file="../date.jsp"%>
		<ul class="w-lful">
			<li><a href="<%=basePath%>/banner/list.do" target="rightFrame">广告管理</a></li>
			<li><a href="<%=basePath%>/posstion/list.do" target="rightFrame">广告位置管理</a></li>
			<li><a href="<%=basePath%>/article/list.do" target="rightFrame">文章管理</a></li>
			<li><a href="<%=basePath%>/links/list.do" target="rightFrame">友情链接管理</a></li>
			<li><a href="<%=basePath%>/AboutUs/list.do" target="rightFrame">关于我们</a></li>
			<li><a href="<%=basePath%>/drugstore/list.do" target="rightFrame">药店入驻</a></li>
			<li><a href="<%=basePath%>/mailfee/list.do" target="rightFrame">运费管理</a></li>
			<!--<li><a href="../type/list.jsp" target="rightFrame">商品参数</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">规格管理</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">商品属性</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">到货通知</a></li>-->
		</ul>
	</div>
</body>
</html>