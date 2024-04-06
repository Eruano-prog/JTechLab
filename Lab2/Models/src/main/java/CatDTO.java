import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
public class CatDTO {
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;
    public Host host;
    public ArrayList<Cat> friends;
}
