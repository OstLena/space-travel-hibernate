import configurations.Environment;
import configurations.FlywayConfigurations;
import configurations.hibernate.DataSource;
import entity.ClientEntity;
import entity.PlanetEntity;
import repository.ClientRepository;
import repository.PlanetRepository;
import service.ClientCrudService;
import service.PlanetCrudService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new FlywayConfigurations()
                .setup()
                .migrate();


        Environment load = Environment.load();
        testClientCrud(load);
        testPlanetCrud(load);
    }

    private static void testClientCrud(Environment load) {
        ClientRepository clientRepository = new ClientRepository(new DataSource(load, ClientEntity.class, PlanetEntity.class));
        ClientCrudService clientCrudService = new ClientCrudService(clientRepository);

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

    private static void testPlanetCrud(Environment load) {
        PlanetRepository planetRepository = new PlanetRepository(new DataSource(load, PlanetEntity.class, ClientEntity.class));
        PlanetCrudService planetCrudService = new PlanetCrudService(planetRepository);

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
}
