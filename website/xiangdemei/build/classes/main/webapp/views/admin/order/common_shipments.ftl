<ul class="wizard-steps">
	<li data-target="#step1" class="complete">
		<span class="step">1</span>
		<span class="title"><small>创建</small></span>
	</li>
	
	<#assign hasValue=false />
	<#if u.orderItemShipments?has_content && u.orderItemShipments?size gt 0>
		<#assign hasValue=true />
	</#if>
	
	<li data-target="#step2" <#if hasValue && u.orderItemShipments[0].state==1>class="complete"</#if>>
		<span class="step" data-toggle="tooltip" data-placement="top" data-original-title="
		<#if hasValue>
			付款渠道:${gsonUtils.getString(u.orderItemShipments[0].extJson, "payChannel")}，
			付款时间:${tagUtils.formatDate(u.orderItemShipments[0].recordCreateTime)}
		</#if>
		">2</span>
		<span class="title"><small>付款</small></span>
	</li>
	
	<#assign hasValue=false />
	<#if u.orderItemShipments?has_content && u.orderItemShipments?size gt 1>
		<#assign hasValue=true />
	</#if>
	<li data-target="#step3" <#if hasValue && u.orderItemShipments[1].state==1>class="complete"</#if>>
		<span class="step" data-toggle="tooltip" data-placement="top" data-original-title="
		<#if hasValue>
			发送渠道:${(gsonUtils.getString(u.orderItemShipments[1].extJson, "sendChannel"))!""},
			发送编码:${(gsonUtils.getString(u.orderItemShipments[1].extJson, "sendNo"))!""},
			发货时间:${tagUtils.formatDate(u.orderItemShipments[1].recordCreateTime)}
		</#if>
		">3</span>
		<span class="title"><small>发货</small></span>
	</li>
	
	<#assign hasValue=false />
	<#if u.orderItemShipments?has_content && u.orderItemShipments?size gt 2>
		<#assign hasValue=true />
	</#if>
	<li data-target="#step4" <#if hasValue && u.orderItemShipments[2].state==1>class="complete"</#if>>
		<span class="step" data-toggle="tooltip" data-placement="top" data-original-title="
		<#if hasValue>
			收货时间:${tagUtils.formatDate(u.orderItemShipments[2].recordCreateTime)}
		</#if>
		">4</span>
		<span class="title"><small>买家收</small></span>
	</li>
	
	<#assign hasValue=false />
	<#if u.orderItemShipments?has_content && u.orderItemShipments?size gt 3>
		<#assign hasValue=true />
	</#if>
	<li data-target="#step4" <#if hasValue && u.orderItemShipments[3].state==1>class="complete"</#if>>
		<span class="step" data-toggle="tooltip" data-placement="top" data-original-title="
		<#if hasValue>
			评论时间:${tagUtils.formatDate(u.orderItemShipments[3].recordCreateTime)}
		</#if>
		">5</span>
		<span class="title"><small>评价</small></span>
	</li>
</ul>
