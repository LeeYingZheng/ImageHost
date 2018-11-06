<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js" type="text/javascript"></script>
<script src="/ImageHost/static/js/ui.js"></script>
<script src="/ImageHost/static/js/doc.js"></script>
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
            <a class="pure-menu-heading" href="/ImageHost/index">Images</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="/ImageHost/user" class="pure-menu-link">User</a></li>
                <li class="pure-menu-item"><a href="/ImageHost/about" class="pure-menu-link">About</a></li>
            </ul>
        </div>
    </div>

    <div class="header">
        <h1>${document.name}</h1><br>

    </div>

    <div class="content">

        <div class="content-head is-center">
            <button class="button-run pure-button" onclick="runScript('${document.id}','${document.name}');"><i
                    class="fa fa-angle-double-right">Run now</i></button>
            <button class="button-error pure-button" onclick="deleteScript('${document.id}','${document.name}');"><i
                    class="fa fa-times">Delete</i></button>
            <br><br>
            <button class="button-secondary pure-button" onclick="location.href='/ImageHost/${document.id}/image';"><i
                    class="fa fa-file">Download image</i></button>
            <button class="button-secondary pure-button" onclick="location.href='/ImageHost/${document.id}/tsa';"><i
                    class="fa fa-file">Download image and TSA</i></button>
            <button class="button-secondary pure-button" onclick="location.href='/ImageHost/image/${document.id}/result';"><i
                    class="fa fa-download">Download result</i></button>



            <h2 class="content-head is-center" style="cursor: pointer;" onclick="expandCollapse('showHide');" id="expand">
            [+]
            Update Document</h2>

        <div id="showHide" style="display: none;">

                <div class="pure-u-1 pure-u-md-2-5">
                    <form:form class="pure-form pure-form-stacked" modelAttribute="fileChange" method="post">
                        <form:input type="hidden" path="id" id="id"/>
                        <form:input type="hidden" path="extension" id="extension"/>
                        <form:input type="hidden" path="content" id="content"/>
                        <div class="pure-u-1">
                            <label for="name">Name</label>
                            <form:input id="name" type="text" path="name" maxlength="100" required="required"/>
                            <div class="has-error">
                                <form:errors path="name"/>
                            </div>
                        </div>

                        <div class="pure-u-1">
                            <label for="cron">Schedule (in cron)&nbsp; <i class="fa fa-info-circle tip"
                                                                          aria-hidden="true"> Help
                                <div class="help">
                                    To remove the schedule job, simply leave the input box blank.<h4>Example Cron
                                    Expressions</h4>
                                    CronTrigger Example 1 - an expression to create a trigger that simply fires every 5
                                    minutes.<br><br>

                                    "0 0/5 * * * ?"<br><br>

                                    CronTrigger Example 2 - an expression to create a trigger that fires every 5
                                    minutes, at 10 seconds after the minute (i.e. 10:00:10 am, 10:05:10 am,
                                    etc.).<br><br>

                                    "10 0/5 * * * ?"<br><br>

                                    CronTrigger Example 3 - an expression to create a trigger that fires at 10:30,
                                    11:30, 12:30, and 13:30, on every Wednesday and Friday.<br><br>

                                    "0 30 10-13 ? * WED,FRI"<br><br>

                                    CronTrigger Example 4 - an expression to create a trigger that fires every half hour
                                    between the hours of 8 am and 10 am on the 5th and 20th of every month. Note that
                                    the trigger will NOT fire at 10:00 am, just at 8:00, 8:30, 9:00 and 9:30.<br><br>

                                    "0 0/30 8-9 5,20 * ?"
                                </div>
                            </i>
                            </label>

                            <form:input id="cron" type="text" path="cron" maxlength="100"/>
                            <div class="has-error">
                                <form:errors path="cron"/>
                            </div>
                        </div>

                        <label for="description">Description</label>
                        <form:textarea id="description" path="description" maxlength="255" rows="4" cols="105"/>

                        <br>
                        <button type="submit" class="pure-button">Update</button>

                    </form:form>
                </div>

        </div>
        <c:if test="${expandcollapse == 'false'}">
            <script>
                expandCollapse('showHide');
            </script>
        </c:if>
    </div>
</div>
<script>
    errorResult('${MESSAGE}');
    successAdd('${MESSAGE2}');
</script>

</body>
</html>


