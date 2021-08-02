<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
        <label for="name">Name:
            <input type="text" name="name" id="name" required>
        </label><br>
        <label for="email">Email:
            <input type="email" name="email" id="email" required>
        </label><br>
        <label for="password">Password:
            <input type="password" name="password" id="password" required>
        </label><br>
        <label for="birthday">Birthday:
            <input type="date" name="birthday" id="birthday" required>
        </label><br>
        <label for="gender">Gender:
            <c:forEach var="gender" items="${requestScope.genders}">
                <input type="radio" name="gender" value="${gender}" id="gender">${gender}
            </c:forEach>
        </label><br>
        <label for="image">Image:
            <input type="file" name="image" id="image">
        </label>
        <button type="submit">Send</button>
    </form>
</body>
</html>
