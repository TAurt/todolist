package com.mtx.todolist.servlet;

import com.mtx.todolist.dto.CreateTaskDto;
import com.mtx.todolist.dto.UserDto;
import com.mtx.todolist.entity.Priority;
import com.mtx.todolist.entity.Status;
import com.mtx.todolist.service.TaskService;
import com.mtx.todolist.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/tasks")
public class TasksServlet extends HttpServlet {

    private final TaskService taskService = TaskService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var tasks = taskService.getAllByUserId(user.getId());
        req.setAttribute("tasks", tasks);
        req.setAttribute("priority", Arrays.stream(Priority.values())
                .map(Enum::name)
                .collect(Collectors.toSet()));
        req.setAttribute("status", Arrays.stream(Status.values())
                .map(Enum::name)
                .collect(Collectors.toSet()));
        req.getRequestDispatcher(JspHelper.getPath("tasks"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var action = req.getParameter("action");
        if (action != null && action.equals("create")) {
            var createTaskDto = CreateTaskDto.builder()
                    .userId(String.valueOf(((UserDto)req.getSession().getAttribute("user")).getId()))
                    .title(req.getParameter("title"))
                    .priority(req.getParameter("priority"))
                    .status(Status.RUNNING.name())
                    .createdDate(req.getParameter("createdDate"))
                    .scheduledDate(req.getParameter("scheduledDate"))
                    .description(req.getParameter("description"))
                    .build();
            taskService.create(createTaskDto);
            resp.sendRedirect("/tasks");
            return;
        }
    }
}
