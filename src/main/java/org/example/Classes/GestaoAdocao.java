package org.example.Classes;


import org.example.Firebase.AnimalFirebaseDAO;
import org.example.Notificador.AnimalAdocaoNotificador;
import org.example.Observer.MatchingStrategy;

public class GestaoAdocao {
    private AnimalAdocaoNotificador notificador;
    private AnimalFirebaseDAO animalDao = new AnimalFirebaseDAO();

    public GestaoAdocao(AnimalAdocaoNotificador notificador) {
        this.notificador = notificador;
    }

    public void adicionarAnimal(Animal animal) {
        animalDao.create(animal.getId(), animal);
        notificador.novoAnimalDisponivel(animal.getDescricao());
    }

    public void realizarAdocao(Animal animal, Adotante adotante, MatchingStrategy strategy) {
        if (strategy.match((org.example.Interface.Animal) animal, adotante)) {
            System.out.println("Match realizado com sucesso entre " + adotante.getNome() + " e o animal: " + animal.getDescricao() + ".");
            animalDao.delete(animal.getId());
        } else {
            System.out.println("Não foi possível realizar o match.");
        }
    }
}
