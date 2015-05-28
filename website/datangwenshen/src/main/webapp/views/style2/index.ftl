<title>网站首页</title>
<div class="shoufen">
	<ul class="fold_wrap" id="sm">
        <li>
            <div class="mask_b"><h4>情侣纹身</h4></div>
            <div class="pic_auto" style="background:url(${ctx}/resources/${webSiteStyle}/images/img09.jpg) no-repeat center 0;"></div>
        </li>
        <li>
            <div class="mask_b"><h4>情侣纹身</h4></div>
            <div class="pic_auto" style="background:url(${ctx}/resources/${webSiteStyle}/images/img09.jpg) no-repeat center 0;"></div>
        </li>
        <li>
            <div class="mask_b"><h4>情侣纹身</h4></div>
            <div class="pic_auto" style="background:url(${ctx}/resources/${webSiteStyle}/images/img09.jpg) no-repeat center 0;"></div>
        </li>
        <li>
            <div class="mask_b"><h4>情侣纹身</h4></div>
            <div class="pic_auto" style="background:url(${ctx}/resources/${webSiteStyle}/images/img09.jpg) no-repeat center 0;"></div>
        </li>
        <li>
            <div class="mask_b"><h4>情侣纹身</h4></div>
            <div class="pic_auto" style="background:url(${ctx}/resources/${webSiteStyle}/images/img09.jpg) no-repeat center 0;"></div>
        </li>
    </ul>
</div>
<!--公司简介-->
<div class="main clearfix">
	<div class="company">
    	<h3>公司简介</h3>
        <div class="company_con">
        	<@common cmd="help_by_aliasesTitle" aliasesTitle="gongsijianjie">
            	${help.context}
	        </@common>
        </div>
    </div>
    <div class="contact">
    	<h3>联系我们</h3>
        <div class="company_con">
        	<ul>
            	<li>手机号：18257178988&nbsp;&nbsp;&nbsp;13175009566</li>
                <li>客服qq：1922857868</li>
                <li>微信号：hzdtws&nbsp;&nbsp;&nbsp;hangzhouwenshen</li>
                <li>微信二维码：<img src="${ctx}/resources/${webSiteStyle}/images/weixin1.png" width="177" height="177" /></li>
            </ul>
        </div>
    </div>
</div>
<!--滚动-->
<div class="gundong clearfix">
	<div class="gundong_title">纹身图库</div>
    <div class="apply">
        <div class="img_l"><img src="${ctx}/resources/${webSiteStyle}/images/left.png" /></div>
        <div class="apply_nav">
            <div class="apply_w">
            	<@datangwenshen cmd="get_pic_list" source=1 pagerString="1_" pageSize="99">
	        		<#list m_pager.itemList as u>
	        			<div class="apply_array">
        					<img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" width="180" height="280" />
	        			</div>
	        		</#list>
			    </@datangwenshen>
            </div>
        </div>
        <div class="img_r"><img src="${ctx}/resources/${webSiteStyle}/images/right.png" /></div>   
    </div>
</div>
<!--注意事项-->
<div class="zhuyi">
	<h2>纹身前后注意事项</h2>
    <div class="zhuyi_con">
    	<@common cmd="help_by_aliasesTitle" aliasesTitle="wash_intro">
        	${help.context}
        </@common>
    </div>
</div>
<!--滚动-->
<div class="gundong clearfix">
	<div class="gundong_title">纹身图库</div>
    <div class="apply">
        <div class="img_l"><img src="${ctx}/resources/${webSiteStyle}/images/left.png" /></div>
        <div class="apply_nav">
            <div class="apply_w">
            	<@datangwenshen cmd="get_pic_list" source=1 pagerString="1_" pageSize="99">
	        		<#list m_pager.itemList as u>
	        			<div class="apply_array">
        					<img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" width="180" height="280" />
	        			</div>
	        		</#list>
			    </@datangwenshen>
            </div>
        </div>
        <div class="img_r"><img src="${ctx}/resources/${webSiteStyle}/images/right.png" /></div>   
    </div>
