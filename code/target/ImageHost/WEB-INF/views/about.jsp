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
                <li class="pure-menu-item"><a href="/ImageHost/user" class="pure-menu-link">User</a></li>
            </ul>
            <a class="pure-menu-heading" href="/ImageHost/about">About</a>
        </div>
    </div>

    <div class="header">
        <h1>About</h1><br/>
        <h2>To help lost lambs.</h2>

    </div>

    <div class="content">
        <div>
            <br />
            <a href="/ImageHost/about#selenium_definition">What is Selenium?</a>
            <a href="/ImageHost/about#script_definition" style="display: block">What is a script?</a>
            <a href="/ImageHost/about#upload_script" style="display: block">How to upload a script?</a>
            <a href="/ImageHost/about#suite_definition" style="display: block">What is a suite?</a>
            <a href="/ImageHost/about#schedule" style="display: block">How to schedule?</a>
        </div>

        <div id="selenium_definition">
            <h2 class="content-subhead">What is Selenium?</h2>
            <p>
                Selenium automates browsers. That's it! What you do with that power is entirely up to you.
                Primarily, it is for automating web applications for testing purposes, but is certainly not limited to just that.
                Boring web-based administration tasks can (and should!) also be automated as well.
            </p>
            <p>
                Selenium has the support of some of the largest browser vendors who have taken (or are taking)
                steps to make Selenium a native part of their browser.
                It is also the core technology in countless other browser automation tools, APIs and frameworks.
            </p>
            <p>
                More information on Selenium could be found <a
                    href="http://www.seleniumhq.org/docs/">here</a>.
            </p>
        </div>


        <div id="script_definition">
            <h2 class="content-subhead">What is a script?</h2>
            <p>
               A script is a Selenium automated test case, used for testing the functionality of a site.
            </p>
        </div>

        <div id="upload_script">
            <h2 class="content-subhead">How to upload a script?</h2>
            <p>
                1) Get Selenium IDE add-on for Firefox <a
                    href="https://addons.mozilla.org/en-US/firefox/addon/selenium-ide/">here</a>.
            </p>
            <p>
                2) Record the test case and actions to be carried out.
            </p>
            <p>
                3) Save the test case, and upload it to this site.
            </p>
            <div class="pure-g">
                <div class="pure-u-1-2">
                    <img class="pure-img-responsive" src="/Selenium/static/img/ide1.JPG" alt="Selenium IDE">
                </div>
                <div class="pure-u-1-2">
                    <img class="pure-img-responsive" src="/Selenium/static/img/ide2.png" alt="Save test case">
                </div>
            </div>
        </div>

        <div id="suite_definition">
            <h2 class="content-subhead">What is a suite?</h2>
            <p>
                A suite is a group of scripts, such that the group could be run at the same time.
            </p>
        </div>

        <div id="schedule">
            <h2 class="content-subhead">How to schedule?</h2>
            <p>
                Both a script and suite could be scheduled. Multiple schedules could be done at the same time.
            </p>
            <p>
                Scheduling is done using the Quartz Enterprize Job Scheduler with the use of their cron expressions.
                The full documentation could be found <a
                    href="http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-06.html">here</a>.
            </p>
        </div>

    </div>
</div>

</body>
</html>


