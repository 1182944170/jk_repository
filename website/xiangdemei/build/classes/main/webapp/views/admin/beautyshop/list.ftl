<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/beautyshop/list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="areaCode" value=""/>
	
	<label>会员ID:</label>
	<input type="text" name="rootUserId" value="${(pager.searchMap.rootUserId)!''}" placeholder="会员ID"/>
	
	<label>美容院名称:</label>
	<input type="text" name="name" value="${(pager.searchMap.name)!''}" placeholder="美容院名称"/>
	
	<label>地址:</label>
	<select class="width-10" name="provinceSelect" id="provinceSelect"></select>
	<select class="width-10" name="citySelect" id="citySelect"></select>
	<select class="width-10" name="countySelect" id="countySelect"></select>
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>


<title>美容院列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>会员ID</th>
						<th>名称</th>
						<th>省</th>
						<th>市</th>
						<th>区</th>
						<th>地址</th>
						<th>体验人数</th>
						<th>评论数</th>
						<th>状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as m>
					<#assign areaJson=commonTag.getCountyPathJson(m.areaCode) />
					<tr>
						<td><span class="green center">${m.rootUserId!}</span></td>
						<td>${m.name}</td>
						<td>${gsonUtils.getDeepValueByJson(areaJson, "province.name")}</td>
						<td>${gsonUtils.getDeepValueByJson(areaJson, "city.name")}</td>
						<td>${gsonUtils.getDeepValueByJson(areaJson, "county.name")}</td>
						<td>${m.address}</td>
						<td>${m.experienceNumber}</td>
						<td>${m.commentCount}</td>
						<td>
						<#if m.state == 0>
							<span class="label label-sm label-warning arrowed">禁用状态</span>
						<#elseif m.state == 1>
							<span class="label label-sm label-success arrowed">正常状态</span>
						<#else>
						</#if>
						</td>
						<td class="center">
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/beautyshop/${m.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>编辑
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/beautyshop/${m.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
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
		</div>
	
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/beautyshop/list" />
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
	if(country.getSelectData()) {
		f.areaCode.value = country.getSelectData();
	}
			
	if(f.rootUserId.value) {
		f.pager.value += "$$rootUserId--" + f.rootUserId.value;
	}
	if(f.name.value) {
		f.pager.value += "$$name--" + f.name.value;
	}
	if(f.areaCode.value) {
		f.pager.value += "$$areaCode--" + f.areaCode.value;
	}
	return true;
}

var country = Object.create(B.Country);
$(document).ready(function(){
	country.regist4Select(${(pager.searchMap.areaCode)!-1},"provinceSelect","citySelect","countySelect");
	RP.addBreadcrumb([{name:"基础配置"}, {name:"美容院列表", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/beautyshop/add"><span>新增美容院</span></a>');
});

</script>
	
						
				
			


