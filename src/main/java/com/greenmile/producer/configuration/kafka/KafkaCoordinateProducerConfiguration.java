package com.greenmile.producer.configuration.kafka;

import com.greenmile.producer.model.coordinate.VehicleCoordinates;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 *
 * @author caioalbmelo
 */
@EnableKafka
@Configuration
public class KafkaCoordinateProducerConfiguration {

    @Autowired
    @Value("${kafka.config.bootstrap.servers}")
    private String bootstrapAddresses;

    @Bean
    public ProducerFactory<String, VehicleCoordinates> coordinatesProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddresses);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, VehicleCoordinates> coordinatesTemplate() {
        KafkaTemplate<String, VehicleCoordinates> kafkaTemplate = new KafkaTemplate<>(coordinatesProducerFactory());
        return kafkaTemplate;
    }
}
