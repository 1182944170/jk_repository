<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title><sitemesh:write property='title'/></title>
		<meta name="keywords" content="<sitemesh:write property='keywords'/>" />
		<meta name="description" content="<sitemesh:write property='description'/>" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="${ctx}/resources/favicon.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${ctx}/resources/favicon.ico" rel="icon" type="image/x-icon" />
		<link href="${ctx}/resources/favicon.ico" rel="bookmark" type="image/x-icon" />
		<!-- basic styles -->
		<link href="${ctx}/resources/cssframework/ace/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/chosen.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/datepicker.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/bootstrap-timepicker.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/daterangepicker.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/colorpicker.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/select2.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/dropzone.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/colorbox.css" />
		<!-- fonts -->
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/open_Sans.css" />

		<!-- ace styles -->

		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/ace-skins.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="${ctx}/resources/cssframework/ace/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx}/resources/cssframework/ace/js/html5shiv.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/respond.min.js"></script>
		<![endif]-->
		
		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${ctx}/resources/cssframework/ace/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script src="${ctx}/resources/cssframework/ace/js/jquery-1.10.2.min.js"></script>
		<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx}/resources/cssframework/ace/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
			ctx = '${ctx}';
			webUrl = '${tagUtils.getWebUrl()}';
		</script>
		<script src="${ctx}/resources/cssframework/ace/js/bootstrap.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="${ctx}/resources/cssframework/ace/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="${ctx}/resources/cssframework/ace/js/fuelux/fuelux.wizard.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.validate.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/additional-methods.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/bootbox.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.maskedinput.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/select2.min.js"></script>
		
		<script src="${ctx}/resources/cssframework/ace/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/chosen.jquery.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/fuelux/fuelux.spinner.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/date-time/bootstrap-timepicker.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/date-time/moment.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/date-time/daterangepicker.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/bootstrap-colorpicker.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.knob.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.autosize.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.maskedinput.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/bootstrap-tag.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/typeahead-bs2.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.dataTables.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.dataTables.bootstrap.js"></script>
		<!-- mindmup-editabletable.js-->
		<script src="${ctx}/resources/plugins/mindmup-editabletable.js"></script>
		
		<!-- page specific plugin scripts -->
		<script src="${ctx}/resources/cssframework/ace/js/dropzone.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/bootbox.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.colorbox-min.js"></script>
		<!-- ace scripts -->
		<script src="${ctx}/resources/cssframework/ace/js/ace-elements.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/ace.min.js"></script>
		
		<script type=text/javascript src="${ctx}/resources/plugins/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
		
		<!--custom js import -->
		<script src="${ctx}/resources/js/base_x.js"></script>
		<script src="${ctx}/resources/static/data_country_static.js"></script>
		<script src="${ctx}/resources/js/country_static.js"></script>
		<script src="${ctx}/resources/js/StringUtils.js"></script>
		<script src="${ctx}/resources/js/admin.js"></script>
		<script src="${ctx}/resources/js/showpages.js"></script>
		<@common cmd="has_include_file" file="admin/sub_website_include_js.ftl">
			<#include "admin/sub_website_include_js.ftl">
		</@common>
		<sitemesh:write property='head'/>
	</head>

	<body>
		<#include "admin-base/main/top.ftl">
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<#include "admin-base/main/left.ftl"/>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="${ctx}/admin/main${suffix}">后台首页</a>
							</li>
						</ul><!-- .breadcrumb -->
						
						<!--
						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div>--><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<sitemesh:write property='body'/>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
							<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
							<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
							<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								Inside
								<b>.container</b>
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
</body>
<script>
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
	$('.date-picker').datepicker({
		autoclose:true,
		language: 'zh-CN',
		todayBtn: 1,
		todayHighlight: 1}
	).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
	$(".chosen-select").chosen(); 
	<#assign defaultColor=(dicSetting.getParameterValue("admin.defaultColor"))!"#438EB9" />
	
	var defaultColor = '${defaultColor}';
	$(".colorpick-btn").each(function() {
		var mThis = $(this);
		if(mThis.attr("data-color") == defaultColor) {
			mThis.trigger("click");
		}
	});
});
</script>

</html>