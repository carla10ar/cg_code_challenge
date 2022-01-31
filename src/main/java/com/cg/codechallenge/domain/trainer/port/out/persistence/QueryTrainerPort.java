package com.cg.codechallenge.domain.trainer.port.out.persistence;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import java.util.Optional;

public interface QueryTrainerPort {

  Optional<Trainer> findByGlobalId(String id);

  Optional<Trainer> findTopByOrderByIdDesc();
}
