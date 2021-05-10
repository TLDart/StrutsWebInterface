<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <title>UserList</title>

</head>
<body>
<s:form action="showUserList" method="post">
    <s:text  name="Department"  />
    <s:textfield name="department"/><br>
    <s:text  name="Type"  />
    <s:textfield name="typeS"/><br>
    <s:submit  cssClass="waves-effect waves-light btn-large"/>
</s:form>
</body>
</html>
