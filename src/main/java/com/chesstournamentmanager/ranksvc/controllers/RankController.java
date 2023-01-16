package com.chesstournamentmanager.ranksvc.controllers;

import com.chesstournamentmanager.ranksvc.models.Rank;
import com.chesstournamentmanager.ranksvc.models.RankRequestModel;
import com.chesstournamentmanager.ranksvc.services.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "rank")
public class RankController {

    private final RankService rankService;

    @Autowired
    public RankController(RankService rankService) {
        this.rankService = rankService;
    }


    @GetMapping
    public Iterable<Rank> getRanks() {
        return rankService.getRanks();
    }

    @GetMapping("/{id}")
    public Rank getRank(
            @PathVariable UUID id) {
        Optional<Rank> rank = rankService.getRank(id);
        if (rank.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Rank with id " + id + " does not exist"
            );
        }
        return rank.get();
    }


    @PostMapping
    public Rank addRank(@RequestBody RankRequestModel rankRequestModel) {
        Rank rank = convertToEntity(rankRequestModel);

        String message = rankService.validateRank(rank);
        if (!message.isEmpty())
        {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    message
            );
        }

        rankService.addNewRank(rank);
        Optional<Rank> returnedRank = rankService.getRank(rank.getId());
        if (returnedRank.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "The rank was not added successfully. Ask the developers to fix this issue."
            );
        }
        return returnedRank.get();
    }

    @PutMapping("/{id}")
    public Rank updateRank(
            @PathVariable UUID id,
            @RequestParam(required = false) short rankNumber,
            @RequestParam(required = false) float points) {
        rankService.updateRank(id, rankNumber, points);
        Optional<Rank> returnedRank = rankService.getRank(id);
        if (returnedRank.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "The rank was not updated successfully. Ask the developers to fix this issue."
            );
        }
        return returnedRank.get();
    }


    @DeleteMapping("/{id}")
    public String deleteRank(@PathVariable UUID id) {
        rankService.deleteRank(id);
        return "Rank with ID " + id + " has been deleted";
    }

    private Rank convertToEntity(RankRequestModel rankRequestModel) {
        return new Rank(
                rankRequestModel.getTournamentId(),
                rankRequestModel.getPlayerId()
        );
    }
}
