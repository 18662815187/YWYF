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
<title>ywyf-edit</title>
<style type="text/css">
#allUrl img {
	　　width: 160px;
	　　height: 120px;
	　　-webkit-transition: -webkit-transform 1s ease 0s;
	　　transition: transform 1s ease 0s;
	　　-webkit-transform-origin: center;
	　　transform-origin: center;
}

#allUrl img:hover {
	　　-webkit-transform: scale(1.2);
	　　transform: scale(1.2);
}
</style>
<style type="text/css">
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
		//uploadFck.do
		//alert(1);
		var options = {
			url : "<%=basePath%>/uploadFck.do",
			dataType : "json",
			type : "post",
			success : function(data) {
				//alert(data.url);
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
			url : "<%=basePath%>/uploadXX",
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
	//批量上传图片
	function uploadPicAu() {

		//上传图片 异步的  	Jquery.form.js
		var options = {
			url : "<%=basePath%>/uploadXX2",
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
							+ '<input type="hidden" name="authimg" value="' + data[i] + '"/>'
				}
				html += '<a href="javascript:;" class="pn-opt" onclick="jQuery(this).parents(\'tr\').remove()">删除</a>'
						+ '</td>' + '</tr>';
				//回显
				$("#tab_4").append(html);

			}
		}
		$("#jvForm").ajaxSubmit(options);
	}

	function remove() {
		$("#tt").remove();
	}
	function submitPd() {
		$.ajax({
			type : 'post',
			url : '/Pvc/saveOrUpdate',
			data : $('#jvForm').serialize(),
			cache : false,
			dataType : 'json',
			success : function(data) {
				//alert(data);
				if (data.status == 1) {
					//alert("修改成功，请重新登录！");
					window.location.href = "/Pvc/list.do";
				} else {
					var html = "<span style='color:red'>" + data.msg
							+ "</span>";

					$("#error").html(html);
				}
			},
			error : function() {
				// view("异常！");    
				alert("网络异常，请联系管理员");
				var html = "<span style='color:red'>"
						+ "网络异常，请联系管理员!客服电话：0571-83731253" + "</span>";

				$("#error").html(html);
			}
		});
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
 var b = "";
	function addSpec() {
		$("input[name='lable']").each(function() {
			b = $(this).val();
		});
		if (b != null && b != "") {
			var html = "&nbsp&nbsp<input type='text' id='speadd' class='required' name='lable' maxlength='30' size='10' />&nbsp&nbsp";
			$("#spesadd").append(html);
		} else {
			alert("请先填完前一空之后再点击！");
		}
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 医生管理 - 添加/编辑</div>
		<form class="ropt">
			<input type="submit" onclick="javascript:history.back(-1);"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<h2 class="h2_ch">
		<span id="tabs"> <!-- class的样式中here是选中后未蓝色底，未被选中的是nor白底 --> <a
			href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
			<a href="javascript:void(0);" ref="#tab_4" title="商品图片" class="nor">医生认证图片</a>
			<!--  
			<a href="javascript:void(0);" ref="#tab_2" title="商品图片" class="nor">药店图集</a>-->
			<a href="javascript:void(0);" ref="#tab_3" title="商品描述" class="nor">医生介绍</a>
		</span>
	</h2>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="<%=basePath%>/doctor/saveOrUpdate"
			method="post" enctype="multipart/form-data">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<input type="hidden" name="id" value="${doctor.id}" />
					<input type="hidden" name="editType" value="${editType}" />
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 医生姓名:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" maxlength="100" size="100"
							value="${doctor.name}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">
							上传头像(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrl" src="${doctor.pic}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="pic" id="imgUrl"
							value="${doctor.pic}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="pic2" onchange="uploadPic1()" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>登录密码:</td>
						<td width="80%" class="pn-fcontent"><input type="password"
							class="required" name="pwd" value="${doctor.pwd}" maxlength="100"
							size="20" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>联系电话(用于登录):</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="tel" maxlength="100" size="20"
							value="${doctor.tel}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>申请时预留医院名字:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="manager" maxlength="100" size="20"
							value="${doctor.authhosname}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">所属范围医院:</td>
						<td width="80%" class="pn-fcontent"><select name="hosId">
								<option value="">请选择医院</option>
								<c:forEach items="${hospitals}" var="hos">
									<option value="${hos.id}"
										<c:if test="${doctor.hosId == hos.id }">selected="selected"</c:if>>${hos.hosName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">上级医生:</td>
						<td width="80%" class="pn-fcontent"><select name="hosId">
								<option value="">请选择上级(不选择默认为没有上级)</option>
								<c:forEach items="${doctors}" var="doc">
									<option value="${doc.id}"
										<c:if test="${doctor.parentId == doc.id }">selected="selected"</c:if>>${doc.name}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 个人地址（私人地址不同于注册地址）:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="address" maxlength="100" size="50"
							value="${doctor.address}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 注册时预留医院名称（用于审核）:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="hosName" maxlength="100" size="50"
							value="${doctor.authhosname}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 性别:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="sex" value="0" checked="checked"
							<c:if test="${doctor.sex==0}">checked="checked"</c:if> />女&nbsp&nbsp<input
							type="radio" class="required" name="sex" value="1"
							<c:if test="${doctor.sex==1}">checked="checked"</c:if> />男</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 年龄:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="age" maxlength="100" size="20"
							value="${doctor.age}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 职称:</td>
						<td width="80%" class="pn-fcontent"><select name="title">
								<option value="0" selected="selected" <c:if test="${doctor.title == 0 }">selected="selected"</c:if>>主治医师</option>
								<option value="1" <c:if test="${doctor.title == 1 }">selected="selected"</c:if>>副主任医师</option>
								<option value="2" <c:if test="${doctor.title == 2 }">selected="selected"</c:if>>主任医师</option>
						</select></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 申请时预留职称（用于审核）:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="authdegree" maxlength="100" size="20"
							value="${doctor.authdegree}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 其他称号:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="degree" maxlength="100" size="20"
							value="${doctor.degree}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">余额:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="money" maxlength="100" size="10"
							value="${doctor.money}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">标签:</td>
						<td width="80%" class="pn-fcontent"><input
							type="text" id="speadd" class="required" name="lable"
							maxlength="30" size="10" style="float: left;" value="${doctor.lable}"/>
							<div id="spesadd" style="float: left;"></div> <input class="add"
							type="button" value="新增" onclick="addSpec();" /><span style="color:red">一个类型一个标签，点新增可以添加多个标签,修改时对第一格内容进行修改</span></td>
						
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否通过审核:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isShow" value="1" checked="checked"
							<c:if test="${doctor.isShow}">checked="checked"</c:if> />通过&nbsp&nbsp<input
							type="radio" class="required" name="isShow" value="0"
							<c:if test="${!doctor.isShow}">checked="checked"</c:if> />未通过</td>
					</tr>

				</tbody>
				<tbody id="tab_4" style="display: none">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">
							上传医生认证图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><input type="file"
							onchange="uploadPicAu()" name="file1" multiple="multiple" /></td>
					</tr>
					<tr id="tt">
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><c:forEach
								items="${doctor.imges}" var="pic">
								<img width="100" height="100" src="${pic}" />
							</c:forEach> <c:if test="${!empty doctor.imges}">
								<a href="javascript:;" class="pn-opt" onclick="remove()">删除</a>
							</c:if></td>
					</tr>
				</tbody>
				<tbody id="tab_2" style="display: none">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h">
							上传医院图集(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><input type="file"
							onchange="uploadPic()" name="file" multiple="multiple" /></td>
					</tr>
					<tr id="tt">
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><c:forEach
								items="${pharmacy.pics}" var="pic">
								<img width="100" height="100" src="${pic}" />
							</c:forEach> <c:if test="${!empty pharmacy.pics}">
								<a href="javascript:;" class="pn-opt" onclick="remove()">删除</a>
							</c:if></td>
					</tr>
				</tbody>
				<tbody id="tab_3" style="display: none">
					<tr>
						<td><textarea rows="20" cols="180" id="productdesc"
								name="description">${doctor.description}</textarea></td>
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
							class="submit" value="提交" /> <!--<input type="button" class="submit" onclick="submitPd()" value="提交" />-->
							&nbsp; <input type="reset" class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>