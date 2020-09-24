package me.aborozdykh.webhostinganalyticaltool.entity.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;

/**
 * @author Andrii Borozdykh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObjectToDto {
    private Long id;
    private String service;
    private String question;
    private Query.Answer answer;
    private LocalDateTime date;
    private int time;

    public enum Answer {
        P, N;
    }
}
