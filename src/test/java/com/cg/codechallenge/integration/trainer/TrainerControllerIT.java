package com.cg.codechallenge.integration.trainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cg.codechallenge.adapter.in.api.trainer.model.TrainerCreationRequestDto;
import com.cg.codechallenge.adapter.in.api.trainer.model.TrainerDto;
import com.cg.codechallenge.helper.trainer.TrainerTestHelper;
import com.cg.codechallenge.integration.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class TrainerControllerIT extends BaseIT {

  @Test
  public void createTrainer() {
    final TrainerCreationRequestDto trainerCreationRequestDto =
        TrainerTestHelper.randomTrainerCreationRequestDto();
    final ResponseEntity<TrainerDto> response =
        testRestTemplate.postForEntity(
            "/api/v1/trainer", trainerCreationRequestDto, TrainerDto.class);

    assertNotNull(response);
    assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  @Test
  public void createTrainerAndGetById() {
    final TrainerCreationRequestDto trainerCreationRequestDto =
        TrainerTestHelper.randomTrainerCreationRequestDto();
    final TrainerDto createdTrainer =
        testRestTemplate.postForObject(
            "/api/v1/trainer", trainerCreationRequestDto, TrainerDto.class);

    assertNotNull(createdTrainer);

    final TrainerDto returnedTrainer =
        testRestTemplate.getForObject(
            "/api/v1/trainer/" + createdTrainer.getId(), TrainerDto.class);

    assertNotNull(returnedTrainer);
    assertEquals(createdTrainer.getId(), returnedTrainer.getId());
    assertEquals(createdTrainer.getEmail(), returnedTrainer.getEmail());
    assertEquals(createdTrainer.getPhone(), returnedTrainer.getPhone());
    assertEquals(createdTrainer.getFirstName(), returnedTrainer.getFirstName());
    assertEquals(createdTrainer.getLastName(), returnedTrainer.getLastName());
  }
}
