package com.greenmile.coordinate.producer.rest.route;

import com.greenmile.coordinate.producer.model.route.Route;
import com.greenmile.coordinate.producer.service.route.RouteService;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author caioalbmelo
 */
public class RouteRestControllerTest {
    
    private RouteRestController controller;
    
    @Mock
    private RouteService serviceMock;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        controller = new RouteRestController();
        controller.setService(serviceMock);
    }
    
    @Test
    public void testThatItDelegatesServiceWhenSavingARoute() {
        Route route = new Route();
        ResponseEntity response = controller.save(route);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        
        verify(serviceMock, times(1)).save(route);
    }
    
}
