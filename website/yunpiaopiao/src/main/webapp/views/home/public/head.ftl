

<!--首页频道部分-->
<!--header begin-->
<div class="header">
<!--top begin-->
<div class="top clearfix">
		<div class="center">
			<div class="left float-l">
				<span>欢迎访问云票票网</span>
				<span><a href="${ctx}/login${suffix}" class="login">登陆</a>|<a href="${ctx}/register${suffix}"  class="reg">注册</a></span>
				<span class="myinfor"><a href="${ctx}/member${suffix}">我的云票票</a><i class="tri01"></i></span>
			</div>
			<div class="right float-r">
				<a onclick="AddFavorite(window.location,document.title)"><i class="home"></i>设为首页</a>
				<a onclick="SetHome(this,window.location)"><i class="collect"></i>加入收藏</a>
			</div>
		</div>
	</div>
	<!--top end-->
	<!--header-content begin-->
	
	<div class="header-content center clearfix">
		<div class="logo float-l">	
			<img src="${ctx}/resources/images/logo.jpg" />
			<h1>
				云票票网<span>ypiaopiao.com</spanp>
			</h1>
		</div>
		<div class="position float-l">
			<i class="position-icon"></i>
			<span>杭州</span>
			<i class="tri02"></i>
		</div>
		<div class="search-box float-r">
			<form class="search" method="get" target="_blank">
				<input type="hidden" value="" name="" />
				<input type="submit" value="搜索" class="search-btn" />
				<input type="text" name="" onblur="if(this.value==''){this.value=this.defaultValue;}" onfocus="if(this.value==this.defaultValue){this.value='';}"  value="请输入影片的关键词" class="search-text">
			</form>
			<div class="text">
				<span>热门：</span>
				<a>一步之遥</a>|<a>撒娇女人最好命</a>|<a>叫醒爱情</a>|<a class="header-more">更多</a>
			</div>
		</div>
	</div>
	
	<!--header-content end-->
	<!--navigation-bg begin-->
	<div class="navigation-bg">
		<ul class="navigation center clearfix">
			<li class="border-l navigation-current"><a href="${ctx}/">首页</a></li>
			<li><a href="${ctx}/movie.html">电影</a></li>
			<li><a href="${ctx}/cinema.html">影院</a></li>
			<li><a href="${ctx}/activice.html">热门活动</a></li>
			<li><a href="${ctx}/electronic.html">电子券</a></li>
			<li><a href="${ctx}/entity.html">实体券</a></li>
			<li class="border-r "><a href="${ctx}/card.html">电影卡</a></li>
		</ul>
	</div>
	<!--navigation-bg end-->
</div>
	
<!--header end-->