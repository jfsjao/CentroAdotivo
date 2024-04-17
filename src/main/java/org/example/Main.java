package org.example;

import org.example.Classes.Animal;
import org.example.Firebase.AnimalFirebaseDAO;
import org.example.Firebase.FirebaseInit;

import java.util.concurrent.TimeUnit;


    public class Main {

        public static void main(String[] args) throws InterruptedException {
            // Inicializa o Firebase (assegure-se de que a inicialização foi realizada em algum lugar do seu código)
            FirebaseInit.initialize();

            AnimalFirebaseDAO animalDAO = new AnimalFirebaseDAO();
            String animalKey = "animal1";
            Animal animal = new Animal(animalKey, "Cachorro", "Labrador", 3);

            // CREATE
            System.out.println("Creating animal...");
            animalDAO.create(animalKey, animal);

            // READ
            System.out.println("Reading animal...");
            animalDAO.read(animalKey, animalRead -> System.out.println("Animal read: " + animalRead.getDescricao()));

            // Aguarde alguns segundos para ler
            TimeUnit.SECONDS.sleep(3);

            // UPDATE
            System.out.println("Updating animal...");
            animal.setIdade(4); // Altera a idade do animal
            animalDAO.update(animalKey, animal);

            // DELETE
            //System.out.println("Deleting animal...");
            //.delete(animalKey);

            // Aguarde alguns segundos para garantir que as operações assíncronas foram concluídas
            TimeUnit.SECONDS.sleep(5);
        }
    }

