package com.cg.codechallenge.domain.trainer.usecase.getbyid;

import static org.springframework.util.Assert.notNull;

import com.cg.codechallenge.domain.trainer.common.TrainerByGlobalIdAssurerFetcher;
import com.cg.codechallenge.domain.trainer.model.Trainer;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTrainerByIdUseCase {

  private final TrainerByGlobalIdAssurerFetcher trainerByGlobalIdAssurerFetcher;

  @Transactional
  public Trainer getById(final String id) {
    notNull(id, "id cannot be null.");

    return trainerByGlobalIdAssurerFetcher.fetch(id);
  }
}
