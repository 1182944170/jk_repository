<title>编辑酒店</title>

<#include "../../../public/top.ftl">
<script type="text/javascript">
	function checkSub(){
		var names = "name,酒店名称;info,酒店简介;oldPrice,酒店原价;discount,酒店折扣;price,酒店现价;saleTime,运营时间;telphone,联系电话;validityTime,有效期;notice,须知";
		return checkCommit(names);		
	}
	
	//每个图片div加上 class="pclass"
	$(function(){
		var img = '${hotel.photos!}';
		initAnalyse(img);
	})
	function initAnalyse(imageObj){
		var photos = imageObj;
		if(photos != '' && photos != null){
			if(photos.indexOf(",")>0){
				var p = photos.split(",");
				$(".pclass").each(function (i){
					if(p.length-1>=i){
						$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p[i]+"')}");
						$(this).find("input").attr("value",p[i]);
					}else{
						$(this).find("img").eq(i).attr("src","${ctx}/resources/images/w_jia.jpg");
					}
				});
		}else{
			$(".pclass").find("img").first().attr("src","${tagUtils.getFileFullPath('"+photos+"')}");
			$(".pclass").find("input").first().attr("value",photos);
		}
	}
}
</script>
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                <#include "../../public.ftl">                 
            </div>
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>编辑信息</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/hotel/dosave${suffix}" id="names" name-"names" method="POST" enctype="multipart/form-data" >
            	        	<input type="hidden" name="id" id="id" value="${hotel.id!}" />
            	        	<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
            	        	<input type="hidden" name="supplierType" id="supplierType" value="${hotel.supplierType!}" />
            	        	<input type="hidden" name="shopType" id="shopType" value="${hotel.shopType!}" />
            	        	<input type="hidden" name="isCommend" id="isCommend" value="${hotel.isCommend!}" />
                        	<ul>
                            	<li><label>酒店名称：</label><input type="text" id="name" name="name" value="${hotel.name!}" /></li>
                                <li><label>酒店简介：</label><input type="text" id="info" name="info" value="${hotel.info!}" /></li>
                                <li><label>酒店原价：</label><input type="text" id="oldPrice" name="oldPrice" value="${hotel.oldPrice!}" class="i_btn" /></li>
                                <li><label>酒店折扣：</label><input type="text" id="discount" name="discount" value="${hotel.discount!}" class="i_btn" /></li>
                                <li><label>酒店现价：</label><input type="text" id="price" name="price" value="${hotel.price!}" class="i_btn" /></li>
                                <li><label>运营时间：</label><input type="text" id="saleTime" name="saleTime" value="${hotel.saleTime!}" class="i_btn" /></li>
                                <li><label>联系号码：</label><input type="text" id="telphone" name="telphone" value="${hotel.telphone!}" class="i_btn"/></li>
                                <li><label>有效期：</label><input type="text" id="validityTime" value="${hotel.validityTime!}" name="validityTime" /></li>
                                <li><label>预约方式：</label><input type="text" id="subscribeType" value="${hotel.subscribeType!}" name="subscribeType" /></li>
                                <li><label>使用须知：</label><input type="text" id="notice" value="${hotel.notice!}" name="notice" /></li>
                                <li>
	                                <label>主图上传：</label>
                                    <dl>
                                    	<dd>
                                            <span>
		                                        <div class="w_jdwl">
		                                            <div id="cards1" class="pclass">
		                                                <img id="imageIds1" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos1" id="fileIds1" onchange="setImagePreview('fileIds1','imageIds1','cards1');">
		                                                    <label>主图01</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards2" class="pclass">
		                                                <img id="imageIds2" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos2" id="fileIds2" onchange="setImagePreview('fileIds2','imageIds2','cards2');">
		                                                    <label>主图02</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards3" class="pclass">
		                                                <img id="imageIds3" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos3" id="fileIds3" onchange="setImagePreview('fileIds3','imageIds3','cards3');">
		                                                    <label>主图03</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards4" class="pclass">
		                                                <img id="imageIds4" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos4" id="fileIds4" onchange="setImagePreview('fileIds4','imageIds4','cards4');">
		                                                    <label>主图04</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards5" class="pclass">
		                                                <img id="imageIds5" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos5" id="fileIds5" onchange="setImagePreview('fileIds5','imageIds5','cards5');">
		                                                    <label>主图05</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                    </dl>
                                </li>
                                <li>   
                                	<label>详情图：</label>
                                    <!-- 引用控制层插件样式 -->
                                    <link rel="stylesheet" href="${ctx}/resources/css/zyUpload.css" type="text/css"> 
                                    <!-- 引用核心层插件 -->
                                    <script type="text/javascript" src="${ctx}/resources/js/core/zyFile.js"></script>
                                    <!-- 引用控制层插件 -->
                                    <script type="text/javascript" src="${ctx}/resources/js/core/zyUpload.js"></script>
                                    <!-- 引用初始化JS -->
                                    <script type="text/javascript" src="${ctx}/resources/js/core/demo.js"></script>                                	
                                    <div id="demo" class="demo"></div>
                                </li>
                            </ul>
                            <div class="xiug_btn">
                                <input type="submit" value="提交" onclick="return checkSub(this.form)"/>
                            </div>
                        </form>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../../public/foot.ftl">