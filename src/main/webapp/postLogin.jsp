<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post Login Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="Css/main.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<c:choose>
    <c:when test="${session.login == true}">

        <jsp:useBean id="HeyBean" scope="request" type="webLogic.model.HeyBean"/>
        <c:choose>
            <c:when test="${HeyBean.validElectionsSize == true}">
                <c:forEach items="${HeyBean.validElections}" var="value">
                    <c:out value="${value}"/>
                    <br>
                </c:forEach>

                <s:form action="selectList" method="post">
                    <s:text name="Select an Election:"/>
                    <s:textfield name="uidS"/><br>
                    <s:submit cssClass="waves-effect waves-light btn-large"/>
                </s:form>
            </c:when>
            <c:otherwise>
                <h1 style="text-align: center">All Done</h1>
                <p style="text-align: center">There are no elections to vote on right now, come back later</p>
            </c:otherwise>

        </c:choose>
    </c:when>
    <c:otherwise>
        <h1 style="text-align: center">To access this page you must be logged in and have the correct permissions</h1>
        <s:form action="index" cssClass="centerButtons">
            <s:submit type="button" value="Go To Login Page" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <img style="width:50%;" class="center-img" src="img/kermit.gif" alt="Nice Try">
    </c:otherwise>
</c:choose>


<a href=${HeyBean.authorizationUrl}>
    <button class="waves-effect waves-light btn-large">
        Link Facebook Account
    </button>
</a>
</body>
</html>
