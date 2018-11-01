<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="A layout example that shows off a responsive product landing page.">

	<title>Image hosting</title>

	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
	<!--[if lte IE 8]>
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-old-ie-min.css">
	<![endif]-->
	<!--[if gt IE 8]><!-->
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
	<!--<![endif]-->
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
	<!--[if lte IE 8]>

	<!--<![endif]-->
	<link href="<c:url value='/static/css/layouts/marketing-old-ie.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/layouts/marketing.css' />" rel="stylesheet"></link>

</head>
<body>
<div class="header">
	<div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
		<a class="pure-menu-heading" href="">Image hosting</a>

	</div>
</div>

<div class="splash-container">
	<div class="splash">
		<h1 class="splash-head">Image hosting portal</h1>
		<p>
			<a href="/ImageHost/index" class="pure-button pure-button-white" style="float: inherit;">Click here!</a>
		</p>
	</div>
</div>

<div class="content-wrapper">
	<div class="content">
		<h2 class="content-head is-center">CS3235</h2>


	</div>
</div>

</body>
</html>