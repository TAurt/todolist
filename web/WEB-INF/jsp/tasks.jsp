<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My tasks</title>
    <style>
        <%@include file="/WEB-INF/resources/css/tasks.css"%>
    </style>
</head>
<body>
    <%@include file="header.jsp"%>
    <div>
        <h1>My tasks</h1>
        <form action="${pageContext.request.contextPath}/tasks" method="post" id="add">
            <input type="hidden" name="action" value="create">
        </form>
        <table title="Tasks">
            <tr>
                <th>№</th>
                <th>Title</th>
                <th>Priority</th>
                <th>Status</th>
                <th>Created date</th>
                <th>Scheduled date</th>
                <th>Completed date</th>
                <th>Description</th>
                <th colspan="2"></th>
            </tr>
            <c:forEach var="task" items="${requestScope.tasks}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${task.title}</td>
                    <td>${task.priority}</td>
                    <td>${task.status}</td>
                    <td>${task.createdDate}</td>
                    <td>${task.scheduledDate}</td>
                    <td>${task.completedDate}</td>
                    <td>${task.description}</td>
                    <td>
                        <form class="delete" action="${pageContext.request.contextPath}/tasks" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${task.id}">
                            <button type="submit">delete</button>
                        </form>
                    </td>
                    <td>
                        <form class="edit" action="${pageContext.request.contextPath}/tasks" method="post">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="id" value="${task.id}">
                            <button type="submit">edit</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>new</td>
                <td><input type="text" name="title" form="add"/></td>
                <td>
                    <select size="1" name="priority" form="add">
                        <c:forEach var="priority" items="${requestScope.priority}">
                            <option value="${priority}">${priority}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>NEW</td>
                <td><input type="date" name="createdDate" form="add"/></td>
                <td><input type="date" name="scheduledDate" form="add"/></td>
                <td><input type="date" name="completedDate" form="add"/></td>
                <td><input type="text" name="description" form="add"/></td>
                <td colspan="2"><button type="submit" form="add">Add</button></td>
            </tr>
        </table>
    </div>
</body>
</html>
