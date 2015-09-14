<title>申请列表</title>
<table>
	<tr><th><b>分类查询 : </b></th>
		<th width="70px;"><a href="${ctx}/admin/appln/list${suffix}" >所有</a></th>
		<th width="70px;"><a href="${ctx}/admin/appln/list${suffix}?type=2" >导游</a></th>
		<th width="70px;"><a href="${ctx}/admin/appln/list${suffix}?type=3" >司机</a></th>
		<th width="70px;"><a href="${ctx}/admin/appln/list${suffix}?type=9" >商家</a></th>
	</tr>
</table><p>
<table><p>
	<tr>
		<th width="70px;">审核状态</th>
		<th width="70px;"><a href="${ctx}/admin/appln/list${suffix}?state=1" >已同意</a></th>
		<th width="70px;"><a href="${ctx}/admin/appln/list${suffix}?state=2" >已拒绝</a></th>
		<th width="70px;"><a href="${ctx}/admin/appln/list${suffix}?state=9" >待审核</a></th>
	</tr>
</table><p></p>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/appln/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<input type="hidden" name="type" value="${type!}"/>
	<label>姓名:</label>
	<input type="text" name="realName" value="${realName!''}" placeholder="姓名"/>

	<label>身份证号码:</label>
	<input type="text" name="cardNo" value="${cardNo!''}" placeholder="身份证号码"/>
	
	<label>店铺名:</label>
	<input type="text" name="shopName" value="${shopName!''}" placeholder="店铺名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5 dotted"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>申请的用户</th>
						<th>申请类型</th>
						<th>商铺名</th>
						<th>状态</th>
						<th>申请时间</th>
						<th>查看详情</th>
					</tr>
				</thead>
				<tbody>
				<#list applnPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="green center">${u.user.userName!}</span></td>
						<td>${dicSetting.getParameterValue("user.type."+u.type)!}</td>
						<td><span class="green center">${u.shopName!}</span></td>
						<td>
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">已通过</span>
							<#elseif u.state == 2>
								<span class="label label-sm label-warning arrowed">已拒绝</span>
							<#else>
								<span class="label label-sm label-warning arrowed">等待审核</span>
							</#if>
						</td>
						<td>${tagUtils.formatDate(u.applnTime)!}</td>
						<td> <a href="${ctx}/admin/appln/detail-${u.id}${suffix}"> 查看 </a> </td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=applnPager action="${ctx}/admin/appln/list${suffix}" />
	</div><!-- /span -->
</div><!-- /row -->

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}</span>
	</h4>
</#if>

<#if infoMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer green"></i>
		<span class="pink">${infoMsg}</span>
	</h4>
</#if>
<script>
	$(document).ready(function() {
		RP.addBreadcrumb([{name:"申请列表"}]);
	});
</script>

