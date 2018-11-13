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

    @PostConstruct
    public void init() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        VehicleCoordinateProducer producer = new VehicleCoordinateProducer(kafkaTemplate);
        executor.scheduleWithFixedDelay(producer, 0, 5, TimeUnit.SECONDS);
    }
}
