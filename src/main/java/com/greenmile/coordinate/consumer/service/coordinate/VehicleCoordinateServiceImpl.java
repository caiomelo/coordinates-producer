package com.greenmile.coordinate.consumer.service.coordinate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenmile.coordinate.consumer.model.coordinate.VehicleCoordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author caioalbmelo
 */
@Service
public class VehicleCoordinateServiceImpl implements VehicleCoordinateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleCoordinateServiceImpl.class);

    @KafkaListener(topics = "coordinates", containerFactory = "coordinateListenerContainerFactory")
    @Override
    public void receive(VehicleCoordinate coordinate) {
        try {
            LOGGER.info("Coordinates received: {}", new ObjectMapper().writeValueAsString(coordinate));
        } catch (JsonProcessingException ex) {
        }
    }

}
