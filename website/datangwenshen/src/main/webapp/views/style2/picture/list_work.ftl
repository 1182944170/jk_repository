<title>大唐作品</title>
<!--banner-->
<div class="banner"><img src="${ctx}/resources/${webSiteStyle}/images/banner.png" width="1030" height="300" /></div>
<!--main-->
<!--left-->
<div class="main clearfix">
	<div class="left fl">
    	<h2>大唐纹身</h2>
       <#include "../common.ftl"/>
        <h2>联系我们</h2>
        <dl>
        	<dt><img src="${ctx}/resources/${webSiteStyle}/images/img01.jpg" width="280" height="98" /></dt>
             <@common cmd="help_by_aliasesTitle" aliasesTitle="contact">
            	${help.context}
	        </@common>
        </dl>
    </div>
    <!--right-->
    <div class="right fr">
    	<div class="fnav">
        	<h6>大唐作品</h6>
            <span>
            	<a href="index${suffix}">首页</a>&gt;<a href="#">大唐作品</a>
            </span>
        </div>
        <div class="tuku clearfix">
        	<div class="ban" id="demo1">
                <div class="ban2" id="ban_pic1">
                    <div class="prev1" id="prev1"><img src="${ctx}/resources/${webSiteStyle}/images/index_tab_l.png" width="28" height="51"  alt=""/></div>
                    <div class="next1" id="next1"><img src="${ctx}/resources/${webSiteStyle}/images/index_tab_r.png" width="28" height="51"  alt=""/></div>
                    <ul>
                    <@datangwenshen cmd="get_pic_list" source=1 pagerString="1_" pageSize="99">
		        		<#list m_pager.itemList as u>
		        			<li>
		        				<a href="javascript:;">
		        					<img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" width="100%"/>
		        				</a>
		        			</li>
		        		</#list>
			   		</@datangwenshen>
                    </ul>
                </div>
                <div class="min_pic">
                    <div class="prev_btn1" id="prev_btn1"><img src="${ctx}/resources/${webSiteStyle}/images/feel3.png" width="9" height="18"  alt=""/></div>
                    <div class="num clearfix" id="ban_num1">
                        <ul>
                        <@datangwenshen cmd="get_pic_list" source=1 pagerString="1_" pageSize="8">
			        		<#list m_pager.itemList as u>
			        			<li>
		        					<img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" width="100%"/>
			        			</li>
			        		</#list>
			   			</@datangwenshen>
                        </ul>
                    </div>
                    <div class="next_btn1" id="next_btn1"><img src="${ctx}/resources/${webSiteStyle}/images/feel4.png" width="9" height="18"  alt=""/></div>
                </div>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery-1.7.2.min.js" ></script>
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/pic_tab.js"></script>
<script type="text/javascript">
	jq('#demo1').banqh({
		box:"#demo1",//总框架
		pic:"#ban_pic1",//大图框架
		pnum:"#ban_num1",//小图框架
		prev_btn:"#prev_btn1",//小图左箭头
		next_btn:"#next_btn1",//小图右箭头
		pop_prev:"#prev2",//弹出框左箭头
		pop_next:"#next2",//弹出框右箭头
		prev:"#prev1",//大图左箭头
		next:"#next1",//大图右箭头
		//pop_div:"#demo2",//弹出框框架
		//pop_pic:"#ban_pic2",//弹出框图片框架
		//pop_xx:".pop_up_xx",//关闭弹出框按钮
		//mhc:".mhc",//朦灰层
		autoplay:true,//是否自动播放
		interTime:5000,//图片自动切换间隔
		delayTime:400,//切换一张图片时间
		pop_delayTime:400,//弹出框切换一张图片时间
		order:0,//当前显示的图片（从0开始）
		picdire:true,//大图滚动方向（true为水平方向滚动）
		mindire:true,//小图滚动方向（true为水平方向滚动）
		min_picnum:5,//小图显示数量
		pop_up:true//大图是否有弹出框
	})
</script>