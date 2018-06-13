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
<title>brand-list</title>
<script type="text/javascript">
	//全选,此js的意思是，找到name值为传进来的name的input标签，设置选中属性true/flase，这个选中属性在
	//下方点击事件中使用this.checked来获取true、flase
	function checkBox(name, checked) {
		$("input[name=" + name + "]").attr("checked", checked);
		//$("input[name=" + name +"]").prop("checked",checked);
	}
	//批量删除,需要配合form标签使用，在需要提交的部分用form包裹，设置id=“jvForm”,在js控制此id的form提交
	//快捷键Ctrl+K直接跳转到该方法
	//传入搜索条件和分页页码,这样做是为了删除之后显示的依然是通过条件搜索过的页面
	function optDelete(Brand_name, isShow,fac_name, pageNo) {
		//请至少选择一个,只取被选中的，使用checked区分,判断长度是否为0，如为0提示至少选一个
		var size = $("input[name=ids]:checked").size();
		if (size == 0) {
			alert("请至少选择一个");
			return;
		}
		//你确定删除吗？
		if (!confirm("你确定删除吗?")) {
			return;
		}
		$("#jvForm").attr(
				"action",
				"<%=basePath%>/brand/deletes.php?Brand_name=" + Brand_name +"&fac_name"+fac_name+ "&isShow=" + isShow
						+ "&pageNo=" + pageNo);
		$("#jvForm").attr("method", "post").submit();
	}
	//已审
	function isShow() {
		//请至少选择一个
		var size = $("input[name='ids']:checked").size();
		if (size == 0) {
			alert("请至少选择一个");
			return;
		}
		//你确定吗
		if (!confirm("你确定上架吗")) {
			return;
		}
		//提交 Form表单
		$("#jvForm").attr("action", "<%=basePath%>/brand/upByIds");
		$("#jvForm").attr("method", "post");
		$("#jvForm").submit();

	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 品牌管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="javascript:window.location.href='<%=basePath%>/brand/toAdd.do'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/brand/list.do" method="post" style="padding-top: 5px;">
			品牌名称: <input type="text" name="Brand_name" value="${Brand_name}" />
			&nbsp&nbsp 厂家名称: <input type="text" name="fac_name"
				value="${fac_name}" /> <select name="isShow">
				<option value="1" <c:if test="${isShow}">selected="selected"</c:if>>是</option>
				<option value="0" <c:if test="${!isShow}">selected="selected"</c:if>>否</option>
			</select> <input type="submit" class="query" value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="checkBox('ids',this.checked)" /></th>
						<th>品牌ID</th>
						<th>品牌名称</th>
						<th>品牌图片</th>
						<th>所属厂家</th>
						<th>是否通过审核</th>
						<th>创建时间</th>
						<th>操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="brand">
						<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'"
							onmouseover="this.bgColor='#eeeeee'">
							<td align="center"><input type="checkbox"
								value="${brand.id}" name="ids" /></td>
							<td align="center">${brand.id}</td>
							<td align="center">${brand.brandName}</td>
							<td align="center"><img width="40" height="40"
								src="${brand.pic}" onerror="javascript:this.src='<%=request.getContextPath()%>/img/error.png'" /></td>
							<td align="center">${brand.facName}</td>
							<td align="center"><c:if test="${brand.isShow}">是</c:if> <c:if
									test="${!brand.isShow}">否</c:if></td>
							<td align="center"><date:date value="${brand.addtime} " /></td>
							<td align="center"><a class="pn-opt"
								href="<%=basePath%>/brand/toEdit.do?id=${brand.id}&Brand_name=${Brand_name}&isShow=${isShow}&pageNo=${pagination.pageNo}&fac_name=${fac_name}">修改</a>
								| <a class="pn-opt"
								onclick="if(!confirm('您确定删除吗？')) {return false;}"
								href="<%=basePath%>/brand/delete.do?id=${brand.id}&Brand_name=${Brand_name}&isShow=${isShow}&pageNo=${pagination.pageNo}&fac_name=${fac_name}">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="page pb15">
			<span class="r inb_a page_b"><span style="font-weight: bold; font-size: 15px;">数据总数
						${pagination.totalCount} 条,单页最大显示 ${pagination.pageSize} 条. </span>
					&nbsp&nbsp&nbsp   <c:forEach
					items="${pagination.pageView}" var="page">
					${page}
			</c:forEach>
			</span>
		</div>
		<div style="margin-top: 15px;">
			<input class="del-button" type="button" value="删除"
				onclick="optDelete('${Brand_name}','${fac_name}','${isShow}','${pagination.pageNo}');" />
				<input class="add" type="button"
					value="通过" onclick="isShow();" />
		</div>
		
			
		
	</div>
</body>
</html>