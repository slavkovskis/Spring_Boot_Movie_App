package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService {

    public List<Production> listAll();
    public Optional<Production> findById(Long id);
    public Optional<Production> findByName(String name);

    public void saveProduction(Production production);
}
