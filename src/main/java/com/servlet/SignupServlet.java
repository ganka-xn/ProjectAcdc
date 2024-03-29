package com.servlet;

import com.model.entities.User;
import com.model.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);
    }

    @SneakyThrows
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession();
        UserRepository userRepository = (UserRepository) session.getAttribute("userRepository");

        String username = request.getParameter("username");
        String pass = request.getParameter("password");

        Optional<User> existingUser = userRepository.getUserByUsername(username);

        if (existingUser.isEmpty()) {
            User newUser = User.builder()
                .login(username)
                .password(pass)
                .build();
            userRepository.create(newUser);
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,response);
        } else {
            response.getWriter().println("Пользователь с таким именем уже существует");
        }
    }
}
