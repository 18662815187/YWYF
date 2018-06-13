<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
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
<title>运费编辑</title>
<style type="text/css">
#allUrl img {
	　　width: 160px;
	　　height: 120px;
	　　-webkit-transition: -webkit-transform 1s ease 0s;
	　　transition: transform 1s ease 0s;
	　　-webkit-transform-origin: center;
	　　transform-origin: center;
}

#allUrl img:hover {
	　　-webkit-transform: scale(1.2);
	　　transform: scale(1.2);
}
</style>
<style type="text/css">
.h2_ch a:hover, .h2_ch a.here {
	color: #FFF;
	font-weight: bold;
	background-position: 0px -32px;
}

.h2_ch a {
	float: left;
	height: 32px;
	margin-right: -1px;
	padding: 0px 16px;
	line-height: 32px;
	font-size: 14px;
	font-weight: normal;
	border: 1px solid #C5C5C5;
	background: url('<%=basePath%>/images/admin/bg_ch.gif') repeat-x scroll
		0% 0% transparent;
}

a {
	color: #06C;
	text-decoration: none;
}
</style>

</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 运费 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="javascript:history.back(-1);"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<h2 class="h2_ch">
		<span id="tabs"> <!-- class的样式中here是选中后未蓝色底，未被选中的是nor白底 --> <a
			href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>

		</span>
	</h2>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="<%=basePath%>/mailfee/saveOrUpdate"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${mailfee.id}" />
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 运费描述:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="title" maxlength="100" size="100"
							value="${mailfee.title}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 商家:</td>
						<td width="80%" class="pn-fcontent"><select name="phId">
								<option value="0">--自营--</option>
								<c:forEach items="${pharmacies}" var="data">
									<option value="${data.id}"
										<c:if test="${mailfee.phId == data.id }">selected="selected"</c:if>>${data.pharmacyName}</option>
								</c:forEach>
						</select></td>
					</tr>


					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 江浙沪运费:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="money" maxlength="100" size="100"
							value="${mailfee.money}" /></td>
					</tr>

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 非江浙沪运费:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="money1" maxlength="100" size="100"
							value="${mailfee.money1}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 满多少包邮:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="topUp" maxlength="100" size="100"
							value="${mailfee.topUp}" /></td>
					</tr>

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 状态:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isUse" value="0" checked="checked"
							<c:if test="${mailfee.isUse==false}">checked="checked"</c:if> />正常&nbsp&nbsp<input
							type="radio" class="required" name="isUse" value="1"
							<c:if test="${mailfee.isUse==true}">checked="checked"</c:if> />禁用&nbsp&nbsp
						</td>
					</tr>

				</tbody>


				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2">
							<div id="error">
								<span style="color: red">${msg}</span>
							</div>
						</td>
					</tr>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" /> <!--<input type="button" class="submit" onclick="submitPd()" value="提交" />-->
							&nbsp; <input type="reset" class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>