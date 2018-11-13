package com.greenmile.coordinate.producer.rest.route;

import com.greenmile.coordinate.producer.model.route.Route;
import com.greenmile.coordinate.producer.service.route.RouteService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author caioalbmelo
 */
@RestController
@RequestMapping("/Route")
public class RouteRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteRestController.class);

    private RouteService service;

    @Autowired
    public void setService(RouteService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@Valid @RequestBody Route route) {
        LOGGER.info("Received a new route (ID {}) for vehicle ID {}", route.getId(), route.getAssignedVehicle());
        service.save(route);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
