package org.example.Interface;

import java.util.List;

public interface AnimalRepository {
    void salvar(IAnimal animal);
    IAnimal buscarPorId(String id);
    List<IAnimal> listar();
    boolean deletar(String id);
}
