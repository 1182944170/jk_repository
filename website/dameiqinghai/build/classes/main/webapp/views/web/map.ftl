<title>线路</title>

<#include "public/top.ftl">

<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
		<#list guideList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("guideListClass","guideListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list rcarList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("rcarListClass","rcarListChild",str,score);
	})
	
	function init(oclass,childclass,str,score){
		var score = score.split(",");
		var photoArray=str.substring(0,str.length-1);
		$("."+oclass+"").each(function(i){
			var p = photoArray.split(";")[i];
			if(p != '' && p != null){
				if(p.indexOf(",")>0){
					p = p.split(",");
					p = p[0];
					p = p.substring(1,p.length);
				}
				$(this).find("img").removeAttr("src");
				$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
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


<div class="w_main">
    <div class="w_mainl fr">
        <div class="w_xianlu">
        	<div class="w_xianlu_top">
            	<div class="w_xl_topl fl"><a href="${ctx}/mapline/list${suffix}"><img src="${ctx}/resources/images/w_xlu.jpg" width="575" height="293" alt="" /></a></div>
                <div class="w_xl_topr fr">
                	<h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                    <span>出发地：青海飞机场</span>
                    <span>目的地：西宁</span>
                    <span>预算费用：3000元</span>
                    <span>适宜月份：12月-3月</span>
                    <p>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</p>
                </div>
            </div>
            <div class="w_xianlucon">
            	<div class="w_tjlx">
                    <ul>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
                                <h5><a href="${ctx}/mapline/detail${suffix}">西宁三日游玩</a></h5>
                                <p>出发地：<em>青海飞机场</em></p>
                                <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                                <p>预算费用：<em>3000元</em></p>
                                <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                            </div>
                        </li>
                        <li>
                            <div class="w_load">
                                <a href="${ctx}/mapline/detail${suffix}"><img src="${ctx}/resources/images/w_pic01.jpg" width="271" height="121" alt="" /></a>
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
            <div class="w_ys">
            	<a href="#">上一页</a>
                <a href="#" class="w_yscur">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">下一页</a>
            </div>
        </div>        
    </div>    
    <div class="w_mainr fl">
    	<div class="w_dydya">
        	<div class="w_dytj">
                <h4><a href="${ctx}/guide/list${suffix}">推荐导游</a></h4>
                <ul>
                	<#list guideList as u>
	                    <li>
	                        <a href="${ctx}/guide/detail-${u.id}${suffix}" class="guideListClass"><img src="${ctx}/resources/images/w_pic02.jpg" width="65" height="65" alt="" /></a>
	                        <h5><a href="${ctx}/guide/detail-${u.id}${suffix}">${u.guideName!}</a>
								<#if u.sex == 1>
	                        		<img src="${ctx}/resources/images/w_sex2.png" width="10" height="15" alt="" />
	                        	<#else>
	                        		<img src="${ctx}/resources/images/w_sex1.png" width="10" height="15" alt="" />
	                        	</#if></h5>
	                        <p class="guideListChild">
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
	                            <span>${u.star!}分</span>
	                            <em>￥<i>${u.price!}</i>/天</em>
	                        </p>
	                        <div><em>实名</em><em>导游证</em></div>
	                    </li>
                    </#list>
                </ul>
             </div>
        </div>
        <div class="w_ggw"><a href="#"><img src="${ctx}/resources/images/w_ggw01.jpg" width="320" height="120" alt="" /></a></div>
        <div class="w_dydya">
        	<div class="w_dytj">
                <h4><a href="${ctx}/car/list${suffix}">推荐租车</a></h4>
                <ul>
                	<#list rcarList as u>
	                    <li>
	                        <a href="${ctx}/rcar/detail-${u.id}${suffix}" class="rcarListClass"><img src="${ctx}/resources/images/w_dd.jpg" width="65" height="65" alt="" /></a>
	                        <h5><a href="${ctx}/rcar/detail${u.id}${suffix}">${u.rcarName!}</a></h5>
	                        <p class="rcarListChild">
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
	                            <span>${u.star!}分</span>
	                            <em>￥<i>${u.price!}</i>/天</em>
	                        </p>
	                        <p><u>${u.carModel!}</u>${u.info!}</p>
	                    </li>
                    </#list>
                </ul>
             </div>
        </div>
    </div>
</div>

<#include "public/foot.ftl">