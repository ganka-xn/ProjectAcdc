package com.servlet;

import com.model.entities.PlayerStats;
import com.model.entities.User;
import com.model.repository.StatsRepository;
import com.model.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserRepository userRepository = (UserRepository) session.getAttribute("userRepository");

        request.setAttribute("users", userRepository.getAll());
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserRepository userRepository = (UserRepository) session.getAttribute("userRepository");

        String btnClicked = request.getParameter("btnClicked");

        if ("loginBtn".equals(btnClicked)) {
            String selectUser = request.getParameter("selectUser");
            String passwordInput = request.getParameter("passwordInput");

            long userId = Long.parseLong(selectUser);
            Optional<User> userOpt = userRepository.get(userId);

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                if (user.getPassword().equals(passwordInput)) {
                    StatsRepository statsRepository = (StatsRepository) session.getAttribute("statsRepository");
                    statsRepository.addPlayerStats(userId, new PlayerStats(userId, 0));

                    session.setAttribute("user", user);
                    response.sendRedirect("question");
                } else {
                    request.setAttribute("errorMessage", "Invalid password.");
                    request.setAttribute("users", userRepository.getAll());
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            }
        } else if ("regBtn".equals(btnClicked)) {
            response.sendRedirect("signup");
        }
    }
}