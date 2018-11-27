<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:forEach items="${requestScope.list}" var="user">
    ${user.id}<br>
    ${user.name}<br>
    ${suer.psw}
</c:forEach>
fsfsfgawgawegse
</body>
</html>
