package com.servlet;

import com.model.repository.StatsRepository;
import com.model.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class IndexServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    private IndexServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        servlet = new IndexServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void doGet_SetsAttributesAndForwardsToIndexJsp() throws ServletException, IOException {
        servlet.doGet(request, response);

        verify(session).setAttribute("statsRepository", any(StatsRepository.class));
        verify(session).setAttribute("userRepository", any(UserRepository.class));
        verify(request).getRequestDispatcher("WEB-INF/index.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_RedirectsToLoginWhenLoginButtonClicked() throws ServletException, IOException {
        when(request.getParameter("btnClicked")).thenReturn("loginBtn");
        servlet.doPost(request, response);

        verify(response).sendRedirect("login");
    }

    @Test
    public void doPost_RedirectsToSignupWhenRegButtonClicked() throws ServletException, IOException {
        when(request.getParameter("btnClicked")).thenReturn("regBtn");
        servlet.doPost(request, response);

        verify(response).sendRedirect("signup");
    }
}
