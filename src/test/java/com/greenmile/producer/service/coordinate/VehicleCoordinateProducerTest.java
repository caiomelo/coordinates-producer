package com.greenmile.producer.service.coordinate;

import com.greenmile.producer.model.coordinate.VehicleCoordinates;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

/**
 *
 * @author caioalbmelo
 */
public class VehicleCoordinateProducerTest {

    private VehicleCoordinateProducer producer;

    @Mock
    private KafkaTemplate templateMock;

    @Before
    public void seUp() {
        MockitoAnnotations.initMocks(this);
        
        producer = new VehicleCoordinateProducer(templateMock);
    }

    @Test
    public void testThatItCreatesAVehicleCoordinatesAndRequestItToBeSent() {
        producer.run();
        
        ArgumentCaptor<VehicleCoordinates> coordinatesCaptor = ArgumentCaptor.forClass(VehicleCoordinates.class);
        verify(templateMock, times(1)).send(eq("coordinates"), coordinatesCaptor.capture());
        
        VehicleCoordinates captured = coordinatesCaptor.getValue();
        assertNotNull(captured);
    }
}
