package me.aborozdykh.webhostinganalyticaltool.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recordNumber;
    private String service;
    private String question;
    @Enumerated(EnumType.STRING)
    private WaitingTimeLine.Answer answer;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
