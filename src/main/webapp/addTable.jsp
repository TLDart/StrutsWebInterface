
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
<s:form action="addTable" method="post">
    <s:text name="Election UID:"  />
    <s:textfield name="uidS" value="test" /><br>
    <s:text name="Table Name:" />
    <s:textfield name="name" /><br>
    <s:submit  cssClass="waves-effect waves-light btn-large"/>
</s:form>
</body>
</html>
