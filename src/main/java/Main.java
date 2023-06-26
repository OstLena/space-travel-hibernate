import configurations.Environment;
import configurations.FlywayConfigurations;
import configurations.hibernate.DataSource;
import entity.ClientEntity;
import entity.PlanetEntity;
import entity.TicketEntity;
import repository.ClientRepository;
import repository.PlanetRepository;
import repository.TicketRepository;
import service.ClientCrudService;
import service.PlanetCrudService;
import service.TicketCrudService;

import java.io.IOException;
import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws IOException {
        new FlywayConfigurations()
                .setup()
                .migrate();


        Environment load = Environment.load();
        ClientRepository clientRepository = new ClientRepository(new DataSource(load, ClientEntity.class, PlanetEntity.class));
        ClientCrudService clientCrudService = new ClientCrudService(clientRepository);

        PlanetRepository planetRepository = new PlanetRepository(new DataSource(load, PlanetEntity.class, ClientEntity.class));
        PlanetCrudService planetCrudService = new PlanetCrudService(planetRepository);

        TicketRepository ticketRepository = new TicketRepository(new DataSource(load, TicketEntity.class, PlanetEntity.class, ClientEntity.class));
        TicketCrudService ticketCrudService = new TicketCrudService(ticketRepository);


        testClientCrud(clientCrudService);
        testPlanetCrud(planetCrudService);
        testTicketCrud(ticketCrudService, clientCrudService, planetCrudService);
    }

    private static void testClientCrud(ClientCrudService clientCrudService) {

        clientCrudService.findAll().forEach(System.out::println);

//        CREATE
        ClientEntity newClient = new ClientEntity();
        newClient.setName("NC");
        clientCrudService.save(newClient);
        for (ClientEntity client : clientCrudService.findAll()) {
            System.out.println(client);
        }

//        UPDATE
        ClientEntity updatedClient = new ClientEntity();
        updatedClient.setId(newClient.getId());
        updatedClient.setName("UPDATED CLIENT");
        clientCrudService.update(updatedClient);

//        FIND BY ID
        ClientEntity clientEntity = clientCrudService.findById(updatedClient.getId());
        System.out.println(clientEntity);

//        DELETE
        clientCrudService.deleteById(updatedClient.getId());
        clientCrudService.findAll().forEach(System.out::println);
    }

    private static void testPlanetCrud(PlanetCrudService planetCrudService) {

        planetCrudService.findAll().forEach(System.out::println);

//        CREATE
        PlanetEntity newPlanet = new PlanetEntity();
        newPlanet.setId("NEWID");
        newPlanet.setName("NEW COMER PLANET");
        planetCrudService.save(newPlanet);
        for (PlanetEntity planet : planetCrudService.findAll()) {
            System.out.println(planet);
        }

//        UPDATE
        PlanetEntity updatedPlanet = new PlanetEntity();
        updatedPlanet.setId(newPlanet.getId());
        updatedPlanet.setName("UPDATED PLANET");
        planetCrudService.update(updatedPlanet);

//        FIND BY ID
        PlanetEntity planetEntity = planetCrudService.findById(updatedPlanet.getId());
        System.out.println(planetEntity);

//        DELETE
        planetCrudService.deleteById(updatedPlanet.getId());
        planetCrudService.findAll().forEach(System.out::println);
    }

    private static void testTicketCrud(TicketCrudService ticketCrudService, ClientCrudService clientCrudService, PlanetCrudService planetCrudService) {

        ticketCrudService.findAll().forEach(System.out::println);

//        CREATE
        TicketEntity newTicket = new TicketEntity();
        newTicket.setCreated(Timestamp.valueOf("2025-07-07 16:47:52"));
        ClientEntity client = clientCrudService.findById(9L);
        PlanetEntity fromPlanet = planetCrudService.findById("MAR");
        PlanetEntity toPlanet = planetCrudService.findById("JUPT");

        newTicket.setClientEntity(client);
        newTicket.setFromPlanet(fromPlanet);
        newTicket.setToPlanet(toPlanet);

        ticketCrudService.save(newTicket);
        for (TicketEntity ticketEntity : ticketCrudService.findAll()) {
            System.out.println(ticketEntity);
        }

//        UPDATE
        newTicket.setClientEntity(clientCrudService.findById(10L));
        newTicket.setFromPlanet(planetCrudService.findById("EAR"));
        newTicket.setToPlanet(planetCrudService.findById("NEPT"));

        ticketCrudService.update(newTicket);

//        FIND BY ID
        TicketEntity ticketEntity = ticketCrudService.findById(newTicket.getId());
        System.out.println(ticketEntity);

//        DELETE
        ticketCrudService.deleteById(newTicket.getId());
        ticketCrudService.findAll().forEach(System.out::println);
    }
}
