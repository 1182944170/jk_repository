	<meta http-equiv="description" content="" />
	<meta http-equiv="keywords" content="" />
	<title>纹身资讯</title>
<div class="main">
    <!--纹身资讯 内页-->
      <!--标题-->
      <div class="tit">
          <h4>杭州大唐纹身(tatto)</h4>
          <h3><a href="news.html">纹身资讯</a></h3>
          <h5>十年纹身老字号</h5>
      </div>        
      <div class="newny">
          <h4>${notice.title}</h4>
           <@common cmd="notice_by_id" aliasesTitle="id">
		        		<b>${notice.content}</b>
		                   
		        	 </@common>
      </div>
</div>

<!--底部-->
<#include "../wechat.ftl"/>