package com.greenmile.coordinate.consumer.repository.route;

import com.greenmile.coordinate.consumer.model.route.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author caioalbmelo
 */
@Repository
public interface RouteRepository extends CrudRepository<Route, String> {

}
