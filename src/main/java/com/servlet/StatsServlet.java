package com.servlet;

import com.model.entities.PlayerStats;
import com.model.repository.StatsRepository;
import com.model.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserRepository userRepository = (UserRepository) session.getAttribute("userRepository");
        StatsRepository statsRepository = (StatsRepository) session.getAttribute("statsRepository");
        Map<Long, PlayerStats> playerStatsMap = statsRepository.getAll();

        request.setAttribute("playerStatsMap", playerStatsMap);
        request.setAttribute("users", userRepository.getAll());
        request.getRequestDispatcher("WEB-INF/stats.jsp").forward(request, response);
    }
}
