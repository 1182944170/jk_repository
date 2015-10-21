<title>用户列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user/list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>会员手机号:</label>
	<input type="text" name="contact" value="${(pager.searchMap.contact)!''}" placeholder="会员手机号"/>
	
	<label>会员ID:</label>
	<input type="text" name="userId" value="${(pager.searchMap.userId)!''}" placeholder="会员ID"/>
	
	<label>是否原始ID:</label>
	<@ace.formSingleSelect options=ace.commonStateOptionsYN checkValue=(pager.searchMap.isRoot?number)!-1 name="isRoot" listKey="value" listValue="valueString"/>
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>会员ID</th>
						<th width="100">手机号</th>
						<th width="60">是否原始ID</th>
						<th>当前余额</th>
						<th>审核冻结金额</th>
						<th>提现冻结金额</th>
						<th>积分余额</th>
						<th>名称</th>
						<th width="30">性别</th>
						<th width="60">状态</th>
						<th>注册时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as m>
					<tr>
						<td><span class="green center">${m.user.id}</span></td>
						<td>${m.contact}</td>
						<td>
							<#if m.isRoot == 1>
								<span class="label label-sm label-success arrowed">是</span>
							<#else>
								<span class="label label-sm label-warning arrowed">否</span>
							</#if>
						</td>
						
						<td>${(m.user.money)}</td>
						<td>${(m.user.childBuyGoodsFreezeMoney)}</td>
						<td>${(m.user.freezeMoney)}</td>
						<td>${(m.user.score)}</td>
						<td>${(m.realName)!""}</td>
						<td><#if m.sex == 1>男<#else>女</#if></td>
						<td>
							<div class="col-xs-3">
								<label>
									<input name="switch-field-1" class="ace ace-switch ace-switch-4" type="checkbox" onchange="doUserState(this, ${m.user.id});" <#if m.user.state == 1>checked=true</#if>>
									<span class="lbl"></span>
								</label>
							</div>
						</td>
						<td>${tagUtils.formatDate(m.user.recordCreateTime)}</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/user/${m.user.id}/detail${suffix}" alt="Detail">
								<i class="icon-pencil bigger-130"></i>查 看
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/user/${m.user.id}/detail${suffix}" class="tooltip-success" data-rel="tooltip" title="查看" data-original-title="Detail">
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
		<@h.page pager=pager action="${ctx}/admin/user/list${suffix}" />
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
	if(f.userId.value) {
		f.pager.value += "$$userId--" + f.userId.value;
	}
	if(f.isRoot.value) {
		f.pager.value += "$$isRoot--" + f.isRoot.value;
	}
	return true;
}

function doUserState(checkbox, uid) {
	var href = ctx + "/admin/user/" + uid;
	if(checkbox.checked) {
		href += "/on"
	} else {
		href += "/off"
	}
	
	window.location.href = href;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"会员管理"}, {name:"会员列表", linkUrl:"${ctx}/admin/user/list${suffix}", active: true}]);
});
</script>

