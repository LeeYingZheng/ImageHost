<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<script src="/ImageHost/static/js/ui.js"></script>
<script src="/ImageHost/static/js/expandcollapse.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js" type="text/javascript"></script>
<script src="/ImageHost/static/js/jquery.growl.js" type="text/javascript"></script>
<script src="/ImageHost/static/js/doc.js"></script>
<script src="/ImageHost/static/js/sequenceinput.js"></script>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Image hosting</title>

    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <link href="<c:url value='/static/css/layouts/table.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/layouts/marketing-old-ie.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/layouts/marketing.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/layouts/side-menu-old-ie.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/layouts/side-menu.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/growl.css' />" rel="stylesheet" type="text/css">

</head>


<body>

<div id="layout">
    <!-- Menu toggle -->
    <a href="#menu" id="menuLink" class="menu-link" onclick="expandMenu()">
        <span></span>
    </a>
    <div id="menu">
        <div class="pure-menu">
            <ul class="pure-menu-list">
                <a class="pure-menu-heading" href="/ImageHost/index">Images</a>
                <li class="pure-menu-item"><a href="/ImageHost/user" class="pure-menu-link">User</a></li>
                <li class="pure-menu-item"><a href="/ImageHost/about" class="pure-menu-link">About</a></li>
            </ul>
        </div>
    </div>

    <div class="header">
        <h1>Images</h1><br/>
        <h2>This is where images can be uploaded.</h2>
    </div>


    <div class="content">
        <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
                   class="pure-form pure-form-stacked">

            <div class="pure-u-1">
                <label for="file">Image</label>
                <form:input id="file" type="file" path="file" accept="image/*" required="required"/>
                <div class="has-error">
                    <form:errors path="file"/>
                </div>
            </div>

            <div class="pure-u-1">
                <label for="description">Description</label>
                <form:textarea id="description" path="description" maxlength="255" rows="4" cols="105"/>
            </div>

            <br>
            <button type="submit" class="pure-button">Upload</button>

        </form:form>


    </div>

</div>

</body>
</html>
