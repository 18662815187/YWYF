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
<title>ywyf-sku-list</title>
<script type="text/javascript">
	//修改
	function updateSku(skuId) {
		//$("#a").find("input").attr("disabled",true);
		//$("input[name='ids']").attr("disabled",false);
		
		$("input[class='a']").attr("disabled", true);
		$(['#sp','#m','#p','#s','#l','#f','#x'].join(skuId+',')+skuId).attr("disabled", false).val();
		//市场价
		//$("#m" + skuId).attr("disabled", false);
		//售价
		//$("#p" + skuId).attr("disabled", false);
		//库存
		//$("#s" + skuId).attr("disabled", false);
		//购买限制
		//$("#l" + skuId).attr("disabled", false);
		//运费
		//$("#f" + skuId).attr("disabled", false);
	}
	//保存
	function addSku(skuId) {
		//$(['#m','#p','#s','#l','#f'].join(skuId+',')+skuId).attr("disabled", true).val();
		//设disabled同时取值
		//规格名
		var sp = $("#sp" + skuId).attr("disabled", true).val();
		//店家零售价
		var m = $("#m" + skuId).attr("disabled", true).val();
		//进货价
		var p = $("#p" + skuId).attr("disabled", true).val();
		//库存
		var s = $("#s" + skuId).attr("disabled", true).val();
		//购买限制
		var l = $("#l" + skuId).attr("disabled", true).val();
		//运费
		var f = $("#f" + skuId).attr("disabled", true).val();
		//厂商指导价
		var x = $("#x" + skuId).attr("disabled", true).val();

		var url = "<%=basePath%>/sku/upApply";
		var params = {
			"spec":sp,
			"marketPrice" : x,
			"price" : m,
			"stock" : s,
			"upperLimit" : l,
			//"deliveFee" : f,
			"superPrice":p,
			"id" : skuId
		};
		//var params1 = '{"marketPrice" : m,"price" : p,"stock" : s,"upperLimit" : l,"deliveFee" : f,"id" : skuId}';
		//异步保存
		$.post(url, params, function(data) {
			alert(data.message);
		}, "json")
	}
	//删除
	function delById(skuId,pid){
		var url="<%=basePath%>/sku/delById";
		var params={
				"skuId" : skuId
		};
		$.post(url, params, function(data) {
			alert(data.message);
			window.location.href="<%=basePath%>/sku/list?id="+pid;
		}, "json")
	}
	//批删
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
		var myArray = new Array()
		$("input[name=ids]:checked").each(function() {
			myArray.push($(this).val());
		})
		var url ="<%=basePath%>/sku/delByIds";
		var params={
				"ids" : myArray
		}
		$.post(url, params, function(data) {
			alert(data.message);
			var test = window.location.href;
			window.location.href= test;
		}, "json")
	}
	//新增
	function toAdd(pro_id){
		window.location.href="<%=basePath%>/sku/toAdd?pro_id=" + pro_id;
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 库存管理 - 列表</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form method="post" id="tableForm">
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="Pn.checkbox('ids',this.checked)" /></th>
						<th>商品编号</th>
						<th>商品规格</th>
						<th>厂家指导价</th>
						<th>店家销售价格</th>
						<th>进货价</th>
						<th>库 存</th>
						<th>购买限制</th>
						<!--  
						<th>运 费</th>-->
						<th>是否赠品</th>
						<th>操 作</th>
					</tr>
				</thead>
				<tbody id="a" class="pn-ltbody">
					<c:forEach items="${skus}" var="sku">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="ids"
								value="${sku.id}" /></td>
							<td align="center">${sku.proId}</td>
							<td align="center"><input class="a" type="text"
								id="sp${sku.id}" value="${sku.spec}" disabled="disabled" size="10" /></td>
							<td align="center"><input class="a" type="text"
								id="x${sku.id}" value="${sku.marketPrice}" disabled="disabled"
								size="10" /></td>
							<td align="center"><input class="a" type="text"
								id="m${sku.id}" value="${sku.price}" disabled="disabled"
								size="10" /></td>
							<td align="center"><input class="a" type="text"
								id="p${sku.id}" value="${sku.superPrice}" disabled="disabled"
								size="10" /></td>
							<td align="center"><input class="a" type="text"
								id="s${sku.id}" value="${sku.stock}" disabled="disabled"
								size="10" /></td>
							<td align="center"><input class="a" type="text"
								id="l${sku.id}" value="${sku.upperLimit}" disabled="disabled"
								size="10" /></td>
							<!--  
							<td align="center"><input class="a" type="text"
								id="f${sku.id}" value="${sku.deliveFee}" disabled="disabled"
								size="10" /></td>-->
							<td align="center"><c:if test="${sku.isFree}">是</c:if> <c:if
									test="${!sku.isFree}">否</c:if></td>
							<td align="center"><a
								href="javascript:updateSku('${sku.id}')" class="pn-opt">编辑</a> |
								<a href="javascript:addSku('${sku.id}')" class="pn-opt">保存</a> |
								<a href="javascript:delById('${sku.id}','${sku.proId}')"
								class="pn-opt">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<div style="margin-top: 15px;">
		<input class="del-button" type="button" value="删除"
			onclick="optDelete();" />&nbsp&nbsp&nbsp<input class="add"
			type="button" value="新增" onclick="toAdd('${pro_id}');" />
	</div>
</body>
</html>