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
        <table title="Tasks">
            <tr>
                <th>№</th>
                <th>Title</th>
                <th>Priority</th>
                <th>Status</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Completed date</th>
                <th>Description</th>
                <th colspan="2"></th>
            </tr>
            <c:forEach var="task" items="${requestScope.tasks}" varStatus="loop">
                <form class="delete" action="${pageContext.request.contextPath}/tasks" method="post" id="delete + ${task.id}">
                    <input type="hidden" name="action" value="delete">
                </form>
                <form class="edit" action="${pageContext.request.contextPath}/tasks" method="post" id="edit + ${task.id}">
                    <input type="hidden" name="action" value="edit">
                </form>
                <input name="startDate" value="${task.startDate}" type="hidden" form="edit + ${task.id}"/>
                <tr style="background-color:${task.status == 'RUNNING' ? 'lightblue' : task.status == 'COMPLETED' ? 'lightgreen' : 'pink'}">
                    <td>${loop.index + 1}</td>
                    <td><input type="text" name="title" value="${task.title}" form="edit + ${task.id}" required/></td>
                    <td>
                        <select size="1" name="priority" form="edit + ${task.id}">
                            <c:forEach var="priority" items="${requestScope.priority}">
                                <c:if test="${task.priority != priority}">
                                    <option value="${priority}">${priority}</option>
                                </c:if>
                            </c:forEach>
                            <option value="${task.priority}" selected>${task.priority}</option>
                        </select>
                    </td>
                    <td>
                        <select size="1" name="status" form="edit + ${task.id}">
                            <c:forEach var="status" items="${requestScope.status}">
                                <c:if test="${task.status != status}">
                                    <option value="${status}">${status}</option>
                                </c:if>
                            </c:forEach>
                            <option value="${task.status}" selected>${task.status}</option>
                        </select>
                    </td>
                    <td>${task.startDate}</td>
                    <td><input type="date" name="endDate" value="${task.endDate}" form="edit + ${task.id}" required/></td>
                    <td>${task.completedDate}</td>
                    <td><textarea type="text" name="description" form="edit + ${task.id}">${task.description}</textarea></td>
                    <td>
                        <input type="hidden" name="id" value="${task.id}" form="delete + ${task.id}">
                        <button type="submit" form="delete + ${task.id}">delete</button>
                    </td>
                    <td>
                        <c:if test="${task.status == 'RUNNING'}">
                            <input type="hidden" name="id" value="${task.id}" form="edit + ${task.id}"/>
                            <button type="submit" form="edit + ${task.id}">edit</button>
                        </c:if>
                        <c:if test="${task.status != 'RUNNING'}">
                            Not edit
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="10"></td>
            </tr>
            <tr style="background-color: #D8E6F3">
                <form action="${pageContext.request.contextPath}/tasks" method="post" id="add">
                    <input type="hidden" name="action" value="create">
                </form>
                <td>New</td>
                <td><input class="add" type="text" name="title" value="New task" form="add" required/></td>
                <td>
                    <select class="add" size="1" name="priority" form="add">
                        <c:forEach var="priority" items="${requestScope.priority}">
                            <option value="${priority}">${priority}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>NEW</td>
                <td><input class="add" type="date" name="startDate" form="add" required/></td>
                <td><input class="add" type="date" name="endDate" form="add" required/></td>
                <td colspan="2"><textarea class="add" type="text" name="description" form="add">Description</textarea></td>
                <td colspan="2"><button type="submit" form="add">Add</button></td>
            </tr>
        </table>
    </div>
    <c:if test="${not empty requestScope.errors}">
        <div class="errors" style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span><br>
            </c:forEach>
        </div>
    </c:if>
</body>
</html>
