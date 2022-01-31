package com.cg.codechallenge.domain.trainer.usecase.globalId;

import static com.cg.codechallenge.helper.trainer.TrainerTestHelper.randomTrainer;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.port.out.persistence.QueryTrainerPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PopulateTrainerGlobalIdUseCaseTest {

  private static final String GLOBAL_ID_1 = "trainer-id-000001";
  private static final String GLOBAL_ID_2 = "trainer-id-000002";
  private static final String GLOBAL_ID_3 = "trainer-id-000003";

  @Mock private QueryTrainerPort queryTrainerPort;
  @InjectMocks private PopulateTrainerGlobalIdUseCase populateTrainerGlobalIdUseCase;

  @AfterEach
  void tearDown() {
    validateMockitoUsage();
    verifyNoMoreInteractions(queryTrainerPort);
  }

  @Test
  public void populateGlobalId_increaseSequence() {
    Trainer trainer = randomTrainer().withGlobalId(null);
    when(queryTrainerPort.findTopByOrderByIdDesc()).thenReturn(empty());
    populateTrainerGlobalIdUseCase.initialize();

    populateTrainerGlobalIdUseCase.populate(trainer);
    assertGlobalId(trainer, GLOBAL_ID_1);

    populateTrainerGlobalIdUseCase.populate(trainer);
    assertGlobalId(trainer, GLOBAL_ID_2);

    populateTrainerGlobalIdUseCase.populate(trainer);
    assertGlobalId(trainer, GLOBAL_ID_3);
  }

  @Test
  public void populateGlobalId_initializeWithExistentSeqAndIncreaseSequence() {
    final Trainer trainer = randomTrainer().withGlobalId(GLOBAL_ID_2);
    when(queryTrainerPort.findTopByOrderByIdDesc()).thenReturn(of(trainer));
    populateTrainerGlobalIdUseCase.initialize();

    populateTrainerGlobalIdUseCase.populate(trainer);
    assertGlobalId(trainer, GLOBAL_ID_3);
  }

  private void assertGlobalId(final Trainer trainer, final String expectedGlobalId) {
    final String globalId = trainer.getGlobalId();
    assertNotNull(globalId);
    assertEquals(expectedGlobalId, globalId);
  }
}
