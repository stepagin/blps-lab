package com.annyarusova.subscriptionservice.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfiguration {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value(value = "${spring.kafka.consumer.group-id}")
    private String kafkaGroupId;

    @Bean
    public Map<String, Object> userConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // TODO настроить десериализацию UserInfo (new JsonDeserializer<>(UserInfo.class))
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> userConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(userConsumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<?> userListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }

    // TODO настроить листенеры на issue (снизу)
//    @Bean
//    public Map<String, Object> issueConsumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
//        return props;
//    }

//    @Bean
//    public KafkaListenerContainerFactory<?> issueListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, UserInfo> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(userConsumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<Long, IssueEntity> issueConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(userConsumerConfigs());
//    }
}
