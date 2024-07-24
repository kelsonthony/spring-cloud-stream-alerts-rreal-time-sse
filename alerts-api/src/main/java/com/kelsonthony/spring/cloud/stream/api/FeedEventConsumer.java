package com.kelsonthony.spring.cloud.stream.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.kelsonthony.spring.cloud.stream.domain.alert.AlertService;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class FeedEventConsumer implements Consumer<FeedEvent> {

    private final AlertService alertService;

    @Override
    public void accept(FeedEvent feedEvent) {
    	log.info(feedEvent.toString());
        alertService.addNewAlert(feedEvent);
    }
}
