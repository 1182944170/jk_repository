<title>租车列表</title>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/rcar/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<label>车型:</label>
	<input type="text" name="carModel" value="${carModel!''}" placeholder="车型"/>
	
	<label>车主姓名:</label>
	<input type="text" name="rcarName" value="${rcarName!''}" placeholder="会员姓名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
</br>
<table>
	<tr>
		<th width="70px;"> <a href="${ctx}/admin/rcar/list${suffix}?state=1" > 所有租车 </a> </th>
		<th width="70px;"> <a href="${ctx}/admin/rcar/list${suffix}?state=99" > 已删除 </a> </th>
	</tr>
</table>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>车主名称</th>
						<th>车辆型号</th>
						<th>价格</th>
						<th>联系电话</th>
						<th>是否推荐</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>查看详情</th>
					</tr>
				</thead>
				<tbody>
				<#list rcarPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id!}</span></td>
						<td><span class="green center">${u.rcarName!}</span></td>
						<td><span class="green center">${u.carModel!}</span></td>
						<td><span class="green center">${u.price!}元/天</span></td>
						<td><span class="green center">${u.telphone!}</span></td>
						<td>
							<#if u.state==1>
								<#if u.isCommend==1>
									<span class="label label-sm label-success arrowed">
										<a href="${ctx}/admin/rcar/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
									</span>
								<#else>		 
									<span class="label label-sm label-warning arrowed">
										<a href="${ctx}/admin/rcar/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
									</span>
								</#if>
							<#else>
								<#if u.isCommend==1>
									<span class="label label-sm label-success arrowed">已推荐</span>
								<#else>		 
									<span class="label label-sm label-warning arrowed">推荐</span>
								</#if>	
							</#if>
						</td>
						<td>
							<#if u.state==1>
								<span class="label label-sm label-success arrowed">正常</span>
							<#elseif u.state==99>		 
								<span class="label label-sm label-warning arrowed">已删除</span>
							</#if>
						</td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td><a href="##">查看详情</a></td>
					</tr>
				</#list>
				<div class="hr hr-18 dotted hr-double"></div>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
			<@h.page pager=rcarPager action="${ctx}/admin/rcar/list${suffix}" />
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
		RP.addBreadcrumb([{name:"租车列表"}]);
	});
</script>
