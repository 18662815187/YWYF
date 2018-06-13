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
<title>药店入驻</title>
<script type="text/javascript">
	//上传图片
	function uploadPic(temp) {
		if(temp=="undefined"||temp==undefined)
			temp="";
		//jquery.form.js
		var options = {
			url : "<%=basePath%>/uploadPic1.do",
			dataType : "json",
			type : "post",
			success : function(data) {
				//alert(1);
				//返回的数据通过下方控件的id给他们设置值,src需要通过attr属性去设置，val可以直接设置
				//原数据存在图片时，重新上传替换URL需对此两个地方进行回显设置
				if(temp!="")
					{
					alert(data.url);
				$("#allUrl"+temp).attr("src", data.url);
				$("#imgUrl"+temp).val(data.url);
					}
				else{
					alert(1);
					$("#allUrl").attr("src", data.url);
					$("#imgUrl").val(data.url);
				}
			}
			
		}
		//ajax模拟jvForm这个form的提交
		$("#jvForm").ajaxSubmit(options);
	}

</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 药店入驻管理 - 修改</div>
		<form class="ropt">
			<input type="submit" onclick="javascript:history.back(-1);"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box" style="float: right">
		<form id="jvForm"
			action="edit.do?name1=${name1}&isDisplay1=${isDisplay1}&pageNo1=${pageNo1}"
			method="post">
			<!-- 下方三个参数为id和列表页的查询条件和当前页码 -->
			<input type="hidden" value="${brand.id}" name="id" /> <input
				type="hidden" value="${name1}" /> <input type="hidden"
				value="${isDisplay1}" /> <input type="hidden" value="${pageNo1}" />
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 姓名:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" value="${dugstore.name}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 手机号:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" value="${dugstore.phone}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 药店:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" value="${dugstore.pharmacy}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 密码:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" value="${dugstore.password}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 申请时间:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" readonly="readonly" value="${dugstore.addtime}"
							maxlength="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 营业执照:</td>
						<td width="80%" class="pn-fcontent"></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrl" src="${drugstore.businessLicense}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="businessLicense" id="imgUrl"
							value="${drugstore.businessLicense}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="pic" onchange="uploadPic()" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 经营许可证:</td>
						<td width="80%" class="pn-fcontent"></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrlbp" src="${drugstore.businesspermit}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="businesspermit" id="imgUrlbp"
							value="${drugstore.businesspermit}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="businesspermit1" onchange="uploadPic('bp')" />
						</td>
					</tr>
						<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 手持身份证:</td>
						<td width="80%" class="pn-fcontent"></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrlhp" src="${drugstore.handheldphoto}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="handheldphoto" id="imgUrlhp"
							value="${drugstore.handheldphoto}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="handheldphoto1" onchange="uploadPic('hp')" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> GSP证:</td>
						<td width="80%" class="pn-fcontent"></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrlGSP" src="${drugstore.GSP}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="GSP" id="imgUrlGSP"
							value="${drugstore.GSP}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="GSP1" onchange="uploadPic('GSP')" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 执业医生资格证书:</td>
						<td width="80%" class="pn-fcontent"></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrlCDQC" src="${drugstore.CDQC}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="CDQC" id="imgUrlCDQC"
							value="${drugstore.CDQC}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="CDQC1" onchange="uploadPic('CDQC')" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">是否通过审核:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							name="status" value="1"
							<c:if test="${brand.isDisplay}">checked="checked"</c:if> />是
							<input type="radio" name="isDisplay" value="0"
							<c:if test="${!brand.isDisplay}">checked="checked"</c:if> />否
						</td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" /> &nbsp; 
							<input type="reset" class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>