<title>${spots.photoName!}画册</title>

<#include "../public/top.ftl">
<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
		<#list spotsPhotoPager.itemList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("spotsPhotoPagerClass","spotsPhotoPagerChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list commendPhotoList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("commendPhotoListClass","commendPhotoListChild",str,score);
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
<!--资讯-->
<div class="w_main">
    <!-- ad -->
    <div class="mm_zixun_ad">
        <a href="##"><img src="${ctx}/resources/images/w_ppic01.jpg" width="1200" height="300" alt="" /></a>
    </div>
    <div class="w_huace">
    	<div class="w_huacel fl">
            <div class="mm_zixun_left_title">
                <a href="${ctx}/photo/list-${spots.id}${suffix}">${spots.spotsName!}</a>
            </div>
            <div class="w_huace_conl">
            	<ul>
            		<#list spotsPhotoPager.itemList as u>
	                	<li>
	                    	<div class="w_hctu">
	                        	<a href="${ctx}/photo/detail-${u.id}${suffix}" class="spotsPhotoPagerClass"><img src="${ctx}/resources/images/w_hcpic02.jpg" width="299" height="179" alt="" /></a>
	                            <div class="w_hctx">
	                                <div><img src="${tagUtils.getFileFullPath(u.user.photo)!}" width="70" height="70" alt="" /></div>
	                                <div>
	                                    <span><u>${u.user.nickName!}</u><a href="${ctx}/photo/detail-${u.id}${suffix}">128图</a></span>
	                                    <p>【${u.spots.spotsName!}】${u.mome!}</p>   
	                                    <i>${tagUtils.formatDate(u.createTime)!}</i>                             
	                                </div>                            
	                            </div>
	                        </div>                        
	                    </li>
                    </#list>
                </ul>
            </div>
            <div class="w_ys">
                <script>
                	new showPages('${ctx}/photo/list-${spots.id}${suffix}','${spotsPhotoPager.totalPages?c}','${spotsPhotoPager.totalCount?c}','${spotsPhotoPager.pagerWebString}').printBaseHtml();
				</script>
            </div>
        </div>
        <div class="w_huacer fr">
            <div class="mm_zixun_left_title">
                <a href="${ctx}/photoBook${suffix}">热门画册</a>
            </div>
            <div class="w_rmhc">
            	<#list commendPhotoList as u>
	                <div class="w_hctua">
						<a href="${ctx}/photo/detail-${u.id}${suffix}" class="commendPhotoListClass"><img src="${ctx}/resources/images/w_hcpic02.jpg" width="224" height="134" alt="" /></a>
	                    <div class="w_hctxa">
	                        <div><img src="${tagUtils.getFileFullPath(u.user.photo!)}" width="40" height="40" alt="" /></div>
	                        <div>
	                            <span>${u.user.nickName!}</span> 
	                            <em>${tagUtils.formatDate(u.createTime)!}<i>10图</i></em>                         
	                        </div>                            
	                    </div>
	                </div>
				</#list>
                <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_hcpic07.jpg" width="224" height="261" alt="" /></a></div>
                <div class="w_ggw"><a href="##"><img src="${ctx}/resources/images/w_hcpic08.jpg" width="224" height="156" alt="" /></a></div>
            </div>
        </div>
    </div>
</div>
<#include "../public/foot.ftl">