<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>Login</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctx}/resources/cssframework/ace/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->

		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/ace-rtl.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx}/resources/cssframework/ace/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx}/resources/cssframework/ace/js/html5shiv.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">Ace</span>
									<span class="white">Application</span>
								</h1>
								<h4 class="blue">&copy; Company Name</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												Please Enter Your Account.
											</h4>

											<div class="space-6"></div>

											<form name="validation-form" id="validation-form" class="form-horizontal" action="${ctx}/admin/dologin" method="post">
												<fieldset>
												<div class="form-group">
													<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userName">Username:</label>

													<div class="col-xs-12 col-sm-9">
														<div class="clearfix">
															<span class="block input-icon input-icon-right">
																<input type="text" name="userName" id="userName" value="${(adminUser.userName)!''}" class="form-control" placeholder="Username"/>
																<i class="icon-user"></i>
															</span>
														</div>
													</div>
												</div>


												<div class="form-group">
													<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="pwd">Password:</label>

													<div class="col-xs-12 col-sm-9">
														<div class="clearfix">
															<span class="block input-icon input-icon-right">
																<input type="password" name="pwd" id="pwd" class="form-control" placeholder="Password" />
																<i class="icon-lock"></i>
															</span>
														</div>
													</div>
												</div>

												
												<div class="clearfix">
													<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i>
														Login
													</button>
												</div>

												<div class="space-4"></div>
												<#if errorMsg??>
													<div class="hr hr-dotted"></div>
													<div class="text-danger center"><i class="icon-remove bigger-110 red"></i>&nbsp;&nbsp;${errorMsg}</div>
												</#if>
												
												</fieldset>
											</form>

										</div><!-- /widget-main -->

										
									</div><!-- /widget-body -->
								</div><!-- /login-box -->

								
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctx}/resources/cssframework/ace/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${ctx}/resources/cssframework/ace/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		
		<script src="${ctx}/resources/cssframework/ace/js/bootstrap.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<script src="${ctx}/resources/cssframework/ace/js/fuelux/fuelux.wizard.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.validate.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/additional-methods.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/bootbox.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/jquery.maskedinput.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/select2.min.js"></script>

		<!-- ace scripts -->

		<script src="${ctx}/resources/cssframework/ace/js/ace-elements.min.js"></script>
		<script src="${ctx}/resources/cssframework/ace/js/ace.min.js"></script>
		<script src="${ctx}/resources/js/admin.js"></script>
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctx}/resources/cssframework/ace/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
			
			jQuery(function($) {
				$('#validation-form').validate({
					errorElement: 'div',
					errorClass: 'help-block',
					focusInvalid: false,
					rules: {
						userName: {
							required: true
						},
						pwd: {
							required: true,
							minlength: 5
						}
					},
			
					messages: {
						userName: {
							required: "Please provide a valid userName."
						},
						pwd: {
							required: "Please specify a password.",
							minlength: "Please specify a secure password."
						}
					},
			
					invalidHandler: function (event, validator) { //display error alert on form submit   
						RP.Form.invalidHandler(event, validator);
					},
				
					errorPlacement: function (error, element) {
						RP.Form.errorPlacement(error, element);	
					},
					highlight: function (e) {
						RP.Form.highlight(e);
					},
					
					success: function (e) {
						RP.Form.success(e);	
					},
					
					submitHandler: function (form) {
						RP.Form.submitHandler(form);	
					}
					
				});
				
				
			});
		</script>
		<!-- inline scripts related to this page -->
</body>
</html>
