<#include "public/top.ftl"/>
<#include "public/head.ftl"/>
<!--content begin-->
<div class="index-content">
	<div class="banner">
		<!--js 幻灯片效果-->
		<img src="${ctx}/resources/images/demoimg/banner.jpg" width="100%">
	</div>

	<div class="index-column">
		<ul class="center clearfix">
			<li><a class="column-ico01"></a><span>兑换码查询</span></li>
			<li><a class="column-ico02"></a><span>电影卡充值</span></li>
			<li><a class="column-ico03"></a><span class="icon-text">团体购票</span></li>
			<li><a class="column-ico04"></a><span class="icon-text">购电子卷</span></li>
			<li><a class="column-ico05"></a><span class="icon-text">影院查询</span></li>
			<li class="margin-none"><a class="column-ico06"></a><span class="icon-text">优惠活动<span></li>
		</ul>
	</div>
	<div class="center">
		<div class="tab-title-box01">
			<ul class="tab-title float-l">
				<li class="active">正在热映</li>
				<li>即将上映</li>
			</ul>
			<a class="title-more float-r" href="${ctx}/movie${suffix}">+more</a>
		</div>
		<div class="tab-con tab-con01">
			<ul class="playing-movie thisclass">
			<@ypp cmd="do_movie_pager" pageSize=6 pagerString="1_movieState--1">
				<#if m_pager.itemList?has_content>
				<#list m_pager.itemList as u>
					<li class="hover-active">
						<h3>
							<a>
								<img src="${tagUtils.getFileFullPath(u.icon)}" width="165" height="210"/>
								<div class="movie-text clearfix">
									<span class="movie-infor float-l">
										<span>${u.name}</span>
										<!--<span>已售票1000张</span>-->
									</span>
									<span class="movie-mark float-r">${u.mark}</span>
								</div>
							</a>
						</h3>
						<!-- <div class="buy-btn">
							<a>购票</a>
						</div> -->
					</li>
				</#list>
				</#if>
			</@ypp>
			</ul>
			<ul class="playing-movie">
				<@ypp cmd="do_movie_pager" pageSize=6 pagerString="1_movieState--2">
					<#if m_pager.itemList?has_content>
					<#list m_pager.itemList as u>
						<li class="hover-active">
							<h3>
								<a href="${ctx}/movie/${u.id}${suffix}">
									<img src="${tagUtils.getFileFullPath(u.icon)}" width="165" height="210"/>
									<div class="movie-text clearfix">
										<span class="movie-infor float-l">
											<span>${u.name}</span>
											<!--<span>已售票1000张</span>-->
										</span>
										<span class="movie-mark float-r">${u.mark}</span>
									</div>
								</a>
							</h3>
							<!-- <div class="buy-btn">
								<a>购票</a>
							</div> -->
						</li>
					</#list>
					</#if>
				</@ypp>
			</ul>
		</div>
		<!--tab-con end-->
		<div class="movie-y-box clearfix">
			<dl class="movie-y">
				<dt class="bg01">电影储蓄卡</dt>
				<dd>
					<img src="${ctx}/resources/images/demoimg/y_img.jpg">
					<p><label>产品名称：</label>电影储蓄卡</p>
					<p><label>类型：</label>储蓄卡</p>
					<p><label>兑换方式：</label>在影院票台处扫码或手动购票</p>
					<p><a>[详情点击查询]</a></p>
				</dd>
			</dl>
			<dl class="movie-y">
				<dt class="bg02">电影储蓄卡</dt>
				<dd>
					<img src="${ctx}/resources/images/demoimg/y_img.jpg">
					<p><label>产品名称：</label>电影储蓄卡</p>
					<p><label>类型：</label>储蓄卡</p>
					<p><label>兑换方式：</label>在影院票台处扫码或手动购票</p>
					<p><a>[详情点击查询]</a></p>
				</dd>
			</dl>
			<dl class="movie-y">
				<dt class="bg03">电影储蓄卡</dt>
				<dd>
					<img src="${ctx}/resources/images/demoimg/y_img.jpg">
					<p><label>产品名称：</label>电影储蓄卡</p>
					<p><label>类型：</label>储蓄卡</p>
					<p><label>兑换方式：</label>在影院票台处扫码或手动购票</p>
					<p><a>[详情点击查询]</a></p>
				</dd>
			</dl>
			<#include "notice/notice_thumb.ftl"/>
			<ul>
				
			</ul>
		</div>
		<!--movie-y-box end-->
		<div class="cinema-show">
			<div class="y-title">
				影院展示
				<a class="title-more float-r" href="${ctx}/cinema${suffix}">+more</a>
			</div>
			<div class="scroll-content">
          		<i class="prev"></i>
          		<i class="next"></i>
          		<div class="scroll">
               		<div class="scroll-pic">
                     	<ul class="count">
                     		<@ypp cmd="do_cinema_pager" pageSize=10 pagerString="1_isRecommend--1">
								<#if m_pager.itemList?has_content>
								<#list m_pager.itemList as u>
									<li>
		                         		<div class="img-box">
		                         			<img src="${tagUtils.getFileFullPath(u.icon)}">
		                         		</div>
										<span>${u.name}</span>
		                         	</li>
								</#list>
								</#if>
							</@ypp>
                     	</ul>
               		</div>
          		</div>
    		</div>
		</div>
		<!--cinema-show end-->
	</div>
</div>
<!--content end-->
<#include "public/foot.ftl"/>
