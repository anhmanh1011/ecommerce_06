    <%@ page language="java" contentType="text/html; charset=utf-8" %>
        <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
                   prefix="tilesx" %>
        <!DOCTYPE html>
        <html lang="en">
        <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <spring:url value="/assets/css/bootstrap.min.css" var="bootstrapCss"/>
        <spring:url value="/assets/css/fontawesome.min.css" var="fontawesome"/>
        <spring:url value="/assets/css/animate.min.css" var="animate"/>
        <spring:url value="/assets/css/fontelectro.css" var="fontelectro"/>
        <spring:url value="/assets/css/owlcarousel.css" var="owlcarousel"/>
        <spring:url value="/assets/css/style.css" var="style"/>
        <spring:url value="/assets/css/colors/yellow.css" var="yellow"/>

        <spring:url value="/assets/js/bootstrap.min.js" var="bootstrapJs"/>
        <spring:url value="/assets/js/jquery.min.js" var="jquery"/>
        <spring:url value="/assets/js/tether.min.js" var="tether"/>
        <spring:url value="/assets/js/bootstrap-hover-dropdown.min.js" var="bootstraphoverdropdown"/>
        <spring:url value="/assets/js/owl.carousel.min.js" var="owlcarouselminjs"/>
        <spring:url value="/assets/js/echo.min.js" var="echominjs"/>
        <spring:url value="/assets/js/wow.min.js" var="wowminjs"/>
        <spring:url value="/assets/js/jquery.easing.min.js" var="jqueryeasingminjs"/>
        <spring:url value="/assets/js/jquery.waypoints.min.js" var="jquerywaypointsminjs"/>
        <spring:url value="/assets/js/electro.js" var="electrojs"/>
        <spring:url value="/assets/js/cart.js" var="cartjs"/>
        <spring:url value="/assets/images/fav-icon.png" var="favicon"/>

        <link rel="stylesheet" type="text/css" href="${bootstrapCss}" media="all" />
        <link rel="stylesheet" type="text/css" href="${fontawesome}" media="all" />
        <link rel="stylesheet" type="text/css" href="${animate}" media="all" />

        <link rel="stylesheet" type="text/css" href="${fontelectro}" media="all" />
        <link rel="stylesheet" type="text/css" href="${owlcarousel}" media="all" />
        <link rel="stylesheet" type="text/css" href="${style}" media="all" />
        <link rel="stylesheet" type="text/css" href="${yellow}" media="all" />


        <title>Electro &#8211; Electronics Ecommerce Theme</title>
        <link
        href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,700italic,800,800italic,600italic,400italic,300italic'
        rel='stylesheet' type='text/css'>
        <link rel="shortcut icon" href="${favicon}" />


        <title><tiles:insertAttribute name="title" ignore="true"/></title>
        </head>


        <body class="page home page-template-default">
        <div id="page" class="hfeed site">
        <a class="skip-link screen-reader-text" href="#site-navigation">Skip to navigation</a>
        <a class="skip-link screen-reader-text" href="#content">Skip to content</a>

        <div class="top-bar">
        <div class="container">
        <nav>
        <ul id="menu-top-bar-left" class="nav nav-inline pull-left animate-dropdown flip">
        <li class="menu-item animate-dropdown"><a title="Welcome to Worldwide Electronics Store" href="#">Welcome to
        Worldwide Electronics Store</a></li>
        </ul>
        </nav>

        <nav>
        <ul id="menu-top-bar-right" class="nav nav-inline pull-right animate-dropdown flip">
        <li class="menu-item animate-dropdown"><a title="Store Locator" href="#"><i class="ec ec-map-pointer"></i>Store
        Locator</a></li>
        <li class="menu-item animate-dropdown"><a title="Track Your Order" href="track-your-order.html"><i class="ec
        ec-transport"></i>Track Your Order</a></li>
        <li class="menu-item animate-dropdown"><a title="Shop" href="shop.html"><i class="ec
        ec-shopping-bag"></i>Shop</a></li>
        <li class="menu-item animate-dropdown"><a title="My Account" href="/login"><i class="ec ec-user"></i>
        <c:if test="${not empty pageContext.request.userPrincipal}">
            <c:out value="${pageContext.request.userPrincipal.name}"/>
        </c:if>
        <c:if test="${ pageContext.request.userPrincipal == null}">
            <c:out value="${'Login'}"/>
        </c:if>

        </a></li>
        </ul>
        </nav>
        </div>
        </div><!-- /.top-bar -->

        <tiles:insertAttribute name="header"/>


        <tiles:insertAttribute name="body"/>

        <footer id="colophon" class="site-footer">
        <tiles:insertAttribute name="footer"/>
        </footer>
        </div><!-- #page -->


        <script type="text/javascript" src="${jquery}"></script>
        <script type="text/javascript" src="${tether}"></script>
        <script type="text/javascript" src="${bootstrapJs}"></script>
        <script type="text/javascript" src="${bootstraphoverdropdown}"></script>
        <script type="text/javascript" src="${owlcarouselminjs}"></script>
        <script type="text/javascript" src="${echominjs}"></script>
        <script type="text/javascript" src="${wowminjs}"></script>
        <script type="text/javascript" src="${jqueryeasingminjs}"></script>
        <script type="text/javascript" src="${jquerywaypointsminjs}"></script>
        <script type="text/javascript" src="${electrojs}"></script>
        <script type="text/javascript" src="${cartjs}"></script>


        </body>

        </html>
