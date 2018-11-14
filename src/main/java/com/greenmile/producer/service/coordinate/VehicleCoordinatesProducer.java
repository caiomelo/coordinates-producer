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
public class VehicleCoordinatesProducer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCoordinatesProducer.class);

    private final KafkaTemplate<String, VehicleCoordinates> kafkaTemplate;

    public VehicleCoordinatesProducer(KafkaTemplate<String, VehicleCoordinates> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public KafkaTemplate<String, VehicleCoordinates> getKafkaTemplate() {
        return kafkaTemplate;
    }

    @Override
    public void run() {
        VehicleCoordinates coordinate = new VehicleCoordinates();
        coordinate.setInstant(new Date());
        coordinate.setLatitude(Math.random());
        coordinate.setLongitude(Math.random());
        coordinate.setVehicleId(UUID.randomUUID().toString());
        kafkaTemplate.send("coordinates", coordinate);
    }

}
