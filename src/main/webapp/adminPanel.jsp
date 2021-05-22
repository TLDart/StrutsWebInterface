<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="Css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>
<c:choose>
    <c:when test="${session.admin == true}">
        <p style="text-align: center">Welcome to the admin Menu</p>
        <s:form action="goToRegister" cssClass="centerButtons">
            <s:submit type="button" value="Register User" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToCreateElection" cssClass="centerButtons">
            <s:submit type="button" value="Create An Election" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToCreateVotingList" cssClass="centerButtons">
            <s:submit type="button" value="Create a Voting List" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToUserList" cssClass="centerButtons">
            <s:submit type="button" value="Show User List" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToElectionList" cssClass="centerButtons">
            <s:submit type="button" value="Show Election List" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToUpdateElection" cssClass="centerButtons">
            <s:submit type="button" value="Update Election" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToFinishedElectionData" cssClass="centerButtons">
            <s:submit type="button" value="Show Finished Election Data" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToAddTable" cssClass="centerButtons">
            <s:submit type="button" value="Add a Table to an Election" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="goToRemoveTable" cssClass="centerButtons">
            <s:submit type="button" value="Remove a Table from an Election"
                      cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="showElection" cssClass="centerButtons">
            <s:submit type="button" value="Show Election Data" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
        <s:form action="showOnlineUsers" cssClass="centerButtons">
            <s:submit type="button" value="Show online users" cssClass="waves-effect waves-light btn-large"/>
        </s:form>
        <br>
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