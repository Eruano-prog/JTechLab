package Lab3.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public Date birthDate;
    @OneToMany
    public List<Cat> cats;

    public String password;
    public String roles;

    public HostDTO toDTO(){
        return new HostDTO(id, name, birthDate, cats, password, roles);
    }
}
