<title><#if user??>编辑<#else>新增</#if>活动管理</title>
<script>
function selectMap(){
		$("#mapdiv").css("display","");
}
</script>
<div class="page-header">
	<h1>
		<#if user??>编辑<#else>新增</#if>活动管理
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form action="${ctx}/admin/spons/saveUserda${suffix}" class="form-horizontal" role="form" id="validation-form" method="POST" enctype="multipart/form-data">
<#if user??>
	<input type="hidden" name="id" value="${user.id!}"/>
</#if>
<fieldset>


<#assign fieldName="userpicture" />
<#assign fieldLabel="真实头像" />
<#include "/common-admin/upload/upload_field_pre.ftl" />
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name"></label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">类型</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			<#if user.type==1>
				<input type="text" readonly name="" id="name" value="私人" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
				<#elseif user.type==2>
				<input type="text" readonly name="" id="name" value="公司" maxlength="32" class="form-control" />
				</#if>
			</span>
		</div>
	</div>
</div>

<div class="form-group"style="position:relative;">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">居住地点:</label>
	<div class="col-xs-12 col-sm-9" >
				<#include "../baidumap.ftl">
				<input type="hidden"  id="lng" value="">
				<input type="hidden"  id="lat" value="">
				<input type="text" id="lnglat" value="${user.usernowlive!}" onclick="selectMap();"/>
				<!--<i class="icon-user"></i>-->
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">领队手机号:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text"  id="oriprice" value="${user.userphone!}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name"></label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-50">
				<b Style="color:red">提示：领队信息图片（第四张为导游证 ，第五张为领队证 必须上传）</b>
			</span>
		</div>
	</div>
</div>
<#assign fieldName="userinformation" />
<#assign fieldLabel="领队信息" />
<#include "/common-admin/upload/upload_field_pre.ftl" />
<#if user.type==2>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name"></label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="companyname">公司名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text"  id="companyname" value="${user.companyname!}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">负责人手机号:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text"  id="oriprice" value="${user.usertelephone!}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">公司电话</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text"  id="disprice" value="${user.telephone!}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name"></label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<b Style="color:red">提示：负责人信息图片（第3张为公司营业执照）</b>
			</span>
		</div>
	</div>
</div>
<#assign fieldName="responsibility" />
<#assign fieldLabel="负责人信息" />
<#include "/common-admin/upload/upload_field_pre.ftl" />
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name"></label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">公司简介:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<@fck value="${(user.entintroduction)!''}" instanceName="entintroduction" inputName="" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		   	 </@fck>
			</span>
		</div>
	</div>
</div>
</#if>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">未通过:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			<textarea cols="80" rows="10" name="msg"></textarea>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
	<label class="blue">
		<input  type="radio" name="typeopp" id="typeopp" value="1" class="ace"  />
		<span class="lbl"> 启用</span>
	</label>
	<label class="red">
		<input  type="radio" name="typeopp" id="typeopp" value="2" class="ace"  />
		<span class="lbl"> 未通过</span>
	</label>
	<label class="red">
		<input  type="radio" name="typeopp" id="typeopp" value="0" class="ace"  />
		<span class="lbl"> 禁用</span>
	</label>

		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-md-offset-3 col-md-9">
		<button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>	
	提交
		</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>取消</button>
	</div>
</div>

</fieldset>
</form>

<#assign fieldName="userpicture" />
<#assign rootPath="jiaju/resource/goodsImg/" />
<#assign fieldNameValue=(user.userpicture)!""  />
<#assign isSingle=1 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="userinformation" />
<#assign rootPath="jiaju/resource/goodsImg/" />
<#assign fieldNameValue=(user.userinformation)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="responsibility" />
<#assign rootPath="jiaju/resource/goodsImg/" />
<#assign fieldNameValue=(user.responsibility)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />


<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}
		</span>
	</h4>
</#if>

<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础设置"}, {name:"<#if goodsDO??>编辑<#else>新增</#if>活动管理",  active: true}]);
	$('#id-input-file-2').ace_file_input({
		no_file:'没图片 ...',
		btn_choose:'请选择图片',
		btn_change:'重新选择图片',
		droppable:false,
		onchange:null,
		thumbnail:false
	});
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			title: {
				required: true
			},
			imgAddress:{
				required: true
			},
			type: {
				required: true
			},
			state:{
				required: true,
				number:true
			}
		},
	
		messages: {
			title: {
				required: "请输入图片标题."
			},
			state: {
				required: "请选择图片状态."
			}
		},
	
		invalidHandler: function (event, validator) { //display error alert on form submit   
			RP.Form.invalidHandler(event, validator);
		},
	
		highlight: function (e) {
			RP.Form.highlight(e);
		},
		
		errorPlacement: function (error, element) {
			RP.Form.errorPlacement(error, element);	
		},
	
		success: function (e) {
			RP.Form.success(e);	
		},
		
		submitHandler: function(form){
			RP.Form.submitHandler(form);
		}
	});
});

 <!--劵价-->
 function   getSubPrice(){
	   var ori_price=$("#oriprice").val();
	   var dis_price=$("#disprice").val();
	   var sub_section=$("#subsection").val();
	   var sub_Price=(ori_price - dis_price)/sub_section
	   $("#subPrice").val(sub_Price);
   }
</script>