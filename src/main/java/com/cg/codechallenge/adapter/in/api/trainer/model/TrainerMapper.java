package com.cg.codechallenge.adapter.in.api.trainer.model;

import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.naharoo.commons.mapstruct.UnidirectionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TrainerMapper extends UnidirectionalMapper<Trainer, TrainerDto> {

  @Mapping(target = "id", source = "globalId")
  TrainerDto map(Trainer source);
}
