<title>积分商城列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/score_shop/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<!--<label>用户ID:</label>
	<input type="text" name="userId" value="${(pager.searchMap.userId)!''}" placeholder="用户ID"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>-->
</form>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">ID</th>
						<th class="center">名称</th>
						<th class="center">图片</th>
						<th class="center">类型</th>
						<th class="center">积分</th>
						<th class="center">库存</th>
						<th class="center">已销售</th>
						<th class="center" width=50>规则</th>
						<th class="center">时间</th>
						<th class="center">创建时间</th>
						<th class="center">状态</th>
						<th class="center"></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td class="center"><span class="green center">${u.id}</span></td>
						<td class="center">${u.name}</td>
						<td class="center"><img src="${tagUtils.getFileFullPath(u.img)}" width=60 /></td>
						<td class="center">${dicSetting.getParameterValue("scoreShop.type." + u.type)}</td>
						<td class="center">${u.score}</td>
						<td class="center">
							<#if u.stockNumber == -1>
								不限制
							<#else>
								${u.stockNumber}
							</#if>
						</td>
						<td class="center">${u.salesNumber}</td>
						<td class="center">${dicSetting.getParameterValue("scoreShop.rule." + u.rule)}</td>
						<td class="center">${tagUtils.formatDate(u.startTime)} ~ ${tagUtils.formatDate(u.endTime)}</td>
						<td class="center">${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td class="center">
						<#if u.state == 0>
							<span class="label label-sm label-warning arrowed">禁用状态</span>
						<#elseif u.state == 1>
							<span class="label label-sm label-success arrowed">正常状态</span>
						<#else>
						</#if>
						</td>
						<td class="center">
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/score_shop/${u.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>编辑
							</a>
							
							<a class="green" href="${ctx}/admin/user_score_shop_log/list${suffix}?pager=1_scoreShopId--${u.id}" alt="编辑">
								<i class="icon-user bigger-130"></i>查看兑换
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/score_shop/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
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
		<@h.page pager=pager action="${ctx}/admin/score_shop/list" />
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
	return true;
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"积分商城列表", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/score_shop/add"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增积分商品</span></a>');
});
</script>

