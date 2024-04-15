package Lab3.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class HostDTO {
    public String name;
    public Date birthDate;
    public List<Cat> cats;
}
