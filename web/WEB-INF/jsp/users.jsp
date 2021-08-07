<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <h1>Users</h1>
    <div>
        <table title="Users">
            <c:forEach var="user" items="${requestScope.users}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.birthday}</td>
                    <td>${user.gender}</td>
                    <td>${user.role}</td>
                    <td>${user.registeredDate}</td>
                    <td><a href="${pageContext.request.contextPath}/users?action=delete&id=${user.id}">
                        <button type="button">delete</button>
                    </a></td>
                    <td><a href="${pageContext.request.contextPath}/edit?id=${user.id}">
                        <button type="button">edit</button>
                    </a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
