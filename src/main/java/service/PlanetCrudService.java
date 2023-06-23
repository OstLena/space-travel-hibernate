package service;

import entity.PlanetEntity;
import repository.PlanetRepository;

import java.util.List;

public class PlanetCrudService {

    private PlanetRepository planetRepository;

    public PlanetCrudService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<PlanetEntity> findAll(){
        return planetRepository.findAll();
    }

    public PlanetEntity findById(String id){
        return planetRepository.findById(id);
    }

    public PlanetEntity save(PlanetEntity entity){

        return planetRepository.save(entity);
    }

    public int deleteById (String id){
         return planetRepository.deleteById(id);
    }

    public PlanetEntity update (PlanetEntity entity){
        return planetRepository.update(entity);
    }
}
