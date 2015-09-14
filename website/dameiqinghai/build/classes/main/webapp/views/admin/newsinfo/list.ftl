<title>发布资讯</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/newsinfo/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<label>资讯标题:</label>
	<input type="text" name="caption" value="${caption!''}" placeholder="资讯标题"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
</br>
<table>
	<tr>
		<th width="70px;"> <a href="${ctx}/admin/newsinfo/list${suffix}?state=1" > 所有资讯 </a> </th>
		<th width="70px;"> <a href="${ctx}/admin/newsinfo/list${suffix}?state=2" > 已删除 </a> </th>
	</tr>
</table>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>标题</th>
						<th>创建人</th>
						<th>状态</th>
						<th>是否热门</th>
						<th>发布时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				 
				<#list newsInfopager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="green center">${u.caption!}</span></td>
						<td><span class="green center">${u.authors!}</span></td>
						<td>
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">正常</span>
							<#else>
								<span class="label label-sm label-warning arrowed">被删除</span>
							</#if>
						</td>
						<td>
							<#if u.isCommend==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/newsinfo/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
								</span>
							<#else>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/newsinfo/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
								</span>
							</#if>
						</td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/newsinfo/edit-${u.id}${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>

								<a class="red" href="${ctx}/admin/newsinfo/delete-${u.id}${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
									<i class="icon-trash bigger-130"></i>
								</a>
							</div>
						</td>
					</tr>
				</#list>
				<div class="hr hr-18 dotted hr-double"></div>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		<@h.page pager=newsInfopager action="${ctx}/admin/news/list${suffix}" />
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
	RP.addBreadcrumb([{name:"基础配置"}, {name:"资讯管理", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/newsinfo/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">发布资讯</span></a>');
});
</script>
