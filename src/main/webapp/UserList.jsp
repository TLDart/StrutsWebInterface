<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="Css/main.css">
    <title>UserList</title>

</head>
<body>
<c:choose>
    <c:when test="${session.admin == true}">
        <s:form action="showUserList" method="post">
            <s:text name="Department"/>
            <s:textfield name="department"/><br>
            <s:text name="Type"/>
            <s:textfield name="typeS"/><br>
            <s:submit cssClass="waves-effect waves-light btn-large"/>
        </s:form>
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

</body>
</html>
