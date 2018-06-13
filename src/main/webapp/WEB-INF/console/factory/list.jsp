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
		if (!confirm("你确定上架吗")) {
			return;
		}
		//提交 Form表单
		$("#jvForm").attr("action", "<%=basePath%>/factory/upByIds");
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
		$("#jvForm").attr("action", "unshow.do");
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
		$("#jvForm").attr("action","<%=basePath%>/factory/delByIds");
		$("#jvForm").attr("method", "post").submit();
	}
</script>
<script type="text/javascript">
$(document).ready(function(){
	var province_id=$("#province").val();
	$("#city option:not(:first)").remove();
	var CCCID=$("#CCCID").val();
	if(province_id!=""){  
        var url="<%=basePath%>/pca/city.aspx";  
        var args={"province_id":province_id};  
        $.getJSON(url,args,function(data){
                for(var i=0;i<data.length;i++){  
                    var city_id=data[i].cityid;  
                    var city_name=data[i].city;
                    if(CCCID==city_id){
                    	$("#city").append("<option value='"+city_id+"' selected='selected' >"+city_name+"</option>");            
                    }else{
                    	$("#city").append("<option value='"+city_id+"'>"+city_name+"</option>");
                    }
                      
                }                 
        });  
    }
	var city_id=$("#city").val();
	//使#county只保留第一个option子节点  
    $("#county option:not(:first)").remove();
	//alert(CCCID);
    if(CCCID!=""){  
        var url="<%=basePath%>/pca/county.do";
						var args = {"city_id" : CCCID};
						var AAAID=$("#AAAID").val();
						$.getJSON(url,args,function(data) {										
											for (var i = 0; i < data.length; i++) {
												var county_id = data[i].areaid;
												var county_name = data[i].area;
												if(AAAID==county_id){
													$("#county").append("<option value='" + county_id
															+ "' selected='selected' >"
															+ county_name
															+ "</option>");
												}else{
													$("#county").append("<option value='" + county_id
															+ "' >"
															+ county_name
															+ "</option>");
												}
												
											}
										});
					}
	
});
 $(function(){  
        //alert("jquery起作用了");  
        $("#province").change(function(){  
            //使#city只保留第一个option子节点  
            $("#city option:not(:first)").remove();  
            var province_id=$(this).val();  
            if(province_id!=""){  
                var url="<%=basePath%>/pca/city.aspx";  
                var args={"province_id":province_id};  
                $.getJSON(url,args,function(data){  
                	//alert(data.length);
                        for(var i=0;i<data.length;i++){  
                            var city_id=data[i].cityid;  
                            var city_name=data[i].city;  
                            $("#city").append("<option value='"+city_id+"' '<c:if test="+ ${provincesId==city_id}+"> selected='selected' </c:if>'>"+city_name+"</option>");  
                        }                 
                });  
            }     
        });  
        $("#city").change(function(){  
            //使#county只保留第一个option子节点  
            $("#county option:not(:first)").remove();  
            var city_id=$(this).val();  
            if(city_id!=""){  
                var url="<%=basePath%>/pca/county.do";
								var args = {
									"city_id" : city_id
								};
								$
										.getJSON(
												url,
												args,
												function(data) {
													//alert(data.length);
													for (var i = 0; i < data.length; i++) {
														var county_id = data[i].areaid;
														var county_name = data[i].area;
														$("#county")
																.append(
																		"<option value='"
																				+ county_id
																				+ "' '<c:if test="+ ${area==county_id}+"> selected='selected' </c:if>'>"
																				+ county_name
																				+ "</option>");
													}
												});
							}

						});
	});
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 厂家管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="window.location.href='<%=basePath%>/factory/toAdd?type=0'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/factory/list.do" method="post"
			style="padding-top: 5px;">
			<input type="hidden" name="PPPID" id="PPPID" value="${provincesId}"></input>
			<input type="hidden" name="CCCID" id="CCCID" value="${cityId}"></input>
			<input type="hidden" name="AAAID" id="AAAID" value="${area}"></input>
			厂家名称: <input type="text" name="fac_name" value="${fac_name}" />审核: <select id="isShow" name="isShow">
				<option value="" >请选择(默认全部)</option>
				<option value="1">通过</option>
				<option value="0">未通过</option>
				
			</select> 省：<select id="province" name="provincesId">
				<option value="">请选择...</option>
				<c:forEach items="${provinces}" var="province">
					<option value="${ province.provinceid}"
						<c:if test="${provincesId==province.provinceid}">selected="selected"</c:if>>${ province.province}</option>
				</c:forEach>
			</select> 市：<select id="city" name="cityId">
				<option value="">请选择...</option>
			</select> 区：<select id="county" name="area">
				<option value="">请选择...</option>
			</select> <input type="submit" class="query" value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" width="100%" border="0"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="Pn.checkbox('ids',this.checked)" /></th>
						<th>厂家编号</th>
						<th>厂家名称</th>
						<th>图片</th>
						<th>厂家电话</th>
						<th>厂家地址</th>
						<th>余额</th>
						<th>注册方式</th>
						<th>注册时间</th>
						<th width="4%">是否审核通过</th>
						<th width="12%">操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="fac">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="ids"
								value="${fac.id}" /></td>
							<td align="center">${fac.id}</td>
							<td align="center">${fac.name}</td>
							<td align="center"><img width="50" height="50"
								src="${fac.pic}"
								onerror="javascript:this.src='<%=request.getContextPath()%>/img/error.png'" /></td>
							<td align="center">${fac.tel}</td>
							<td align="center">${fac.address}</td>
							<td align="center">${fac.money}</td>
							<td align="center"><c:if test="${fac.regType==0}">后台录入</c:if>
							<c:if test="${fac.regType==1}">PC注册</c:if>
							<c:if test="${fac.regType==2}">微网站注册</c:if>
							</td>
							<td align="center"><date:date value="${fac.regTime} " /></td>
							<td align="center"><c:if test="${fac.isShow}">已通过</c:if> <c:if
									test="${!fac.isShow}">未通过</c:if></td>
							<td align="center"><a
								href="<%=basePath%>/factory/toEdit?id=${fac.id}" class="pn-opt">修改</a>
								| <a href="<%=basePath%>/factory/delById?id=${fac.id}"
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
					value="已审" onclick="isShow();" /><!--  &nbsp<input class="del-button"
					type="button" value="未审" onclick="unShow();" />-->
			</div>
		</form>
	</div>
</body>
</html>