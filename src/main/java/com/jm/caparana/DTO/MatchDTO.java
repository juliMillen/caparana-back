package com.jm.caparana.DTO;

import com.jm.caparana.Enum.MatchState;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchDTO {

    private Long idMatch;

    private String rival;

    private LocalDateTime dateTime;

    private String location;

    private Integer teamGoals;

    private Integer rivalGoals;

    private MatchState state;
}
