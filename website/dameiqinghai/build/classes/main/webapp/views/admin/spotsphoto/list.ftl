<title>画册列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/spotsphoto/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<label>画册名:</label>
	<input type="text" name="photoName" value="${photoName!''}" placeholder="画册名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
</br>
<table>
	<tr>
		<th width="70px;"> <a href="${ctx}/admin/spotsphoto/list${suffix}?state=1" > 所有画册 </a> </th>
		<th width="70px;"> <a href="${ctx}/admin/spotsphoto/list${suffix}?state=2" > 已删除 </a> </th>
	</tr>
</table>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>景点</th>
						<th>画册名</th>
						<th>是否推荐</th>
						<th>创建时间</th>
						<th>查看子画册</th>
					</tr>
				</thead>
				<tbody>
				<#list spotsPhotoPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id!}</span></td>
						<td><span class="green center">${u.spots.spotsName!}</span></td>
						<td><span class="green center">${u.photoName!}</span></td>
						<td>
							<#if u.isCommend==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/spotsphoto/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
								</span>
							<#else>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/spotsphoto/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
								</span>
							</#if>
						</td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td><a href="${ctx}/admin/photobook/list-${u.spots.id}${suffix}"> 《${u.spots.spotsName!}》所有画册 </a></td>
					</tr>
				</#list>
				<div class="hr hr-18 dotted hr-double"></div>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
			<@h.page pager=spotsPhotoPager action="${ctx}/admin/spotsphoto/list${suffix}" />
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
	RP.addBreadcrumb([{name:"基础配置"}, {name:"画册管理", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/spotsphoto/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加画册</span></a>');
});
</script>
