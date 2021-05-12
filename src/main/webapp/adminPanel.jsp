<%@ taglib prefix="s" uri="/struts-tags" %>
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
<s:form action="redirectUserList" cssClass="centerButtons">
<s:submit type="button" value="Update Election" cssClass="waves-effect waves-light btn-large"/>
</s:form>
<br>
<s:form action="redirectUserList" cssClass="centerButtons">
    <s:submit type="button" value="Show Finished Election Data" cssClass="waves-effect waves-light btn-large"/>
</s:form>
<br>
<s:form action="redirectUserList" cssClass="centerButtons">
    <s:submit type="button" value="Show Realtime Data" cssClass="waves-effect waves-light btn-large"/>
</s:form>
<br>
<s:form action="goToAddTable" cssClass="centerButtons">
    <s:submit type="button" value="Add a Table to an Election" cssClass="waves-effect waves-light btn-large"/>
</s:form>
<br>
<s:form action="goToRemoveTable" cssClass="centerButtons">
    <s:submit type="button" value="Remove a Table from an Election" cssClass="waves-effect waves-light btn-large"/>
</s:form>
<br>
</body>
</html>