<title>查看申请</title>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="applnName">真实姓名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" readonly value=${appln.realName!} />
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="shopName">店铺名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" readonly value=${appln.shopName!} />
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="telphone">联系电话:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" readonly value=${appln.telphone!} />
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="cardNo">身份证号:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" readonly value=${appln.cardNo!} />
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="">身份证正面图:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<a href="${tagUtils.getFileFullPath(appln.cardFront!)}" target= _blank> 
					<img src="${tagUtils.getFileFullPath(appln.cardFront!)}" style="display: block; width: 200px; height: 150px; float:left;"/>
				</a>
				<a href="${tagUtils.getFileFullPath(appln.cardNegate!)}" target= _blank> 
					<img src="${tagUtils.getFileFullPath(appln.cardNegate!)}" style="display: block; width: 200px; height: 150px; float:left;"/>
				</a>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="">营业执照:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<a href="${tagUtils.getFileFullPath(appln.cedula!)}" target= _blank> 
					<img src="${tagUtils.getFileFullPath(appln.cedula!)}" style="display: block; width: 240px; height: 180px;"/>
				</a>
			</span>
		</div>
	</div>
</div>

<#if appln.state != 1 && appln.state != 2>
	<div class="form-group">
		<div class="col-md-offset-3 col-md-9">
			<a href="${ctx}/admin/appln/agree-${appln.id}-1"><i class="icon-ok bigger-110"></i>同意</a>
			&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
			<a href="${ctx}/admin/appln/agree-${appln.id}-2"><i class="icon-undo bigger-110"></i>拒绝</a>
		</div>
	</div>
</#if>

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}
		</span>
	</h4>
</#if>
