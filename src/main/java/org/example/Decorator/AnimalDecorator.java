package org.example.Decorator;

import org.example.Interface.IAnimal;

public abstract class AnimalDecorator implements IAnimal {
    protected IAnimal animalDecorado;

    public AnimalDecorator(IAnimal animal) {
        this.animalDecorado = animal;
    }

    @Override
    public String getId() {
        return animalDecorado.getId();
    }

    @Override
    public void setId(String id) {
        animalDecorado.setId(id);
    }

    @Override
    public String getEspecie() {
        return animalDecorado.getEspecie();
    }

    @Override
    public void setEspecie(String especie) {
        animalDecorado.setEspecie(especie);
    }

    @Override
    public String getRaca() {
        return animalDecorado.getRaca();
    }

    @Override
    public void setRaca(String raca) {
        animalDecorado.setRaca(raca);
    }

    @Override
    public int getIdade() {
        return animalDecorado.getIdade();
    }

    @Override
    public void setIdade(int idade) {
        animalDecorado.setIdade(idade);
    }

    @Override
    public String getDescricao() {
        return animalDecorado.getDescricao();
    }
}
