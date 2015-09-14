
<title>酒店</title>

<#include "public/top.ftl">

<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
		<#list foodList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("foodListClass","foodListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list hotelList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("hotelListClass","hotelListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list hotelPager.itemList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("hotelPagerClass","hotelPagerChild",str,score);
		
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
	<div class="w_mainl fl">
    	<div class="w_ban"><a href="##"><img src="${ctx}/resources/images/w_hpic01.jpg" width="858" height="235" alt="" /></a></div>
        <div class="w_people">
        	<!--特价酒店-->
            <div class="w_hotelcon">
            	<h4><em>特价酒店</em></h4>
                <div class="w_food">
                	<dl>
                		<#list hotelList as u>
                			<dd class="hotelListClass">
	                        	<a href="${ctx}/hotel/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_hpic03.jpg" width="204" height="139" alt="" /></a>
	                            <h5><a href="${ctx}/hotel/detail-${u.id}${suffix}">${u.name!}</a></h5>
	                            <p>${u.info!}</p>
	                            <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
	                            <div class="hotelListChild"> <u>
	                            	<img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
		                            	${u.star!}分</u><i>售出302</i></div>
	                        </dd>
                		</#list>
                    </dl>                   
                </div>
            </div>
        	<!--筛选-->
            <div class="w_hotelsx">
                <div class="w_select">
                    <a href="##" class="w_pcur">默认</a>
                    <a href="##">价格<img src="${ctx}/resources/images/w_jx01.png" alt="" /></a>
                    <a href="##">发布时间<img src="${ctx}/resources/images/w_jx02.png" alt="" /></a>
                    <a href="##">综合排序<img src="${ctx}/resources/images/w_jx02.png" alt="" /></a>
                    <div class="w_xiala" id="w_xiala">
                        <p class="set">地区</p>
                        <ul class="new">
                            <li>西宁</li>
                            <li>西宁</li>
                            <li>西宁</li>
                        </ul>
                    </div>   
                </div>
                <!--下拉-->
                <script type="text/javascript">
				$(function(){
				
					$(".w_xiala p").click(function(){
						var ul=$(".new");
						if(ul.css("display")=="none"){
							ul.slideDown();
						}else{
							ul.slideUp();
						}
					});
					
					$(".set").click(function(){
						var _name = $(this).attr("name");
						if( $("[name="+_name+"]").length > 1 ){
							$("[name="+_name+"]").removeClass("select");
							$(this).addClass("select");
						} else {
							if( $(this).hasClass("select") ){
								$(this).removeClass("select");
							} else {
								$(this).addClass("select");
							}
						}
					});
					
					$(".w_xiala li").click(function(){
						var li=$(this).text();
						$(".sw_xiala p").html(li);
						$(".new").hide();
						/*$(".set").css({background:'none'});*/
						$("p").removeClass("select") ;   
					});
				})
				</script>             
            </div>      
            <!--商品-->      
            <div class="w_food">
            	<dl>
            		<#list hotelPager.itemList as u>
            			<dd class="hotelPagerClass">
                        	<a href="${ctx}/hotel/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_hpic04.jpg" width="200" height="148" alt="" /></a>
                            <h5><a href="${ctx}/hotel/detail-${u.id}${suffix}">${u.shops.shopName!}</a></h5>
                            <p>${u.name!}</p>
                            <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
                            <div class="hotelPagerChild"><u>
	                            	<img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
		                            	${u.star!}分</u><i>售出302</i></div>
                        </dd>
            		</#list>
                </dl>                   
            </div>
            <!--页数-->
            <div class="w_ys">
            	<script>
                	new showPages('${ctx}/hotel${suffix}','${hotelPager.totalPages?c}','${hotelPager.totalCount?c}','${hotelPager.pagerWebString}').printBaseHtml();
				</script>
            </div>
        </div>
    </div>
    <div class="w-mainr fr">
    	<div class="w_hotelcon">
        	<h4><a href="${ctx}/food${suffix}">热门美食</a></h4>
            <div class="w_dajia">
            	<ul>
            		<#list foodList as u>
	            		<li class="foodListClass">
	                   		<a href="${ctx}/food/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_hpic02.jpg" width="100" height="76" alt="" /></a>
	                    	<h6><a href="${ctx}/food/detail-${u.id}${suffix}">${u.name!}</a></h6>
	                        <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
	                        <div class="foodListChild"><u>
	                            	<img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
		                            	${u.star!}分</u><i>售出302</i></div>
	                    </li>
            		</#list>
                </ul>
            </div>
        </div>
        <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_ypic03.jpg" width="320" height="120" alt="" /></a></div>
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
        <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_ypic06.jpg" width="320" height="90" alt="" /></a></div>
        <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_ypic07.jpg" width="320" height="90" alt="" /></a></div>
        <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_ypic08.jpg" width="320" height="144" alt="" /></a></div>
    </div>
</div>

<#include "public/foot.ftl">