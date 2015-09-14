<div id="my_menu" class="sdmenu">
    <div>
        <span><img src="${ctx}/resources/images/p_ico01.png" alt="" /><em>订单</em></span>
        <a href="${ctx}/order/list${suffix}"><em>全部订单</em></a>
        <a href="${ctx}/order/add${suffix}"><em>app订单新增</em></a>
        <a href="${ctx}/order/list${suffix}?orderpageType=1"><em>美食订单</em></a>
        <a href="${ctx}/order/list${suffix}?orderpageType=2"><em>艺人订单</em></a>
        <a href="${ctx}/order/list${suffix}?orderpageType=3"><em>酒店订单</em></a>
    </div>
    <div>
        <span><img src="${ctx}/resources/images/p_ico02.png" /><em>我的同游</em></span>
        <a href="${ctx}/sameway/direct${suffix}"><em>我发布的</em></a>
        <a href="${ctx}/sameway/assist${suffix}"><em>我参加的</em></a>
    </div>
    <div>
        <span><img src="${ctx}/resources/images/p_ico03.png" /><em>我的画册</em></span>
        <a href="${ctx}/photo/add${suffix}"><em>发布画册</em></a>
        <a href="${ctx}/photo/direct${suffix}"><em>管理画册</em></a>
    </div>
    <div>
        <span><img src="${ctx}/resources/images/p_ico04.png" /><em>我的游记</em></span>
        <a href="${ctx}/travel/add${suffix}"><em>发布游记</em></a>
        <a href="${ctx}/travel/direct${suffix}"><em>管理游记</em></a>
    </div>
    <div>
        <span><img src="${ctx}/resources/images/p_ico5.png" /><em>设置</em></span>
        <a href="${ctx}/user/setting_data${suffix}"><em>修改资料</em></a>
        <a href="${ctx}/user/setting_pwd${suffix}"><em>密码修改</em></a>
    </div>
    <div>
	    <#if sessionUser.isFood == 1>
	     	<span><img src="${ctx}/resources/images/p_ico09.png" /><em>美食商户</em></span>
	    	<a href="${ctx}/food/add${suffix}"><em>发布美食</em></a>
	    	<a href="${ctx}/food/direct${suffix}"><em>管理美食</em></a>
	    </#if>
    </div>
    <div>
	    <#if sessionUser.isHotel == 1>
	     	<span><img src="${ctx}/resources/images/p_ico08.png" /><em>酒店商户</em></span>
	    	<a href="${ctx}/hotel/add${suffix}"><em>发布房间</em></a>
	    	<a href="${ctx}/hotel/direct${suffix}"><em>管理房间</em></a>
	    </#if>
    </div>
    <div>
	    <#if sessionUser.isArt == 1>
	     	<span><img src="${ctx}/resources/images/p_ico10.png" /><em>艺人商户</em></span>
	    	<a href="${ctx}/art/add${suffix}"><em>发布艺人</em></a>
	    	<a href="${ctx}/art/direct${suffix}"><em>管理艺人</em></a>
	    </#if>
    </div>
    <div>
        <span><img src="${ctx}/resources/images/p_ico06.png" /><em>租车</em></span>
        <#if sessionUser.isCar == 1>
        	<a href="${ctx}/rcar/add${suffix}"><em>发布租车</em></a>
        	<a href="${ctx}/rcar/direct${suffix}"><em>管理租车</em></a>
        <#else>
    		<a href="${ctx}/appln/car${suffix}"><em>申请租车</em></a>
        </#if>
    </div>
    <div>
        <span><img src="${ctx}/resources/images/p_ico07.png" /><em>导游</em></span>
        <#if sessionUser.isGuide == 1>
        	<a href="${ctx}/guide/add${suffix}"><em>发布导游</em></a>
        	<a href="${ctx}/guide/direct${suffix}"><em>管理导游</em></a>
        <#else>
    		<a href="${ctx}/appln/guide${suffix}"><em>申请导游</em></a>
        </#if>
        
    </div>
    <div>
    <#if sessionUser.isFood == 1 && sessionUser.isHotel == 1 && sessionUser.isArt == 1>
    <#else>
        <span><img src="${ctx}/resources/images/p_ico08.png" /><em>申请商户</em></span>
    </#if>
        <#if sessionUser.isFood == 1>
        <#else>
    		<a href="${ctx}/appln/food${suffix}"><em>开通美食商户</em></a>
        </#if>
        
        <#if sessionUser.isHotel == 1>
        <#else>
    		<a href="${ctx}/appln/hotel${suffix}"><em>开通酒店商户</em></a>
        </#if>
        
        <#if sessionUser.isArt == 1>
        <#else>
    		<a href="${ctx}/appln/art${suffix}"><em>开通艺人商户</em></a>
        </#if>
    </div>

</div>                
