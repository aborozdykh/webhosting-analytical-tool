package me.aborozdykh.webhostinganalyticaltool.service.impl;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.repository.WaitingTimeLineRepository;
import me.aborozdykh.webhostinganalyticaltool.service.WaitingTimeLineService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * @author Andrii Borozdykh
 */
@Service
public class WaitingTimeLineImpl implements WaitingTimeLineService {
    private final WaitingTimeLineRepository waitingTimeLineRepository;

    public WaitingTimeLineImpl(WaitingTimeLineRepository waitingTimeLineRepository) {
        this.waitingTimeLineRepository = waitingTimeLineRepository;
    }

    @Override
    public List<WaitingTimeLine> saveAll(List<WaitingTimeLine> waitingTimeLineList) {
        return waitingTimeLineRepository.saveAll(waitingTimeLineList);
    }

    @Override
    public List<WaitingTimeLine> findAll() {
        return waitingTimeLineRepository.findAll();
    }

    @Override
    public Long getLastRecordNumber() {
        Pageable pageable = PageRequest.of(0, 1);
        List<WaitingTimeLine> content
                = waitingTimeLineRepository.findLastRecord(pageable).getContent();
        return content.isEmpty() ? 0L : content.get(0).getRecordNumber();
    }
}
