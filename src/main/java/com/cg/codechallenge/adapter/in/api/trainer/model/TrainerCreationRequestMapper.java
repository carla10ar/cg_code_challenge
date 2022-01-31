package com.cg.codechallenge.adapter.in.api.trainer.model;

import com.cg.codechallenge.domain.trainer.usecase.create.TrainerCreationRequest;
import com.naharoo.commons.mapstruct.UnidirectionalMapper;
import org.mapstruct.Mapper;

@Mapper
public interface TrainerCreationRequestMapper
    extends UnidirectionalMapper<TrainerCreationRequestDto, TrainerCreationRequest> {}
