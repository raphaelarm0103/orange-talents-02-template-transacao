package br.com.api.transacao.kafka;

import br.com.api.transacao.request.TransacaoRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaServer {

    private final KafkaProperties kafkaProperties;


    public KafkaServer(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransacaoRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransacaoRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(transactionConsumerFactory());

        return factory;
    }

    @Bean
    private ConsumerFactory<? super String,? super TransacaoRequest> transactionConsumerFactory() {
        JsonDeserializer<TransacaoRequest> jsonDeserializer = new JsonDeserializer<>(TransacaoRequest.class, false);
        StringDeserializer stringDeserializer = new StringDeserializer();

        return new DefaultKafkaConsumerFactory((Map<String, Object>) consumerConfigurations(), stringDeserializer, jsonDeserializer);

    }

    private Object consumerConfigurations() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getConsumer().getAutoOffsetReset());

        return properties;
    }
}
