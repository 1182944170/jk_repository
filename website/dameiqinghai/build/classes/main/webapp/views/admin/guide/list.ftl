<title>导游列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/guide/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<label>导游号:</label>
	<input type="text" name="guideNo" value="${guideNo!''}" placeholder="导游号"/>
	
	<label>导游姓名:</label>
	<input type="text" name="guideName" value="${guideName!''}" placeholder="导游姓名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>导游名称</th>
						<th>性别</th>
						<th>价格</th>
						<th>从业时间</th>
						<th>导游证件编号</th>
						<th>联系电话</th>
						<th>是否推荐</th>
						<th>服务地区</th>
						<th>发布时间</th>
						<th>查看详情</th>
					</tr>
				</thead>
				<tbody>
				<#list guidePager.itemList as u>
					<tr>
						<td><span class="green center">${u.id!}</span></td>
						<td><span class="green center">${u.guideName!}</span></td>
						<td>${dicSetting.getParameterValue("sex.sexType."+u.sex)}</td>
						<td><span class="green center">${u.price!}元/天</span></td>
						<td><span class="green center">${u.jobTime!}</span></td>
						<td><span class="green center">${u.guideNo!}</span></td>
						<td><span class="green center">${u.telphone!}</span></td>
						<td>
							<#if u.isCommend==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/guide/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
								</span>
							<#else>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/guide/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
								</span>
							</#if>
						</td>
						<td><span class="green center">${u.serverArea!}</span></td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td><a href="##">查看详情</a></td>
					</tr>
				</#list>
				<div class="hr hr-18 dotted hr-double"></div>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
			<@h.page pager=guidePager action="${ctx}/admin/guide/list${suffix}" />
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
	$(document).ready(function() {
		RP.addBreadcrumb([{name:"导游列表"}]);
	});
</script>
