<title>首页</title>

<#include "public/top.ftl">
<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		initarea('spotsClass','');
		initarea('foodClass','foodListClass');
		initarea('hotelClass','hotelListClass');
		initarea('artClass','artListClass');
		var score = "";
		var str ='' ;
		<!--美食 -->
		<#list foodList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("foodListClass","foodListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list hotelList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("hotelListClass","hotelListChild",str,score);

		<!--工艺 -->
		str = (str=='') ? '' : str='';
		<#list artList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("artListClass","artListChild",str,score);

		<!--导游 -->
		str = (str=='') ? '' : str='';
		<#list guideList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("guideListClass","guideListChild",str,score);
		
		<!--租车 -->
		str = (str=='') ? '' : str='';
		<#list rcarList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("rcarListClass","rcarListChild",str,score);
		
		<!--同游 -->
		str = (str=='') ? '' : str='';
		<#list wayList as u>
			str +="${u.photo!}"+";";
		</#list>
		init("wayListClass","wayListChild",str,score);
	})
	<#--init(最上级节点,评分星级节点,图片字符串,评分星级0~5) 解析首页上的各个列表页面 -->
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
	<#--appendScore(当前图片的父节点,星星数量,黄色星,灰色星) 解析首页上的评分星级img -->
	function appendScore(obj,score,y,d){
		var newscore=3;
		if(!isNaN(score) && score != "" ){
			 newscore = score;
		}
		for(i=0;i<5;i++){
			if(i<newscore){
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+y+"");
			}else{
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+d+"");
			}
		}
	}
	<#--initarea(className) 解析设置为热门的地区 -->
	function initarea(c,p){
		var areaIds = "${hotareaList.areaIds!}";
		var areaNames = "${hotareaList.areaName!}";
		var idsarr = areaIds.split(",");
		var namearr = areaNames.split(",");
		for(i = 0 ; i < idsarr.length; i++){
			$("."+c+"").append("<li onclick=\"searchByCode(this,'"+c+"','"+p+"');\"><input type=\"hidden\" value=\""+idsarr[i]+"\" \/>"+namearr[i]+"<\/li>")
		}
	}
	<#--searchByCode(this对象,className) 解析设置为热门的地区 -->
	function searchByCode(o,c,p){
		$("."+c+"").children().each(function (i){
			if($(this).find("input").val()==$(o).find("input").val()){
				o.className = "on";
				<!-- 被选中的citycode,所属页面 传回后台过滤 -->
				var citycode = $(this).find("input").val();
				search(citycode,c,p);
				
			}else{
				$(this).removeAttr("class");
			}
		})
	}
	function search(citycode,c,p){
		<!-- 重新加载美食 -->
		<!--citycode 用作比较 -->
		<!-- c 用于找出对应的 foodListClass-->
		$("."+p+"").each(function(i){
		<!-- 找出foodListClass 循环里的u.shops.areaCode -->
			var dbcode = $(this).find("input[type='hidden']").val();
			if('hot' == citycode){
				$("."+p+"").each(function(i){
    					$(this).show();
        		});
			}else{
			<!-- u.shops.areaCode 到后台 找出city 并返回页面 -->
				$.ajax({
					async : false,
			        cache : false,
			        type : 'POST',
			        dataType :JSON,
			        url : "${ctx}/food/search/?citycode="+citycode+"&dbcode="+dbcode,
			        error : function(data){
				<!-- citycode == city 就不操作 否则 foodListClass当前的元素块hide()-->
		        		var str = data.responseText;
	        			if(str.indexOf("true")>0){
		        			$("."+p+"").each(function(j){
		        				if(i==j){
		        					$(this).show();
		        				}
	        				})
	        			}else{
		        			$("."+p+"").each(function(j){
		        				if(i==j){
		        					$(this).hide();
		        				}
		        			})
	        			}
			        }
				});
			}
		})
		
	}
</script>


<div class="w_focus">
    <ul class="w_pic">
    	<#list slideList as u>
            <li><img src="${tagUtils.getFileFullPath(u.icon!)}"/></li>
        </#list>
    </ul>
    <a class="prev" href="javascript:void(0)"></a>
    <a class="next" href="javascript:void(0)"></a>
    <ul class="hd">
    	<#list slideList as u>
            <li></li>
        </#list>
    </ul>
    <script type="text/javascript">
		/*鼠标移过，左右按钮显示*/
		jQuery(".w_focus").hover(function(){ jQuery(this).find(".prev,.next").stop(true,true).fadeTo("show",0.2) },function(){ jQuery(this).find(".prev,.next").fadeOut() });
		/*SuperSlide图片切换*/
		jQuery(".w_focus").slide({ mainCell:".w_pic",effect:"fold", autoPlay:true, delayTime:600, trigger:"click"});
	</script>
</div>
<!--内容-->
<div class="w_main">
    <div class="w_top01">
        <#--线路页面引入 -->
        <!--线路-->
     	<#include "mapline/more.ftl">
         <#--推荐位引入 -->
        <div class="w_tuij fr">
            <!--推荐导游-->
	     	<#include "person/guide/recommend.ftl">
             <!--租车-->
	     	<#include "person/rcar/recommend.ftl">
        </div>
    </div>
    <!--定制同游-->
    <div class="w_dzlx">
    	<a href="##">定制路线，一起同游</a>
    </div>
    
     <#--同游页面引入 -->
    <!--同游-->
    <#include "person/sameway/more.ftl">
     <#--画册引入 -->
    <!--画册-->
    <#include "photo/more.ftl">
     <#--景点引入 -->
    <!--景点玩乐-->
    <#include "spots/more.ftl">
    
    <!--广告位-->
    <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_pic10.jpg" width="1200" height="90" alt="" /></a></div>
    
    <#--商品页面引入 -->
    <!--特色美食-->
    <#include "person/supplier/dfood/more.ftl">
    <!--酒店住宿-->
    <#include "person/supplier/dhotel/more.ftl">
    <!--广告位-->
    <div class="w_ggw"><a href="${ctx}/hotel${suffix}"><img src="${ctx}/resources/images/w_pic16.jpg" width="1200" height="90" alt="" /></a></div>
    <!--手工艺人-->
    <#include "person/supplier/dart/more.ftl">
    
    <script type="text/javascript">jQuery(".w_tab").slide();</script>
    <!--广告位-->
    <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_pic10.jpg" width="1200" height="90" alt="" /></a></div>
    
    <#--游记引入 -->
    <!--游记-->
   <#--
    <#include "person/travel/more.ftl">
   -->
</div>

<#include "public/foot.ftl">