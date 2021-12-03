package com.teachmeskills.hibernateintro.controller;

import com.teachmeskills.hibernateintro.model.User;
import com.teachmeskills.hibernateintro.service.BaseService;
import com.teachmeskills.hibernateintro.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private BaseService<User> service;

    public void init() {
        service = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/new" -> showNewForm(req, resp);
                case "/insert" -> insertUser(req, resp);
                case "/update" -> updateUser(req, resp);
                case "/delete" -> deleteUser(req, resp);
                case "/edit" -> showEditForm(req, resp);
                default -> listUser(req, resp);
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = service.findAll();
        req.setAttribute("listUser", users);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/list-user.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lName = req.getParameter("lastName");
        String address = req.getParameter("address");
        boolean isMarried = req.getParameter("isMarried").equals("true");
        User user = new User(name, lName, address, isMarried);
        service.create(user);
        resp.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String lName = req.getParameter("lastName");
        String address = req.getParameter("address");
        boolean isMarried = req.getParameter("isMarried").equals("true");
        User user = new User(name, lName, address, isMarried);
        user.setId(id);
        service.update(user);
        resp.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteById(id);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/user-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existedUser = service.findById(id);
        req.setAttribute("user", existedUser);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/WEB-INF/pages/user-form.jsp");
        dispatcher.forward(req, resp);
    }
}
