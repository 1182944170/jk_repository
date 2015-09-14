<title>画册详情</title>

<#include "../public/top.ftl">
<script>
// 取详情图（多个） 追加图片
var flag = false;
<#if sessionUser??>
	 flag=(('${sessionUser.id}'!='' ? '${sessionUser.id}' : '') == '${photoBook.user.id}' ) ? true : false;
</#if>
	$(function(){
		init('pclass');
	})
	function init(o){
		$("."+o+"").children().children().each(function(i){
			if(flag) $(this).find("img").before("<input type=\"checkbox\" ></input>");
		})
	}
	function getChecked(){
		var arr = new Array(); 
		var j = 0;
		$("input[type='checkbox']").each(function(i){
			if($(this).is(':checked')){
				arr[j] = i;
				j++;
			}
		})
		return arr;
	}
	function imgremove(){
		var arr = getChecked();
		if(arr.length !=0){
			if(!confirm("确认要删除？")){ 
				return false;		
			}
		}else{
			return false;
		}
		var photoBookId = '${photoBook.id}';
		$.ajax({
	        async : false,
	        cache : false,
	        type : 'POST',
	        dataType :JSON,
	        url : "${ctx}/photo/delete/"+photoBookId+"?arr="+arr,
	        error : function(){
        	}
		})
		$("input:checked").parent().parent().remove();
	}
	function changecss(but){
		but.style.backgroundColor = '#AAAAAA';
		but.onmouseout =function(){
		but.style.backgroundColor = '';
		}
	}
</script>
<div class="w_maina">
    <div class="w_pccon">
    	<div class="w_pcxont">
        	<div class="w_pcxol fl">
            	<span><img src="${tagUtils.getFileFullPath(photoBook.user.photo)!}" width="120" height="120" alt="" /></span>
                <h5>
                	<em>${photoBook.user.nickName!}</em>
                    <i>【${photoBook.spots.spotsName!}】${photoBook.memo!}</i>
                </h5>
            </div>
            <div class="w_pcxor fr">
            	<em>所在景点</em>
                <i>青海湖</i>
            </div>
        </div>
        <div>
        	<#if sessionUser??>
        		<#if sessionUser.id == photoBook.userId>
		        	<ul>
		        		<li>
		        		<input class="butclass" type="button" onmouseover="changecss(this);" style="color:red;margin-left:80%;" value="批量删除" onclick="imgremove()"/>
		        		<a href="${ctx}/photo/edit-${photoBook.id}${suffix}">
		        		<input onmouseover="changecss(this);" class="butclass" type="button" style="color:green;margin-left:10px" value="添加照片" />
		        		</a>
		        		<a href="${ctx}/photo/direct${suffix}?userId=${sessionUser.id}">
		        			<input class="butclass" onmouseover="changecss(this);" type="button" value="返回" style="line-height:20px;margin-left:10px;margin-right:5px;width:68px;"/>
		        		</a>
		        		</li>
		        	</ul>
	        	 <#else>
		        	<a href="${ctx}/photo/list-${photoBook.spots.id}${suffix}">
					<input class="butclass" onmouseover="changecss(this);" type="button" value="返回" style="line-height:20px;margin-left:94%;margin-right:5px;width:68px;"/>
		    		</a>
	        	</#if>
	        <#else>
	        	<a href="${ctx}/photo/list-${photoBook.spots.id}${suffix}">
				<input class="butclass" onmouseover="changecss(this);" type="button" value="返回" style="line-height:20px;margin-left:94%;margin-right:5px;width:68px;"/>
	    		</a>
	     	</#if>
        <div>
        <div class="gallery pclass">	 
            <ul>
            <#list listObj as u>
            	<li>
                	<a href="${tagUtils.getFileFullPath(u!)}"><img src="${tagUtils.getFileFullPath(u!)}" width="228" height="137" /></a>
                    <span>${photoBook.name}</span>
                    <div class="listimg">
                        <div class="summary">
                        	<p><em></em>${tagUtils.formatDate(photoBook.createTime)!}</p>
                        </div>
                    </div>
                 </li>
             </#list>
            </ul>
        </div> 
               
            <div class="clear"></div>
            <!--页数-->
            <div class="w_ys">
            	
            </div>
        <script type="text/javascript" src="${ctx}/resources/js/zoom.min.js"></script>
        <script type="text/javascript">
		$(document).ready(function(){
			$('.gallery li').hover(function(){
				$(".summary",this).stop().animate({top:'0px'},{queue:false,duration:100});
			},function(){
				$(".summary",this).stop().animate({top:'22px'},{queue:false,duration:100});
			});
		});
		</script>
    </div>
</div>

<#include "../public/foot.ftl">