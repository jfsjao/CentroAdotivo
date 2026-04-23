package org.example.Repository;

import org.example.Interface.AdocaoRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAdocaoRepository implements AdocaoRepository {
    private final Map<String, String> adocoesPorAnimalId = new HashMap<>();

    @Override
    public void salvar(String animalId, String adotanteId) {
        adocoesPorAnimalId.put(animalId, adotanteId);
    }

    @Override
    public boolean existeParaAnimal(String animalId) {
        return adocoesPorAnimalId.containsKey(animalId);
    }
}
