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
<title>ywyf-add</title>
<style type="">
.h2_ch a:hover, .h2_ch a.here {
	color: #FFF;
	font-weight: bold;
	background-position: 0px -32px;
}

.h2_ch a {
	float: left;
	height: 32px;
	margin-right: -1px;
	padding: 0px 16px;
	line-height: 32px;
	font-size: 14px;
	font-weight: normal;
	border: 1px solid #C5C5C5;
	background: url('<%=basePath%>/images/admin/bg_ch.gif') repeat-x scroll
		0% 0% transparent;
}

a {
	color: #06C;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(function() {
		var tObj;
		//取id=tabs的a标签结果集
		$("#tabs a").each(function() {
			if ($(this).attr("class").indexOf("here") == 0) {
				tObj = $(this)
			}
			$(this).click(function() {
				var c = $(this).attr("class");
				if (c.indexOf("here") == 0) {
					return;
				}
				var ref = $(this).attr("ref");
				var ref_t = tObj.attr("ref");
				tObj.attr("class", "nor");
				$(this).attr("class", "here");
				$(ref_t).hide();
				$(ref).show();
				tObj = $(this);
				if (ref == '#tab_3') {
					// 编辑器参数
					var kingEditorParams = {
						//指定上传文件参数名称
						filePostName : "file",
						//指定上传文件请求的url。
						uploadJson : '<%=basePath%>/uploadKE',
						//上传类型，分别为image、flash、media、file
						dir : "image",
						width : '1000px',
						height : '400px',
						//失去焦点自动同步设置到textarea
						afterBlur : function() {
							this.sync();
						}
					};
					KindEditor.create('#productdesc', kingEditorParams);
					KindEditor.sync();
				}
			});
		});
	});
	//上传图片
	function uploadPic1() {
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
	//批量上传图片
	function uploadPic() {
		//上传图片 异步的  	Jquery.form.js
		var options = {
			url : "<%=basePath%>/uploadPics.do",
			type : "post",
			dataType : "json",
			//datas就是后台传来的urls
			success : function(data) {
				//alert(data);
				//alert(data[0]);
				//多图片回显,增加tr行和td列,原本此行是不存在的，在回显时给加上去，然后在第二个列（td）中加上两个input标签，第一个是遍历回显图片
				//第二个input设置成hidden用于提交数据
				var html = '<tr>'
						+ '<td width="20%" class="pn-flabel pn-flabel-h"></td>'
						+ '<td width="80%" class="pn-fcontent">';
				for (var i = 0; i < data.length; i++) {
					html += '<img width="100" height="100" src="' + data[i] + '" />'
							+ '<input type="hidden" name="imgs" value="' + data[i] + '"/>'
				}
				html += '<a href="javascript:;" class="pn-opt" onclick="jQuery(this).parents(\'tr\').remove()">删除</a>'
						+ '</td>' + '</tr>';
				//回显
				$("#tab_2").append(html);

			}
		}
		$("#jvForm").ajaxSubmit(options);
	}
	 var b = "";
		function addSpec() {
			$("input[name='specs']").each(function() {
				b = $(this).val();
			});
			if (b != null && b != "") {
				var html = "&nbsp&nbsp<input type='text' id='speadd' class='required' name='specs' maxlength='30' size='10' />&nbsp&nbsp";
				$("#spesadd").append(html);
			} else {
				alert("请先填完前一空之后再点击！");
			}
		}
	//重置
	function returnUrl() {
		var test = window.location.href;
		window.location.href = test;
	}
	$(function() {
		//初始化加载，获取是修改还是新增
		var type='${type}';
		if(type==1){
			$("#hhsp").hide();
		}
	});
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#funId option:not(:first)").remove();
	var TTTID=$("#TTTID").val();
	var FFFID=$("#FFFID").val();
	var DDDID=$("#DDDID").val();
	if(TTTID!=""){  
        var url="<%=basePath%>/funType/queryByType";  
        var args={"typeId":TTTID};  
        $.getJSON(url,args,function(data){
                for(var i=0;i<data.length;i++){  
                    var id=data[i].id;
                    var name=data[i].name;
                    if(FFFID==id){
                    	$("#funId").append("<option value='"+id+"' selected='selected' >"+name+"</option>");            
                    }else{
                    	$("#funId").append("<option value='"+id+"'>"+name+"</option>");
                    }
                      
                }                 
        });  
    }
	//使#county只保留第一个option子节点  
    $("#diseaseId option:not(:first)").remove();
	//alert(CCCID);
    if(FFFID!=""){  
        var url="<%=basePath%>/DisType/queryByFid";
						var args = {"fid" : FFFID};
						$.getJSON(url,args,function(data) {	
											for (var i = 0; i < data.length; i++) {
												var id = data[i].id;
												var name = data[i].name;
												if(DDDID==id){
													$("#diseaseId").append("<option value='" + id
															+ "' selected='selected' >"
															+ name
															+ "</option>");
												}else{
													$("#diseaseId").append("<option value='" + id
															+ "' >"
															+ name
															+ "</option>");
												}
												
											}
										});
					}
	
});
 $(function(){  
       // alert("jquery起作用了");  
        $("#proTypeId").change(function(){  
        	//alert(1);
            //使#city只保留第一个option子节点  
            $("#funId option:not(:first)").remove();  
            var typeId=$(this).val();  
            if(typeId!=""){  
                var url="<%=basePath%>/funType/queryByType";  
                var args={"typeId":typeId};  
                $.getJSON(url,args,function(data){  
                	//alert(data.length);
                        for(var i=0;i<data.length;i++){  
                            var id=data[i].id;  
                            var name=data[i].name;  
                            $("#funId").append("<option value='"+id+"' '<c:if test="+ ${typeId==id}+"> selected='selected' </c:if>'>"+name+"</option>");  
                        }                 
                });  
            }     
        });  
        $("#funId").change(function(){  
            //使#county只保留第一个option子节点  
            $("#diseaseId option:not(:first)").remove();  
            var funId=$(this).val();  
            if(funId!=""){  
                var url="<%=basePath%>/DisType/queryByFid";
								var args = {
									"fid" : funId
								};
								$
										.getJSON(
												url,
												args,
												function(data) {
													//alert(data.length);
													for (var i = 0; i < data.length; i++) {
														var id = data[i].id;
														var name = data[i].name;
														$("#diseaseId")
																.append(
																		"<option value='"
																				+ id
																				+ "' '<c:if test="+ ${area==id}+"> selected='selected' </c:if>'>"
																				+ name
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
		<div class="rpos">当前位置: 商品管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="javascript:history.back(-1);"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<h2 class="h2_ch">
		<span id="tabs"> <!-- class的样式中here是选中后为蓝色底，未被选中的是nor白底 --> <a
			href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
			<a href="javascript:void(0);" ref="#tab_2" title="商品图片" class="nor">商品图片</a>
			<a href="javascript:void(0);" ref="#tab_3" title="商品描述" class="nor">商品描述</a>
			<a href="javascript:void(0);" ref="#tab_4" title="包装清单" class="nor">包装清单</a>
		</span>
	</h2>
	<input type="hidden" id="TTTID" name="TTTID" value="${pro.proTypeid}"></input>
	<input type="hidden" id="FFFID" name="FFFID" value="${pro.funId}"></input>
	<input type="hidden" id="DDDID" name="DDDID" value="${pro.diseaseId}"></input>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="<%=basePath%>/product/saveOrUpdate"
			method="post" enctype="multipart/form-data">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<input type="hidden" name="id" value="${pro.id}"></input>
					<input type="hidden" name="isShow" value="0"></input>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 商品名称:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" maxlength="100" size="100"
							value="${pro.name}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">商品品牌:</td>
						<td width="80%" class="pn-fcontent"><select name="brandId">
								<option value="">请选择品牌</option>
								<c:forEach items="${brands}" var="brand">
									<option value="${brand.id}"
										<c:if test="${pro.brandId == brand.id }">selected="selected"</c:if>>${brand.brandName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">生产厂家:</td>
						<td width="80%" class="pn-fcontent"><select name="facId">
								<option value="">请选择生产厂家</option>
								<c:forEach items="${factories}" var="fac">
									<option value="${fac.id}"
										<c:if test="${pro.facId == fac.id }">selected="selected"</c:if>>${fac.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">所属药店:</td>
						<td width="80%" class="pn-fcontent"><select name="phId">
								<option value="0">请选择所属药店（不选默认自营）</option>
								<c:forEach items="${pharmacies}" var="ph">
									<option value="${ph.id}"
										<c:if test="${pro.phId == ph.id }">selected="selected"</c:if>>${ph.pharmacyName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">产品类型:</td>
						<td width="80%" class="pn-fcontent"><select id="proTypeId" name="proTypeid">
								<option value="">请选择所属类型</option>
								<c:forEach items="${types}" var="type">
									<option value="${type.id}"
										<c:if test="${pro.proTypeid == type.id }">selected="selected"</c:if>>${type.typeName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">按系统分类:</td>
						<td width="80%" class="pn-fcontent"><select id="funId" name="funId">
								<option value="">请选择</option>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">按疾病分类:</td>
						<td width="80%" class="pn-fcontent"><select id="diseaseId" name="diseaseId">
								<option value="">请选择</option>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrl" src="${pro.pic}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="pic" id="imgUrl" value="${pro.pic}" />
							<!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic --> <input
							type="file" name="pic2" onchange="uploadPic1()" />
						</td>
					</tr>
					<tr id="hhsp">
						<td width="20%" class="pn-flabel pn-flabel-h">产品规格:</td>
						<td width="80%" class="pn-fcontent" id="spe"><input
							type="text" id="speadd" class="required" name="specs"
							maxlength="30" size="10" style="float: left;"
							value="${pro.specs}" />
							<div id="spesadd" style="float: left;"></div> <input class="add"
							type="button" value="新增" onclick="addSpec();" /></td>

					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 批准文号:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="batchNum" maxlength="30" size="30"
							value="${pro.batchNum}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否为处方药:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isCounter" value="0" checked="checked"
							<c:if test="${!pro.isCounter}">checked="checked"</c:if> />非处方&nbsp&nbsp<input
							type="radio" class="required" name="isCounter" value="1"
							<c:if test="${pro.isCounter}">checked="checked"</c:if> />处方</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*没有可不填</span> 剂型:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="dosage" maxlength="20" size="20"
							value="${pro.dosage}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否包邮:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="whetherFee" value="1" checked="checked"
							<c:if test="${pro.whetherFee}">checked="checked"</c:if> />包邮&nbsp&nbsp<input
							type="radio" class="required" name="whetherFee" value="0"
							<c:if test="${!pro.whetherFee}">checked="checked"</c:if> />不包邮</td>
					</tr>
					<!-- 
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否通过审核:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isShow" value="0" checked="checked"
							<c:if test="${!pro.isShow}">checked="checked"</c:if> />否&nbsp&nbsp<input
							type="radio" class="required" name="isShow" value="1"
							<c:if test="${pro.isShow}">checked="checked"</c:if> />是</td>
					</tr>  -->
					
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否为活动产品:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isActivity" value="0" checked="checked"
							<c:if test="${!pro.isActivity}">checked="checked"</c:if> />否&nbsp&nbsp<input
							type="radio" class="required" name="isActivity" value="1"
							<c:if test="${pro.isActivity}">checked="checked"</c:if> />是</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否新品:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isNew" value="0" checked="checked"
							<c:if test="${!pro.isNew}">checked="checked"</c:if> />否&nbsp&nbsp<input
							type="radio" class="required" name="isNew" value="1"
							<c:if test="${pro.isNew}">checked="checked"</c:if> />是</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否热卖:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isHot" value="0" checked="checked"
							<c:if test="${!pro.isHot}">checked="checked"</c:if> />否&nbsp&nbsp<input
							type="radio" class="required" name="isHot" value="1"
							<c:if test="${pro.isHot}">checked="checked"</c:if> />是</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否推荐:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isCommend" value="0" checked="checked"
							<c:if test="${!pro.isCommend}">checked="checked"</c:if> />否&nbsp&nbsp<input
							type="radio" class="required" name="isCommend" value="1"
							<c:if test="${pro.isCommend}">checked="checked"</c:if> />是</td>
					</tr>
				</tbody>
				<tbody id="tab_2" style="display: none">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><input type="file"
							onchange="uploadPic()" name="pics" multiple="multiple" /></td>
					</tr>
					<tr id="tt">
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><c:forEach
								items="${pro.pics}" var="pic">
								<img width="100" height="100" src="${pic}" />
							</c:forEach> <c:if test="${!empty pro.pics}">
								<a href="javascript:;" class="pn-opt" onclick="remove()">删除</a>
							</c:if></td>
					</tr>
				</tbody>
				<tbody id="tab_3" style="display: none">
					<tr>
						<td><textarea rows="20" cols="180" id="productdesc"
								name="description">${pro.description}</textarea></td>
					</tr>
				</tbody>
				<tbody id="tab_4" style="display: none">
					<tr>
						<td><textarea rows="20" cols="180" id="productList"
								name="packageList">${pro.packageList}</textarea></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2">
							<div id="error">
								<span style="color: red">${msg}</span>
							</div>
						</td>
					</tr>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit"
							class="submit" value="提交" /> &nbsp; <input type="button"
							class="reset" onclick="returnUrl()" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>