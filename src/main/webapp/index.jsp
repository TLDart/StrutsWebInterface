<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>
<h1>Please Login to Continue</h1>
<s:form action="login" method="post">
    <s:text  name="CC :"  />
    <s:textfield name="ccs"/><br>
    <s:text name="Password:"  />
    <s:textfield name="password" /><br>
    <s:submit  cssClass="waves-effect waves-light btn-large"/>
</s:form>
</body>
</html>
