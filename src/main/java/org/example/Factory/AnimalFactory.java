package org.example.Factory;

import org.example.Classes.Animal;

public class AnimalFactory {

    public static Animal criarAnimal(String id, String especie, String raca, int idade) {
        return new Animal(id, especie, raca, idade);
    }
}
