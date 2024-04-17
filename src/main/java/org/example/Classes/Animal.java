package org.example.Classes;

import org.example.Interface.IAnimal;

public class Animal implements IAnimal {
    private String id;
    private String especie;
    private String raca;
    private int idade;

    public Animal(String id, String especie, String raca, int idade) {
        this.id = id;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getEspecie() {
        return especie;
    }

    @Override
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    @Override
    public String getRaca() {
        return raca;
    }

    @Override
    public void setRaca(String raca) {
        this.raca = raca;
    }

    @Override
    public int getIdade() {
        return idade;
    }

    @Override
    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String getDescricao() {
        return especie + " " + raca + ", " + idade + " anos";
    }
}
