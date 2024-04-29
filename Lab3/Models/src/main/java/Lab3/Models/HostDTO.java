package Lab3.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class HostDTO {
    public Integer id;
    public String name;
    public Date birthDate;
    @JsonIgnore
    public List<Cat> cats;

    public Host toHost(){
        return new Host(id, name, birthDate, cats);
    }
}
