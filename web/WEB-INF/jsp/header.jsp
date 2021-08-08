<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="/WEB-INF/resources/css/header.css"%>
</style>
<div class="header">
    <c:if test="${not empty sessionScope.user}">
        <span class="user">Hello, ${sessionScope.user.name}</span>
        <form  class="logout" action="${pageContext.request.contextPath}/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </c:if>
</div>
