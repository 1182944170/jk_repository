<title>游记详情</title>

<#include "../../public/top.ftl">
<div class="w_main">
    <div class="youji_xiangq">
    	<h4><em>${travel.title!}</em><span><a href="${ctx}/index${suffix}">首页</a><i>&gt;&gt;</i>游记详情</span></h4>
        <div class="youji_xqtop">
        	<div class="youji_tu fl">
            	<img src="${tagUtils.getFileFullPath(travel.cover!)}" width="780" height="310" alt="" />
            </div>
            <div class="youji_tur fl">
            	<div class="youji_toux">
                	<span><img src="${tagUtils.getFileFullPath(travel.user.photo!)}" width="50" height="50" alt="" /></span>
                    <span>
                    	<em>${travel.user.nickName!}</em>
                        <i>出发时间：${travel.startTime!}</i>
                    </span>
                </div>
                <p><strong>简介：</strong>${travel.introduction!}</p>
                <p><strong>出发地：</strong>${travel.goAddress!}</p>
                <p><strong>行程时间：</strong>${travel.playNumber!}</p>
                <p><strong>人均花销：</strong>${travel.consume!}</p>
                <p><strong>途径景点：</strong>${travel.playPlace!}</p>
                <p><strong>出行方式：</strong>自${travel.runType!}</p>
            </div>
        </div>
        <div class="youji_ready">
        	<ul>
            	<li>
                	<h5><img src="${ctx}/resources/images/youji_bq.png" width="30" height="30" alt="" /><em>出行准备</em></h5>
                    <p>${travel.fixUp!}</p>
                </li>
                <li>
                	<h5><img src="${ctx}/resources/images/youji_bq.png" width="30" height="30" alt="" /><em>美食</em></h5>
                    <p>${travel.foodDesc!}</p>
                </li>
                <li>
                	<h5><img src="${ctx}/resources/images/youji_bq.png" width="30" height="30" alt="" /><em>住宿</em></h5>
                    <p>${travel.hotelDesc!}</p>
                </li>
            </ul>
            <div class="youji_xcap">
            	<h5>行程安排</h5>
                <dl>
                	${travel.content!}
                </dl>
            </div>
        </div>
    </div>
</div>
<#include "../../public/foot.ftl">