<title>我发布的同游</title>

<#include "../../public/top.ftl">
<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
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
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                <#include "../public.ftl">
            </div>
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>我发布的</em></h5>
                    <div class="w_tymid">
                    	<#list wayPager.itemList as u>
	                        <div class="w_tycon">
	                            <div class="w_tycontu">
	                                <a href="${ctx}/sameway/detail-${u.id}${suffix}" class="wayPagerClass"><img src="${ctx}/resources/images/w_pic05.jpg" width="225" height="150" alt="" /></a>
	                                <div class="w_tucon">
	                                    <p>${u.wayNumber!}<em>天</em></p>
	                                    <span><em>${tagUtils.formatDate(u.createTime)!}</em><i>${u.wayTitle!}</i></span>
	                                </div>
	                            </div>
	                            <div class="w_zicon">
	                                <p><a href="${ctx}/sameway/detail-${u.id}${suffix}">${u.toAddress!}</a></p>
	                                <span><img src="${tagUtils.getFileFullPath(u.user.photo!)}" width="28" height="29" alt="" /><em>${u.user.nickName!}</em><a href="${ctx}/sameway/detail${suffix}">${u.enterNumber!}人报名</a></span>
	                            </div>
                        	</div>
                        </#list>
                    </div>
                    <!--页数-->
                    <div class="w_ys">
                        <script>
	                    	new showPages('${ctx}/sameway/direct${suffix}','${wayPager.totalPages?c}','${wayPager.totalCount?c}','${wayPager.pagerWebString}').printBaseHtml();
						</script>
                    </div>  
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../public/foot.ftl">