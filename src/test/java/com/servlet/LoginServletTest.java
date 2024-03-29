package com.servlet;


import com.model.entities.Role;
import com.model.entities.User;
import com.model.repository.StatsRepository;
import com.model.repository.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    @Test
    public void test_get_request_returns_login_jsp_with_all_users() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        UserRepository userRepository = mock(UserRepository.class);
        StatsRepository statsRepository = mock(StatsRepository.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userRepository")).thenReturn(userRepository);
        when(userRepository.getAll()).thenReturn(Arrays.asList(
            new User(1L, "Alisa", "qwerty", Role.USER),
            new User(2L, "Bob", "", Role.GUEST),
            new User(3L, "Carl", "admin", Role.ADMIN),
            new User(4L, "Smith", "admin", Role.ADMIN)
        ));

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/login.jsp")).thenReturn(requestDispatcher);

        // When
        new LoginServlet().doGet(request, response);

        // Then
        verify(request).setAttribute("users", userRepository.getAll());
        verify(requestDispatcher).forward(request, response);
    }



}
