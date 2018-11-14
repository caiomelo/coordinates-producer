package com.greenmile.producer.service.coordinate;

import com.greenmile.producer.model.coordinate.VehicleCoordinates;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author caioalbmelo
 */
@Service
public class VehicleCoordinatesServiceImpl implements VehicleCoordinatesService {

    @Autowired
    private KafkaTemplate<String, VehicleCoordinates> kafkaTemplate;

    private ScheduledExecutorService executor;

    public void setExecutor(ScheduledExecutorService executor) {
        this.executor = executor;
    }

    public void setKafkaTemplate(KafkaTemplate<String, VehicleCoordinates> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public final void init() {
        if (executor == null) {
            executor = buildExecutor();

        }
        VehicleCoordinatesProducer producer = new VehicleCoordinatesProducer(kafkaTemplate);
        executor.scheduleWithFixedDelay(producer, 0, 5, TimeUnit.SECONDS);
    }

    protected ScheduledExecutorService buildExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}
