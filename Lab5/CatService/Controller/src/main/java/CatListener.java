import JTechLabs.Lab5.CatService.BLL.CatService;
import JTechLabs.Lab5.CatService.Models.CatWithHostnameMessage;
import JTechLabs.Lab5.CatService.Models.CatnameWithHostnameMessage;
import JTechLabs.Lab5.CatService.Models.HostnameWithCatColorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CatListener {
    final private CatService catService;
    final private ObjectMapper mapper;

    @Autowired
    public CatListener(CatService catService, ObjectMapper objectMapper) {
        this.catService = catService;
        this.mapper = objectMapper;
    }

    @KafkaListener(topics = "cat.save")
    public void saveCatPoint(String message){
        CatWithHostnameMessage cat;
        try {
            cat = mapper.readValue(message, CatWithHostnameMessage.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        catService.addCat(cat.getHostName(), cat.getCat());
    }

    @KafkaListener(topics = "cat.delete")
    public void deleteCatPoint(String message){
        CatWithHostnameMessage cat;
        try {
            cat = mapper.readValue(message, CatWithHostnameMessage.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        catService.addCat(cat.getHostName(), cat.getCat());
    }

    @KafkaListener(topics = "cat.put")
    public void putCatPoint(String message){
        CatWithHostnameMessage cat;
        try {
            cat = mapper.readValue(message, CatWithHostnameMessage.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        catService.modifyCat(cat.getHostName(), cat.getCat());
    }

    @KafkaListener(topics = "cat.get")
    public void getCatPoint(String message){
        CatnameWithHostnameMessage cat;
        try {
            cat = mapper.readValue(message, CatnameWithHostnameMessage.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        catService.getCat(cat.getHostname(), cat.getCatname());
    }

    @KafkaListener(topics = "cat.get")
    public void getCatByColorPoint(String message){
        HostnameWithCatColorMessage cat;
        try {
            cat = mapper.readValue(message, HostnameWithCatColorMessage.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        catService.getCatsByColor(cat.getHostName(), cat.getColor());
    }
}