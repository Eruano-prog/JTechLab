package JTechLabs.Lab5.APIService.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "HostName")
    public Host host;
    @ManyToMany(fetch = FetchType.LAZY)
    public List<Cat> friends = new ArrayList<>();

    public CatDTO toDTO() {
        return new CatDTO(this.id, this.name, this.birthDate, this.type, this.color, this.host, this.friends);
    }
}
