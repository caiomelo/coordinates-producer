package com.greenmile.producer.service.coordinate;

import com.greenmile.producer.model.coordinate.VehicleCoordinate;

/**
 *
 * @author caioalbmelo
 */
public interface VehicleCoordinateService {

    void receive(VehicleCoordinate coordinate);

}
