<title>管理导游</title>


<#include "../../public/top.ftl">
<script type="text/javascript">
	//解析管理图片 每个图片div加上 class="pclass"
	$(function(){
		var str="";
		<#list guidePager.itemList as u>
			str +="${u.photos!}"+";";
		</#list>
		var photoArray=str.substring(0,str.length-1);
		$(".pclass").each(function(i){
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
		})
	})
</script>

<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                <#include "../public.ftl">                      
            </div>
            <div class="w_personr fr">
                <div class="w_gldy">
                    <div class="w_perdingd">
                        <dl>
                            <dt>
                                <div class="dd_02 fl">导游详情</div>
                                <div class="dd_03 fl">服务地区</div>
                                <div class="dd_04 fl">出行价格</div>
                                <div class="dd_05 fl">发布日期</div>
                                <div class="dd_06 fl">操作</div>
                            </dt>
                            <#list guidePager.itemList as u>
	                            <dd>
	                                <div class="dd_02 fl pclass">
	                                    <span><a href="#"><img src="${ctx}/resources/images/w_guide_pic01.jpg" alt="" /></a></span>
	                                    <span>
	                                        <h6><a href="#">${u.guideName!}</a></h6>
	                                        <p>${u.guideDesc!}</p>
	                                        <em>联系电话：${u.telphone!}</em>
	                                    </span>
	                                </div>
	                                <div class="dd_03 fl">${u.serverArea!}</div>
	                                <div class="dd_04 fl">${u.price!}</div>
	                                <div class="dd_05 fl">${tagUtils.formatDate(u.createTime)!}</div>
	                                <div class="dd_06 fl">
	                                	<#if u.issueState == 99>
	                                		<span> 待审核 </span>
	                                	<#elseif u.issueState == 2>
	                                		<span> 拒绝发布 </span>
	                                	<#else>
		                                	<span>
		                                        <a href="${ctx}/guide/edit-${u.id}${suffix}">编辑</a>
		                                        <a href="${ctx}/guide/delete-${u.id}${suffix}"><img src="${ctx}/resources/images/gl_shanh.png" /></a>
		                                    </span>
	                                	</#if>
	                                </div>
	                            </dd>
                            </#list>
                        </dl>             
                    </div>
                    
                    <div class="w_ys">
                    	<script>
	                    	new showPages('${ctx}/guide/direct${suffix}?userId=${sessionUser.id}','${guidePager.totalPages?c}','${guidePager.totalCount?c}','${guidePager.pagerWebString}').printBaseHtml();
						</script>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../public/foot.ftl">