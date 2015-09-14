<title> 美食详情 </title>

<#include "../../../public/top.ftl">
<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
		<#list foodList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("foodListClass","foodListChild",str,score);
		
		var values ="${food.photos!}";
		initDetail('detailclass',values);
		initPage(3);//初始化 传入分页显示几条数据
		var str1 = "";
		//获取图片
		str1 = getStr('commentc');
		//解析评论页面 
		initComment('commentc',str1);
	})
	function getStr(o){
		var s ="";
		$("."+o+"").each(function(i){
			if(i>0){
				if($(this).find("input").val()!="" && $(this).find("input").val()!="undefined"){
					s +=";"+$(this).find("input").val();
				}else{
					s +=";"+null;
				}
			}else{
				if($(this).find("input").val()!="" && $(this).find("input").val()!="undefined"){
					s +=$(this).find("input").val();
				}else{
					s +="null";
				}
			}
		})
		if(s == "" || s =="undefined")s = $("."+o+"").find("input").val();
		return s;
	}
	function initComment(o,str){
		var arr = "";
		var p = "";
		var ph = "";
		var html = "";
		var h = "";
		var n = 1;
		$("."+o+"").each(function(i){
			if(str.indexOf(";")>0){
				arr = str.split(";"); 
				nstr = arr[i];
				if(nstr == 'null'){
						
				}else{
					   if(nstr.indexOf(",")>0){
							p = nstr.split(","); 
							for(j=0;j<p.length;j++){//2个div
								html = getDiv(n);
								h = getDivBig(n);
								$(this).append(html);
								$(".parentDiv").append(h);
								ph = p[j];
								ph = ph.substring(1,ph.length);
								var jj = j+1;
								$(this).children().eq(jj).find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");	
								//$("#imgId"+n+"").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
								$("#bigdivId"+n+"").find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
								n++;
							}
		    			}else{
									html = getDiv(n);
									h = getDivBig(n);
									$(this).append(html);
									$(".parentDiv").append(h);
									//$(this).parent().parent().parent().parent().append(h);
									var ph = nstr;
									var d1 = 1;
									$(this).children().eq(d1).find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
									//$("#imgId"+n+"").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
									$("#bigdivId"+n+"").find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
					    }
			     }
			}else{
				if(str.indexOf(",")>0){
					p = str.split(","); 
					for(j=0;j<p.length;j++){//3个div
						html = getDiv(n);
						h = getDivBig(n);
						$(this).append(html);
						$(".parentDiv").append(h);
						//$(this).parent().parent().parent().parent().append(h);
						ph = p[j];
						ph = ph.substring(1,ph.length);
						var d1 = j+1;
						$(this).children().eq(d1).find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
						//$("#imgId"+n+"").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
						$("#bigdivId"+n+"").find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
						n++;
					}
				}else{
					html = getDiv(n);
					h = getDivBig(n);
					$(this).append(html);
					$(".parentDiv").append(h);
					var ph = str;
					var d1 = 1;
					$(this).children().eq(d1).find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
					//$("#imgId"+n+"").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
					$("#bigdivId"+n+"").find("img").attr("src","${tagUtils.getFileFullPath('"+ph+"')}");
				}
			}
		})
	}
	function getDiv(n){
		var d = "";
		d="<div class=\"small_pic commentImg\"><a href=\".pic_0"+n+"\"><img src=\"\" width=\"94\" height=\"70\" alt=\"\" \/><\/a><\/div>";
		return d;	
	}
	function getDivBig(n){
		var d = "";
		d="<div id=\"bigdivId"+n+"\" class=\"pic_0"+n+" commentImgInfo\" style=\"display:none;\"><img id=\"imgId"+n+"\" src=\"\" \/><\/div>";
		return d;	
	}
	function initPage(n){
		var num = getPages(n); 
		$(".infoclass").each(function (i){
			$(this).css("display","");
			if(i >= num){
				$(this).css("display","none");
			}
		})
	}
	// 取详评论情图（多个）
	function initDetail(o,arr){
		var imgs ="";
		var html = "";
		if(arr.indexOf(",")>0){
			var p = arr.split(",");
			var len = $(".liclass").children().length;
				for(j=0;j<p.length;j++){
					for(i = 0 ; i<p.length; i++){
						imgs = p[i];
						imgs = imgs.substring(1,imgs.length)	
						$("."+o+"").children().children().eq(i).find("img").removeAttr("src");
						$("."+o+"").children().children().eq(i).find("img").attr("src","${tagUtils.getFileFullPath('"+imgs+"')!}");
					}	
				}
				for(k=0;k<len;k++){
					if(k>=p.length){
					 	$("#li"+k+"").remove();
					}
				}
		}else{
			var p = arr.substring(1,arr.length);
			var len = $(".liclass").children().length;
			for(k=0;k<len;k++){
					if(k>=1){
					 	$("#li"+k+"").remove();
					}
			}
			$("."+o+"").children().children().eq(0).find("img").removeAttr("src");
			$("."+o+"").children().children().eq(0).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')!}");
		}	
	}
	function addComment(){
		var v = $("#commentIds").css("display") == "block";
		var val = $("#commentText").val().trim("");
		if(val == "" && v){
			return false;
			alert("评论内容不能为空");
		}else if(val != ""){
			return true;
		}
		$("#commentIds").css("display","");
		return false;
	}
	
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
    <div class="w_jiudian_top">
    	<h4><a href="${ctx}/index${suffix}">首页</a>&gt;<a href="${ctx}/food${suffix}">美食</a>&gt;<em>${food.shops.shopName!}</em></h4>
    	<script type="text/javascript" src="${ctx}/resources/js/jquery.SuperSlide.2.1.js"></script>
        <div id="w_slidebox" class="w_slidebox fl">
            <div class="bd detailclass">
                <ul class="liclass">
                  <li id="li0"><img src="" width="600" height="457" alt="" /></li>
                  <li id="li1"><img src="" width="600" height="457" alt="" /></li>
                  <li id="li2"><img src="" width="600" height="457" alt="" /></li>
                  <li id="li3"><img src="" width="600" height="457" alt="" /></li>
                  <li id="li4"><img src="" width="600" height="457" alt="" /></li>
                </ul>
            </div>
            <a class="prev" href="javascript:void(0)"></a>
            <a class="next" href="javascript:void(0)"></a>    
        </div>    
        <script type="text/javascript">
        jQuery(".w_slidebox").slide({mainCell:".bd ul",autoPlay:true});
        </script>
        <div class="w_jiudian_topr fr">
        	<div class="w_trs">
                <h5>${food.name!}</h5>
                <p>${food.info!}</p>
                <span>${commonTag.getCountyPath(food.shops.areaCode!)}</span>
                <em><u>营运时间：${food.saleTime!}</u><i>有效期：${food.validityTime!}</i></em>
            </div>
            <div class="w_trz">
            	<p>￥<em>${food.price!}</em></p>                
                <span><em>折扣<i>${food.discount!} 折</i></em><u></u><em>原价<b>￥${food.oldPrice!}</b></em></span>
            </div>
            <div class="w_trx">
            	<span>售出302</span>
                <span>
                	<img src="${ctx}/resources/images/w_pf.png" />
                    <img src="${ctx}/resources/images/w_pf.png" />
                    <img src="${ctx}/resources/images/w_pf.png" />
                    <img src="${ctx}/resources/images/w_pf.png" />
                    <img src="${ctx}/resources/images/w_pfh.png" />
                    <em>4分</em>
                </span>
                <span>已有500人评论</span>
            </div>
        </div>
    </div>
    <div class="w_mainl fl">
        <div class="w_jdtops">
        	<ul>
            	<li><a href="#w_xh01">商家位置</a></li>
                <li><a href="#w_xh02">购买须知</a></li>
                <li><a href="#w_xh03">商家介绍</a></li>
                <li><a href="#w_xh04">会员评价（325）</a></li>
            </ul>
            <div class="w_jdadr" id="w_xh01">
                <h5>商家位置</h5>
                <div class="w_jdar">
                    <span>
                        <em><img src="${ctx}/resources/images/w_map.jpg" width="427" height="296" alt="" /></em>                   
                    </span>
                    <span>
                        <h6>${food.shops.shopName!}</h6>
                        <p>地址：${commonTag.getCountyPath(food.shops.areaCode!)}${food.address!}</p>
                        <p>电话：${food.telphone!}</p>
                        <p>公交：12路  7路</p>
                    </span>
                </div>
            </div>
            <div class="w_jdadr" id="w_xh02">
            	<h5>购买须知</h5>
                <div class="w_jdatd">
                    <div><span>有效期</span><em>${food.validityTime!}</em></div>
                    <div><span>预约方式</span><em>${food.subscribeType}</em></div>
                    <div><span>入住须知</span><em>${food.notice}</em></div>
                    
                </div>
            </div>
            <div class="w_jdadr" id="w_xh03">
            	<h5>商家介绍</h5>
                <div class="w_jdatu">
                     <img src="${ctx}/resources/images/w_jdpic05.jpg" width="860" height="466" alt="" /> 
                     <img src="${ctx}/resources/images/w_jdpic05.jpg" width="860" height="466" alt="" /> 
                     <img src="${ctx}/resources/images/w_jdpic05.jpg" width="860" height="466" alt="" />                   
                </div>
            </div>
            <!--详情图-->			
            <script type="text/javascript" src="${ctx}/resources/js/content_zoom.js"></script>
            <script type="text/javascript">
	            $(document).ready(function() {
	                $('div.small_pic a').fancyZoom({scaleImg: true, closeOnClick: true});
	                $('#zoom_word_1').fancyZoom({width:400, height:200});
	                $('#zoom_word_2').fancyZoom();
	                $('#zoom_flash').fancyZoom();
	            });
            </script>
            <div class="w_jdadr" id="w_xh04">
            	<h5>会员评价</h5>
                <div class="w_hypj">
                	<h4>
                    	<span>整体评价：</span>
                        <em>
                        	<img src="${ctx}/resources/images/w_star.png" width="26" height="24" />
                            <img src="${ctx}/resources/images/w_star.png" width="26" height="24" />
                            <img src="${ctx}/resources/images/w_star.png" width="26" height="24" />
                            <img src="${ctx}/resources/images/w_star.png" width="26" height="24" />
                            <img src="${ctx}/resources/images/w_star.png" width="26" height="24" />
                            <i>5分</i>
                        </em>
                    </h4>
                    <h6>全部</h6>
                    
                    <#-- 循环评论的内容 -->
                    <#if sessionUser??> 
                    <div>
                    <#list infoList as u >
	                    <dl class="infoclass" style="display:none">
	                    	<dt>
	                        	<img src="${tagUtils.getFileFullPath(userList[u_index].photo)!}" width="60px" height="60" alt="" />
	                            <em>${userList[u_index].nickName!}</em>
	                        </dt>
	                        <dd>
	                        	<span>${tagUtils.formatDate(u.createTime)!}说：</span>
	                            <p>${u.comment!}</p>
	                            <div class="w_jdtall parentDiv">
	                                <div class="zxx_out_box">
	                                    <div class="zxx_in_box">                                        
	                                        <div class="zxx_main_con fix mb20">
	                                            <div class="zxx_zoom_left commentc">
                                                	<input type="hidden" value="${u.photos!}"/>
	                                            </div>                                            
	                                        </div>
	                                    </div>
	                            	</div>
	                            <!-- 要放大显示的div -->                            
	                            	
	                            </div>
	                        </dd>
	                    </dl>  
                    </#list>                  
                	<form action="${ctx}/infomation/add${suffix}" method="POST" enctype="multipart/form-data">
                	<div style="display:none" id="commentIds">
                       <dl>
                       	 	<textarea id="commentText" name="comment" style="width:100%;height:200px;"></textarea>
                       </dl>
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
                    	</dl>
                    </div>
                    <input type="hidden" value="${food.id!}" name="supplierId" />
                    <input type="hidden" value="${sessionUser.id!}" name="userId" />
                    <input onclick="return addComment();" type="submit" value="评论" style="width:5%;margin-left:95%;text-align:center;background:Gray;color:#FFFFFF"/>
                	</form>
                    <h6 style="text-align:center" onclick="initPage(3)" >查看更多评论......共[<i style="color:red" onclick="initPage('${infoListSize}')" title="显示其余全部评论......">${infoListSize}</i>]条</h6>                 
                   </div>
                   </#if>
                   <#-- 全部评论-->
                </div>
            </div>
        </div>
    </div>
    <div class="w_mainr fr">
    	<div class="w_hotelcon">
        	<h4><a href="${ctx}/food${suffix}">推荐美食</a></h4>
            <div class="w_dajia">
            	<ul>
            		<#list foodList as u>
	                    <li class="foodListClass">
	                   		<a href="${ctx}/food/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_hpic04.jpg" width="100" height="76" alt="" /></a>
	                    	<h6>${u.name!}</h6>
	                        <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
	                        <div class="foodListChild"><u>
	                        	<img src="${ctx}/resources/images/w_pf.png" />
	                        	<img src="${ctx}/resources/images/w_pf.png" />
	                        	<img src="${ctx}/resources/images/w_pf.png" />
	                        	<img src="${ctx}/resources/images/w_pf.png" />
	                        	<img src="${ctx}/resources/images/w_pfh.png" />
	                        		4分</u><i>售出302</i>
	                        </div>
	                    </li>
                    </#list>
                </ul>
            </div>
        </div>
        <div class="w_ggw"><a href="#"><img src="${ctx}/resources/images/w_ypic06.jpg" width="320" height="90" alt="" /></a></div>
        <div class="w_ggw"><a href="#"><img src="${ctx}/resources/images/w_ypic07.jpg" width="320" height="90" alt="" /></a></div>
        <div class="w_ggw"><a href="#"><img src="${ctx}/resources/images/w_ypic08.jpg" width="320" height="144" alt="" /></a></div>
    </div>
</div>

<#include "../../../public/foot.ftl">