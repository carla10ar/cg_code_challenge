package com.cg.codechallenge.helper.trainer;

import com.cg.codechallenge.adapter.in.api.trainer.model.TrainerCreationRequestDto;
import com.cg.codechallenge.domain.trainer.model.Trainer;
import com.cg.codechallenge.domain.trainer.usecase.create.TrainerCreationRequest;
import org.jeasy.random.EasyRandom;

public class TrainerTestHelper {

  private static final EasyRandom RANDOM = new EasyRandom();

  public static Trainer randomTrainer() {
    return RANDOM.nextObject(Trainer.class);
  }

  public static TrainerCreationRequest randomTrainerCreationRequest() {
    return RANDOM.nextObject(TrainerCreationRequest.class).withEmail(randomEmail());
  }

  public static TrainerCreationRequestDto randomTrainerCreationRequestDto() {
    return RANDOM.nextObject(TrainerCreationRequestDto.class).withEmail(randomEmail());
  }

  private static String randomEmail() {
    return "email" + RANDOM.nextInt() + "@cg.com";
  }
}
