package com.greenmile.coordinate.producer.service.coordinate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenmile.coordinate.producer.model.coordinate.VehicleCoordinate;
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
            //if it' the first coordinate for the route, send status change message
            //else, if it was already in a stop, verify whether it' finished
            //if it' finished, send add executed stop message
            //also if it's finished, verify whether it took longer than the current longest and send longest message
            //also if it's finished, verify whether it was the last stop and send status update change
            //
            //what to do? save new coordinate, verify what kind of update is it and inform info-provider?
            LOGGER.info("Coordinates received: {}", new ObjectMapper().writeValueAsString(coordinate));
        } catch (JsonProcessingException ex) {
        }
    }

}
