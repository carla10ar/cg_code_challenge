package com.cg.codechallenge.adapter.out.trainer.persistence;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

  Optional<Trainer> findByGlobalId(String globalId);

  Optional<Trainer> findTopByOrderByIdDesc();
}
