package com.chesstournamentmanager.ranksvc.services;

import com.chesstournamentmanager.ranksvc.models.Rank;
import com.chesstournamentmanager.ranksvc.repositories.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class RankService {
    private final RankRepository rankRepository;

    @Autowired
    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    // Read methods

    public Iterable<Rank> getRanks() {
        return rankRepository.findAll();
    }

    public Optional<Rank> getRank(UUID id) {
        return rankRepository.findById(id);
    }

    // Update methods

    public void addNewRank(Rank rank) {
        rankRepository.save(rank);
    }

    // Update methods

    public void updateRank(UUID id, short rankNumber, float points) {
        boolean changedValue = false;

        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Rank with id " + id + " does not exist"
                ));

        if (rankNumber > 0 &&
                !Objects.equals(rank.getRankNumber(), rankNumber)) {
            rank.setRankNumber(rankNumber);
            changedValue = true;
        }

        if (points >= 0f &&
                !Objects.equals(rank.getPoints(), points)) {
            rank.setPoints(points);
            changedValue = true;
        }

        if (changedValue) {
            rankRepository.save(rank);
        }
    }

    // Delete methods

    public void deleteRank(UUID id) {
        boolean exists = rankRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Rank with id " + id + " does not exist");
        }

        rankRepository.deleteById(id);
    }

    // Validation methods

    public String validateRank(Rank rank) {
        if (rank.getTournamentId().toString().isBlank()) {
            return "No tournament id was provided";
        }

        if (rank.getPlayerId().toString().isBlank()) {
            return "No player id was provided";
        }

        return "";
    }
}
