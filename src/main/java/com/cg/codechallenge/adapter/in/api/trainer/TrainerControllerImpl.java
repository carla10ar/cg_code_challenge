package com.cg.codechallenge.adapter.in.api.trainer;

import com.cg.codechallenge.adapter.in.api.trainer.model.TrainerCreationRequestDto;
import com.cg.codechallenge.adapter.in.api.trainer.model.TrainerDto;
import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.usecase.create.CreateTrainerUseCase;
import com.cg.codechallenge.domain.trainer.usecase.create.TrainerCreationRequest;
import com.cg.codechallenge.domain.trainer.usecase.getbyid.GetTrainerByIdUseCase;
import com.naharoo.commons.mapstruct.MappingFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TrainerControllerImpl implements TrainerController {

  private final MappingFacade mappingFacade;
  private final CreateTrainerUseCase createTrainerUseCase;
  private final GetTrainerByIdUseCase getTrainerByIdUseCase;

  @Override
  public TrainerDto create(final TrainerCreationRequestDto trainerCreationRequestDto) {
    log.info("Creating new trainer with email={}", trainerCreationRequestDto.getEmail());

    final TrainerCreationRequest trainerCreationRequest =
        mappingFacade.map(trainerCreationRequestDto, TrainerCreationRequest.class);
    final Trainer trainer = createTrainerUseCase.create(trainerCreationRequest);
    log.info(
        "Successfully created trainer with id={} and email={}",
        trainer.getGlobalId(),
        trainer.getEmail());
    return mappingFacade.map(trainer, TrainerDto.class);
  }

  @Override
  public TrainerDto getById(final String id) {
    log.info("Retrieving trainer by id={}", id);
    final Trainer trainer = getTrainerByIdUseCase.getById(id);
    log.info(
        "Successfully returned trainer with id={} and email={}",
        trainer.getGlobalId(),
        trainer.getEmail());
    return mappingFacade.map(trainer, TrainerDto.class);
  }
}
