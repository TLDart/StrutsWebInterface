<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
</head>
<body>
<jsp:useBean id="HeyBean" scope="request" type="webLogic.model.HeyBean"/>
<table>
            <c:out value="${HeyBean.electionDataMessage}" /><br>

</table>

</body>
</html>
