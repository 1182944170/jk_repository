<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title><sitemesh:write property='title'/></title>
		<meta name="keywords" content="<sitemesh:write property='keywords'/>" />
		<meta name="description" content="<sitemesh:write property='description'/>" />
		<link href="${ctx}/resources/favicon.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${ctx}/resources/favicon.ico" rel="icon" type="image/x-icon" />
		<link href="${ctx}/resources/favicon.ico" rel="bookmark" type="image/x-icon" />
		<sitemesh:write property='head'/>
	</head>

	<body>
		<sitemesh:write property='body'/>
	</body>
</html>