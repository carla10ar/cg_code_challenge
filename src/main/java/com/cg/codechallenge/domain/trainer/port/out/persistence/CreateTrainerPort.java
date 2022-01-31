package com.cg.codechallenge.domain.trainer.port.out.persistence;

import com.cg.codechallenge.domain.trainer.model.Trainer;

public interface CreateTrainerPort {

  Trainer create(Trainer trainer);
}
