
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
<s:form action="createElection" method="post">
    <s:text name="Election Title:"  />
    <s:textfield name="title" /><br>
    <s:text name="Description:" />
    <s:textfield name="description" /><br>
    <s:text name="Department:" />
    <s:textfield name="department" /><br>
    <s:text name="Type:" />
    <s:textfield name="typeS" placeholder="(0 - student, 1 - teacher, 2 - staff)" /><br>
    <s:text name="Start Time:" />
    <s:textfield name="stTimeVal" placeholder="yyyy/MM/dd/hh/mm"/>
    <s:text name="End Time:" />
    <s:textfield name="endTimeVal" placeholder="yyyy/MM/dd/hh/mm"/>
    <s:submit  cssClass="waves-effect waves-light btn-large"/>
</s:form>
</body>
</html>
