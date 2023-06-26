package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString(exclude = {"fromPlanetSet", "toPlanetSet"})
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

    @OneToMany(mappedBy = "fromPlanet", fetch = FetchType.EAGER)
    private Set<TicketEntity> fromPlanetSet;

    @OneToMany(mappedBy = "toPlanet", fetch = FetchType.EAGER)
    private Set<TicketEntity> toPlanetSet;
}
