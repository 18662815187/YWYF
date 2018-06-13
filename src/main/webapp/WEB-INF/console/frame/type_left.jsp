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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>product-left</title>
<link href="./css/admin.css" rel="stylesheet" type="text/css"/>
<link href="./css/theme.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.validate.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.treeview.css" rel="stylesheet" type="text/css"/>
<link href="./css/jquery.ui.css" rel="stylesheet" type="text/css"/>

<script src="./js/jquery.js" type="text/javascript"></script>
<script src="./js/jquery.ext.js" type="text/javascript"></script>
<script src="./js/jquery.form.js" type="text/javascript"></script>
<script src="./js/itcast.js" type="text/javascript"></script>
<script src="./js/admin.js" type="text/javascript"></script>
</head>
<body class="lbody">
<div class="left">
<%@ include file="../date.jsp" %>
     <ul class="w-lful">
		<li><a href="<%=basePath%>/type/list.do" target="rightFrame">大分类管理</a></li>
		<li><a href="<%=basePath%>/funType/list.do" target="rightFrame">二级分类管理</a></li>
		<li><a href="<%=basePath%>/DisType/list.do" target="rightFrame">三级分类管理</a></li> 
		<!-- <li><a href="/type/list.do" target="rightFrame">商品分类</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">商品参数</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">规格管理</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">商品属性</a></li>
		<li><a href="../type/list.jsp" target="rightFrame">到货通知</a></li>-->
     </ul>
</div>
</body>
</html>