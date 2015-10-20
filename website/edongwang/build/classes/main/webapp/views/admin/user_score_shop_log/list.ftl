<title>积分商城日志列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_score_shop_log/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	
	<label>会员手机号:</label>
	<input type="text" name="contact" value="${(pager.searchMap.contact)!''}" placeholder="会员手机号"/>
	
	<label>会员姓名:</label>
	<input type="text" name="realName" value="${(pager.searchMap.realName)!''}" placeholder="会员姓名"/>
	
	<label>商品ID:</label>
	<input type="text" name="scoreShopId" value="${(pager.searchMap.scoreShopId)!''}" placeholder="商品ID"/>
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>
<#if scoreShop??>
	<#if scoreShop.state == 1 && scoreShop.type==2>
	<a href="${ctx}/admin/score_shop/${scoreShop.id}/dolottery"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">为该抽奖商品抽奖</span></a>
	<div class="hr hr-5"></div>
	</#if>
</#if>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>会员ID/会员姓名/会员手机号</th>
						<th>商品ID/名称</th>
						<th>发货状态</th>
						<th>兑换时间</th>
						<th>处理时间</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.userId}/${u.user.realName}/${u.user.contact}</td>
						<td>${u.scoreShopId}/${tagUtils.cutString(u.scoreShop.name, 10)}</td>
						<td>
						<#if u.sendShopState == 0>
							<span class="label label-sm label-warning arrowed">未处理</span>
						<#elseif u.sendShopState == 1>
							<#if u.scoreShop.type == 2>
								<span class="label label-sm label-success arrowed">已经中奖</span>
							<#else>
								<span class="label label-sm label-success arrowed">兑换成功</span>
							</#if>
						<#elseif u.sendShopState == 2>
							<span class="label label-sm label-success arrowed">以中奖待处理</span>
						<#else>
							<#if u.scoreShop.type == 2>
								<span class="label label-sm label-warning arrowed">未中奖</span>
							<#else>
								<span class="label label-sm label-warning arrowed">兑换失败</span>
							</#if>
						</#if>
						</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td>
						<#if u.sendShopState == 0>
							未处理
						<#else>
							${tagUtils.formatDate(u.recordModifyTime)}
						</#if></td>
						
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/user_score_shop_log/${u.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>
							</a>
							
							<a class="green" href="${ctx}/admin/user/${u.userId}/detail${suffix}" alt="查看会员信息">
								<i class="icon-pencil bigger-130"></i> 查看会员信息
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
		<@h.page pager=pager action="${ctx}/admin/user_score_shop_log/list" />
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
	if(f.contact.value) {
		f.pager.value += "$$contact--" + f.contact.value;
	}
	
	if(f.realName.value) {
		f.pager.value += "$$realName--" + f.realName.value;
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

