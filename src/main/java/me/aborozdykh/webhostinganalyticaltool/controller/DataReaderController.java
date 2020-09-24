package me.aborozdykh.webhostinganalyticaltool.controller;

import me.aborozdykh.webhostinganalyticaltool.util.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Andrii Borozdykh
 */
@Controller
@RequestMapping("/data")
public class DataReaderController {

    @PostMapping("/upload-file")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (dataReaderService.hasCorrectFormat(file)) {
            try {
                var reviewRequestDtoList = dataReaderService
                        .getDataFromFile(file.getInputStream());
                dataToDbSaver.saveToDb(reviewRequestDtoList);

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

    @PostMapping("/read-file")
    public ResponseEntity<ResponseMessage> readFromFile(@RequestParam("path") String path) {
        getClass().getResourceAsStream(path);
        return null;
    }
}
