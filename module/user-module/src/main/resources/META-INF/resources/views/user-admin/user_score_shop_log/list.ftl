<title>用户积分商城日志列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_score_shop_log/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>用户ID:</label>
	<input type="text" name="userId" value="${(pager.searchMap.userId)!''}" placeholder="用户ID"/>
	
	<label>商品ID:</label>
	<input type="text" name="scoreShopId" value="${(pager.searchMap.scoreShopId)!''}" placeholder="商品ID"/>
	
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
						<th>userId</th>
						<th>商品ID</th>
						<th>用户信息</th>
						<th>发货状态</th>
						<th>时间</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.userId}</td>
						<td>${u.scoreShopId}</td>
						<td>name:${u.name} concact:${u.concact} 省区: ${commonTag.getCountyPath(u.areaCode)} address: ${u.address}</td>
						<td>
						<#if u.sendShopState == 0>
							<span class="label label-sm label-warning arrowed">未处理</span>
						<#elseif u.sendShopState == 1>
							<span class="label label-sm label-success arrowed">处理成功</span>
						<#else>
							<span class="label label-sm label-warning arrowed">处理失败</span>
						</#if>
						</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
					
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/user_score_shop_log/${u.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<a href="${ctx}/admin/user_score_shop_log/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
										<span class="green">
											<i class="icon-edit bigger-120"></i>
										</span>
									</a>
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
		<@h.page pager=pager action="${ctx}/admin/user_score_log/list" />
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
	if(f.userId.value) {
		f.pager.value += "$$userId--" + f.userId.value;
	}
	if(f.scoreShopId.value) {
		f.pager.value += "$$scoreShopId--" + f.scoreShopId.value;
	}
	return true;
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"用户积分日志列表", active: true}]);
});
</script>
