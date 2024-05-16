package ru.stepagin.blps.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.stepagin.blps.dto.IssueDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    @Value(value = "${spring.kafka.bootstrap-server}")
    private String kafkaServer;

    @Bean
    public Map<String, Object> issueProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public Map<String, Object> userProducerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Long, IssueDto> issueDtoProducerFactory() {
        return new DefaultKafkaProducerFactory<>(issueProducerConfigs());
    }

    @Bean
    public ProducerFactory<String, String> personDtoProducerFactory() {
        return new DefaultKafkaProducerFactory<>(userProducerConfigs());
    }

    @Bean(name = "issue-kafka-template")
    public KafkaTemplate<Long, IssueDto> issueKafkaTemplate() {
        return new KafkaTemplate<>(issueDtoProducerFactory());
    }

    @Bean(name = "user-kafka-template")
    public KafkaTemplate<String, String> userKafkaTemplate() {
        return new KafkaTemplate<>(personDtoProducerFactory());
    }
}
