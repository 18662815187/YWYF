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
<title>type-list</title>
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
	function optDelete(id, isshow, optionName, pageNo, flag) {
		if (id !== null && flag != null && flag == 1) {
			//这两行是用于数据后方的删除按钮,需传入id,这两行必须放最前面
			var delByID = $('#' + id);
			delByID.attr("checked", true);
		}
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
				"/type/deletes.do?optionName=" + optionName + "&isshow="
						+ isshow + "&pageNo=" + pageNo);
		$("#jvForm").attr("method", "post").submit();
	}
	function optDeleteOne(id, isshow, optionName, pageNo) {
		if (!confirm("你确定删除吗?")) {
			return;
		}
		$("#jvForm").attr(
				"action",
				"/type/delete.do?id=" + id + "&isshow=" + isshow + "&pageNo="
						+ pageNo);
		$("#jvForm").attr("method", "post").submit();
	}

	function toEdit(id) {
		$("#jvForm").attr(
				"action",
				"<%=basePath%>/banner/init?id=" + id);

		$("#jvForm").attr("method", "post").submit();
	}
	function toAdd() {
		$("#roptF").attr(
				"action",
				"<%=basePath%>/banner/init");
		$("#roptF").attr("method", "post").submit();
	}
	function Delete(id){
		if (!confirm("你确定删除吗?")) {
			return;
		}
		$("#jvForm").attr(
				"action",
				"<%=basePath%>/banner/deletes?ids=" + id);
		$("#jvForm").attr("method", "post").submit();
	}
	function Deletes(){
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
				"<%=basePath%>/banner/deletes");
		$("#jvForm").attr("method", "post").submit();
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 分类管理 - 列表</div>
		<form class="ropt" id="roptF">
			<input class="add" type="button" value="添加" onclick="toAdd();" />
			<!-- onclick="javascript:window.location.href='toAdd.do?optionName=${optionName}&isshow=${isshow}&pageNo=${pageNo}'" -->
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/banner/list.do" method="post"
			style="padding-top: 5px;">
			分类名称: <input type="text" name="name" value="${name}" /> <input
				type="hidden" value="${flag}" /> <input type="submit" class="query"
				value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="checkBox('ids',this.checked)" /></th>
						<th>ID</th>
						<th>名称</th>
						<th>跳转链接</th>
						<th>类型</th>
						<th>封面</th>
						<th>具体位置</th>
						<th>是否展示</th>
						<th>添加时间</th>
						<th>操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="banner">
						<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'"
							onmouseover="this.bgColor='#eeeeee'">

							<td align="center"><input type="checkbox"
								value="${banner.id}" name="ids" id="${banner.id}" /></td>
							<td align="center">${banner.id}</td>
							<td align="center">${banner.name}</td>
							<td align="center"><a href="${banner.url}" style="color:blue" target="_blank">${banner.url}</a></td>
							<td align="center"><c:choose>
									<c:when test="${banner.type==1}">产品</c:when>
									<c:when test="${banner.type==2}">医生</c:when>
									<c:when test="${banner.type==3}">活动</c:when>
									<c:otherwise>通知</c:otherwise>
								</c:choose></td>
								<td align="center"><img width="50" height="50"
								src="${banner.pic}" onerror="javascript:this.src='<%=request.getContextPath()%>/img/error.png'" /></td>
								<td align="center">${banner.posName}</td>
								<td align="center"><c:if test="${banner.status==0}">未展示</c:if><c:if test="${banner.status==1}">展示</c:if></td>
								<td align="center"><date:date value="${banner.addtime} " /></td>
								
							<td align="center"><a class="pn-opt" href="#"
								onclick="toEdit('${banner.id}');return false;">修改</a> | <a
								class="pn-opt" href="#"
								onclick="Delete('${banner.id}');return false;">删除</a> <%-- 								<a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="delete.do?id=${data.id}&name=${optionName}&isDisplay=${isshow}&pageNo=${pagination.pageNo}">删除</a> --%>

								<!-- 							<a class="pn-opt" --> <%-- 								href="/type/toEdit.do?id=${data.id}&name=${optionName}&isshow=${isshow}&pageNo=${pagination.pageNo}">修改</a> --%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="page pb15">
			<span class="r inb_a page_b"> <c:forEach
					items="${pagination.pageView}" var="page">
					${page}
			</c:forEach>
			</span>
		</div>
		<div style="margin-top: 15px;">
			<input class="del-button" type="button" value="删除"
				onclick="Deletes();" />
		</div>
	</div>
</body>
</html>