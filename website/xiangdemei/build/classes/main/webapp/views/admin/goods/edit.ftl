<#assign goodsTitle="" />
<#if type == 3>
	<#assign goodsTitle="积分商品" />
<#elseif type == 2>
	<#assign goodsTitle="定制商品" />
<#elseif type == 1>
	<#assign goodsTitle="普通商品" />
<#elseif type == 4>
	<#assign goodsTitle="项目" />
</#if>

<title><#if goods??>编辑<#else>新增</#if> ${goodsTitle}</title>
<div class="page-header">
	<h1>
		<#if goods??>编辑<#else>新增</#if>${goodsTitle}
		<small class="red">
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/goods/dosave" enctype="multipart/form-data">
<#if goods??>
	<input type="hidden" name="id" value="${goods.id}"/>
</#if>
<input type="hidden" name="type" value="${type}"/>
<input type="hidden" name="ext" id="ext" value='${(goods.ext)!}'/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(goods.name)!''}" class="form-control" placeholder="名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<#assign fieldName="thumbImg" />
<#assign fieldLabel="封面图" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<#assign fieldName="imgs" />
<#assign fieldLabel="图片" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="attrs">属性:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="attrs" id="attrs" value="${(goods.attrs)!''}" class="form-control" placeholder="属性"/>
				<i class="icon-user"></i>
			</span>
			<small class="red">多个以,分割</small>
		</div>
	</div>
</div>

<#assign fieldName="attrsDescImgs" />
<#assign fieldLabel="属性描述图片" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="contacts">联系电话:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="contacts" id="contacts" value="${(goods.contacts)!''}" class="form-control" placeholder="联系电话"/>
				<i class="icon-user"></i>
			</span>
			<small class="red">多个以,分割</small>
		</div>
	</div>
</div>

<#assign fieldName="descImgs" />
<#assign fieldLabel=goodsTitle + "描述图片" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<#if type == 3>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="score">所需积分:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-20">
					<input type="text" name="score" id="score" value="${(goods.score)!'0'}" class="form-control" placeholder="所需积分"/>
					<i class="icon-user"></i>
				</span>
				
			</div>
		</div>
	</div>
<#elseif type == 4> <!-- 项目商品有服务时长 跟体验案例-->
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="serviceTime">服务时长:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				 <span class="block input-icon width-20">
					<input type="text" name="serviceTime" id="serviceTime" value="${(gsonUtils.getInt(goods.extJson, "serviceTime"))!'0'}" class="form-control" placeholder="服务时长"/>
					<i class="icon-user"></i>
				</span>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="serviceProcess">服务流程:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				 <span class="block input-icon width-60">
					<input type="text" name="serviceProcess" id="serviceProcess" value="${(gsonUtils.getString(goods.extJson, "serviceProcess"))!''}" class="form-control" placeholder="服务流程"/>
					<i class="icon-user"></i>
				</span>
				
				<small class="red">多个以,分割</small>
			</div>
		</div>
	</div>
	
	<#assign fieldName="projectExperienceImgs" />
	<#assign fieldLabel="体验案例" />
	<#include "/common-admin/upload/upload_field_pre.ftl" />
</#if>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="money">原始金额/折后价:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="input-icon width-20">
				<input type="text" name="money" id="money" value="${(goods.money)!'0'}" class="form-control" placeholder="金额"/>
				<i class="icon-user"></i>
			</span>
			
			<span class="input-icon width-20">
				<input type="text" name="discountMoney" id="discountMoney" value="${(goods.discountMoney)!'0'}" class="form-control" placeholder="折后价"/>
				<i class="icon-user"></i>
			</span>
			<small class="red">单位(元)</small>
		</div>
	</div>
</div>
<#if type != 4> <!--美容院的项目没有分红的概念 -->
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="oneLevelMoney">一级/二级/原始:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="input-icon width-20">
					<input type="text" name="oneLevelMoney" id="oneLevelMoney" value="${(goods.oneLevelMoney)!'100'}" class="form-control" placeholder=""/>
					<i class="icon-user"></i>
				</span>
				<span class="input-icon width-20">
					<input type="text" name="twoLevelMoney" id="twoLevelMoney" value="${(goods.twoLevelMoney)!'50'}" class="form-control" placeholder=""/>
					<i class="icon-user"></i>
				</span>
				<span class="input-icon width-20">
					<input type="text" name="rootLevelMoney" id="rootLevelMoney" value="${(goods.rootLevelMoney)!'100'}" class="form-control" placeholder=""/>
					<i class="icon-user"></i>
				</span>
				
				<small class="red">单位(元)</small>
			</div>
		</div>
	</div>
