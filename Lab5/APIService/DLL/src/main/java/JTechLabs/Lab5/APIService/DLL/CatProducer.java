package JTechLabs.Lab5.APIService.DLL;

import JTechLabs.Lab5.APIService.Models.Cat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CatProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void putCat(Cat cat) throws JsonProcessingException {
        String catAsMessage = objectMapper.writeValueAsString(cat);
        kafkaTemplate.send("cat.put", catAsMessage);
    }

    public void saveCat(Cat cat) throws JsonProcessingException {
        String catAsMessage = objectMapper.writeValueAsString(cat);
        kafkaTemplate.send("cat.save", catAsMessage);
    }

    public void deleteCat(Cat cat) throws JsonProcessingException {
        String catAsMessage = objectMapper.writeValueAsString(cat);
        kafkaTemplate.send("cat.delete", catAsMessage);
    }
}