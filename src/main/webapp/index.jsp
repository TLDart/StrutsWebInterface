<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>

<h1>Please Login to Continue</h1>
<jsp:useBean id="HeyBean" scope="session" class="webLogic.model.HeyBean"/>
<s:form action="login" method="post">
    <s:text  name="CC :"  />
    <s:textfield name="ccs"/><br>
    <s:text name="Password:"  />
    <s:textfield name="password" /><br>
    <s:submit  cssClass="waves-effect waves-light btn-large"/>
</s:form>
    <a href = ${HeyBean.loginFacebookUrl}>
        <button class="waves-effect waves-light btn-large">
            Login with Facebook
        </button></a>
</body>
</html>