<#else>
	<input type="hidden" name="oneLevelMoney" value="0"/>
	<input type="hidden" name="twoLevelMoney" value="0"/>
	<input type="hidden" name="rootLevelMoney" value="0"/>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">选择美容院:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
			<@xdm cmd="beauty_shop_list">
				<@ace.formMultiSelectWithoutDefault options=m_list checkValues=(mBeautyShopList)! name="supportBeautyShopIds" attributes="class='chosen-select width-60' data-placeholder='请选择美容院...'" listKey="id" listValue="name"/>
			</@xdm>
			</div>
		</div>
	</div>
</#if>


<#if type != 2> <!-- 定制离子水不参与首页的排序 -->
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sortIndex">排序:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="input-icon width-20">
				<input type="text" name="sortIndex" id="sortIndex" value="${(goods.sortIndex)!'0'}" class="form-control" placeholder="排序"/>
				<i class="icon-user"></i>
			</span>
			
			<small class="red">* 0表示不参与排序，值越小越靠前.</small>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sortIndex">商家名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="input-icon width-40">
				<input type="text" name="shopName" id="shopName" value="${(goods.shopName)!''}" class="form-control" placeholder="商家名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div> 
<#else>
	<input type="hidden" name="shopName" value=""/>
	<input type="hidden" name="sortIndex" value="0"/>
</#if>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">可见度选择:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@xdm cmd="factory_list_without_default">
			<@ace.formSingleSelect options=m_list checkValue=(goods.factoryId)!-1 name="factoryId" listKey="id" listValue="factoryName" attributes="" defaultChooiceTip="--所有可见--"/>
		</@xdm>
		
		<span class="input-icon width-60">
			<input type="text" name="groupIds" id="groupIds" value="${(goods.groupIds)!''}" class="form-control" placeholder="圈子ID集"/>
			<i class="icon-user"></i>
		</span>
		<small class="red">* 如需填写请用,号分割</small>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="startTimeString">上线时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="input-group  width-40">
				<input class="form-control" id="startTimeString" name="startTimeString" onfocus="WdatePicker({skin:'blue',dateFmt:'yyyy-MM-dd HH:mm'})" value="${tagUtils.formatDate((goods.startTime)!0, 'yyyy-MM-dd HH:mm')}" type="text"/>
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
				<input class="form-control" id="endTimeString" name="endTimeString" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm'})" value="${tagUtils.formatDate((goods.endTime)!0, 'yyyy-MM-dd HH:mm')}" type="text" />
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
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(goods.state)!-1 name="state" isWrap=true/>
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

<#assign fieldName="thumbImg" />
<#assign rootPath="resource/goodsThumbImg/" />
<#assign fieldNameValue=(goods.thumbImg)!"" />
<#assign isSingle=1 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="imgs" />
<#assign rootPath="resource/goodsImg/" />
<#assign fieldNameValue=(goods.imgs)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="attrsDescImgs" />
<#assign rootPath="resource/goodsAttrDescImg/" />
<#assign fieldNameValue=(goods.attrsDescImgs)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="descImgs" />
<#assign rootPath="resource/goodsDescImg/" />
<#assign fieldNameValue=(goods.descImgs)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#if type == 4> <!-- 项目商品有服务时长 跟体验案例-->
	<#assign fieldName="projectExperienceImgs" />
	<#assign rootPath="resource/projectExperienceImgs/" />
	<#if goods??>
		<#assign fieldNameValue=gsonUtils.getJsonValue((goods.extJson)!, "projectExperienceImgs") />
	<#else>
		<#assign fieldNameValue="" />
	</#if>
	
	<#assign isSingle=0 />
	<#include "/common-admin/upload/upload_field_after.ftl" />
</#if>
<script>

$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if goods??>编辑<#else>新增</#if>${goodsTitle}",  active: true}]);
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			salesNumber: {
				required: true,
				number:true
			},
			
			money: {
				required: true,
				number:true
			},
			
			discountMoney: {
				required: true,
				number:true
			},
			oneLevelMoney: {
				required: true,
				number:true
			},
			twoLevelMoney: {
				required: true,
				number:true
			},
			rootLevelMonry: {
				required: true,
				number:true
			},
			sortIndex: {
				required: true,
				number:true
			},
			startTimeString:{
				required: true
			},
			endTimeString:{
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
