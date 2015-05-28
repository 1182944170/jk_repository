ta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>纹身资讯</title>
<div class="main">
    <!--纹身资讯-->
      <!--标题-->
      <div class="tit">
          <h4>杭州大唐纹身(tatto)</h4>
          <h3><a href="news.html">纹身资讯</a></h3>
          <h5>十年纹身老字号</h5>
      </div>    
      <div class="newa">
          <dl>
              <dt><img src="${ctx}/resources/${webSiteStyle}/images/news_pic.jpg" width="400" height="403" alt="" /></dt>
              <dd>
                  <ul>
                      
		        		<#list pager.itemList as u>
		                    <li><a href="${ctx}/news/${u.id}/detail${suffix}"><i></i><b>${u.title}</b><em>${tagUtils.formatDate(u.recoreCreateTime)}</em></a></li>
		        		</#list>
		        	
                  </ul>
              </dd>
          </dl>
      </div>
     
      	 <script>
       			new showPages('${ctx}/news${suffix}','${pager.totalPages?c}','${pager.totalCount?c}','${pager.pagerWebString}').printBaseHtml();
		</script>
</div>

<!--底部-->
<#include "../wechat.ftl"/>