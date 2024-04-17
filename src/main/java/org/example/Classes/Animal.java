package org.example.Classes;

import com.google.firebase.database.Exclude;

public class Animal {
    private String id;
    private String especie;
    private String raca;
    private int idade;

    public Animal() {
        // Construtor padrão necessário para chamadas ao DataSnapshot.getValue(Animal.class)
    }

    public Animal(String id, String especie, String raca, int idade) {
        this.id = id;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
    }

    // Getters e setters para propriedades que serão armazenadas no Firebase
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Método para obter a descrição do animal
    @Exclude // Não precisa ser persistido no Firebase
    public String getDescricao() {
        return "Espécie: " + especie + ", Raça: " + raca + ", Idade: " + idade;
    }
}