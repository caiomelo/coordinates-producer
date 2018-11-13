package com.greenmile.producer.service.route;

import com.greenmile.producer.model.route.Route;
import java.util.Optional;

/**
 *
 * @author caioalbmelo
 */
public interface RouteService {
    
    Optional<Route> findOne(String id);
    
    Route save(Route route);
    
}
