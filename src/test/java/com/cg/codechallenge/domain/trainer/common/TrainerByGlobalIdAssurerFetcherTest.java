package com.cg.codechallenge.domain.trainer.common;

import static com.cg.codechallenge.helper.trainer.TrainerTestHelper.randomTrainer;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.port.out.persistence.QueryTrainerPort;
import com.naharoo.commons.mstoolkit.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainerByGlobalIdAssurerFetcherTest {

  @Mock private QueryTrainerPort queryTrainerPort;
  @InjectMocks private TrainerByGlobalIdAssurerFetcher trainerByGlobalIdAssurerFetcher;

  @AfterEach
  void tearDown() {
    validateMockitoUsage();
    verifyNoMoreInteractions(queryTrainerPort);
  }

  @Test
  public void fetch_normalFlow() {
    final Trainer trainer = randomTrainer();
    final String globalId = trainer.getGlobalId();
    when(queryTrainerPort.findByGlobalId(globalId)).thenReturn(of(trainer));

    final Trainer returnedTrainer = trainerByGlobalIdAssurerFetcher.fetch(globalId);

    assertEquals(trainer, returnedTrainer);
  }

  @Test
  public void fetch_throwsExceptionWhenTrainerNotExists() {
    final String globalId = "ereere";
    when(queryTrainerPort.findByGlobalId(globalId)).thenReturn(empty());

    assertThatThrownBy(() -> trainerByGlobalIdAssurerFetcher.fetch(globalId))
        .isNotNull()
        .isInstanceOf(ResourceNotFoundException.class);
    verify(queryTrainerPort).findByGlobalId(globalId);
  }
}
