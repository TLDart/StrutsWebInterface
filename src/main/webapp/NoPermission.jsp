<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>You Sneaky Boy</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link rel="stylesheet" href="Css/main.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<h1 style="text-align: center">Actions cannot be called Directly, please use the correct page</h1>
<s:form action="index" cssClass="centerButtons">
    <s:submit type="button" value="Go To Login Page" cssClass="waves-effect waves-light btn-large"/>
</s:form>
<br>
<img style="width:50%;" class="center-img" src="img/callit.gif" alt="Nice Try">
</body>
</html>
