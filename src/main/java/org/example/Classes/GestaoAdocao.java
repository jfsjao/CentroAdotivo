package org.example.Classes;

import org.example.Interface.IAnimal;
import org.example.Observer.Observer;
import org.example.Observer.Subject;
import org.example.Observer.MatchingStrategy;

import java.util.ArrayList;
import java.util.List;

public class GestaoAdocao implements Subject {
    private static GestaoAdocao instance;  // Única instância

    private List<Observer> observers = new ArrayList<>();
    private List<IAnimal> animaisDisponiveis = new ArrayList<>();
    private List<String> animalToAdotante = new ArrayList<>();

    private GestaoAdocao() {}

    public static synchronized GestaoAdocao getInstance() {
        if (instance == null) {
            instance = new GestaoAdocao();
        }
        return instance;
    }

    public void adicionarAnimal(IAnimal animal) {
        if (animaisDisponiveis.stream().noneMatch(a -> a.getId().equals(animal.getId()))) {
            animaisDisponiveis.add(animal);
            notifyObservers("Novo animal disponível para adoção: " + animal.getDescricao());
        } else {
            System.err.println("Erro: ID do animal já está em uso.");
        }
    }

    public List<IAnimal> getAnimaisDisponiveis() {
        return new ArrayList<>(animaisDisponiveis);  // Retorna uma cópia para evitar manipulação direta
    }

    public boolean realizarAdocao(IAnimal animal, Adotante adotante, MatchingStrategy strategy) {
        if (strategy.match(animal, adotante)) {
            linkAnimalAdotante(animal.getId(), adotante.getId());
            System.out.println("Match realizado com sucesso entre " + adotante.getNome() + " e o animal: " + animal.getDescricao() + ".");
            return true;  // Indica sucesso no match
        }
        return false;  // Indica falha no match
    }

    public boolean deletarAnimal(String animalId) {
        if (podeExcluir(animalId)) {
            animaisDisponiveis.removeIf(animal -> animal.getId().equals(animalId));
            System.out.println("Animal deletado com sucesso.");
            return true;
        } else {
            System.err.println("Erro: Animal não pode ser deletado, ainda está vinculado.");
            return false;
        }
    }

    private boolean podeExcluir(String animalId) {
        return !animalToAdotante.contains(animalId);
    }

    private void linkAnimalAdotante(String animalId, String adotanteId) {
        animalToAdotante.add(animalId);
    }

    public void registrarAdotante(Adotante adotante) {
        addObserver(adotante);
    }

    public IAnimal buscarAnimal(String animalId) {
        for (IAnimal animal : animaisDisponiveis) {
            if (animal.getId().equals(animalId)) {
                return animal;
            }
        }
        return null; // Retorna null se o animal não for encontrado
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
