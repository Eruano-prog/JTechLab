import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HostName")
    public Host host;
    @ManyToMany
    public ArrayList<Cat> friends;
}
