package JTechLabs.Lab5.APIService.DLL;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value(("${spring.kafka.bootstrap-servers}"))
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic CatPutTopic() {
        return TopicBuilder
                .name("cat.put")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic CatSaveTopic() {
        return TopicBuilder
                .name("cat.save")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic CatDeleteTopic() {
        return TopicBuilder
                .name("cat.delete")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic HostPutTopic() {
        return TopicBuilder
                .name("host.put")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic HostSaveTopic() {
        return TopicBuilder
                .name("host.save")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic HostDeleteTopic() {
        return TopicBuilder
                .name("host.delete")
                .partitions(1)
                .replicas(1)
                .build();
    }
}