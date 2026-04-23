package org.example.Service;

import org.example.Classes.Animal;
import org.example.Factory.AnimalFactory;
import org.example.Interface.AnimalDeletionPolicy;
import org.example.Interface.AnimalRepository;
import org.example.Interface.IAnimal;
import org.example.Observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalDeletionPolicy deletionPolicy;
    private final Subject subject;

    public AnimalService(AnimalRepository animalRepository, AnimalDeletionPolicy deletionPolicy, Subject subject) {
        this.animalRepository = animalRepository;
        this.deletionPolicy = deletionPolicy;
        this.subject = subject;
    }

    public void adicionarAnimal(String id, String especie, String raca, int idade) {
        validarId(id);
        validarEspecie(especie);
        validarIdDisponivel(id);

        Animal animal = AnimalFactory.criarAnimal(id, especie, raca, idade);
        animalRepository.salvar(animal);
        subject.notifyObservers("Novo animal dispon\u00EDvel para ado\u00E7\u00E3o: " + animal.getDescricao());
    }

    public boolean deletarAnimal(String animalId) {
        if (!deletionPolicy.podeExcluirAnimal(animalId)) {
            return false;
        }
        return animalRepository.deletar(animalId);
    }

    public boolean vacinarAnimal(String animalId) {
        IAnimal animal = animalRepository.buscarPorId(animalId);
        if (animal == null) {
            return false;
        }

        animal.vacinar();
        return true;
    }

    public List<String> listarAnimaisDisponiveis() {
        List<String> descricoes = new ArrayList<>();
        for (IAnimal animal : animalRepository.listar()) {
            descricoes.add(animal.getDescricao());
        }
        return descricoes;
    }

    private void validarId(String id) {
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("ID inv\u00E1lido, deve conter apenas n\u00FAmeros.");
        }
    }

    private void validarEspecie(String especie) {
        if (!especie.equalsIgnoreCase("c\u00E3o") && !especie.equalsIgnoreCase("gato")) {
            throw new IllegalArgumentException("Esp\u00E9cie inv\u00E1lida, deve ser 'c\u00E3o' ou 'gato'.");
        }
    }

    private void validarIdDisponivel(String id) {
        if (animalRepository.buscarPorId(id) != null) {
            throw new IllegalArgumentException("Erro: ID do animal j\u00E1 est\u00E1 em uso.");
        }
    }
}
