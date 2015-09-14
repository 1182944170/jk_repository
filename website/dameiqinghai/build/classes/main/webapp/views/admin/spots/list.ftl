<title>景点列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/spots/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<label>景点名:</label>
	<input type="text" name="spotsName" value="${spotsName!''}" placeholder=" 景点名"/>
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
						<th>景点名</th>
						<th>景点地址</th>
						<th>出发地</th>
						<th>目的地</th>
						<th>行程天数</th>
						<th>平均花费</th>
						<th>适宜时间</th>
						<th>门票方式</th>
						<th>是否推荐</th>
						<th>简介</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list spotsPager.itemList as u>
					<#if u.state!=99>
					<tr>
						<td><span class="green center">${u.id!}</span></td>
						<td><span class="green center">${u.spotsName!}</span></td>
						<td>${commonTag.getCountyPath(u.areaCode)}</td>
						<#--[ ${tagUtils.cutString(u.address,12)} ]-->
						<td><span class="green center">${u.fromAddress!}</span></td>
						<td><span class="green center">${u.toAddress!}</span></td>
						<td><span class="green center">${u.travelDays!}</span></td>
						<td><span class="green center">${u.cost!}</span></td>
						<td><span class="green center">${u.propernessDate!}</span></td>
						<td><span class="green center">${dicSetting.getParameterValue("travel.type."+u.ticketType)!}</span></td>
						<td>
							<#if u.isCommend==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/spots/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
								</span>
							<#else>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/spots/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
								</span>
							</#if>
						</td>
						<td><span class="green center">${u.production!}</span></td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/spots/edit-${u.id}${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>

								<a class="red" href="${ctx}/admin/spots/delete-${u.id}${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
									<i class="icon-trash bigger-130"></i>
								</a>
							</div>
						</td>
					</tr>
					</#if>
				</#list>
				<div class="hr hr-18 dotted hr-double"></div>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
			<@h.page pager=spotsPager action="${ctx}/admin/order/list${suffix}" />
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
$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础配置"}, {name:"景点管理", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/spots/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加景点</span></a>');
});
</script>