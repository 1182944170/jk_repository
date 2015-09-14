<title>导游详情</title>

<#include "../../public/top.ftl">

<script type="text/javascript">
	$(function(){
		var cars ="${guide.photos!}";
		initDetail('detailclass',cars);
	})
	// 取详情图（多个）
	function initDetail(o,arr){
		var len = 0;
		if(arr.indexOf(";")>0){
			len = arr.split(";").length;
			for(i = 0 ; i<len;i++){
			var p = arr.split(";")[i];
			if(p != '' && p != null){
				if(p.indexOf(",")>0){
					p = p.split(",");
					p = p[0];
					p = p.substring(1,p.length);
				}
				$("."+o+"").children().children().eq(i).find("img").removeAttr("src");
				$("."+o+"").children().children().eq(i).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
			}
		}
		}else{
			var p = arr.indexOf(",")>0 ? arr.split(",")[0] : arr;
			$("."+o+"").find("img").removeAttr("src");
			$("."+o+"").find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
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
        <div class="w_gd_topr fr">
        	<div class="w_guidetx">  
                <ul>
                    <li><a><img src="${tagUtils.getFileFullPath(guide.user.photo!)}" width="60" height="60" alt="" /></a></li>
                    <li>
                        <div>
                            <a><i>${guide.guideName!}</i>
                            	<#if guide.sex == 1>
                            		<img src="${ctx}/resources/images/w_sex2.png" />
                            	<#else>
                            		<img src="${ctx}/resources/images/w_sex1.png" />
                            	</#if>
                            </a>
                            <span><em>￥</em>${guide.price!}<em>/天</em></span>
                        </div>
                        <div><b>实名</b><b>导游证</b></div>
                    </li>
                </ul> 
                <p><strong>个人简介：</strong>${guide.guideDesc!}</p>
                <p><strong>从业时间：</strong>${guide.jobTime!}</p>
                <p><strong>导游证号：</strong>${guide.guideNo!}</p>
                <p><strong>服务地区：</strong>${guide.serverArea!}</p>
                <p><strong>联系电话：</strong><i>${guide.telphone!}</i></p>
            </div>
            <div class="w_gdpj fr">
            	<em>整体评价：</em>
                <span>
                	<img src="${ctx}/resources/images/w_pfd.png" alt="" />
                    <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                    <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                    <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                    <img src="${ctx}/resources/images/w_pfd.png" alt="" />
                </span>
            </div>
        </div>
    </div>
	<div class="w_gd_con">
        <ul>
            <li>
                <h6><em>服务标准</em></h6>
                <p>${guide.serverGauge!}</p>
            </li>
         </ul>
    </div>
</div>

<#include "../../public/foot.ftl">