package com.model.repository;

import com.model.entities.PlayerStats;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class StatsRepository {
    private final Map<Long, PlayerStats> playerStatsMap;

    public StatsRepository() {
        this.playerStatsMap = new HashMap<>();
    }

    public Map<Long, PlayerStats> getAll() {
        return playerStatsMap;
    }

    public Optional<PlayerStats> get(Long playerId) {
        return Optional.ofNullable(playerStatsMap.get(playerId));
    }

    public void addPlayerStats(Long playerId, PlayerStats playerStats) {
        if (!playerStatsMap.containsKey(playerId)) {
            playerStatsMap.put(playerId, playerStats);
        }
    }

    public void updatePlayerStats(Long playerId) {
        PlayerStats playerStats = playerStatsMap.get(playerId);
        if (playerStats != null) {
            playerStats.setGamesPlayed(playerStats.getGamesPlayed() + 1);
        }
    }

}
