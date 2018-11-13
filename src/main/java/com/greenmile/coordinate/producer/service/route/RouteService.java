package com.greenmile.coordinate.producer.service.route;

import com.greenmile.coordinate.producer.model.route.Route;
import java.util.Optional;

/**
 *
 * @author caioalbmelo
 */
public interface RouteService {
    
    Optional<Route> findOne(String id);
    
    Route save(Route route);
    
}
