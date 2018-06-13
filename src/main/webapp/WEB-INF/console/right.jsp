<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="head.jsp"%>
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
<title>syrm-right</title>
</head>
<body>
	<div class="box-positon">
		<h1>当前位置: 首页 - 欢迎页</h1>
	</div>
	<div class="body-box">

		<div class="welcom-con">
			<div class="we-txt">
				<p>
					欢迎使用生医容美微信活动管理系统！<br /> <br /> 生医容美官方客服电话：0571-83731253<br /> <br />
					您上次登录的时间是：
					<fmt:formatDate value="${LONGIN_TIME}"
						pattern="yyyy-MM-dd HH:mm:ss" />
					<br /><br />
				</p>
			</div>
			<ul class="ms">
				<li class="wxx" style="text-align:center;">通知公告</li>
				<li class="attribute" style="text-align: center;">发布时间</li>
			</ul>
			<div class="ms-xx">
				<c:forEach items="${notices}" var="n">
					
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td style="float:left;width:500px;text-align:center;" height="30" align="right"><a style="color:ff33cc" href="notice/toNotice?id=${n.id}">${n.title}</a></td>
								<td style="text-align: left;" width="590" height="30" align="right"><fmt:formatDate value="${n.addtime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
						</table>
				
				</c:forEach>
			</div>

		</div>

	</div>

</body>
</html>