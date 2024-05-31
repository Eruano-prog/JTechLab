package JTechLabs.Lab5.CatService.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HostName")
    public Host host;

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Cat> friends = new ArrayList<>();

    public CatDTO toDTO() {
        return new CatDTO(this.id, this.name, this.birthDate, this.type, this.color, this.host, this.friends);
    }

    public void setFromDTO(CatDTO dto) {
        this.name = dto.getName();
        this.birthDate = dto.getBirthDate();
        this.type = dto.getType();
        this.color = dto.getColor();
        this.host = dto.getHost();
    }
}
