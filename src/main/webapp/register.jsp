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
<s:form action="register" method="post">
    <s:text  name="Name:"  />
    <s:textfield  name="name"/><br>
    <s:text name="Password:"  />
    <s:textfield name="password" /><br>
    <s:text name="Departament:" />
    <s:textfield name="department" /><br>
    <s:text name="Address:" />
    <s:textfield name="address" /><br>
    <s:text name="PhoneNumber:" />
    <s:textfield name="phoneNr" /><br>
    <s:text name="cc Number:" />
    <s:textfield name="ccNr" /><br>
    <s:text name="Role:" />
    <s:textfield name="role" placeholder="(0 - student, 1 - teacher, 2 - staff)" /><br>
    <s:text name="CC Validity:" />
    <s:textfield name="ccVal" placeholder="dd/MM/yyyy"/>
    <s:submit  cssClass="waves-effect waves-light btn-large"/>
</s:form>
</body>
</html>