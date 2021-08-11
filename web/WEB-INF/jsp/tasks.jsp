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
        <form class="delete" action="${pageContext.request.contextPath}/tasks" method="post" id="delete">
            <input type="hidden" name="action" value="delete">
        </form>
        <form class="edit" action="${pageContext.request.contextPath}/tasks" method="post" id="edit">
            <input type="hidden" name="action" value="edit">
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
                    <td><input type="text" name="title" value="${task.title}" form="edit"/></td>
                    <td>
                        <select size="1" name="priority" form="edit">
                            <c:forEach var="priority" items="${requestScope.priority}">
                                <c:if test="${task.priority != priority}">
                                    <option value="${priority}">${priority}</option>
                                </c:if>
                            </c:forEach>
                            <option value="${task.priority}" selected>${task.priority}</option>
                        </select>
                    </td>
                    <td>
                        <select size="1" name="status" form="edit">
                            <c:forEach var="status" items="${requestScope.status}">
                                <c:if test="${task.status != status}">
                                    <option value="${status}">${status}</option>
                                </c:if>
                            </c:forEach>
                            <option value="${task.status}" selected>${task.status}</option>
                        </select>
                    </td>
                    <td>${task.createdDate}</td>
                    <td><input type="date" name="scheduledDate" value="${task.scheduledDate}" form="edit"/></td>
                    <td>${task.completedDate}</td>
                    <td><textarea type="text" name="description" form="edit">${task.description}</textarea></td>
                    <td>
                        <input type="hidden" name="id" value="${task.id}" form="delete">
                        <button type="submit" form="delete">delete</button>
                    </td>
                    <td>
                        <input type="hidden" name="id" value="${task.id}" form="edit"/>
                        <button type="submit" form="edit">edit</button>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10"></td>
            </tr>
            <tr>
                <td>New</td>
                <td><input class="add" type="text" name="title" value="New task" form="add"/></td>
                <td>
                    <select class="add" size="1" name="priority" form="add">
                        <c:forEach var="priority" items="${requestScope.priority}">
                            <option value="${priority}">${priority}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>NEW</td>
                <td><input class="add" type="date" name="createdDate" form="add"/></td>
                <td><input class="add" type="date" name="scheduledDate" form="add"/></td>
                <td colspan="2"><textarea class="add" type="text" name="description" form="add">Description</textarea></td>
                <td colspan="2"><button type="submit" form="add">Add</button></td>
            </tr>
        </table>
    </div>
</body>
</html>
