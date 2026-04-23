package org.example.Classes;

import org.example.Interface.AdocaoRepository;
import org.example.Interface.AnimalDeletionPolicy;
import org.example.Observer.Observer;
import org.example.Observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class GestaoAdocao implements Subject, AnimalDeletionPolicy {
    private static GestaoAdocao instance;

    private final List<Observer> observers = new ArrayList<>();
    private AdocaoRepository adocaoRepository;

    private GestaoAdocao() {}

    public static synchronized GestaoAdocao getInstance() {
        if (instance == null) {
            instance = new GestaoAdocao();
        }
        return instance;
    }

    public void configurarRepositorioAdocao(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    @Override
    public boolean podeExcluirAnimal(String animalId) {
        return !adocaoRepository.existeParaAnimal(animalId);
    }

    public void registrarAdotante(Adotante adotante) {
        addObserver(adotante);
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
