package me.aborozdykh.webhostinganalyticaltool.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import me.aborozdykh.webhostinganalyticaltool.service.QueryService;
import me.aborozdykh.webhostinganalyticaltool.service.WaitingTimeLineService;
import me.aborozdykh.webhostinganalyticaltool.util.DataParser;
import me.aborozdykh.webhostinganalyticaltool.util.FileReaderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author Andrii Borozdykh
 */
@SpringBootTest
@AutoConfigureMockMvc
class AverageTimeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FileReaderUtil fileReaderUtil;
    @Autowired
    private DataParser dataParser;
    @Autowired
    private QueryService queryService;
    @Autowired
    private WaitingTimeLineService waitingTimeLineService;

    @Test
    void getAverageTime() throws Exception {
        var inputStream = getClass().getClassLoader().getResourceAsStream("testData.csv");
        var records = fileReaderUtil.getDataFromFile(inputStream);
        var queryList = dataParser.getQueryList(records);
        var waitingTimeLineList = dataParser.getWaitingTimeList(records);
        queryService.saveAll(queryList);
        waitingTimeLineService.saveAll(waitingTimeLineList);

        MockHttpServletRequestBuilder builder
                = MockMvcRequestBuilders
                .get("/evaluate/average-time/")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].averageTime").value("83"))
                .andExpect(jsonPath("$[1].averageTime").value("100"))
                .andExpect(jsonPath("$[2].averageTime").value("-"));
    }
}
