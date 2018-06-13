<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>product-list</title>
<script type="text/javascript">
//已审
function isShow() {
	//请至少选择一个
	var size = $("input[name='ids']:checked").size();
	if (size == 0) {
		alert("请至少选择一个");
		return;
	}
	//你确定吗
	if (!confirm("你确定解禁吗")) {
		return;
	}
	//提交 Form表单
	$("#jvForm").attr("action", "<%=basePath%>/links/show");
	$("#jvForm").attr("method", "post");
	$("#jvForm").submit();

}
//下架
function unShow() {
	//请至少选择一个
	var size = $("input[name='ids']:checked").size();
	if (size == 0) {
		alert("请至少选择一个");
		return;
	}
	//你确定吗
	if (!confirm("你确定禁用吗")) {
		return;
	}
	//提交 Form表单
	$("#jvForm").attr("action", "<%=basePath%>/links/unShow");
	$("#jvForm").attr("method", "post");
	$("#jvForm").submit();

}
	function optDelete() {
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
		$("#jvForm").attr("action","<%=basePath%>/links/deletes");
		$("#jvForm").attr("method", "post").submit();
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 友情链接管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="window.location.href='<%=basePath%>/links/init'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/links/list.do" method="post"
			style="padding-top: 5px;">
			标题: <input type="text" name="keyword" value="${keyword}" /> <input type="submit" class="query" value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" width="100%" border="0"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="Pn.checkbox('ids',this.checked)" /></th>
						<th>ID</th>
						<th>标题</th>
						<th>链接</th>
						<th>状态</th>
						<th>排序值</th>
						<th width="12%">操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="ar">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="ids"
								value="${ar.id}" /></td>
							<td align="center">${ar.id}</td>
							<td align="center">${ar.linkName}</td>
							<td align="center">${ar.linkUrl}</td>
							<td align="center">
							<c:choose>
							<c:when test="${ar.linkStatus==0}">正常</c:when>
							<c:otherwise>不显示</c:otherwise>
							</c:choose>
							</td>
							<td align="center">${ar.listorder}</td>
							
							
							
							<td align="center"><a href="<%=basePath%>/links/init?id=${ar.id}" class="pn-opt">修改</a>
								| <a href="<%=basePath%>/links/deletes?ids=${ar.id}"
								onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page pb15">
				<span class="r inb_a page_b"><span style="font-weight: bold; font-size: 15px;">数据总数
						${pagination.totalCount} 条,单页最大显示 ${pagination.pageSize} 条. </span>
					&nbsp&nbsp&nbsp  <c:forEach
						items="${pagination.pageView}" var="page">
				${page}
				</c:forEach>
				</span>
			</div>
			<div style="margin-top: 15px;">
				<input class="del-button" type="button" value="删除"
					onclick="optDelete();" />&nbsp<input class="add" type="button"
					value="解禁" onclick="isShow();" /> &nbsp<input class="del-button"
					type="button" value="禁止" onclick="unShow();" />
			</div>
		</form>
	</div>
</body>
</html>