<div class="notice-column float-r">
	<div class="tab-title-box02">
		<ul class="tab-title float-l">
			<li class="not-tit active">最新公告</li>
			<!--<li class="act-tit">热门活动</li>-->
		</ul>
		<a class="title-more float-r" href="${ctx}/notice/list${suffix}">+more</a>
	</div>
	<div class="tab-con tab-con02">
		<ul class="last-notice thisclass">
			<@common cmd="notice_list" pagerString="1_" pageSize=7>
				<#if m_pager.itemList?has_content>
				<#list m_pager.itemList as u>
					<li><a href="${ctx}/notice/${u.id}${suffix}"><i>${u_index + 1}</i>${u.title}</a></li>
				</#list>
				</#if>
			</@common>
		</ul>
		<!--
		<ul class="popular-activities">
			<li><a><i>1</i>热门活动新华国际影城-大兴店万圣节专场优惠新华国际影城-大兴店万圣节专场优惠</a></li>
			<li><a><i>2</i>新华国际影城-大兴店万圣节专场优惠新华国际影城-大兴店万圣节专场优惠</a></li>
			<li><a><i>3</i>新华国际影城-大兴店万圣节专场优惠新华国际影城-大兴店万圣节专场优惠</a></li>
			<li><a><i>4</i>新华国际影城-大兴店万圣节专场优惠新华国际影城-大兴店万圣节专场优惠</a></li>
			<li><a><i>5</i>新华国际影城-大兴店万圣节专场优惠新华国际影城-大兴店万圣节专场优惠</a></li>
			<li><a><i>6</i>新华国际影城-大兴店万圣节专场优惠新华国际影城-大兴店万圣节专场优惠</a></li>
			<li class="last-li"><a><i>7</i>新华国际影城-大兴店万圣节专场优惠新华国际影城-大兴店万圣节专场优惠</a></li>
		</ul>-->
	</div>
</div>
<!--notice-column end-->