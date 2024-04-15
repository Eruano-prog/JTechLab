package Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public String name;
    public Date birthDate;
    public String type;
    public catColor color;
    @ManyToOne
    @JoinColumn(name = "HostName")
    public Host host;
    @ManyToMany
    public List<Cat> friends;
}
