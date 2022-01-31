package com.cg.codechallenge.domain.trainer.usecase.globalId;

import static java.lang.String.format;
import static org.springframework.util.Assert.notNull;

import com.cg.codechallenge.domain.trainer.model.GlobalIdAware;
import com.cg.codechallenge.domain.trainer.port.out.persistence.QueryTrainerPort;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PopulateTrainerGlobalIdUseCase {

  private static final String GLOBAL_ID_TEMPLATE = "%s%06d";
  private static Long TRAINER_SEQ = 0L;

  private final QueryTrainerPort queryTrainerPort;

  public void populate(final GlobalIdAware globalIdAware) {
    notNull(globalIdAware, "globalIdAware cannot be null.");

    final String bearerAbbreviation = GlobalIdBearer.TRAINER.getPrefix();
    final Long sequenceNumber = getNextSequence();

    final String globalId = format(GLOBAL_ID_TEMPLATE, bearerAbbreviation, sequenceNumber);

    globalIdAware.setGlobalId(globalId);
  }

  @PostConstruct
  void initialize() {
    TRAINER_SEQ =
        queryTrainerPort
            .findTopByOrderByIdDesc()
            .map(t -> extractLastSequenceNumber(t.getGlobalId()))
            .orElse(0L);
  }

  @Synchronized
  private Long getNextSequence() {
    return TRAINER_SEQ += 1;
  }

  private Long extractLastSequenceNumber(final String globalId) {
    final Pattern p = Pattern.compile("\\d+");
    final Matcher m = p.matcher(globalId);
    return m.find() ? Long.parseLong(m.group()) : 0L;
  }
}
