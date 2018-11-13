package com.greenmile.producer.service.coordinate;

import com.greenmile.producer.model.coordinate.VehicleCoordinates;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 *
 * @author caioalbmelo
 */
public class VehicleCoordinateProducer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCoordinateProducer.class);

    private final KafkaTemplate<String, VehicleCoordinates> kafkaTemplate;

    public VehicleCoordinateProducer(KafkaTemplate<String, VehicleCoordinates> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run() {
        VehicleCoordinates coordinate = new VehicleCoordinates();
        coordinate.setInstant(new Date());
        coordinate.setLatitude(Math.random());
        coordinate.setLongitude(Math.random());
        coordinate.setVehicleId(UUID.randomUUID().toString());

        try {
            kafkaTemplate.send("coordinates", coordinate);
        } catch (Exception ex) {
            LOGGER.warn("error sending coordinate", ex);
        }
    }

}
