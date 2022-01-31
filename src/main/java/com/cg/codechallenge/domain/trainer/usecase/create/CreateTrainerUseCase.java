package com.cg.codechallenge.domain.trainer.usecase.create;

import static org.springframework.util.Assert.notNull;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.port.out.persistence.CreateTrainerPort;
import com.cg.codechallenge.domain.trainer.usecase.globalId.PopulateTrainerGlobalIdUseCase;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateTrainerUseCase {

  private final CreateTrainerPort createTrainerPort;
  private final PopulateTrainerGlobalIdUseCase populateTrainerGlobalIdUseCase;

  @Transactional
  public Trainer create(final TrainerCreationRequest creationRequest) {
    notNull(creationRequest, "creationRequest cannot be null.");

    final Trainer trainer =
        Trainer.builder()
            .email(creationRequest.getEmail())
            .phone(creationRequest.getPhone())
            .firstName(creationRequest.getFirstName())
            .lastName(creationRequest.getLastName())
            .build();
    populateTrainerGlobalIdUseCase.populate(trainer);

    return createTrainerPort.create(trainer);
  }
}
