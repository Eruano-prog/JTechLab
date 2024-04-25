package Lab3.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class CatDTO {
    public Integer id;
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;
    public Host host;
    public List<Cat> friends;
}
