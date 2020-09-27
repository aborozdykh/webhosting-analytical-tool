package me.aborozdykh.webhostinganalyticaltool.controller;

import me.aborozdykh.webhostinganalyticaltool.service.QueryService;
import me.aborozdykh.webhostinganalyticaltool.service.WaitingTimeLineService;
import me.aborozdykh.webhostinganalyticaltool.util.DataParser;
import me.aborozdykh.webhostinganalyticaltool.util.FileReaderUtil;
import me.aborozdykh.webhostinganalyticaltool.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Andrii Borozdykh
 */
@Controller
@RequestMapping("/data")
public class DataReaderController {
    private final FileReaderUtil fileReaderUtil;
    private final DataParser dataParser;
    private final QueryService queryService;
    private final WaitingTimeLineService waitingTimeLineService;

    public DataReaderController(FileReaderUtil fileReaderUtil,
                                DataParser dataParser,
                                QueryService queryService,
                                WaitingTimeLineService waitingTimeLineService) {
        this.fileReaderUtil = fileReaderUtil;
        this.dataParser = dataParser;
        this.queryService = queryService;
        this.waitingTimeLineService = waitingTimeLineService;
    }

    @PostMapping("/upload-file")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;

        if (fileReaderUtil.hasCorrectFormat(file)) {
            try {
                var records = fileReaderUtil
                        .getDataFromFile(file.getInputStream());
                var queryList = dataParser.getQueryList(records);
                var waitingTimeLineList = dataParser.getWaitingTimeList(records);
                queryService.saveAll(queryList);
                waitingTimeLineService.saveAll(waitingTimeLineList);
                message = "Uploaded the file successfully: "
                        + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}
