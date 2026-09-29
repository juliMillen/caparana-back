package com.jm.caparana.Entity;

import com.jm.caparana.Enum.MatchState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMatch;

    private String rival;

    private LocalDateTime dateTime;

    private String location;

    private int teamGoals;

    private int rivalGoals;

    private MatchState state;


}
