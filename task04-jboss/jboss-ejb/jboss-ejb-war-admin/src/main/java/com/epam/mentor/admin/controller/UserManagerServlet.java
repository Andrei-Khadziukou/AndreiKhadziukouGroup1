package com.epam.mentor.admin.controller;

import com.epam.mentor.api.IAccountEjbLocal;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 11/21/14
 */
@WebServlet(description = "User manager servlet", urlPatterns = {"/usermanage"})
public class UserManagerServlet extends HttpServlet {

    @EJB
    private IAccountEjbLocal accountEjb;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        toPage(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        toPage(req, resp);
    }

    private void toPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/usermanage.jsp").forward(req,resp);
    }
}
