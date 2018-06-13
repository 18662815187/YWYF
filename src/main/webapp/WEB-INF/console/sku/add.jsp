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
		<form id="jvForm" action="<%=basePath%>/sku/apply" method="post">
			<!-- 下方三个参数为id和列表页的查询条件和当前页码 -->
			<input type="hidden" value="${pro_id}" name="proId" />
		
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 规格:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="spec" value="${sku.spec}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 厂商指导价:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="marketPrice" value="${sku.marketPrice}"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 药房零售价:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="price" value="${sku.price}"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 运费:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="deliveFee" value="${sku.deliveFee}"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 库存:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="stock" value="${sku.stock}"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 购买限制:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="upperLimit" value="${sku.upperLimit}"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 进货价:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="superPrice" value="${sku.superPrice}"
							maxlength="10" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 包邮起始价:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="mailFree" value="${sku.mailFree}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否赠品:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							name="isFree" value="1"
							<c:if test="${sku.isFree}">checked="checked"</c:if> />是 <input
							type="radio" name="isFree" value="0"
							<c:if test="${!sku.isFree}">checked="checked"</c:if> />否</td>
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