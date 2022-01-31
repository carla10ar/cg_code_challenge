package com.cg.codechallenge.adapter.out.trainer.persistence;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.port.out.persistence.CreateTrainerPort;
import com.cg.codechallenge.domain.trainer.port.out.persistence.QueryTrainerPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TrainerPersistenceAdapter implements CreateTrainerPort, QueryTrainerPort {

  private final TrainerRepository trainerRepository;

  @Override
  public Trainer create(Trainer trainer) {
    return trainerRepository.save(trainer);
  }

  @Override
  public Optional<Trainer> findByGlobalId(String id) {
    return trainerRepository.findByGlobalId(id);
  }

  @Override
  public Optional<Trainer> findTopByOrderByIdDesc() {
    return trainerRepository.findTopByOrderByIdDesc();
  }
}
