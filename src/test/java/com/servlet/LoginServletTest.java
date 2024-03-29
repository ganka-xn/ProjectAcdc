package com.servlet;


import com.model.entities.PlayerStats;
import com.model.entities.Role;
import com.model.entities.User;
import com.model.repository.StatsRepository;
import com.model.repository.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class LoginServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserRepository userRepository;
    private StatsRepository statsRepository;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userRepository = mock(UserRepository.class);
        statsRepository = mock(StatsRepository.class);
    }

    @Test
    public void test_getRequest_returns_login_jsp_with_allUsers() throws ServletException, IOException {

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

    @Test
    public void test_post_request_with_valid_user_and_password_logs_in_and_redirects_to_question_page() throws ServletException, IOException {
        // Given
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userRepository")).thenReturn(userRepository);
        when(session.getAttribute("statsRepository")).thenReturn(statsRepository);

        when(request.getParameter("btnClicked")).thenReturn("loginBtn");
        when(request.getParameter("selectUser")).thenReturn("1");
        when(request.getParameter("passwordInput")).thenReturn("qwerty");

        User user = new User(1L, "Alisa", "qwerty", Role.USER);
        when(userRepository.get(1L)).thenReturn(Optional.of(user));

        // When
        new LoginServlet().doPost(request, response);

        // Then
        verify(statsRepository).addPlayerStats(1L, new PlayerStats(1L, 0));
        verify(session).setAttribute("user", user);
        verify(response).sendRedirect("question");
    }
}