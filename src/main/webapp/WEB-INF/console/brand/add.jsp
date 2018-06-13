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
<title>ywyf-edit</title>
<script type="text/javascript">
	//上传图片
	function uploadPic() {
		//jquery.form.js
		var options = {
			url : "<%=basePath%>/uploadFck.do",
			dataType : "json",
			type : "post",
			success : function(data) {
				//alert(1);
				//返回的数据通过下方控件的id给他们设置值,src需要通过attr属性去设置，val可以直接设置
				//原数据存在图片时，重新上传替换URL需对此两个地方进行回显设置
				$("#allUrl").attr("src", data.url);
				$("#imgUrl").val(data.url);
			}
		}
		//ajax模拟jvForm这个form的提交
		$("#jvForm").ajaxSubmit(options);
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 品牌管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="javascript:history.back(-1);"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="float: right">
		<form id="jvForm"
			action="<%=basePath%>/brand/add.do?name1=${name1}&isDisplay1=${isDisplay1}&pageNo1=${pageNo1}"
			method="post">
			<!-- 下方三个参数为id和列表页的查询条件和当前页码 -->
			<input type="hidden" value="${brand.id}" name="id" /> <input
				type="hidden" value="${Brand_name1}" /> <input type="hidden"
				value="${fac_name1}" /><input type="hidden" value="${isShow1}" />
			<input type="hidden" value="${pageNo1}" />
			<input type="hidden" value="${brand.isShow}" name="isShow" />
			<input type="hidden" value="${brand.isDel}" name="isDel" />
			<input type="hidden" value="${brand.addtime}" name="addtime" />
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 品牌名称:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="brandName" value="${brand.brandName}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传品牌LOGO(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">所属厂家:</td>
						<td width="80%" class="pn-fcontent"><select name="facId">
								<option value="">请选择厂家</option>
								<c:forEach items="${factories}" var="fac">
									<option value="${fac.id}"
										<c:if test="${brand.facId == fac.id }">selected="selected"</c:if>>${fac.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrl" src="${brand.pic}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="pic" id="imgUrl" value="${brand.pic}" />
							<!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic --> <input
							type="file" name="pic1" onchange="uploadPic()" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否可用:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							name="isDisplay" value="1"
							<c:if test="${brand.isShow}">checked="checked"</c:if> />可用 <input
							type="radio" name="isDisplay" value="0"
							<c:if test="${!brand.isShow}">checked="checked"</c:if> />不可用</td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" /> &nbsp; <input type="reset"
							class="reset" value="重置" /></td>
					</tr>
					<tr>
						<td class="pn-fbutton" colspan="2"><span style="color: red">${msg}</span></td>
					</tr>
				</tbody>
				
			</table>
		</form>
	</div>
</body>
</html>