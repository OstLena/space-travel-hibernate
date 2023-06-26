package service;


import entity.TicketEntity;
import repository.ClientRepository;
import repository.PlanetRepository;
import repository.TicketRepository;

import java.util.List;

public class TicketCrudService {

    private TicketRepository ticketRepository;
    private PlanetRepository planetRepository;
    private ClientRepository clientRepository;

    public TicketCrudService(TicketRepository ticketRepository, PlanetRepository planetRepository, ClientRepository clientRepository) {
        this.ticketRepository = ticketRepository;
        this.planetRepository = planetRepository;
        this.clientRepository = clientRepository;
    }

    public List<TicketEntity> findAll() {
        return ticketRepository.findAll();
    }

    public TicketEntity findById(Long id) {
        return ticketRepository.findById(id);
    }

    public TicketEntity save(TicketEntity entity) {
        if (clientRepository.findById(entity.getClientEntity().getId()) == null) {
            throw new RuntimeException("Client with ID " + entity.getClientEntity().getId() + " doesn't exist");
        }

        if (planetRepository.findById(entity.getFromPlanet().getId()) == null) {
            throw new RuntimeException("Planet with ID " + entity.getFromPlanet().getId() + " doesn't exist");
        }

        if (planetRepository.findById(entity.getToPlanet().getId()) == null) {
            throw new RuntimeException("Client with ID " + entity.getToPlanet().getId() + " doesn't exist");
        }
        return ticketRepository.save(entity);
    }

    public int deleteById(Long id) {
        return ticketRepository.deleteById(id);
    }

    public TicketEntity update(TicketEntity entity) {
        return ticketRepository.update(entity);
    }
}
