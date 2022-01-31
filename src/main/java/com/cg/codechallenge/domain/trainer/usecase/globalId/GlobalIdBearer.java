package com.cg.codechallenge.domain.trainer.usecase.globalId;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GlobalIdBearer {
  TRAINER("trainer-id-");

  @Getter private final String prefix;
}
