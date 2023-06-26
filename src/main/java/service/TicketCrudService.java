package service;


import entity.TicketEntity;
import repository.TicketRepository;

import java.util.List;

public class TicketCrudService {

    private TicketRepository ticketRepository;

    public TicketCrudService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketEntity> findAll(){
        return ticketRepository.findAll();
    }

    public TicketEntity findById(Long id){
        return ticketRepository.findById(id);
    }

    public TicketEntity save(TicketEntity entity){
        return ticketRepository.save(entity);
    }

    public int deleteById (Long id){
        return ticketRepository.deleteById(id);
    }

    public TicketEntity update (TicketEntity entity){
        return ticketRepository.update(entity);
    }
}