</div>
<!--洗纹身视频-->
<div class="wenshen">
	<div class="wenshen_title"><h5>洗纹身视频</h5></div>
    <div class="wenshen_con clearfix">
    	<div class="shipin fl">
    	<object id="tudouHomePlayer" name="tudouHomePlayer" width="445" height="353" data="http://js.tudouui.com/bin/lingtong/PortalPlayer_166.swf" type="application/x-shockwave-flash"><param name="allowfullscreen" value="true"><param name="allowscriptaccess" value="always"><param name="bgcolor" value="#000000"><param name="wMode" value="Opaque"><param name="quality" value="high"><param name="allowFullScreenInteractive" value="true"><param name="flashvars" value="abtest=0&amp;referrer=http%3A%2F%2Fv.baidu.com%2Flink%3Furl%3Ddm_00pw_klemzFaU2vO4w7zo2Cc1yuX_dCEHtgd-yB9KwhC-tqPQIQDpQkRn6zaM4-Kc5mGTxA41v4oBrf9wwl7KhwpypQc-R1pWFi17PdcI1ZRmmuxM.%26page%3DvideoMultiNeed&amp;href=http%3A%2F%2Fwww.tudou.com%2Fprograms%2Fview%2FKRpCj3e6zNk&amp;USER_AGENT=Mozilla%2F5.0%20(Windows%20NT%206.1%3B%20WOW64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F41.0.2272.118%20Safari%2F537.36&amp;areaCode=330100&amp;yjuid=&amp;yseid=1432783721268YF05Gc&amp;ypvid=1432783721267uIDPBD&amp;yrpvid=&amp;yrct=&amp;frame=0&amp;noCookie=0&amp;yseidtimeout=1432790921268&amp;yseidcount=1&amp;fac=1&amp;aop=0&amp;listType=0&amp;listCode=&amp;listId=0&amp;lid=0&amp;paid=&amp;paidTime=&amp;paidType=&amp;lshare=1&amp;icode=KRpCj3e6zNk&amp;iid=147494142&amp;sp=http://g3.tdimg.com/b39cb8f19504c4a1bdc680231a5e0036/p_2.jpg&amp;segs=%7B%223%22%3A%5B%7B%22seconds%22%3A165370%2C%22no%22%3A0%2C%22pt%22%3A3%2C%22k%22%3A105509723%2C%22size%22%3A10592483%7D%5D%2C%222%22%3A%5B%7B%22seconds%22%3A165370%2C%22no%22%3A0%2C%22pt%22%3A2%2C%22k%22%3A105508700%2C%22size%22%3A5815373%7D%5D%2C%224%22%3A%5B%7B%22seconds%22%3A165370%2C%22no%22%3A0%2C%22pt%22%3A4%2C%22k%22%3A105510736%2C%22size%22%3A19616061%7D%5D%2C%2299%22%3A%5B%7B%22seconds%22%3A165370%2C%22no%22%3A0%2C%22pt%22%3A99%2C%22k%22%3A105511396%2C%22size%22%3A68128658%7D%5D%7D&amp;tvcCode=-1&amp;channel=99&amp;tict=3&amp;hd=2&amp;ol=1&amp;olw=720&amp;olh=576&amp;olr=3292830&amp;kw=%E7%BE%8E%E5%A5%B3%E7%BA%B9%E8%BA%AB%E6%97%B6%E7%9A%84%E8%A1%A8%E6%83%85%20%E5%A8%81%E6%B5%B7%E7%BA%B9%E8%BA%AB%20%E5%94%90%E6%9C%9D%E7%BA%B9%E8%BA%AB&amp;mediaType=vi&amp;np=0&amp;sh=0&amp;st=0&amp;videoOwner=114654763&amp;ocode=OmwfJmmvBfQ&amp;time=165&amp;vcode=&amp;ymulti=&amp;lang=&amp;isFeature=0&amp;is1080p=0&amp;hasWaterMark=0&amp;actionID=0&amp;resourceId=&amp;tpa=&amp;cs=&amp;k=%E7%BE%8E%E5%A5%B3%E7%BA%B9%E8%BA%AB%E6%97%B6%E7%9A%84%E8%A1%A8%E6%83%85%7C%E5%A8%81%E6%B5%B7%E7%BA%B9%E8%BA%AB%7C%E5%94%90%E6%9C%9D%E7%BA%B9%E8%BA%AB&amp;prd=&amp;uid=0&amp;ucode=&amp;vip=0&amp;juid=019mc914pi6nl&amp;seid=019mc914plbjg&amp;showWS=0&amp;ahcb=0&amp;wtime=0&amp;lb=0&amp;scale=0&amp;dvd=0&amp;hideDm=0&amp;pepper=http://css.tudouui.com/bin/binder/pepper_17.png&amp;panelEnd=http://css.tudouui.com/bin/lingtong/PanelEnd_13.swz&amp;panelRecm=http://css.tudouui.com/bin/lingtong/PanelRecm_9.swz&amp;panelShare=http://css.tudouui.com/bin/lingtong/PanelShare_7.swz&amp;panelCloud=http://css.tudouui.com/bin/lingtong/PanelCloud_12.swz&amp;panelDanmu=http://css.tudouui.com/bin/lingtong/PanelDanmu_18.swz&amp;aca="></object>
    	
    	</div>
        <div class="quwenshen fr">
	        	<@common cmd="help_by_aliasesTitle" aliasesTitle="index_wash">
	            	${help.context}
		        </@common>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/pic_cut.js"></script>
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/notknow.js"></script>