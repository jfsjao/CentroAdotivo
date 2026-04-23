package org.example.Service;

import org.example.Classes.Animal;
import org.example.Classes.GestaoAdocao;
import org.example.Decorator.VaccinatedDecorator;
import org.example.Factory.AnimalFactory;
import org.example.Interface.IAnimal;

import java.util.ArrayList;
import java.util.List;

public class AnimalService {
    private final GestaoAdocao gestaoAdocao;

    public AnimalService(GestaoAdocao gestaoAdocao) {
        this.gestaoAdocao = gestaoAdocao;
    }

    public void adicionarAnimal(String id, String especie, String raca, int idade) {
        validarId(id);
        validarEspecie(especie);

        Animal animal = AnimalFactory.criarAnimal(id, especie, raca, idade);
        gestaoAdocao.adicionarAnimal(animal);
    }

    public boolean deletarAnimal(String animalId) {
        return gestaoAdocao.deletarAnimal(animalId);
    }

    public boolean vacinarAnimal(String animalId) {
        IAnimal animal = gestaoAdocao.buscarAnimal(animalId);
        if (animal == null) {
            return false;
        }

        IAnimal animalVacinado = new VaccinatedDecorator(animal);
        return gestaoAdocao.atualizarAnimal(animalId, animalVacinado);
    }

    public List<String> listarAnimaisDisponiveis() {
        List<String> descricoes = new ArrayList<>();
        for (IAnimal animal : gestaoAdocao.getAnimaisDisponiveis()) {
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
}
