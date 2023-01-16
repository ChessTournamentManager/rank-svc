package com.chesstournamentmanager.ranksvc.repositories;
import com.chesstournamentmanager.ranksvc.models.Rank;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface RankRepository extends CassandraRepository<Rank, UUID> {
}
