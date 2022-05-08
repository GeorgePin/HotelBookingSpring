<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:form method="post" modelAttribute="user" action="login">

    <spring:label path="login">Login</spring:label>
    <spring:input path="login"/>

    <spring:label path="password">Password</spring:label>
    <spring:input path="password"/>

    <input type="submit" value="Submit"/>
</spring:form>
<h1>${name}</h1>
</body>
</html>
