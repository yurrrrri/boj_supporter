package com.huh.BaekJoonSupporter.boundedContext.teamrule;

import com.huh.BaekJoonSupporter.boundedContext.team.Team;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "team")
public class TeamRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String target;

    private String difficulty;

    private Long targetNumber;

    @ManyToOne
    private Team team;

}
