package org.example.Notificador;

import org.example.Observer.Observer;
import org.example.Observer.Subject;
import java.util.ArrayList;
import java.util.List;

public class AnimalAdocaoNotificador implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Método auxiliar para adicionar a funcionalidade específica de notificação de novos animais
    public void novoAnimalDisponivel(String descricaoAnimal) {
        notifyObservers("Novo animal disponível para adoção: " + descricaoAnimal);
    }
}
