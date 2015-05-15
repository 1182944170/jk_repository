<title>楼盘推荐列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/house_recommend/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>推荐用户ID:</label>
	<input type="text" name="recommendUserId" value="${(pager.searchMap.recommendUserId)!''}" placeholder="推荐用户ID"/>
	
	<label>接单用户ID:</label>
	<input type="text" name="acceptSalesmanId" value="${(pager.searchMap.acceptSalesmanId)!''}" placeholder="接单用户"/>
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>客户信息</th>
						<th>用户需求</th>
						<th>楼盘</th>
						<th>推荐者信息</th>
						<th>业务员信息</th>
						<th>进度</th>
						<th>创建时间</th>
						<th>状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>
						 name:${u.customerName} <br/>
						 contact:${u.contact}
						</td>
						<td>
						 类型:${dicSetting.getParameterValue("house.propertyType." + u.propertyType)} <br/>
						 面积:${dicSetting.getParameterValue("house.surfaceType." + u.surfaceType)} <br/>
						 总价:${dicSetting.getParameterValue("house.totalPriceType." + u.totalPriceType)} <br/>
						 区域:${commonTag.getCountyPath(u.areaCode)} <br/>
						 信息:${u.customerInfo}
						</td>
						<td>${u.house.name}</td>
						<td>userId:${u.recommendUser.id}<br/>T:${u.recommendUser.contact} <br/>姓名: ${u.recommendUser.realName}</td>
						
						<td><#if u.state==1>
							该推荐未被接单
						<#else>
							userId:${u.acceptSalesman.id}<br/>T:${u.acceptSalesman.contact} <br/>姓名: ${u.acceptSalesman.realName}
						</#if></td>
						<td>
						<#if u.state==1>
							无
						<#else>
							<#if u.progresses?has_content>
								<#list u.progresses as progress>
									<#if progress.type==1>
										有效性:<#if progress.state==1><span class="label label-sm label-success arrowed">有效</span><#else><span class="label label-sm label-warning arrowed">无效</span></#if>,
										星级:${gsonUtils.getInt(progress.extJson, "infoStar")},意向:${gsonUtils.getInt(progress.extJson, "intentStar")}, 备注:${gsonUtils.getString(progress.extJson, "remark")}
									<#elseif progress.type==2>
										回访:<#if progress.state==1><span class="label label-sm label-success arrowed">有效</span><#else><span class="label label-sm label-warning arrowed">无效</span></#if>,
										备注:${gsonUtils.getString(progress.extJson, "remark")}
									<#elseif progress.type==3>
										成交:<#if progress.state==1><span class="label label-sm label-success arrowed">有效</span><#else><span class="label label-sm label-warning arrowed">无效</span></#if>,
										成交时间:${tagUtils.formatDate(gsonUtils.getLong(progress.extJson, "dealTime") )},
										面积:${gsonUtils.getDouble(progress.extJson, "surface")},
										价格:${gsonUtils.getDouble(progress.extJson, "price")},
										佣金价格:${gsonUtils.getDouble(progress.extJson, "commissionPrice")}
									<#elseif progress.type==4>
										完结:<#if progress.state==1><span class="label label-sm label-success arrowed">有效</span><#else><span class="label label-sm label-warning arrowed">无效</span></#if>
									<#else>
									</#if>
										处理时间:${tagUtils.formatDate(progress.recordCreateTime)}
									<br/>
								</#list>
							<#else>
								已被接单，但无进度
							</#if>
						</#if></td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td><#if u.state==1>
							<span class="label label-sm label-warning arrowed">该推荐未被接单</span>
						<#elseif u.state==2>
							<span class="label label-sm label-warning arrowed">处理中</span>
						<#else>
							<span class="label label-sm label-success arrowed">完结状态</span>
						</#if></td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<#if u.state==2 && u.progresses?has_content && u.progresses?size == 3>
								<a class="green" href="${ctx}/admin/house_recommend/${u.id}/over?state=1" alt="Edit">
									<i class="icon-pencil bigger-130"></i> 完结
								</a>
							</#if>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
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
		<@h.page pager=pager action="${ctx}/admin/house_recommend/list" />
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
	if(f.recommendUserId.value) {
		f.pager.value += "$$recommendUserId--" + f.recommendUserId.value;
	}
	if(f.acceptSalesmanId.value) {
		f.pager.value += "$$acceptSalesmanId--" + f.acceptSalesmanId.value;
	}
	return true;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"楼盘推荐管理"}, {name:"楼盘推荐列表", linkUrl:"${ctx}/admin/house/list", active: true}]);
});
</script>

