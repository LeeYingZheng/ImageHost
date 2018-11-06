<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js" type="text/javascript"></script>
<script src="/ImageHost/static/js/ui.js"></script>
<script src="/ImageHost/static/js/doc.js"></script>
<script src="/ImageHost/static/js/input.js"></script>
<script src="/ImageHost/static/js/expandcollapse.js"></script>
<script src="/ImageHost/static/js/jquery.growl.js" type="text/javascript"></script>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>Image hosting</title>


    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
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

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="/ImageHost/index" class="pure-menu-link">Images</a></li>
                <a class="pure-menu-heading" href="/ImageHost/user">User</a>
                <li class="pure-menu-item"><a href="/ImageHost/about" class="pure-menu-link">About</a></li>
            </ul>


        </div>
    </div>

    <div class="header">
        <h1>User information</h1><br>

    </div>

    <div class="content">

        <div class="pure-g">
            <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
                <form:form class="pure-form pure-form-stacked" modelAttribute="user" method="post">
                    <form:input type="hidden" path="id" id="id"/>
                    <form:input type="hidden" path="firstName" id="firstName"/>
                    <form:input type="hidden" path="lastName" id="lastName"/>

                    <div class="pure-u-1">
                        <label for="browser">Browser for testing</label>
                        <form:select path="browser" id="browser" required="required" style="width: 100%;">
                            <option>${user.browser}</option>
                            <c:forEach items="${browsers}" var="b">
                                <c:if test="${b.name != user.browser}">
                                    <option>${b.name}</option>
                                </c:if>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="pure-u-1" id="emailDiv">

                        <label for="email">Email &nbsp; <i class="fa fa-info-circle tip" aria-hidden="true">
                            <div class="help">
                                Results for the test cases would be sent to this email address.
                            </div>
                        </i></label>
                        <div>
                        <form:input name="email" type="email" path="email" maxlength="100" required="required" class='hey'/>
                        <input type='button' id='remove' value='Remove'/>
                        </div>
                    </div>

                    <input type="button" value="Add another email" onClick="addInput('emailDiv');"/>
                    <br />

                    <button type="submit" class="pure-button">Update</button>

                </form:form>
            </div>
        </div>
    </div>
</div>
<script>
    successAdd('${MESSAGE}');
    emailSeparate('${user.email}')
</script>

</body>
</html>


