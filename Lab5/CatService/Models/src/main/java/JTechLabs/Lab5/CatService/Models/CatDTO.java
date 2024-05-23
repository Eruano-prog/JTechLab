package JTechLabs.Lab5.CatService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatDTO {
    public Integer id;
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;
    public Host host;
    public List<Cat> friends;

    public Cat toCat(){
        return new Cat(id, name, birthDate, type, color, host, friends);
    }
}
