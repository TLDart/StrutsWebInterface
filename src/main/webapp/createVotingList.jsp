
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="Css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <title>RegisterUser</title>
</head>
<body>
<s:form action="createVotingList" method="post">
    <s:text name="Election UID:"  />
    <s:textfield name="uidS" /><br>
    <s:text name="List name:"  />
    <s:textfield name="name" /><br>
    <s:text name="Type:" />
    <s:textfield name="typeS" placeholder="(0 - student, 1 - teacher, 2 - staff)" /><br>
    <s:text name="MemberList:" />
    <s:textfield name="memberList" placeholder="Insert the members separated by ','"/><br>
    <s:submit  cssClass="waves-effect waves-light btn-large"/>
</s:form>
</body>
</html>
