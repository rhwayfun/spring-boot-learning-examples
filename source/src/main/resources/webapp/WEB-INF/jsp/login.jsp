<!DOCTYPE html>

<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>

<html>
<head>
    <meta charset="utf-8">
    <title>Welcome SpringBoot</title>
    <script src="/js/jquery-3.1.1.min.js"></script>
    <script src="/js/index.js"></script>
</head>
<body>

<form name="f" action="/login" method="post">
    <input id="name" name="username" type="text"/><br>
    <input id="password" name="password" type="password"><br>
    <input type="submit" value="login">
    <input name="_csrf" type="hidden" value="${_csrf}"/>
</form>

<p id="users">

</p>

<script>
    $(function () {
        $('[name=f]').focus()
    })
</script>
</body>

</html>