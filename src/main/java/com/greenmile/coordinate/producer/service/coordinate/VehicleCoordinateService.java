package com.greenmile.coordinate.producer.service.coordinate;

import com.greenmile.coordinate.producer.model.coordinate.VehicleCoordinate;

/**
 *
 * @author caioalbmelo
 */
public interface VehicleCoordinateService {

    void receive(VehicleCoordinate coordinate);

}
