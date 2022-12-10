package com.chesstournamentmanager.ranksvc.models;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(value = "rank")
public class Rank {
    private static final short DEFAULT_RANK_NUMBER = 1;
    @PrimaryKey
    private final UUID id;
    @Column("tournament_id")
    private final UUID tournamentId;
    @Column("player_id")
    private final UUID playerId;
    @Column("rank_number")
    private short rankNumber;
    @Column("points")
    private float points;
    @Column("created_at")
    private final LocalDateTime createdAt;

    public Rank (UUID tournamentId, UUID playerId) {
        this.id = UUID.randomUUID();
        this.tournamentId = tournamentId;
        this.playerId = playerId;
        this.rankNumber = DEFAULT_RANK_NUMBER;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getTournamentId() {
        return tournamentId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public short getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(short rankNumber) {
        this.rankNumber = rankNumber;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
