package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@ToString(exclude = "ticketEntitySet")
@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="clientEntity", fetch = FetchType.EAGER)
    private Set<TicketEntity> ticketEntitySet;
}
