<title><#if subChapter??>编辑<#else>新增</#if>试卷</title>
<div class="page-header">
	<h1>
		<#if subChapter??>编辑<#else>新增</#if>试卷
		<small>
			<i class="icon-double-angle-right"></i>
			
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/subchapter/dosave">
<#if subChapter??>
	<input type="hidden" name="id" value="${subChapter.id}"/>
</#if>
<input type="hidden" name="parent.id" value="${chapter.id}"/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">试卷名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(subChapter.name)!''}" class="form-control" placeholder="试卷名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="thumb">简写:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="thumb" id="thumb" value="${(subChapter.thumb)!''}" class="form-control" placeholder="简写"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">所属章节:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" value="${(chapter.name)!''}" readonly class="form-control" placeholder="所属章节"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="examTime">考试时间:</label>
	<div class="col-xs-12 col-sm-9 ace-spinner" >
		<div class="input-group  width-40">
			<input type="text" name="examTime" id="examTime" value="${(subChapter.examTime)!'0'}" class="input-mini spinner-input form-control" id="spinner1" maxlength="3">
			<div class="spinner-buttons input-group-btn btn-group-vertical">
				<button type="button" class="btn spinner-up btn-xs btn-info"><i class="icon-chevron-up"></i></button>
				<button type="button" class="btn spinner-down btn-xs btn-info"><i class="icon-chevron-down"></i></button>
			</div>
			<label class="gray"> *单位为分钟</label>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="totalSubjectNum">总题目数量:</label>
	<div class="col-xs-12 col-sm-9 ace-spinner" >
		<div class="input-group  width-20">
			<input type="text"name="totalSubjectNum" id="totalSubjectNum" value="${(subChapter.totalSubjectNum)!'0'}" class="input-mini spinner-input form-control" id="spinner1" maxlength="3">
			<div class="spinner-buttons input-group-btn btn-group-vertical">
				<button type="button" class="btn spinner-up btn-xs btn-info"><i class="icon-chevron-up"></i></button>
				<button type="button" class="btn spinner-down btn-xs btn-info"><i class="icon-chevron-down"></i></button>
			</div>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="totalScore">总分数:</label>
	<div class="col-xs-12 col-sm-9 ace-spinner" >
		<div class="input-group  width-20">
			<input type="text" name="totalScore" id="totalScore" value="${(subChapter.totalScore)!'0'}" class="input-mini spinner-input form-control" id="spinner1" maxlength="3">
			<div class="spinner-buttons input-group-btn btn-group-vertical">
				<button type="button" class="btn spinner-up btn-xs btn-info"><i class="icon-chevron-up"></i></button>
				<button type="button" class="btn spinner-down btn-xs btn-info"><i class="icon-chevron-down"></i></button>
			</div>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="passScore">及格分数:</label>
	<div class="col-xs-12 col-sm-9 ace-spinner" >
		<div class="input-group  width-20">
			<input type="text" name="passScore" id="passScore" value="${(subChapter.passScore)!'0'}" class="input-mini spinner-input form-control" id="spinner1" maxlength="3">
			<div class="spinner-buttons input-group-btn btn-group-vertical">
				<button type="button" class="btn spinner-up btn-xs btn-info"><i class="icon-chevron-up"></i></button>
				<button type="button" class="btn spinner-down btn-xs btn-info"><i class="icon-chevron-down"></i></button>
			</div>
		</div>
	</div>
</div>

<div class="form-group">
<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="price">价格:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-25">
				<input type="text" name="price" id="price" value="${(subChapter.price)!'0'}" class="form-control" placeholder="价格"/>
				<label class="gray"> * 免费填 0</label>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>


<div class="form-group">
<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="discount">折扣:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-25">
				<input type="text" name="discount" id="discount" value="${(subChapter.discount)!'0'}" class="form-control" placeholder="折扣"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(subChapter.state)!-1 name="state" isWrap=true/>
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
	RP.addBreadcrumb([{name:"题库"},{name:"试卷列表", linkUrl:"${ctx}/admin/chapter/${chapter.id}/subchapters"}, {name:"<#if subChapter??>编辑<#else>新增</#if>试卷",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			thumb: {
				required: true
			},
			state: {
				required: true
			},
			examTime: {
				required: true,
				number:true
			},
			totalSubjectNum: {
				required: true,
				number:true
			},
			totalScore: {
				required: true,
				number:true
			},
			passScore: {
				required: true,
				number:true
			},
			price: {
				required: true,
				number:true
			},
			discount: {
				required: true,
				number:true
			}
		},
	
		messages: {
			name: {
				required: "请输入试卷名称."
			},
			thumb: {
				required: "请输入试卷简写."
			},
			state: {
				required: "请选择试卷状态."
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
		
		submitHandler: function(form) {RP.Form.submitHandler(form);}
	});
});
</script>