<title>同游详情</title>

<#include "../../public/top.ftl">

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
    <div class="w_mainl fl">
        <div class="w_tydetail">
            <h4><a href="#">首页</a>&gt;<a href="${ctx}/way${suffix}">同游</a>&gt;<em>${sameWay.wayTitle!}</em></h4>
            <div class="w_tydts">
            	<div class="w_tydtsl fl">
                	<span><img src="${tagUtils.getFileFullPath(sameWay.user.photo!)}" width="100" height="98" alt="" /></span>
                    <em><i>${sameWay.user.nickName!}</i>
						<#if sameWay.user.sex == 1>
                    		<img src="${ctx}/resources/images/w_sex2.png" width="10" height="15" alt="" />
                    	<#else>
                    		<img src="${ctx}/resources/images/w_sex1.png" width="10" height="15" alt="" />
                    	</#if></em>
                    <p><b>个人简介：</b>${sameWay.user.userDesc!}</p>
                </div>
                <div class="w_tydtsr fl">
                	<h5>${sameWay.wayTitle!}<em>报名中</em></h5>
                    <p><img src="${ctx}/resources/images/w_tyico01.png" alt="" /><em>出发时间：</em>${sameWay.stateTime!}共3天</p>
                    <p><img src="${ctx}/resources/images/tyico02.png" alt="" /><em>出发地点：</em>${sameWay.goAddress!}</p>
                    <p><img src="${ctx}/resources/images/ty_ico03.png" alt="" /><em>出行方式：</em>${sameWay.rentType!}</p>
                    <p><img src="${ctx}/resources/images/ty_ico04.png" alt="" /><em>出行人数：</em>${sameWay.wayNumber!}人</p>
                    <p><img src="${ctx}/resources/images/ty_ico05.png" alt="" /><em>联系电话：</em><i>${sameWay.contact!}</i></p>
                    <#if sessionUser??>
                		<a href="${ctx}/sameway/enter-${sameWay.id}${suffix}?userId=${sessionUser.id}" id="content"><em></em></a>
                    	<#list userList as u> 
                    			<style>
                    				.darks{color: rgb(255, 255, 255); width: 160px; height: 42px; padding: 0px; background: rgb(153, 153, 153)}
                    			</style>
                    			<script>
                    				$(function (){
                    					if(${userListSize!} - ${sameWay.wayNumber!} >= 0){	
                    						$("#content").removeAttr("href");
	                    					$("#content").find("em").text("报名人数已满");
	                    					$("#content").css({color: "red", background: "Gray"});
                    					} else if(${u.id} == ${sessionUser.id}){
	                    					$("#content").removeAttr("href");
	                    					$("#content").find("em").text("已报名");
	                    					$("#content").css({color: "red", background: "Gray"});
                    					} else {
	                    					$("#content").find("em").text("我要报名");
                    					}
                    				})
                    			</script>
                    	</#list>
                    <#else>
                    	<a href="${ctx}/login${suffix}">去登陆</a>
                    </#if>
                </div>
            </div>
            <div class="w_tydtsmid">
            	<h5>已报名人数（${userListSize!}）&nbsp;共<em>${sameWay.wayNumber!}</em>人</h5>
                <script type="text/javascript" src="${ctx}/resources/js/jquery.SuperSlide.2.1.js"></script>
                <div class="ladyScroll">
                    <a class="prev" href="javascript:void(0)"></a>
                    <div class="scrollWrap">
                        <div class="dlList"> 
                        	<#list userList as u>
                                <dl>
                                  <dt><img src="${tagUtils.getFileFullPath(u.photo!)}" width="60" height="60"></dt>
                                  <dd>${u.nickName!}</dd>
                                </dl> 
                            </#list>   
                        </div>
                    </div>
                    <a class="next" href="javascript:void(0)"></a>
            </div>
            <script type="text/javascript">
            jQuery(".ladyScroll").slide({ mainCell:".dlList", effect:"leftLoop",vis:8, autoPlay:true});
            </script>
            </div>
            <div class="w_tydtsxia">
            	<h5>景点大致</h5>
                <p><strong>青海湖：</strong>这里资源十分丰富，许多矿藏储量在全国居于首位。已发现矿产120余种，探明储量的有110种，钾、钠、镁、锂、溴、芒硝、石棉、化工灰岩和硅储量居
全国第一位，其中许多矿产是属于国内外急需的资源。闻名遐迩的柴达木盆地，山川藏珍、戈壁埋矿，素有“聚宝盆”之美誉。其中盐湖有30多个，已探明总储量
700亿吨，单是察尔汗湖的盐就可以从地球到月亮架起一座6米厚，12米宽的盐桥。水能资源是青海能源最大优势，蕴藏量达2165万千瓦，可开发利用的为1800万
千瓦，年发电量770亿度。青海天然草原辽阔，是我国五大牧区之一，可利用草场面积5亿亩，发展畜牧业物质基础雄厚。全省有经济动物400多种，野生植物1000
余种，具有贮藏量大、种类多、用途广、高原特色显著的特点。大部分可开发利用，药用价值极高。旅游资源也相当丰富，有“百鸟的王国”的青海湖鸟岛，“高原
的西双版纳”孟达自然保护区，藏传佛教著名寺院湟中塔尔寺，伊斯兰教西北四大清真寺之一的东关大寺，阿尼玛卿大雪山等，是登山、旅游的好去处海藏咽喉的
日月山和全国最大的人工水库龙羊峡、都兰国际狩猎场、坎布拉森林公园等旅游景点将成为新的经济增长点。</p>
				<span>
                	<img src="${ctx}/resources/images/w_tyxq.jpg" width="860" height="360" alt="" />
                </span>
            </div>
        </div>        
    </div>
    <div class="w_mainr fr">
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

<#include "../../public/foot.ftl">