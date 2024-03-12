package service.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;


@Entity
@Table(name = "cats")
@Getter
@Setter
public class Cat {
    @Id
    String name;
    Date birthDate;
    String type;
    catColor color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name")
    Host host;
    @ManyToMany
    ArrayList<Cat> friends;
}
