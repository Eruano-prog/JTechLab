package JTechLabs.Lab5.APIService.DLL;

import Lab5.Models.Host;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HostProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public HostProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void putHost(Host host) throws JsonProcessingException {
        String hostAsMessage = objectMapper.writeValueAsString(host);
        kafkaTemplate.send("host.put", hostAsMessage);
    }

    public void saveHost(Host host) throws JsonProcessingException {
        String hostAsMessage = objectMapper.writeValueAsString(host);
        kafkaTemplate.send("host.save", hostAsMessage);
    }

    public void deleteHost(Host host) throws JsonProcessingException {
        String hostAsMessage = objectMapper.writeValueAsString(host);
        kafkaTemplate.send("host.delete", hostAsMessage);
    }
}