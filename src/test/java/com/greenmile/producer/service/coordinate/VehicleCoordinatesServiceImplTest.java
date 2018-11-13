package com.greenmile.producer.service.coordinate;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.anyLong;
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
public class VehicleCoordinatesServiceImplTest {

    private VehicleCoordinatesServiceImpl service;

    @Mock
    private KafkaTemplate templateMock;

    @Mock
    private ScheduledExecutorService executorMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new VehicleCoordinatesServiceImpl();
        service.setExecutor(executorMock);
        service.setKafkaTemplate(templateMock);
    }

    @Test
    public void testThatItSchedulesCoordinatesProducerTaskUsingGivenExecutorAndKafkaTemplate() {
        service.init();
        
        ArgumentCaptor<VehicleCoordinateProducer> producerCaptor = ArgumentCaptor.forClass(VehicleCoordinateProducer.class);
        verify(executorMock, times(1)).scheduleWithFixedDelay(
                producerCaptor.capture(), anyLong(), anyLong(), eq(TimeUnit.SECONDS));

        VehicleCoordinateProducer captured = producerCaptor.getValue();
        assertEquals(templateMock, captured.getKafkaTemplate());
    }

}
