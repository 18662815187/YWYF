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
		if (!confirm("你确定审核通过吗")) {
			return;
		}
		//提交 Form表单
		$("#jvForm").attr("action", "<%=basePath%>/article/show");
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
		if (!confirm("你确定下架吗")) {
			return;
		}
		//提交 Form表单
		$("#jvForm").attr("action", "<%=basePath%>/article/unShow");
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
		$("#jvForm").attr("action","<%=basePath%>/article/deletes");
		$("#jvForm").attr("method", "post").submit();
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 文章管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="window.location.href='<%=basePath%>/article/init'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/article/list.do" method="post"
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
						<th>封面图</th>
						<th>录入时间</th>
						<th>是否显示</th>
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
							<td align="center">${ar.title}</td>
							<td align="center"><img width="50" height="50" src="${ar.pic}" onerror="javascript:this.src='<%=request.getContextPath()%>/img/error.png'" /></td>
							<td align="center"><date:date value="${ar.addtime} " /></td>
							<td align="center">
							<c:choose>
							<c:when test="${ar.isShow==1}">显示</c:when>
							<c:otherwise>不显示</c:otherwise>
							</c:choose>
							</td>
							<td align="center"><a href="<%=basePath%>/article/init?id=${ar.id}" class="pn-opt">修改</a>
								| <a href="<%=basePath%>/article/deletes?ids=${ar.id}"
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
					value="允许显示" onclick="isShow();" /> &nbsp<input class="del-button"
					type="button" value="禁止显示" onclick="unShow();" />
			</div>
		</form>
	</div>
</body>
</html>