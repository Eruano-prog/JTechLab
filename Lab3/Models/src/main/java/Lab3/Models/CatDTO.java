package Lab3.Models;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class CatDTO {
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;
    public Host host;
    public List<Cat> friends;
}
