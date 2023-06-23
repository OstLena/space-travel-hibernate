package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "planet")
public class PlanetEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
