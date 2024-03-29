package com.servlet;

import com.model.entities.PlayerStats;
import com.model.entities.Question;
import com.model.entities.User;
import com.model.repository.AnswerRepository;
import com.model.repository.QuestionRepository;
import com.model.repository.StatsRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@WebServlet("/question")
public class QuestServlet extends HttpServlet {
    private final QuestionRepository questionRepository = new QuestionRepository();
    private final AnswerRepository answerRepository = new AnswerRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            Optional<Question> firstQuestion = questionRepository.get(1L);
            request.setAttribute("question", firstQuestion);
        } else {
            Long questionID = Long.parseLong(idParam);
            Optional<Question> question = questionRepository.get(questionID);
            request.setAttribute("question", question);
        }
        request.setAttribute("answers", answerRepository);
        request.getRequestDispatcher("/WEB-INF/question.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StatsRepository statsRepository = (StatsRepository) session.getAttribute("statsRepository");

        User user = (User) session.getAttribute("user");
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            long questionID = Long.parseLong(idParam);
            long userAnswerID = Long.parseLong(request.getParameter("answer"));
            long correctAnswerID = questionRepository.getCorrectAnswerID(questionID).orElseThrow();

            if (userAnswerID == correctAnswerID) {
                long nextQuestionId = questionID + 1L;
                Optional<Question> nextQuestion = questionRepository.get(nextQuestionId);
                if (nextQuestion.isPresent()) {
                    response.sendRedirect(request.getContextPath() + "/question?id=" + nextQuestionId);
                } else {
                    statsRepository.updatePlayerStats(user.getId());
                    request.setAttribute("message", "Вы выиграли. Поздравляем!");
                    request.setAttribute("restart", true);
                    request.getRequestDispatcher("WEB-INF/endgame.jsp").forward(request, response);
                }
            } else {
                statsRepository.updatePlayerStats(user.getId());
                request.setAttribute("message", "Неправильный ответ! Игра завершена.");
                request.setAttribute("restart", true);
                request.getRequestDispatcher("WEB-INF/endgame.jsp").forward(request, response);

            }
        } else {
            response.getWriter().println("Некорректный запрос. Параметр 'id' отсутствует.");
        }
    }
}
