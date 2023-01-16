package com.chesstournamentmanager.ranksvc.models;

import java.util.UUID;

public class RankRequestModel {
    private UUID tournamentId;
    private UUID playerId;

    public RankRequestModel(UUID tournamentId, UUID playerId) {
        this.tournamentId = tournamentId;
        this.playerId = playerId;
    }

    public UUID getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(UUID tournamentId) {
        this.tournamentId = tournamentId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }
}
