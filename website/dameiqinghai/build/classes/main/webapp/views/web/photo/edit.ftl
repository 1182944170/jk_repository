<title>发布画册</title>

<#include "../public/top.ftl">

<script type="text/javascript">
	function checkSub(){
		var names = "name,画册名";
		return checkCommit(names);
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
                	<h5><em>添加画册</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/photo/dosave${suffix}" method="POST" enctype="multipart/form-data">
                			<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
                			<input type="hidden" name="id" value="${photoBook.id!}" />
                			<input type="hidden" name="spotsId" value="${photoBook.spotsId!}" />
                        	<ul>
                                <li><label>照片名：</label><input type="text" readonly="readonly" id="name" name="name" value="${photoBook.name!}" class="i_btn" /></li>
                                <li>
	                                <label>画册图片：</label>
                                    <dl>
                                    	<dd>
                                            <span>
		                                        <div class="w_jdwl">
		                                            <div id="cards1">
		                                                <img id="imageIds1" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos1" id="fileIds1" onchange="setImagePreview('fileIds1','imageIds1','cards1');">
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
                                	<label>画册简介：</label>
                                    <div class="shixj">
                                    	<textarea id="memo" name="memo" >${photoBook.memo!}</textarea>
                                    </div>
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


<#include "../public/foot.ftl">