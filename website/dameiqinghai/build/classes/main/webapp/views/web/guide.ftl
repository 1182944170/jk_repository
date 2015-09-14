<title>导游</title>

<#include "public/top.ftl">

<script type="text/javascript">
	//解析管理图片 每个图片div加上 class="pclass"
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	var s1 ='';
	var s2 ='';
	var p1 = '';
	var p2 = '';
	var score = '';
	$(function(){
		<#list guidePager.itemList as u>
			s1 +="${u.photos!}"+";";
		    s2 +="${u.user.photo!}"+";";
		</#list>
		p1=s1.substring(0,s1.length-1);
		p2=s2.substring(0,s2.length-1);
		initphoto(p1,p2,'guidePagerClass','guidePagerChild',score);
		
		s1 = (s1=='') ? '' : s1='';
		s2 = (s2=='') ? '' : s2='';
		p1 = (p1=='') ? '' : p1='';
		p2 = (p2=='') ? '' : p2='';
		<#list carList as u>
			s1 +="${u.photos!}"+";";
		    s2 +="${u.user.photo!}"+";";
		</#list>
		p1=s1.substring(0,s1.length-1);
		p2=s2.substring(0,s2.length-1);
		initphoto(p1,p2,'carListClass','carListChild',score);
		
	});
	
	function initphoto(p1,h1,o,childclass,score){
		$("."+o+"").each(function(i){
			var p = p1.split(";")[i];
			var h = h1.split(";")[i];
			if(p != '' && p != null){
				if(p.indexOf(",")>0){
					p = p.split(",");
					p = p[0];
					p = p.substring(1,p.length);
				}
				$(this).find("img").removeAttr("src");
				$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
				$(this).find("img[class='imgclass']").attr("src","${tagUtils.getFileFullPath('"+h+"')}");
			}
			$("."+childclass+"").each(function(k){
					if(i == k){
					 	appendScore($(this),parseInt(score[i]),yellowStar,darkStar);
					 	return false;
					 }
					
			})
		})
	}
	
	function appendScore(obj,score,y,d){
		if(score == 'NaN' || score == '' ) score = 0;
		for(i=0;i<5;i++){
			if(i<score){
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+y+"");
			}else{
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+d+"");
			}
		}
	}
</script>

<!--导游-->
<div class="w_main">
    <div class="w_guidecon">
        <div class="w_mainl fl">
            <div class="w_guidel">
                <h4><a>热门导游</a><span><a href="${ctx}/guide/more${suffix}">更多&gt;&gt;</a></span></h4>
                <div class="w_food">
                    <dl>
                    	<#list guidePager.itemList as u>
	                        <dd class="guidePagerClass">
	                            <a href="${ctx}/guide/detail-${u.id}${suffix}"><img  width="266" height="159" alt="" /></a>
	                            <div class="w_guidetx">  
	                                <ul>
	                                    <li><a href="${ctx}/guide/detail-${u.id}${suffix}"><img class="imgclass" src="${tagUtils.getFileFullPath(u.user.photo!)}" width="36" height="36" alt="" /></a></li>
	                                    <li>
	                                        <div>
	                                            <a href="${ctx}/guide/detail-${u.id}${suffix}"><i>${u.guideName!}</i></a>
	                                            <span><em>￥</em>${u.price!}<em>/天</em></span>
	                                        </div>
	                                        <div><b>实名</b><b>导游证</b></div>
	                                    </li>
	                                </ul> 
	                            </div>
	                            <p>${u.guideDesc!}</p>
	                            <div class="guidePagerChild"><u>
	                            	<img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
		                            	${u.star!}分</u></div>
	                        </dd>
                        </#list>
                    </dl>                   
                </div>
            </div>
        </div>
        <div class="w_guider fr">    
            <div class="w_tjlx">
                <h3>热门路线<a href="##">更多&gt;&gt;</a></h3>
                <ul>
                    <li>
                        <div class="w_load">
                            <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="295" height="121" alt="" /></a>
                            <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                            <p>出发地：<em>青海飞机场</em></p>
                            <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                            <p>预算费用：<em>3000元</em></p>
                            <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                        </div>
                        <div class="w_load">
                            <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="295" height="121" alt="" /></a>
                            <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                            <p>出发地：<em>青海飞机场</em></p>
                            <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                            <p>预算费用：<em>3000元</em></p>
                            <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="w_ggw"><img src="${ctx}/resources/images/w_guide_pic02.jpg" width="1200" height="120" alt="" /></div>
    <div class="w_guidecon">
        <div class="w-mainr fl">
            <div class="w_hotelcon">
                <h4><a href="${ctx}/news${suffix}">最新资讯</a></h4>
                <div class="w_hotnew">
                    <ul>
                    	<#list newsList as u>
                        	<li><a href="${ctx}/news/detail-${u.id}${suffix}"><em>${u.caption!}</em><i>${tagUtils.formatDate(u.createTime)!}</i></a></li>
                       	</#list>
                    </ul>
                </div>
            </div>
            <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_ypic07.jpg" width="320" height="90" alt="" /></a></div>
            <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_ypic08.jpg" width="320" height="144" alt="" /></a></div>
        </div>
        <div class="w_gdcon fr">
            <div class="w_guidel">
                <h4><a>热门租车</a><span><a href="${ctx}/rcar/more${suffix}">更多&gt;&gt;</a></span></h4>
                <div class="w_food">
                    <dl>
                    	<#list carList as u>
	                        <dd class="carListClass">
	                            <a href="${ctx}/rcar/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_guide_pic03.jpg" width="266" height="159" alt="" /></a>
	                            <div class="w_guidetx">  
	                                <ul>
	                                    <li><a href="${ctx}/rcar/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_guide_toux.png" width="36" height="36" alt="" /></a></li>
	                                    <li>
	                                        <div>
	                                            <a href="${ctx}/rcar/detail${suffix}"><i>${u.rcarName!}</i></a>
	                                            <span><em>￥</em>${u.price!}<em>/天</em></span>
	                                        </div>
	                                        <div><b>实名</b><b>行驶证</b><b>司机</b></div>
	                                    </li>
	                                </ul> 
	                            </div>
	                            <p><u>${u.carModel}</u>${u.rcarDesc}</p>
	                            <div class="carListChild"><u>
	                            	<img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
		                            	${u.star!}分</u></div>
	                        </dd>
                        </#list>
                    </dl>                   
                </div>
            </div>
        </div>
    </div>
</div>
<#include "public/foot.ftl">