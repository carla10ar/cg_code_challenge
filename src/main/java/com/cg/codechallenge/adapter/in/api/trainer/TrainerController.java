package com.cg.codechallenge.adapter.in.api.trainer;

import static org.springframework.http.HttpStatus.CREATED;

import com.cg.codechallenge.adapter.in.api.trainer.model.TrainerCreationRequestDto;
import com.cg.codechallenge.adapter.in.api.trainer.model.TrainerDto;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Validated
public interface TrainerController {

  String PATH = "/api/v1/trainer";

  @PostMapping(PATH)
  @ResponseStatus(CREATED)
  TrainerDto create(
      @Valid @NotNull @RequestBody TrainerCreationRequestDto trainerCreationRequestDto);

  @GetMapping(PATH + "/{id}")
  TrainerDto getById(@NotNull @PathVariable("id") String id);
}
