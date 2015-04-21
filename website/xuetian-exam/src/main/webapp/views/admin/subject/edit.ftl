<title><#if subject??>编辑<#else>新增</#if>试题</title>
<div class="page-header">
	<h1>
		<#if subject??>编辑<#else>新增</#if>试题
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
		<a href="#modal-table" role="button" class="green" data-toggle="modal"> 编辑该试题选项</a>
	</h1>
</div>

<div id="modal-table" class="modal fade" tabindex="-1" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">×</span>
					</button>
					编辑该试题选项
				</div>
			</div>

			<div class="modal-body no-padding">
				<table id="subjectOptionTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
					<thead>
						<tr>
							<th>选项名称</th>
							<th>选项内容</th>
							<th>是否正确答案(1-是，0-否)</th>
						</tr>
					</thead>
					<tbody>
					<#if subject??>
						<#list subject.options as option>
						<tr>
							<td>${option.optionName}</td>
							<td>${option.optionContent}</td>
							<td>${option.isRightAnswer}</td>
						</tr>
						</#list> 
					<#else>
						<#list 0..3 as i>
						<tr>
							<td><#if i==0>A<#elseif i==1>B<#elseif i==2>C<#elseif i==3>D</#if></td>
							<td>点击编辑选项内容</td>
							<td>0</td>
						</tr>
						</#list> 
					</#if>
					</tbody>
				</table>
			</div>

			<div class="modal-footer no-margin-top">
				<button class="btn btn-sm btn-danger pull-right" data-dismiss="modal">
					<i class="icon-ok"></i>
					编辑完成
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>
								
								
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/subject/dosave">
<input type="hidden" name="optionArray"/>
<input type="hidden" name="examClassify.id"/>
<#if subject??>
	<input type="hidden" name="id" value="${subject.id}"/>
</#if>

<fieldset>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="vedioPath">所属试卷名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon width-80">
			<select class="width-10" name="coursesSelect" id="coursesSelect"></select>
			<select class="width-10" name="speecialtySelect" id="speecialtySelect"></select>
			<select class="width-10" name="chapterSelect" id="chapterSelect"></select>
			<select class="width-20" name="subchapterSelect" id="subchapterSelect"></select>
		</span>
		</div>
	</div>
</div>
<div class="hr hr-18 hr-double dotted"></div>

<div class="form-group">
<div class="form-group width-50 pull-left">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">试题名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon">
			<input type="text" name="title" id="title" value="${(subject.title)!''}" class="form-control" placeholder="试题名字"/>
			<i class="icon-user"></i>
		</span>
		</div>
	</div>
</div>
<div class="form-group width-50 pull-left">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="score">试题得分:</label>
	<div class="col-xs-12 col-sm-9 ace-spinner" >
		<div class="input-group  width-20">
			<input type="text" name="score" id="score" value="${(subject.score)!'1'}" class="input-mini spinner-input form-control" id="spinner1" maxlength="3">
			<div class="spinner-buttons input-group-btn btn-group-vertical">
				<button type="button" class="btn spinner-up btn-xs btn-info"><i class="icon-chevron-up"></i></button>
				<button type="button" class="btn spinner-down btn-xs btn-info"><i class="icon-chevron-down"></i></button>
			</div>
		</div>
	</div>
</div>
</div>

<div class="form-group">
<div class="form-group width-50 pull-left">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">试题类型:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign subjectTypeOptions = [{"value": 1, "valueString":"单选题"}, {"value": 2, "valueString":"简写题"}]/>
		<@ace.formSingleSelect options=subjectTypeOptions checkValue=(subject.subjectType)!1 name="subjectType" listKey="value" listValue="valueString"/>
		<small>* 简写题不需要填写选项内容</small>
		</div>
	</div>
</div>
<div class="form-group width-50 pull-left">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(subject.state)!-1 name="state" isWrap=false/>
		</div>
	</div>
</div>
</div>

<div class="form-group">
<div class="form-group width-50 pull-left">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="score">知识点:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon width-40">
			<@common cmd="document_list">
				<@ace.formSingleSelect options=m_list checkValue=(subject.document.id)!-1 name="document.id" listKey="id" listValue="name"/>
			</@common>
		</span>
		</div>
	</div>
</div>
</div>

<div class="form-group">
<div class="form-group width-50 pull-left">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="commentNum">评论数:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon width-40">
			<input type="text" name="commentNum" id="commentNum" value="${(subject.commentNum)!'0'}" readonly class="form-control" placeholder="评论数"/>
			<i class="icon-user"></i>
		</span>
		</div>
	</div>
</div>
<div class="form-group width-50 pull-left">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="supportNum">点赞数:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon width-40">
			<input type="text" name="supportNum" id="supportNum" value="${(subject.supportNum)!'0'}" readonly class="form-control" placeholder="点赞数"/>
			<i class="icon-user"></i>
		</span>
		</div>
	</div>
</div>
</div>

<div class="form-group width-50">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="vedioPath">视频解析路径:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon">
			<input type="text" name="vedioPath" id="vedioPath" value="${(subject.vedioPath)!'http://'}" class="form-control" placeholder="视频解析路径:"/>
			<i class="icon-user"></i>
		</span>
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
var classify = Object.create(XT.Classify);

$(document).ready(function(){
	RP.addBreadcrumb([{name:"题库"},{name:"试题列表", linkUrl:"${ctx}/admin/subject/list"}, {name:"<#if subject??>编辑<#else>新增</#if>试题",  active: true}]);
	classify.registClassify4Select(${(subject.examClassify.id)!'-1'}, "coursesSelect","speecialtySelect","chapterSelect","subchapterSelect");
	
	$('#subjectOptionTable').editableTableWidget();
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			title: {
				required: true
			},
			score: {
				required: true,
				number:true
			},
			isSingeChoice: {
				required: true
			},
			vedioPath: {
				required: true
			},
			state: {
				required: true,
				number:true
			}
		},
	
		messages: {
			title: {
				required: "请输入试题标题."
			},
			score: {
				required: "请输入试题分数."
			},
			isSingeChoice: {
				required: "请选择是否单选."
			},
			vedioPath: {
				required: "请填写视频解析路径."
			},
			state: {
				required: "请选择试题状态."
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
		
		submitHandler: function(form) {
			if(!classify.getSelectData()) {
				alert("请选择所属试卷!");
				return;
			} else {
				form["examClassify.id"].value = classify.getSelectData();
			}
			//读取 options
			var options = [];
			$("#subjectOptionTable tbody tr").each(function(){
				var children = $(this).children();
				var option = {};
				option.optionName = $(children[0]).text();
				option.optionContent = $(children[1]).text();
				option.isRightAnswer = $(children[2]).text();
				options.push(JSON.stringify(option));
			});
			form.optionArray.value = "[" + options.toString() + "]";
			RP.Form.submitHandler(form);
		}
	});
});
</script>