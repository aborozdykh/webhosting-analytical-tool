package me.aborozdykh.webhostinganalyticaltool.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Andrii Borozdykh
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "queries")
public class Query {
    @Id
    @GeneratedValue
    private Long id;
    private Long lineNumber;
    private String service;
    private String question;
    @Enumerated(EnumType.STRING)
    private Answer answer;
    private LocalDateTime date;
    private int time;

    public enum Answer {
        P, N;
    }
}
