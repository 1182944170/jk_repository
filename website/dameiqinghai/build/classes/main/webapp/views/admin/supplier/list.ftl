<title>商品列表</title>
<table>
	<tr><th><b>商品类型 : </b></th>
		<th width="70px;"><a href="${ctx}/admin/supplier/list${suffix}">所有</a></th>
		<#assign sTypes = dicSetting.getParameterMap("supplier.type")> 
		<#list sTypes?keys as key>
			<#if key_index lt 10>
				<th width="70px;"><a href="${ctx}/admin/supplier/list${suffix}?shopType=${key}">${sTypes[key]}</a></th>
			</#if>
		</#list>
	</tr>
</table><p></p>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/supplier/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<input type="hidden" name="shopType" value="${shopType!}"/>
	<label>商品名:</label>
	<input type="text" name="name" value="${name!''}" placeholder="商品名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
</br>

<div class="hr hr-5 dotted"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>发布者</th>
						<th>商品名称</th>
						<th>商店名称</th>
						<th>是否推荐</th>
						<th>类型</th>
						<th>发布时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list supplierPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="green center">${u.shops.user.nickName!}</span></td>
						<td><span class="green center">${u.name!}</span></td>
						<td><span class="green center">${u.shops.shopName!}</span></td>
						<td>
							<#if u.isCommend==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/supplier/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
								</span>
							<#else>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/supplier/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
								</span>
							</#if>
						</td>
						<td>${dicSetting.getParameterValue("supplier.type."+u.shopType)!}</td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td>
							<a href="${ctx}/admin/supplier/detail-${u.id}${suffix}">查看详情</a>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		<#if shopType??>
			<@h.page pager=supplierPager action="${ctx}/admin/supplier/list${suffix}?shopType=${shopType!}" />
		<#else>
			<@h.page pager=supplierPager action="${ctx}/admin/supplier/list${suffix}" />
		</#if>
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
		RP.addBreadcrumb([{name:"商品列表"}]);
	});
</script>
