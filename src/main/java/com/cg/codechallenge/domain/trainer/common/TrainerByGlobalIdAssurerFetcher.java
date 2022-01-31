package com.cg.codechallenge.domain.trainer.common;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.port.out.persistence.QueryTrainerPort;
import com.naharoo.commons.mstoolkit.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TrainerByGlobalIdAssurerFetcher {

  private final QueryTrainerPort queryTrainerPort;

  @Transactional(readOnly = true)
  public Trainer fetch(final String globalId) {
    return queryTrainerPort
        .findByGlobalId(globalId)
        .orElseThrow(() -> ResourceNotFoundException.createInstance(Trainer.class, "id", globalId));
  }
}
