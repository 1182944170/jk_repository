<title>分类管理</title>
<script language="javascript">
	function returnlist(){
		window.location.href="${ctx}/admin/bankextion/1/onelist/";
	}
	function nolist(){
		window.location.href="${ctx}/admin/bankextion/list/";
	}
	
</script>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		<input id="btn2"  type="button" onclick="nolist();" value="未处理" />
		<input id="btn2"  type="button" onclick="returnlist();" value="已处理"/>
		
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>用户</th>
						<th>电话</th>
						<th>银行卡号</th>
						<th>提现金额</th>
						<th>状态</th>
						<th></th>
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>

				        <td><span class="gray center" style="color:Green">${u.name!}</span></td>
				        <td><span class="gray center" style="color:Green">${u.phone!}</span></td>
						<td><span class="gray center">${u.monlycard!}</span></td>
						<td><span class="gray center">${u.extractionMonly!}</span></td>
						 <#if u.state == 2 >
						<td><span class="gray center" style="color:Green"><b>已处理</b></span></td>
						</#if>
						<#if u.state == 1 >
						<td><span class="gray center" style="color:red"><b>未审核</b></span></td>
						</#if>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<#if u.state==1>
								<a class="green" href="${ctx}/admin/bankextion/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130">提现处理</i>
								</a>
							</#if>	
							</div>
	
							<div class="visible-xs visible-sm hidden-md hidden-lg">
								<div class="inline position-relative">
									<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
										<i class="icon-caret-down icon-only bigger-120"></i>
									</button>
									<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
										<li>
											<a href="${ctx}/admin/cal/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="编辑" data-original-title="Edit">
												<span class="green">
													<i class="icon-edit bigger-120"></i>
												</span>
											</a>
										</li>
	
										<!--<li>
											<a href="${ctx}/admin/cal/${u.id}/delete${suffix}" onclick="return confirm('你确定删除么?');" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
												<span class="red">
													<i class="icon-trash bigger-120"></i>
												</span>
											</a>
										</li>-->
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
		<@h.page pager=pager action="${ctx}/admin/lable/list" />
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

