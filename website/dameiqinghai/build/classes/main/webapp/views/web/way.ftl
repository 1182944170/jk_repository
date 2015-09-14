<title>同游</title>

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
		
		str = (str=='') ? '' : str='';
		<#list wayPager.itemList as u>
			str +="${u.photo!}"+";";
		</#list>
		init("wayPagerClass","wayPagerChild",str,score);
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
    <!-- ad -->
    <div class="mm_zixun_ad">
        <a href=""><img src="${ctx}/resources/images/mm_ad02.jpg"></a>
    </div>   
    <div class="w_mainl fl">
        <div class="w_tongyou">
            <div class="mm_zixun_left_title">
                <a>同游</a>
            </div>
            <div class="w_tymid">
                <#list wayPager.itemList as u>
		    		<div class="w_tycon">
			        	<div class="w_tycontu">
			            	<a href="${ctx}/sameway/detail-${u.id}${suffix}" class="wayPagerClass"><img src="${ctx}/resources/images/w_pic05.jpg" width="283" height="188" alt="" /></a>
			                <div class="w_tucon">
			                	<p>15<em>天</em></p>
			                    <span><em>${tagUtils.formatDate(u.createTime)!}</em><i>${u.wayTitle!}</i></span>
			                </div>
			            </div>
			            <div class="w_zicon">
			                <p><a href="${ctx}/sameway/detail-${u.id}${suffix}">${u.toAddress!}</a></p>
			                <span><img src="${tagUtils.getFileFullPath(u.user.photo)!}" width="28" height="29" alt="" /><em>${u.user.nickName}</em><a href="##">我要报名</a></span>
			            </div>
		        	</div>
		    	</#list>
            </div>
            <div class="w_ys">
            	<script>
                	new showPages('${ctx}/way${suffix}','${wayPager.totalPages?c}','${wayPager.totalCount?c}','${wayPager.pagerWebString}').printBaseHtml();
				</script>
            </div>
        </div>        
    </div>    
    <div class="w_mainr fr">
    	<div class="w_dydya">
        	<div class="w_dytj">
                <h4><a href="${ctx}/guide/detail${suffix}">推荐导游</a></h4>
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
                <h4><a href="${ctx}/car/detail${suffix}">推荐租车</a></h4>
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