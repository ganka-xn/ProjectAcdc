package com.servlet;

import com.model.repository.StatsRepository;
import com.model.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private final StatsRepository statsRepository = new StatsRepository();
    private final UserRepository userRepository = new UserRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("statsRepository", statsRepository);
        session.setAttribute("userRepository", userRepository);

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonClicked = request.getParameter("btnClicked");
        if ("loginBtn".equals(buttonClicked)) {
            response.sendRedirect("login");
        } else if ("regBtn".equals(buttonClicked)) {
            response.sendRedirect("signup");
        }
    }
}
