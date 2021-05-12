<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post Login Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>
<jsp:useBean id="HeyBean" scope="request" type="webLogic.model.HeyBean"/>
    <c:forEach items="${HeyBean.validElections}" var="value">
        <c:out value="${value}" />
        <br>
    </c:forEach>

    <s:form action="reg" method="post">
        <s:text  name="Select The Election:"  />
        <s:textfield name="ccs"/><br>
        <s:submit  cssClass="waves-effect waves-light btn-large"/>
    </s:form>
</body>
</html>
