package org.example.Interface;

public interface AdocaoRepository {
    void salvar(String animalId, String adotanteId);
    boolean existeParaAnimal(String animalId);
}
