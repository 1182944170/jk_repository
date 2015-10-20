<title>楼盘推荐列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/house_recommend/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>推荐用户名:</label>
	<input type="text" name="recommendUserRealName" value="${(pager.searchMap.recommendUserRealName)!''}" placeholder="推荐用户名"/>
	
	<label>接单用户名:</label>
	<input type="text" name="acceptSalesmanRealName" value="${(pager.searchMap.acceptSalesmanRealName)!''}" placeholder="接单用户名"/>

	<label>楼盘名字:</label>
	<input type="text" name="houseName" value="${(pager.searchMap.houseName)!''}" placeholder="楼盘名字"/>
		
	<label>楼盘ID:</label>
	<input type="text" name="houseId" value="${(pager.searchMap.houseId)!''}" placeholder="楼盘ID"/>
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<p>
<table>
	<tr>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/list${suffix}" > 查看所有 </a> </th>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/list${suffix}?state=1" > 未被接单 </a> </th>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/list${suffix}?state=2"> 查看处理中 </a> </th>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/list${suffix}?state=8"> 已完成的 </a> </th>
	</tr>
</table>
<div class="hr hr-5"></div>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>客户信息</th>
						<th width=80>客户需求</th>
						<th width=100>推荐楼盘</th>
						<th>推荐人信息</th>
						<th>接单人信息</th>
						<th width=300>进度</th>
						<th>创建时间</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>
						 ${u.customerName} <br/>
						 ${u.contact}
						</td>
						<td>
						<span class="step" data-toggle="tooltip" data-placement="top" data-original-title="类型:${dicSetting.getParameterValue("house.propertyType." + u.propertyType)}  
								 面积:${dicSetting.getParameterValue("house.surfaceType." + u.surfaceType)} 
								 总价:${dicSetting.getParameterValue("house.totalPriceType." + u.totalPriceType)}  
								 区域:${commonTag.getCountyPath(u.areaCode)} 
								 信息:${u.customerInfo}
						">查 看</span>
						</td>
						<td class="hidden-480">${u.house.name}</td>
						<td> ${u.recommendUser.realName} </br>${u.recommendUser.contact} </br></td>
						
						<td><#if u.state==1>
							该推荐未被接单
						<#else>
							${u.acceptSalesman.realName} </br>${u.acceptSalesman.contact}
						</#if></td>
						<td>
						<#assign hasWaitLeader=0 />
						<#if u.state==1>
							无
						<#else>
							<#assign intents = ["D", "D", "C", "B", "A"]/>
							<ul class="wizard-steps">
								<#assign hasValue=false />
								<#if u.progresses?has_content && u.progresses?size gt 0>
									<#assign hasValue=true />
								</#if>
								
								<li data-target="#step1" <#if u.progressState == 0>class="complete"</#if>>
									<span class="step">1</span>
									<span class="title"><small>无效</small></span>
								</li>
								
								
								<li data-target="#step1" <#if hasValue && u.progresses[0].state==1>class="complete"</#if>>
									<span class="step" data-toggle="tooltip" data-placement="top" data-original-title="
									<#if hasValue>
										意向:${intents[gsonUtils.getInt(u.progresses[0].extJson, "intentStar")]}, 备注:${gsonUtils.getString(u.progresses[0].extJson, "remark")},处理时间:${tagUtils.formatDate(u.progresses[0].recordCreateTime)}
									</#if>
									">2</span>
									<span class="title"><small>有效</small></span>
								</li>
								
								<#assign hasValue=false />
								<#if u.progresses?has_content && u.progresses?size gt 1>
									<#assign hasValue=true />
								</#if>
								<#--<li data-target="#step2" <#if hasValue && u.progresses[1].state==1>class="complete"</#if>>
									<span class="step" data-toggle="tooltip" data-placement="top" data-original-title="
									<#if hasValue>
										意向:${intents[gsonUtils.getInt(u.progresses[1].extJson, "intentStar")]},备注:${(gsonUtils.getString(u.progresses[1].extJson, "remark"))!""},处理时间:${tagUtils.formatDate(u.progresses[1].recordCreateTime)}
									</#if>
									">3</span>
									<span class="title"><small>到访</small></span>
								</li>
								-->
								<#assign hasValue=false />
								<#if u.progresses?has_content && u.progresses?size gt 2>
									<#assign hasValue=true />
								</#if>
								<li data-target="#step3" <#if hasValue && u.progresses[2].state==1>class="complete"<#elseif hasValue && u.progresses[2].state==2>class="active"</#if>>
									<span class="step" data-toggle="tooltip" data-placement="top"  <#if hasValue && u.progresses[2].state==2>style="border-color:#C47952"</#if> data-original-title="
									<#if hasValue>
										<#if u.progresses[2].state==1><#elseif u.progresses[2].state==2><#assign hasWaitLeader=1 />等待负责人确认<#else></#if>
										成交时间:${tagUtils.formatDate(gsonUtils.getLong(u.progresses[2].extJson, "dealTime") )},
										成交面积:${gsonUtils.getDouble(u.progresses[2].extJson, "surface")},
										价格:${gsonUtils.getDouble(u.progresses[2].extJson, "price")}万元,
										处理时间:${tagUtils.formatDate(u.progresses[2].recordCreateTime)}
									</#if>
									">4</span>
									<#if u.overType == 1>
										<span class="title"><small>接单人成交</small></span>
									<#elseif u.overType == 2>
										<span class="title"><small>推荐人成交</small></span>
									<#elseif u.overType == 9>
										<span class="title"><small>双方成交</small></span>
									<#else>
										<span class="title"><small>成交</small></span>
									</#if>
								</li>
								
								<#assign hasValue=false />
								<#if u.progresses?has_content && u.progresses?size gt 3>
									<#assign hasValue=true />
								</#if>
								<li data-target="#step4" <#if hasValue && u.progresses[3].state==1>class="complete"</#if>>
									<span class="step" data-toggle="tooltip" data-placement="top"  <#if progress?? && progress.state==1><#elseif progress?? && progress.state==2>style="border-color:#C47952"</#if> data-original-title="
									<#if hasValue>
										完结:处理时间:${tagUtils.formatDate(u.progresses[3].recordCreateTime)}
									</#if>
									">5</span>
									<span class="title"><small>完结</small></span>
								</li>
							</ul>
						</#if>
						
							
						</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td><#if u.state==1>
							<span class="label label-sm label-warning arrowed">该推荐未被接单</span>
						<#elseif u.state==2>
							<span class="label label-sm label-warning arrowed">处理中</span>
							<a class="green" href="${ctx}/admin/house_recommend/${u.id}/stop${suffix}" onclick="if(!confirm('确认设置无效么?')){return false;}" alt="设置无效">
								<i class="icon-pencil bigger-130"></i> 设置无效
							</a>
						<#else>
							<span class="label label-sm label-success arrowed">完结状态</span>
						</#if></td>
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
	if(f.recommendUserRealName.value) {
		f.pager.value += "$$recommendUserRealName--" + f.recommendUserRealName.value;
	}
	if(f.acceptSalesmanRealName.value) {
		f.pager.value += "$$acceptSalesmanRealName--" + f.acceptSalesmanRealName.value;
	}
	if(f.houseId.value) {
		f.pager.value += "$$houseId--" + f.houseId.value;
	}
	if(f.houseName.value) {
		f.pager.value += "$$houseName--" + f.houseName.value;
	}
	return true;
}

$(document).ready(function(){ 
	RP.addBreadcrumb([{name:"楼盘推荐管理"}, {name:"楼盘推荐列表", linkUrl:"${ctx}/admin/house_recommend/list", active: true}]);
});
</script>

