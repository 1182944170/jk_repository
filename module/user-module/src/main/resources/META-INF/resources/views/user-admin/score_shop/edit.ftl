<title><#if scoreShop??>编辑<#else>新增</#if>积分商品</title>
<div class="page-header">
	<h1>
		<#if scoreShop??>编辑<#else>新增</#if>积分商品
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/score_shop/dosave" enctype="multipart/form-data">
<#if scoreShop??>
	<input type="hidden" name="id" value="${scoreShop.id}"/>
	<input type="hidden" name="img" value="${scoreShop.img}"/>
	<input type="hidden" name="salesNumber" value="${scoreShop.salesNumber}"/>
	<input type="hidden" name="recordCreateTime" value="${scoreShop.recordCreateTime}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">积分商品名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(scoreShop.name)!''}" class="form-control" placeholder="积分商品名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">积分商品ICON:</label>
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
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">描述:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(scoreShop.remark)!''}" instanceName="remark" inputName="remark" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">商品类型:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign scoreShopTypes=dicSetting.getParameterMap("scoreShop.type") />
		<@ace.formSingleSelect options=scoreShopTypes checkValue=(scoreShop.type)!"-1" name="type" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">商品规则:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign scoreShopRules=dicSetting.getParameterMap("scoreShop.rule") />
		<@ace.formSingleSelect options=scoreShopRules checkValue=(scoreShop.rule)!"-1" name="rule" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="score">积分:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="score" id="score" value="${(scoreShop.score)!'0'}" class="form-control" placeholder="积分"/>
				<i class="icon-user"></i>
			</span>
			
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="stockNumber">积分商品库存:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="stockNumber" id="stockNumber" value="${(scoreShop.stockNumber)!'-1'}" class="form-control" placeholder="积分商品库存"/>
				<i class="icon-user"></i>
			</span>
			
			<small>* -1 没有限制</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="startTimeString">上线时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="input-group  width-40">
				<input class="form-control date-picker" id="startTimeString" name="startTimeString" value="${tagUtils.formatDate((scoreShop.startTime)!0, 'yyyy-MM-dd')}" type="text" data-date-format="yyyy-mm-dd" />
				<span class="input-group-addon">
					<i class="icon-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
</div> 
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="endTimeString">下线时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="input-group  width-40">
				<input class="form-control date-picker" id="endTimeString" name="endTimeString" value="${tagUtils.formatDate((scoreShop.endTime)!0, 'yyyy-MM-dd')}" type="text" data-date-format="yyyy-mm-dd" />
				<span class="input-group-addon">
					<i class="icon-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(scoreShop.state)!-1 name="state" isWrap=true/>
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
	RP.addBreadcrumb([ {name:"<#if scoreShop??>编辑<#else>新增</#if>积分商品",  active: true}]);
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
			score:{
				required: true
			},
			type:{
				required: true
			},
			stockNumber: {
				required: true,
				number:true
			},
			rule: {
				required: true
			},
			state:{
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