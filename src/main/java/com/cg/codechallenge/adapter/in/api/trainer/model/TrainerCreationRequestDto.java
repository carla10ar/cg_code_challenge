package com.cg.codechallenge.adapter.in.api.trainer.model;

import com.cg.codechallenge.adapter.in.api.trainer.common.validation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrainerCreationRequestDto {
  @NotBlank @ValidEmail private String email;
  @NotBlank private String phone;
  @NotBlank private String firstName;
  @NotBlank private String lastName;
}
