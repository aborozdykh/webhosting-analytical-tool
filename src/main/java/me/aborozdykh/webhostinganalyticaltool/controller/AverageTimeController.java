package me.aborozdykh.webhostinganalyticaltool.controller;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.AverageTime;
import me.aborozdykh.webhostinganalyticaltool.service.AverageTimeService;
import me.aborozdykh.webhostinganalyticaltool.service.EvaluateQueryService;
import me.aborozdykh.webhostinganalyticaltool.service.WaitingTimeLineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/evaluate")
public class AverageTimeController {
    private final AverageTimeService averageTimeService;
    private final EvaluateQueryService evaluateQueryService;
    private final WaitingTimeLineService waitingTimeLineService;

    public AverageTimeController(AverageTimeService averageTimeService,
                                 EvaluateQueryService evaluateQueryService,
                                 WaitingTimeLineService waitingTimeLineService) {
        this.averageTimeService = averageTimeService;
        this.evaluateQueryService = evaluateQueryService;
        this.waitingTimeLineService = waitingTimeLineService;
    }

    @GetMapping("/average-time")
    public List<AverageTime> getAverageTime() {
        return averageTimeService.getAverageTimeList(evaluateQueryService.findAll(),
                waitingTimeLineService.findAll());
    }
}
