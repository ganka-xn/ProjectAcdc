package com.servlet;

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

/** todo допилить */

public class IndexServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    IndexServlet servlet;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);

        MockitoAnnotations.openMocks(this);
        servlet = new IndexServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void doPost_redirectsToLogin_when_loginButtonClicked() throws ServletException, IOException {
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
