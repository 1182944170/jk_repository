<title>景点详情</title>

<#include "../public/top.ftl">
<div class="w_main">
    <div class="gonglv_con">
        <div class="youji_xiangq">
            <h4><em>${spots.address!} - ${spots.spotsName!} ${spots.travelDays!}日游攻略</em><span><a href="${ctx}/index${suffix}">首页</a><i>&gt;&gt;</i>游记详情</span></h4>
            <div class="youji_xqtop">
                <div class="youji_tu fl">
                    <img src="${tagUtils.getFileFullPath(spots.photos!)}" width="780" height="310" alt="" />
                </div>
                <div class="youji_tur fl">            	
                    <p><strong>简介：</strong>${spots.production!}</p>
                    <p><strong>出发地：</strong>${spots.fromAddress!}</p>
                    <p><strong>行程时间：</strong>${spots.travelDays!}天</p>
                    <p><strong>人均花销：</strong>${spots.cost!}元</p>
                    <span>适宜时间：${spots.propernessDate!} </span>
                </div>
            </div>
            <div class="youji_ready">
                <ul>
                    <li>
                        <h5><img src="${ctx}/resources/images/youji_bq.png" width="30" height="30" alt="" /><em>出行准备</em></h5>
                        <p>${spots.run!}</p>
                    </li>
                    <li>
                        <h5><img src="${ctx}/resources/images/youji_bq.png" width="30" height="30" alt="" /><em>门票</em></h5>
                        <p>${dicSetting.getParameterValue("travel.type."+spots.ticketType)!}</p>
                    </li>
                    <li>
                        <h5><img src="${ctx}/resources/images/youji_bq.png" width="30" height="30" alt="" /><em>路线指南</em></h5>
                        <span><img src="${tagUtils.getFileFullPath(spots.mapInfo!)}" width="640" height="376" alt="" /></span>
                    </li>
                </ul>
                <div class="youji_xcap">
                    <h5>行程安排</h5>
                    <dl>
                        <dd>
                            <h6>第一天</h6>
                            <p>你经历过第一次去厦门，因为不懂如何玩，对厦门印象大打折扣么？你久仰厦门，却不知厦门到底美在哪？</p>
                            <p>有没有想过旅程中有个人，静静地帮你或你们记录美好的瞬间？旅途完毕，还可以看到让人惊喜的照片集？不造作摆pose，看到自然流露的喜悦和感动。绝对比自   拍神器更
    能记录美好旅途。</p>
                            <p>你羡慕牵着TA去旅行的主角么？现在，不管你单身与否，都可以带着摄影师去旅行啦！</p>
                            <span>
                                <img src="${ctx}/resources/images/youji_pic01.jpg" />
                            </span>
                            <p>你经历过第一次去厦门，因为不懂如何玩，对厦门印象大打折扣么？你久仰厦门，却不知厦门到底美在哪？</p>
                            <span>
                                <img src="${ctx}/resources/images/youji_pic02.jpg" />
                            </span>
                        </dd>
                        <dd>
                            <h6>第二天</h6>
                            <p>买上一杯意式咖啡选择座在院子外静看外面人来人往的世界，下午把时间花在各式精美咖啡厅的小院子内才能领略到古浪屿最浪漫真谛！你每到一个地方旅行是否都要照相以证、
          明你到此一游？有否想到如果去旅行会有人帮自己照出很多美丽的照片？不再单调的剪刀手。你可以这样的美丽：
    </p>
                            <span>
                                <img src="${ctx}/resources/images/youji_pic03.jpg" />
                            </span>
                        </dd>
                        <dd>
                            <h6>第三天</h6>
                            <p> 雷峰塔位于净慈寺前，为南屏山向北伸展的余脉，濒湖勃然隆重起，林木葱郁。虽小巧玲珑，名气在西湖上却是数一数二，因为山巅有吴越时建造的雷峰塔是西湖
    众多古塔中最为风光的塔，七十余年前塔倒山虚，连山名也换成了夕照山。后来宝塔修复，风景依旧。山上种埴了大量香樟、枫香、榆树等观赏树木，夕阳斜照，宝塔
    生辉，佛光宝气，普映山水，景色富丽堂皇。元朝尹廷高《雷峰》诗云：烟光山色淡溟蒙，千尺浮图兀倚空。湖上画船归欲尺，孤峰犹带夕阳红。</p>
                            <span>
                                <img src="${ctx}/resources/images/youji_pic04.jpg" />
                            </span>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../public/foot.ftl">