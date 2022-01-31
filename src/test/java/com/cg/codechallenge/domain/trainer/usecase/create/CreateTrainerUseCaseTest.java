package com.cg.codechallenge.domain.trainer.usecase.create;

import static com.cg.codechallenge.helper.trainer.TrainerTestHelper.randomTrainerCreationRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.port.out.persistence.CreateTrainerPort;
import com.cg.codechallenge.domain.trainer.usecase.globalId.PopulateTrainerGlobalIdUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateTrainerUseCaseTest {

  @Mock private CreateTrainerPort createTrainerPort;
  @Mock private PopulateTrainerGlobalIdUseCase populateTrainerGlobalIdUseCase;
  @InjectMocks private CreateTrainerUseCase createTrainerUseCase;

  @AfterEach
  void tearDown() {
    validateMockitoUsage();
    verifyNoMoreInteractions(createTrainerPort, populateTrainerGlobalIdUseCase);
  }

  @Test
  public void create_normalFlow() {
    final TrainerCreationRequest trainerCreationRequest = randomTrainerCreationRequest();
    when(createTrainerPort.create(any(Trainer.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    final Trainer createdTrainer = createTrainerUseCase.create(trainerCreationRequest);

    verify(populateTrainerGlobalIdUseCase).populate(any(Trainer.class));
    assertEquals(trainerCreationRequest.getEmail(), createdTrainer.getEmail());
    assertEquals(trainerCreationRequest.getPhone(), createdTrainer.getPhone());
    assertEquals(trainerCreationRequest.getFirstName(), createdTrainer.getFirstName());
    assertEquals(trainerCreationRequest.getLastName(), createdTrainer.getLastName());
  }
}
