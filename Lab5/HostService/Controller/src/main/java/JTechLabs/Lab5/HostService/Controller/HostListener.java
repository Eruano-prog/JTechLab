package JTechLabs.Lab5.HostService.Controller;

import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HostListener {

    @KafkaListener(topics = "host.save")
    public void saveHostPoint(String  message){

    }
}
