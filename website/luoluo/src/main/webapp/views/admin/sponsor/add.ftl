<title>新增活动管理</title>
<script type="text/javascript" src="${ctx}/resources/js/tool.js"></script>
<script type="text/javascript">
	function checkSub(){
		if($("#userSex").val()==1){
			var names = "name,主办方;usernowlive,居住地址;username,领队名字;userphone,领队手机号码";
			return checkCommit(names);
		}else{
			var names = "name,主办方;usernowlive,居住地址;username,领队名字;userphone,领队手机号码;companyname,公司姓名;responname,负责人姓名;usertelephone,负责人手机号;";
			return checkCommit(names);
		}
	}
</script>
<script>
function selectMap(){
		$("#mapdiv").css("display","");
}
</script>
<div class="page-header">
	<h1>
		新增活动管理
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form action="${ctx}/admin/spons/addsavese${suffix}" class="form-horizontal" role="form" id="validation-form" method="POST" onsubmit="return commitSubmit()"  enctype="multipart/form-data">
<#if user??>
	<input type="hidden" name="id" value=""/>
</#if>
<fieldset>

<div class="form-group">
<#if infoMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer green"></i>
		<span class="pink">${infoMsg}</span>
	</h4>
</#if>
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="cardFrontPage">真实头像上传</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			 <span>
	              <div class="w_jdwl">
	                  <div id="cardf">
	                       <img id="previewb" width="166" height="127" src="${ctx}/resources/images/zhengmian.jpg" style="display: block; width: 240px; height: 180px;">
	         			<span>
	             	 	<input type="file" name="cardFrontPage" id="cardFrontPage" onchange="setImagePreview('cardFrontPage','previewb','cardf');">
	                </span>
	            </div>	
	          </div>
	        </span>
		</div>
	</div>
</div>

<div class="form-group">
<br>
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">类型</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			<select name="type" id="userSex"><option value="1">个人</option><option value="2" selected = "selected">公司</option></select>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">主办方名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" maxlength="32" class="form-control" />
				<b class="classerror" id="showss"></b>
				<!--<i class="icon-user"></i>-->
			</span>
			 <b id="cue" style="color:red ;align:center"> </b>  
		</div>
	</div>
</div>

<div class="form-group"style="position:relative;">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">居住地点:</label>
	<div class="col-xs-12 col-sm-9" >
				<#include "../baidumap.ftl">
				<input type="hidden" name="lng" id="lng" value="">
				<input type="hidden" name="lat" id="lat" value="">
				<input type="text" id="lnglat" name="usernowlive" onclick="selectMap();"/>
				<!--<i class="icon-user"></i>-->
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="username">领队姓名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="username" id="username"  maxlength="32" class="form-control" />
				<b class="classerror" id="showss"></b>
				<!--<i class="icon-user"></i>-->
			</span>
			 <b id="cue" style="color:red ;align:center"> </b>  
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">领队手机号:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="userphone" id="userphone" onchange="checkTel(this.value,'showss')" maxlength="32" class="form-control" />
				<b class="classerror" id="showss"></b>
				<!--<i class="icon-user"></i>-->
			</span>
			 <b id="cue" style="color:red ;align:center"> </b>  
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

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name"></label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
	</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name"></label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<b Style="color:red">提示：当选择公司时 必须填写下列内容</b>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="companyname">公司姓名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="companyname" id="companyname" value=""  maxlength="32" class="form-control" />
				<b class="classerror" id="showsss"></b>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="responname">负责人姓名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="responname" id="responname" value=""  maxlength="32" class="form-control" />
				<b class="classerror" id="showsss"></b>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">负责人手机号:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="usertelephone" id="usertelephone" value="" onchange="checkTel(this.value,'showsss')" maxlength="32" class="form-control" />
				<b class="classerror" id="showsss"></b>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">公司电话</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="telephone" id="telephone" value="" maxlength="32" class="form-control" />
			</span>
			<i>温馨提示：匹配形式如 0511-4405222 或 021-87888822</i>
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
			<span class="block input-icon width-40">
				<i class="icon-user">请显示公司注册图片</i>
			</span>
		</div>
		
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">公司简介:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<@fck value="" instanceName="entintroduction" inputName="entintroduction" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-md-offset-3 col-md-9">
		<button class="btn btn-info" type="submit"onclick="return checkSub(this.form)"><i class="icon-ok bigger-110"></i>提  交</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重  置</button>
	</div>
</div>
</fieldset>
</form>


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
			cardFrontPage: {
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
			cardFrontPage: {
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

</script>
