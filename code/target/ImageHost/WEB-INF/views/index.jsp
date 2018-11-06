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
        <!-- Hamburger icon -->
        <span></span>
    </a>
    <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="/ImageHost/index">Images</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="/ImageHost/user" class="pure-menu-link">User</a></li>
                <li class="pure-menu-item"><a href="/ImageHost/about" class="pure-menu-link">About</a></li>
            </ul>
        </div>
    </div>


    <div class="header">
        <h1>Images</h1><br/>
        <h2>This is where you can view images that had been stored.</h2>
    </div>

    <div class="header">
        <a class="pure-button pure-button-green" href="/ImageHost/image/upload">Upload image</a>
        <br/>
        <br/>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th width="250">File Name</th>
                <th width="300">Description</th>
                <th width="750">Content</th>
                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${fileChange}" var="doc" varStatus="counter">
                <%--<c:if test="${doc.type}">--%>
                    <tr>
                        <td>${doc.name}</td>
                        <td>${doc.description}</td>
                        <td><img src="data:${doc.type};base64, ${doc.content}" width="750"/></td>
                        <td>
                            <a class="pure-button pure-button-more" href="/ImageHost/image/${doc.id}">More</a>
                            <a class="pure-button pure-button-d" onclick="deleteScript('${doc.id}','${doc.name}');">Delete</a>
                        </td>
                    </tr>
                <%--</c:if>--%>
            </c:forEach>
            </tbody>
        </table>


    </div>
</div>


</body>
</html>
