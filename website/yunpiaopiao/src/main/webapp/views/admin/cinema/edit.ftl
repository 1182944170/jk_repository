<title><#if cinema??>编辑<#else>新增</#if>影院</title>
<div class="page-header">
	<h1>
		<#if cinema??>编辑<#else>新增</#if>影院
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/cinema/dosave${suffix}" enctype="multipart/form-data">
<#if cinema??>
	<input type="hidden" name="id" value="${cinema.id}"/>
	<input type="hidden" name="icon" value="${cinema.icon}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">影院名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(cinema.name)!''}" class="form-control" placeholder="影院名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="address">影院地址:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="address" id="address" value="${(cinema.address)!''}" class="form-control" placeholder="影院地址"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">支持的服务:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.formMultiSelect options=allServices checkValues=(supportServices)! name="supportServices" attributes="class='chosen-select width-60' data-placeholder='请选择...'" listKey="k" listValue="v"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="contact">联系电话:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="contact" id="contact" value="${(cinema.contact)!''}" class="form-control" placeholder="联系电话"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">特色:</label>
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="clearfix">
		<@fck value="${(cinema.feature)!''}" instanceName="feature" inputName="feature" height="300px;" toolbarSet="Basic">
	    	${fck_body}
	    </@fck>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mark">评分:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="mark" id="mark" value="${(cinema.mark)!'0'}" class="form-control" placeholder="mark分"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">影院图片:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="iconFile" id="id-input-file-2">
			</div>
			<small>* 如已经存在的影院图片,如果不修改则不需要填写</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">内容:</label>
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="clearfix">
		<@fck value="${(cinema.content)!''}" instanceName="content" inputName="content" height="300px;" toolbarSet="Basic">
	    	${fck_body}
	    </@fck>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">是否推荐:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonOptions checkValue=(cinema.isRecommend)!1 name="isRecommend" isWrap=true/>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-md-offset-3 col-md-9">
		<button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>提  交</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重  置</button>
	</div>
</div>
</fieldset>
</form>
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
	RP.addBreadcrumb([{name:"影院"}, {name:"<#if cinema??>编辑<#else>新增</#if>影院",  active: true}]);
	$(".chosen-select").chosen();
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
			name: {
				required: true
			},
			address: {
				required: true
			},
			contact: {
				required: true
			},
			feature: {
				required: true
			},
			mark: {
				required: true,
				number:true
			},
			content: {
				required: true
			},
			isRecommend: {
				required: true
			}
		},
	
		messages: {
			
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
		
		submitHandler: function(form) {RP.Form.submitHandler(form);}
	});
});
</script>