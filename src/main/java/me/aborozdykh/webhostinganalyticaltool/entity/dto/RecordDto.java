package me.aborozdykh.webhostinganalyticaltool.entity.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;

/**
 * @author Andrii Borozdykh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RecordDto {
    private String recordType;
    private String service;
    private String question;
    private WaitingTimeLine.Answer answer;
    private LocalDate date;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int time;
}
