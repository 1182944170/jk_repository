<title>更多租车</title>

<#include "../../public/top.ftl">

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
		<#list rcarPagerOrder.itemList as u>
			s1 +="${u.photos!}"+";";
		    s2 +="${u.user.photo!}"+";";
		</#list>
		p1=s1.substring(0,s1.length-1);
		p2=s2.substring(0,s2.length-1);
		initphoto(p1,p2,'rcarPagerClass','rcarPagerChild',score);
		
		s1 = (s1=='') ? '' : s1='';
		s2 = (s2=='') ? '' : s2='';
		p1 = (p1=='') ? '' : p1='';
		p2 = (p2=='') ? '' : p2='';
		<#list guideList as u>
			s1 +="${u.photos!}"+";";
		    s2 +="${u.user.photo!}"+";";
		</#list>
		p1=s1.substring(0,s1.length-1);
		p2=s2.substring(0,s2.length-1);
		initphoto(p1,p2,'guideListClass','guideListChild',score);
		
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
<div class="w_main">
    <div class="w_guidecon">
        <div class="w_mainl fl">
            <div class="w_guidel">
                <!--筛选-->
                <div class="w_select">
                    <a href="${ctx}/rcar/more${suffix}" class="w_pcur">默认</a>
                    <a href="${ctx}/rcar/more-price${suffix}">价格<img src="${ctx}/resources/images/w_jx01.png" alt="" /></a>
                    <a href="${ctx}/rcar/more-time${suffix}">发布时间<img src="${ctx}/resources/images/w_jx02.png" alt="" /></a>
                    <div class="w_xiala" id="w_xiala">
                        <p class="set">地区</p>
                        <ul class="new">
                            <li>西宁</li>
                            <li>西宁</li>
                            <li>西宁</li>
                        </ul>
                    </div>   
                    <!--下拉-->
                            
                </div>
                <div class="w_gdcon">
                    <div class="w_food">
                        <dl>
                            <#list rcarPagerOrder.itemList as u>
		                        <dd class="carClass">
		                            <a href="${ctx}/rcar/detail-${u.id}${suffix}" class="rcarPagerClass"><img src="${ctx}/resources/images/w_guide_pic03.jpg" width="266" height="159" alt="" /></a>
		                            <div class="w_guidetx">  
		                                <ul>
		                                    <li><a href="${ctx}/rcar/detail-${u.id}${suffix}"><img src="${tagUtils.getFileFullPath(u.user.photo!)}" width="36" height="36" alt="" /></a></li>
		                                    <li>
		                                        <div>
		                                            <a href="${ctx}/rcar/detail-${u.id}${suffix}"><i>${u.rcarName!}</i></a>
		                                            <span><em>￥</em>${u.price!}<em>/天</em></span>
		                                        </div>
		                                        <div><b>实名</b><b>行驶证</b><b>司机</b></div>
		                                    </li>
		                                </ul> 
		                            </div>
		                            <p><u>${u.carModel!}</u>${u.rcarDesc!}</p>
		                            <div class="rcarPagerChild"><u><img src="${ctx}/resources/images/w_pf.png" alt="" />
	                                <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                                <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                                <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                                <img src="${ctx}/resources/images/w_pfh.png" alt="" />${u.star!}分</u></div>
		                        </dd>
	                        </#list>
                        </dl>                   
                    </div>
                </div>
            </div>
            <!--页数-->
            <div class="w_ys">
            	<script>
                	new showPages('${ctx}/rcar/more${suffix}','${rcarPagerOrder.totalPages?c}','${rcarPagerOrder.totalCount?c}','${rcarPagerOrder.pagerWebString}').printBaseHtml();
				</script>
            </div>
        </div>
        <div class="w_guider fr">    
            <div class="w_dydya">
                <div class="w_dytj">
                    <h4><a href="##">推荐导游</a></h4>
                    <ul>
                        <#list guideList as u>
		                    <li>
		                        <a href="${ctx}/guide/detail-${u.id}${suffix}" class="guideListClass"><img src="${ctx}/resources/images/w_pic02.jpg" width="50" height="50" alt="" /></a>
		                        <h5><a href="${ctx}/guide/detail-${u.id}${suffix}">${u.guideName!}</a>
		                        	<#if u.sex == 1>
		                        		<img src="${ctx}/resources/images/w_sex2.png" width="10" height="15" alt="" />
		                        	<#else>
		                        		<img src="${ctx}/resources/images/w_sex1.png" width="10" height="15" alt="" />
		                        	</#if>
		                        	<em>￥<i>${u.price!}</i>/天</em></h5>
		                        <p class="guideListChild">
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
		                            <span>5分</span>
		                        </p>
		                    </li>
	                    </#list>
                    </ul>
                 </div>
            </div>
            <div class="w_tjlx">
                <h3>热门路线<a href="##">更多&gt;&gt;</a></h3>
                <ul>
                    <li>
                        <div class="w_load">
                            <a href="##"><img src="${ctx}/resources/images/w_pic01.jpg" width="295" height="121" alt="" /></a>
                            <h5><a href="##">西宁三日游玩</a></h5>
                            <p>出发地：<em>青海飞机场</em></p>
                            <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                            <p>预算费用：<em>3000元</em></p>
                            <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                        </div>
                        <div class="w_load">
                            <a href="##"><img src="${ctx}/resources/images/w_pic01.jpg" width="295" height="121" alt="" /></a>
                            <h5><a href="##">西宁三日游玩</a></h5>
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
</div>


<#include "../../public/foot.ftl">