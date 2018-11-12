package com.greenmile.coordinate.consumer.service.coordinate;

import com.greenmile.coordinate.consumer.model.coordinate.VehicleCoordinate;

/**
 *
 * @author caioalbmelo
 */
public interface VehicleCoordinateService {

    void receive(VehicleCoordinate coordinate);

}
