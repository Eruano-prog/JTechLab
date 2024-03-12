package service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Getter
@Setter
public class Host {
    @Id
    String name;
    Date birthDate;
    @OneToMany
    ArrayList<Cat> cats;
}
