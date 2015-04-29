<!--footer begin-->
<div class="footer">
	<div class="center">
		<div class="footer-top">
			<span class=""><!--云票票：--></span>
			<!--<a class="padding-l">上海</a>|<a>北京</a>|<a>杭州</a>|<a>宁波</a>|<a>嘉兴</a>|<a>绍兴</a>|<a>湖州</a>|<a>台州</a>|<a>南京</a>|<a>无锡</a>|<a>常州</a>|<a>苏州</a>|<a>南通</a>|<a>广州</a>|<a>深圳</a>|<a>佛山</a>|<a>武汉</a>|<a>重庆</a>|<a>成都</a>|<a>天津</a>|<a>合肥</a>|-->
		</div>
		<div class="footer-middle clearfix">
			<div class="footer-logo">
				LOGO区域
			</div>
			<@common cmd="help_list_group_type">
				<#list map?keys as key>
				<dl>
				<dt>${dicSetting.getParameterValue("help.type." + key)}</dt>
				<#list map[key] as v>
				<dd><a href="${ctx}/help/${v.aliasesTitle}${suffix}">${v.title}</a></dd>
				</#list>
				</dl>
	            </#list>
			</@common>
			<div class="service-phone">
				<p>客服电话</p>
				<h1>0571-85358280</h1>
			</div>
		</div>
		<div class="footer-bottom">
			<p><span>Copyright©2007-2014 Gewara.</span><span>All Rights Reserved</span><span>泸ICP备09050772号</span><span>ICP证：泸B2-20120044</span></p>
			<div class="footer-img">
				<a><img src="${ctx}/resources/images/demoimg/footer_img01.jpg"></a>
				<a><img src="${ctx}/resources/images/demoimg/footer_img02.jpg"></a>
				<a><img src="${ctx}/resources/images/demoimg/footer_img03.jpg"></a>
				<a><img src="${ctx}/resources/images/demoimg/footer_img04.jpg"></a>
				<a><img src="${ctx}/resources/images/demoimg/footer_img05.jpg"></a>
				<a><img src="${ctx}/resources/images/demoimg/footer_img06.jpg"></a>
				<a><img src="${ctx}/resources/images/demoimg/footer_img07.jpg"></a>
			</div>
		</div>
	</div>
</div>
<!--footer end-->
</body>
</html>