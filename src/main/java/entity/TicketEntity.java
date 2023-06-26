package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Timestamp created;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private ClientEntity clientEntity;

    @ManyToOne
    @JoinColumn(name="from_planet_id", nullable=false)
    private PlanetEntity fromPlanet;

    @ManyToOne
    @JoinColumn(name="to_planet_id", nullable=false)
    private PlanetEntity toPlanet;
}
