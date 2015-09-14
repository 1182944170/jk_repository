<title>租车详情</title>

<#include "../../public/top.ftl">

<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
		<#list akinList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("akinListClass","akinListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list elseList as u>
		str +="${u.photos!}"+";";
		</#list>
		init("elseListClass","elseListChild",str,score);
		var cars ="${car.photos!}";
		initDetail('detailclass',cars);
	})
	// 取详情图（多个）
	function initDetail(o,c){
		$("."+o+"").children().children().each(function(i){
				if(c != '' && c != null){
					if(c.indexOf(",")>0){
					var	cc = c.split(",");
					if(i < cc.length){
						var	p=cc[i].substring(1,cc[i].length);
					}
					}
					$(this).find("img").removeAttr("src");
					$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
				}
		})
	}
	
	// 取主图
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

<!--导游-->
<div class="w_main">
	<div class="w_gd_top">
    	<div class="w_gd_topl fl">
        	<script type="text/javascript" src="${ctx}/resources/js/jquery.SuperSlide.2.1.js"></script>
        	<div id="w_slidebox" class="w_slidebox">
                <div class="bd detailclass">
                    <ul>
                        <li><img src="${ctx}/resources/images/w_gdpic01.png" width="584" height="306" alt="" /></li>
                        <li><img src="${ctx}/resources/images/w_gdpic01.png" width="584" height="306" alt="" /></li>
                        <li><img src="${ctx}/resources/images/w_gdpic01.png" width="584" height="306" alt="" /></li>
                        <li><img src="${ctx}/resources/images/w_gdpic01.png" width="584" height="306" alt="" /></li>
                        <li><img src="${ctx}/resources/images/w_gdpic01.png" width="584" height="306" alt="" /></li>
                    </ul>
                </div>
                <a class="prev" href="javascript:void(0)"></a>
                <a class="next" href="javascript:void(0)"></a>    
            </div>    
            <script type="text/javascript">
            jQuery(".w_slidebox").slide({mainCell:".bd ul",autoPlay:true});
            </script>
        </div>
        <div class="w_car_topr fr">        	
            <p><span>车主：<em>${car.rcarName!}</em></span><span><i>实名已认证</i><b>驾驶证</b></span></p>
            <p>车型：<em>${car.carModel!}</em></p>
            <p>类型：<em>${car.carType!}</em></p>
            <p>人数：<em>${car.bearNum!}</em></p>
            <p>简介：<em>${car.rcarDesc!}</em></p>
            <div class="w_car_con">
            	<div class="w_car_img fl"><img src="${tagUtils.getFileFullPath(car.user.photo!)}" width="78" height="78" alt="" /></div>
                <div class="w_car_ny fr">
                	<ul>
                    	<li>
                        	<h6>联系电话</h6>
                            <em>${car.telphone!}</em>
                        </li>
                        <li>
                        	<h6>价格</h6>
                            <p>￥<i>${car.price!}</i>/天</p>
                        </li>
                        <li>
                        	<h6>好评率</h6>
                            <span>
                            	<img src="${ctx}/resources/images/w_pfd.png" alt="" />
                                <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                                <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                                <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                                <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                            </span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
	<div class="w_gd_con">
        <ul>
            <li>
                <h6><em>租车事项</em></h6>
                <p>${car.rcarItems!}</p>
            </li>
            <li>
                <h6><em>其他型号</em></h6>
                <div class="w_carpic">
                	<div class="w_gdcon">
                        <div class="w_food">
                            <dl>
                            	<#list elseList as u>
	                                <dd class="elseListClass">
	                                    <a href="${ctx}/rcar/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_guide_pic03.jpg" width="272" height="165" alt="" /></a>
	                                    <p><u>${u.carModel!}</u>${u.carType!}</p>
	                                    <div class="elseListChild"><u><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pfh.png" />4分</u><span><em>￥</em>${u.price!}<em>/天</em></span></div>
	                                </dd>
                                </#list>
                            </dl>                   
                        </div>
                    </div>
                </div>
            </li>
         </ul>
    </div>
    <div class="w_car_bottom">
    	<h6><em>相似车型</em></h6>
        <div class="w_carpic">
            <div class="w_gdcon">
                <div class="w_food">
                    <dl>
                        <#list akinList as u>
                            <dd class="akinListClass">
                                <a href="${ctx}/rcar/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_guide_pic03.jpg" width="272" height="165" alt="" /></a>
                                <p><u>${u.carModel!}</u>${u.carType!}</p>
                                <div class="akinListChild"><u><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pfh.png" />4分</u><span><em>￥</em>${u.price!}<em>/天</em></span></div>
                            </dd>
                        </#list>                           
                    </dl>                   
                </div>
            </div>
        </div>
    </div>
</div>


<#include "../../public/foot.ftl">