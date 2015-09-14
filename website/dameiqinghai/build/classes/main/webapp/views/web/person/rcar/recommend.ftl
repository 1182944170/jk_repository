<div class="w_dytj">
    <h4><a>租车信息</a></h4>
    <ul>
    	<#list rcarList as u>
            <li class="rcarListClass">
                <a href="${ctx}/rcar/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_pic03.jpg" width="60" height="50" alt="" /></a>
                <h5><a href="${ctx}/rcar/detail-${u.id}${suffix}">${u.carModel!}</a><em>￥<i>${u.price!}/天</i></em></h5>
                <p>${u.carType!}</p>
            </li>
        </#list>
    </ul>
 </div>