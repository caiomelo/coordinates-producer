package com.greenmile.producer.service.coordinate;

import com.greenmile.producer.model.coordinate.VehicleCoordinates;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

/**
 *
 * @author caioalbmelo
 */
public class VehicleCoordinatesProducerTest {

    private VehicleCoordinatesProducer producer;

    @Mock
    private KafkaTemplate templateMock;

    @Before
    public void seUp() {
        MockitoAnnotations.initMocks(this);

        producer = new VehicleCoordinatesProducer(templateMock);
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
