package me.aborozdykh.webhostinganalyticaltool.entity.mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.RecordDto;
import me.aborozdykh.webhostinganalyticaltool.exception.WrongDataException;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class RecordDtoMapper {
    public static final String QUERY_TYPE = "D";
    public static final String WAITING_TIME_LINE_TYPE = "C";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public RecordDto getRecordDtoFromRecord(String record) {
        String[] data = record.split(" ");
        var recordDto = new RecordDto();
        recordDto.setRecordType(data[0]);
        recordDto.setService(data[1]);
        recordDto.setQuestion(data[2]);
        recordDto.setAnswer(WaitingTimeLine.Answer.valueOf(data[3]));
        if (recordDto.getRecordType().equals(WAITING_TIME_LINE_TYPE)) {
            recordDto.setDate(getDateForOnceDateFormat(data[4]));
            recordDto.setTime(Integer.parseInt(data[5]));
        } else if (recordDto.getRecordType().equals(QUERY_TYPE)) {
            if (data[4].contains("-")) {
                recordDto.setDateFrom(getFromDateDoubleDateFormat(data[4]));
                recordDto.setDateTo(getToDateDoubleDateFormat(data[4]));
            } else {
                recordDto.setDateFrom(getDateForOnceDateFormat(data[4]));
                recordDto.setDateTo(getDateForOnceDateFormat(data[4]));
            }
        } else {
            throw new WrongDataException("Only " + WAITING_TIME_LINE_TYPE + " and "
                    + QUERY_TYPE + " types of line are possible!");
        }
        return recordDto;
    }

    private boolean verifyDateFormat(String date) {
        return date.length() == 10;
    }

    private String modifyDateFormat(String date) {
        String[] data = date.split(" ");
        if (data[0].length() == 1 && data[1].length() == 2 && data[2].length() == 4) {
            date = "0" + date;
        } else if (data[0].length() == 2 && data[1].length() == 1 && data[2].length() == 4) {
            date = date.substring(0, 2) + "0" + date.substring(3, date.length() - 1);
        } else {
            throw new WrongDataException("Wrong date in file!");
        }
        return date;
    }

    private String prepareDate(String date) {
        if (!verifyDateFormat(date)) {
            date = modifyDateFormat(date);
        }
        return date;
    }

    private LocalDate getDateForOnceDateFormat(String date) {
        date = prepareDate(date);
        return LocalDate.parse(date, FORMATTER);
    }

    private LocalDate getFromDateDoubleDateFormat(String date) {
        String[] dates = date.split("-");
        return getDateForOnceDateFormat(dates[0]);
    }

    private LocalDate getToDateDoubleDateFormat(String date) {
        String[] dates = date.split("-");
        return getDateForOnceDateFormat(dates[1]);
    }
}
