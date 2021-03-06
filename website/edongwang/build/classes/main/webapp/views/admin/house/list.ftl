<title>楼盘列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/house/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>楼盘名字:</label>
	<input type="text" name="houseName" value="${(pager.searchMap.houseName)!''}" placeholder="楼盘名字"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>楼盘名称</th>
						<th width=50>标签</th>
						<th>物业类型</th>
						<th width=165>区域/地址</th>
						<th>面积</th>
						<th>均价</th>
						<th>推荐奖励</th>
						<th>成交奖励</th>
						<th width=50>佣金</th>
						<th>协议时间</th>
						<th>创建时间</th>
						<th>状态</th>
						<th width=70>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.name}</td>
						<td>${(u.houseTag)!""}</td>
						<td>${dicSetting.getParameterValue("house.propertyType." + u.propertyType)}</td>
						<td>${commonTag.getCountyPath(u.areaCode)}[ ${tagUtils.cutString(u.address,12)} ]</td>
						<td>${dicSetting.getParameterValue("house.surfaceType." + u.surfaceType)}</td>
						<td>${u.avgPrice}万</td>
						<td>${u.recommendPrice}元</td>
						<td>${u.bargainPrice}元</td>
						<td>${u.commissionString}</td>
						<td>${tagUtils.formatDate(u.protocolBeginTime, "yyyy-MM-dd")}~${tagUtils.formatDate(u.protocolEndTime, "yyyy-MM-dd")}</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td>
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">正常状态</span>
							<#elseif u.state==0>
								<span class="label label-sm label-success arrowed">即将上线状态</span>
							<#else>
								<span class="label label-sm label-warning arrowed">删除状态</span>
							</#if>
						</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/house/${u.id}/edit" alt="Edit">
								<i class="icon-pencil bigger-130"></i>
							</a>
							
							<a class="green" href="${ctx}/admin/house_recommend/list${suffix}?pager=1_houseId--${u.id}" alt="查看推荐列表">
								<i class="icon-pencil bigger-130"></i> 推荐
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/house/${u.id}/edit" class="tooltip-success" data-rel="tooltip" title="修改" data-original-title="Edit">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/house/list" />
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

function fromSearch(f){
	if(f.houseName.value) {
		f.pager.value += "$$houseName--" + f.houseName.value;
	}
	return true;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"楼盘管理"}, {name:"楼盘列表", linkUrl:"${ctx}/admin/house/list", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/house/add"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增楼盘</span></a>');
});
</script>

