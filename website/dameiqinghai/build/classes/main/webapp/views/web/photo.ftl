<title>画册</title>

<#include "public/top.ftl">

<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
		<#list photoBookList1 as u>
			str +="${u.photos!}"+";";
		</#list>
		init("spotsListClass1","spotsListChild1",str,score);
		
		str = (str=='') ? '' : str='';
		<#list photoBookList2 as u>
			str +="${u.photos!}"+";";
		</#list>
		init("spotsListClass2","spotsListChild2",str,score);
		
		str = (str=='') ? '' : str='';
		<#list photoBookList3 as u>
			str +="${u.photos!}"+";";
		</#list>
		init("spotsListClass3","spotsListChild3",str,score);
		
		
		str = (str=='') ? '' : str='';
		<#list spotsPhotoPager.itemList as u>
			<@dameiqinghai cmd="get_spots_photos_list" spotsId="${u.spots.id}" isCommend="1" pagerString="1_" pageSize="8">
				<#list p_pager.itemList as u>
					str +="${u.photos!}"+";";
				</#list>
			</@dameiqinghai>
		</#list>
		init("p_pagerClass","p_pagerChild",str,score);
		
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
    <div class="w_picnycon">
        <div class="mm_zixun_left_title">
            <a>画册</a>
        </div>  
        <!--图片放大-->    
        <script type="text/javascript" src="${ctx}/resources/js/jquery.zoomImgRollover.js"></script>
		<script type="text/javascript">
        $(document).ready(function() {
            $(".testimg").zoomImgRollover();
        });
        </script>
        <div class="w_demo">
        	<#list photoBookList1 as u>
            	<a href="${ctx}/photo/detail-${u.id}${suffix}" class="spotsListClass1">
            		<img class="testimg" src="${ctx}/resources/images/w_ppic02.jpg" width="210" height="244" alt="" border="0">
            		<em>${u.spots.spotsName!}</em>
            	</a>
            </#list>
            <#list photoBookList2 as u>
            	<a href="${ctx}/photo/detail-${u.id}${suffix}" class="spotsListClass2">
            		<img class="testimg" src="images/w_ppic03.jpg" width="360" height="244" alt="" border="0">
            		<i>${u.spots.spotsName!}</i>
            	</a>
            </#list>
            
            <#list photoBookList3 as u>
            	<a href="${ctx}/photo/detail-${u.id}${suffix}" class="spotsListClass3">
            		<img class="testimg" src="${ctx}/resources/images/w_ppic02.jpg" width="210" height="244" alt="" border="0">
            		<em>${u.spots.spotsName!}</em>
            	</a>
            </#list>
        </div>
        <div class="w_pictp">
        	<#list spotsPhotoPager.itemList as u>
        	<dl>
        		<dt>
        			<a href="${ctx}/photo/list-${u.spots.id}${suffix}">
        				<img src="${tagUtils.getFileFullPath(u.cover!)}" width="378" height="380" alt="" />
        			</a>
        			<a href="${ctx}/photo/list${suffix}"><em>${u.photoName!}</em></a>
        		</dt>
				<@dameiqinghai cmd="get_spots_photos_list" spotsId="${u.spots.id}" isCommend="1" pagerString="1_" pageSize="8">
					<#list p_pager.itemList as u>
						<dd>
							<a href="${ctx}/photo/detail-${u.id}${suffix}" class="p_pagerClass">
								<img src="${ctx}/resources/images/w_ppic05.png" width="185" height="180" alt="" />
							</a>
							<a href="${ctx}/photo/detail-${u.id}${suffix}"><em>${u.name!}</em></a>
						</dd>
					</#list>
				</@dameiqinghai>
			</dl>
			</#list>
        </div>
        <div class="w_ys">
            <script>
            	new showPages('${ctx}/photo${suffix}','${spotsPhotoPager.totalPages?c}','${spotsPhotoPager.totalCount?c}','${spotsPhotoPager.pagerWebString}').printBaseHtml();
			</script>
        </div>
    </div>        
</div>

<#include "public/foot.ftl">