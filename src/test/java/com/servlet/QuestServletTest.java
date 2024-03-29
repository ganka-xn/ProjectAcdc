package com.servlet;

import com.model.entities.Question;
import com.model.entities.User;
import com.model.repository.AnswerRepository;
import com.model.repository.QuestionRepository;
import com.model.repository.StatsRepository;
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
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

public class QuestServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private QuestionRepository questionRepository;
//
//    @Mock
//    private AnswerRepository answerRepository;

    @Mock
    private StatsRepository statsRepository;

    @Mock
    private PrintWriter printWriter;

    private QuestServlet servlet;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        servlet = new QuestServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void doPost_WithIncorrectAnswer_ForwardsToEndgameJsp() throws ServletException, IOException {

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("answer")).thenReturn("24");
        when(questionRepository.getCorrectAnswerID(1L)).thenReturn(Optional.of(42L));
        User user = new User();
        user.setId(1L);
        when(session.getAttribute("user")).thenReturn(user);
        when(session.getAttribute("statsRepository")).thenReturn(statsRepository);

        servlet.doPost(request, response);

        verify(statsRepository).updatePlayerStats(user.getId());
        verify(request).setAttribute(eq("message"), anyString());
        verify(request).setAttribute(eq("restart"), eq(true));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_WithoutIdParam_WritesErrorMessage() throws ServletException, IOException {

        when(request.getParameter("id")).thenReturn(null);

        servlet.doPost(request, response);

        verify(printWriter).println("Некорректный запрос. Параметр 'id' отсутствует.");
    }
}

