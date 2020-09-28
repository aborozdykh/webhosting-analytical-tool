package me.aborozdykh.webhostinganalyticaltool.entity.dto;

import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.aborozdykh.webhostinganalyticaltool.entity.ResponseType;

/**
 * @author Andrii Borozdykh
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RecordDto {
    private String recordType;
    private String serviceId;
    private String question;
    private ResponseType responseType;
    private LocalDate date;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private int time;
}
