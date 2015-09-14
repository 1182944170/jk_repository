
<title>发布房间</title>

<#include "../../../public/top.ftl">

<script type="text/javascript">
	function checkSub(){
		var names = "name,房间名称;info,房间简介;oldPrice,房间原价;discount,房间折扣;price,房间现价;saleTime,运营时间;telphone,联系电话;validityTime,有效期;notice,须知";
		return checkCommit(names);		
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
                	<h5><em>发布信息</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/hotel/dosave${suffix}" id="names" name="names" method="POST" enctype="multipart/form-data" >
            	        	<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
            	        	<input type="hidden" name="supplierType" id="supplierType" value="5" />
            	        	<input type="hidden" name="shopType" id="shopType" value="5" />
            	        	<input type="hidden" name="shopId" id="shopId" value="${shopId!}" />
                        	<ul>
                        		<li><label>房间名称：</label><input type="text" id="name" name="name" class="i_btn"/></li>
                                <li><label>房间简介：</label><input type="text" id="info" name="info" class="i_btn"/></li>
                                <li><label>房间原价：</label><input type="text" id="oldPrice" name="oldPrice" class="i_btn" /></li>
                                <li><label>房间折扣：</label><input type="text" id="discount" name="discount" class="i_btn" /></li>
                                <li><label>房间现价：</label><input type="text" id="price" name="price" class="i_btn" /></li>
                                <li><label>运营时间：</label><input type="text" id="saleTime" name="saleTime" class="i_btn" /></li>
                                <li><label>联系号码：</label><input type="text" id="telphone" name="telphone" class="i_btn"/></li>
                                <li><label>有效期：</label><input type="text" id="validityTime" name="validityTime" class="i_btn"/></li>
                                <li><label>预约方式：</label><input type="text" id="subscribeType" name="subscribeType" class="i_btn"/></li>
                                <li><label>使用须知：</label><input type="text" id="notice" name="notice" class="i_btn"/></li>
                                <li>
	                                <label>主图上传：</label>
                                    <dl>
                                    	<dd>
                                            <span>
		                                        <div class="w_jdwl">
		                                            <div id="cards1">
		                                                <img id="imageIds1" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards2">
		                                                <img id="imageIds2" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards1">
		                                                <img id="imageIds3" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards4">
		                                                <img id="imageIds4" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards5">
		                                                <img id="imageIds5" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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