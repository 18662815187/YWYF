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
				"/mailfee/deletes.do?optionName=" + optionName + "&isshow="
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
				"<%=basePath%>/mailfee/init?id=" + id);

		$("#jvForm").attr("method", "post").submit();
	}
	function toAdd() {
		$("#roptF").attr(
				"action",
				"<%=basePath%>/mailfee/init");
		$("#roptF").attr("method", "post").submit();
	}
	function Delete(id){
		if (!confirm("你确定删除吗?")) {
			return;
		}
		$("#jvForm").attr(
				"action",
				"<%=basePath%>/mailfee/deletes?ids=" + id);
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
		$("#jvForm").attr("action","<%=basePath%>/mailfee/deletes");
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
		<form action="<%=basePath%>/mailfee/list.do" method="post"
			style="padding-top: 5px;">
			分类名称: <input type="text" name="keyword" value="${keyword}" /> <input
				type="hidden" value="${flag}" /> <input type="submit" class="query"
				value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>ID</th>
			<th>商家</th>
			<th>江浙沪运费</th>
			<th>非江浙沪运费</th>
			<th>满多少包邮</th>
			<th>运费描述</th>
			<th>添加时间</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${pagination.list}" var="mailfee">
		<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${mailfee.id}"/></td>
			<td align="center">${mailfee.id}</td>
			<td align="center">${mailfee.phId}</td>
			<td align="center">${mailfee.money}</td>
			<td align="center">${mailfee.money1}</td>
			<td align="center">${mailfee.topUp}</td>
			<td align="center">${mailfee.title}</td>
			<td align="center">${mailfee.addtime}</td>
			<td align="center">
			<a href="<%=basePath%>/mailfee/init?id=${mailfee.id}" class="pn-opt">查看</a>
							 | <a
								class="pn-opt" href="#"
								onclick="Delete('${mailfee.id}');return false;">删除</a> 
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