<title><#if movie??>编辑<#else>新增</#if>电影</title>
<div class="page-header">
	<h1>
		<#if movie??>编辑<#else>新增</#if>电影
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/movie/dosave${suffix}" enctype="multipart/form-data">
<#if movie??>
	<input type="hidden" name="id" value="${movie.id}"/>
	<input type="hidden" name="icon" value="${movie.icon}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">电影名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(movie.name)!''}" class="form-control" placeholder="电影名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">类别:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign slideshowTypes=dicSetting.getParameterMap("movie.type") />
		<@ace.formSingleSelect options=slideshowTypes checkValue=(movie.type)!-1 name="type" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">所属区域:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign slideshowTypes=dicSetting.getParameterMap("movie.countryArea") />
		<@ace.formSingleSelect options=slideshowTypes checkValue=(movie.countryArea)!-1 name="countryArea" listKey="key" listValue="value"/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">语言:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign slideshowTypes=dicSetting.getParameterMap("movie.lang") />
		<@ace.formSingleSelect options=slideshowTypes checkValue=(movie.lang)!-1 name="countryArea" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">演员列表:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.formMultiSelect options=actorList checkValues=(actors)! name="actors" attributes="class='chosen-select width-60' data-placeholder='Choose Actor...'" listKey="id" listValue="name"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">上线影院列表:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.formMultiSelect options=cinemaList checkValues=(cinemas)! name="cinemas" attributes="class='chosen-select width-60' data-placeholder='Choose Ciname...'" listKey="id" listValue="name"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mark">mark分:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="mark" id="mark" value="${(movie.mark)!'0'}" class="form-control" placeholder="mark分"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="time">时长:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="time" id="time" value="${(movie.time)!'0'}" class="form-control" placeholder="时长"/>
				<i class="icon-user"></i>
			</span>
			<small>* 单位为分钟</small>
		</div>
	</div>
</div> 


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="onlineTime">上线时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="input-group  width-40">
				<input class="form-control date-picker" id="onlineString" name="onlineString" value="${tagUtils.formatDate((movie.onlineTime)!0, 'yyyy-MM-dd')}" type="text" data-date-format="yyyy-mm-dd" />
				<span class="input-group-addon">
					<i class="icon-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
</div> 
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="offString">下线时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="input-group  width-40">
				<input class="form-control date-picker" id="offString" name="offString" value="${tagUtils.formatDate((movie.offTime)!0, 'yyyy-MM-dd')}" type="text" data-date-format="yyyy-mm-dd" />
				<span class="input-group-addon">
					<i class="icon-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">ICON:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="iconFile" id="id-input-file-2">
			</div>
			<small>* 已经存在的icon如果不修改则不需要填写</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">内容:</label>
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="clearfix">
		<@fck value="${(movie.content)!''}" instanceName="content" inputName="content" height="300px;" toolbarSet="Basic">
	    	${fck_body}
	    </@fck>
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
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if movie??>编辑<#else>新增</#if>电影",  active: true}]);
	$('#id-input-file-2').ace_file_input({
		no_file:'No File ...',
		btn_choose:'Choose',
		btn_change:'Change',
		droppable:false,
		onchange:null,
		thumbnail:false
	});
	
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
				
	$(".chosen-select").chosen(); 
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			type: {
				required: true
			},
			countryArea: {
				required: true
			},
			mark: {
				required: true
			},
			onlineString: {
				required: true
			},
			offString: {
				required: true
			},
			lang: {
				required: true
			},
			time:{
				required: true
			},
			content:{
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
		
		submitHandler: function(form) {
			RP.Form.submitHandler(form);
		}
	});
});
</script>