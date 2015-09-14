<title>我的画册</title>

<#include "../public/top.ftl">
<!--内容-->
<script type="text/javascript">
	$(function(){
		initList('detailclass');
	})
	function initList(oclass){
		var str="";
		<#list photoBookPager.itemList as u>
			str +="${u.photos}"+";";
		</#list>
		var photoArray=str.substring(0,str.length-1);
		init(oclass,photoArray);
		
	}
	function init(o,arr){
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
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                 <#include "../person/public.ftl">             
            </div>
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>我的画册</em></h5>
                    <div class="w_ppics detailclass">
                    	<ul>
                    	<#list photoBookPager.itemList as u >
                        	<li>
                            	<a href="${ctx}/photo/detail-${u.id}${suffix}"><img src="" width="286" height="172" /></a>
                                <a href="${ctx}/photo/detail-${u.id}${suffix}"><em>${u.name}</em></a>
                                 <a href="${ctx}/photo/del-${u.id}${suffix}"><input type="button" value="删除" style="color:red;margin-left:90%"/></a>
                            </li>
                        </#list>
                        </ul>
                    </div>
                    
                    <!--页数-->
		            <div class="w_ys">
		            	<script>
		                	new showPages('${ctx}/photo/direct${suffix}','${photoBookPager.totalPages?c}','${photoBookPager.totalCount?c}','${photoBookPager.pagerWebString}').printBaseHtml();
						</script>
		            </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../public/foot.ftl">