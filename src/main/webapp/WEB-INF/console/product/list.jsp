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
	//上架
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
		$("#jvForm").attr("action", "<%=basePath%>/product/isShowByIds");
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
		$("#jvForm").attr("action", "<%=basePath%>/product/unShowByIds");
		$("#jvForm").attr("method", "post");
		$("#jvForm").submit();

	}
	function optDelete(isShow,fac_name,pageNo,provincesId,cityId,area) {
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
		$("#jvForm").attr("action","<%=basePath%>/product/delByIds");
		$("#jvForm").attr("method", "post").submit();
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 商品管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="window.location.href='<%=basePath%>/product/toAdd.do?type=0'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/product/list.do" method="post"
			style="padding-top: 5px;">
			产品名称: <input type="text" name="pro_name" value="${pro_name}" />&nbsp&nbsp
			药店名称: <input type="text" name="ph_name" value="${ph_name}" />
			<!--  
			<select name="brandId">
				<option value="">请选择品牌</option>
				<c:forEach items="${brands}" var="brand">
					<option value="${brand.id}"
						<c:if test="${brandId == brand.id}">selected="selected"</c:if>>${brand.name}
					</option>
				</c:forEach>
			</select> -->
			<select name="isShow">
				<option value="" selected="selected">请选择(默认全部)</option>
				<option value="1" >通过</option>
				<option value="0" >未通过</option>
			</select> <input type="submit" class="query" value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" width="100%" border="0"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="Pn.checkbox('ids',this.checked)" /></th>
						<th>商品编号</th>
						<th>商品名称</th>
						<th>图片</th>
						<th>规格</th>
						<th>批准文号</th>
						<th>是否处方</th>
						<th>剂型</th>
						<th>品牌</th>
						<th>厂家</th>
						<th>发布店家</th>
						<th width="4%">新品</th>
						<th width="4%">热卖</th>
						<th width="4%">推荐</th>
						<th width="4%">是否审核通过</th>
						<th width="4%">创建时间</th>
						<th width="12%">操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="product">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="ids"
								value="${product.proId}" /></td>
							<td align="center">${product.proId}</td>
							<td align="center">${product.proName}</td>
							<td align="center"><img width="50" height="50"
								src="${product.pic}"
								onerror="javascript:this.src='<%=request.getContextPath()%>/img/error.png'" /></td>
							<td align="center">${product.specs}</td>
							<td align="center">${product.batchNum}</td>
							<td align="center">
							<c:if test="${product.isCounter}">处方药</c:if>
							<c:if test="${!product.isCounter}">非处方药</c:if>
							</td>
							<td align="center">${product.dosage}</td>
							<td align="center">${product.brandName}</td>
							<td align="center">${product.facName}</td>
							<td align="center">${product.phName}</td>
							<td align="center"><c:if test="${product.isNew}">是</c:if> <c:if
									test="${!product.isNew}">否</c:if></td>
							<td align="center"><c:if test="${product.isHot}">是</c:if> <c:if
									test="${!product.isHot}">否</c:if></td>
							<td align="center"><c:if test="${product.isCommend}">是</c:if>
								<c:if test="${!product.isCommend}">否"</c:if></td>
							<td align="center"><c:if test="${product.isShow}">上架</c:if>
								<c:if test="${!product.isShow}">下架</c:if></td>
							<td align="center"><date:date value="${product.createTime} " /></td>
							<td align="center"><a href="<%=basePath%>/product/toEdit?id=${product.proId}&type=1" class="pn-opt">修改</a> | <a href="<%=basePath%>/product/delByIds?ids=${product.proId}"
								onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
								| <a href="<%=basePath%>/sku/list?id=${product.proId}" class="pn-opt">库存</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
					onclick="optDelete();" /><input class="add" type="button"
					value="上架" onclick="isShow();" /><input class="del-button"
					type="button" value="下架" onclick="unShow();" />
			</div>
		</form>
	</div>
</body>
</html>