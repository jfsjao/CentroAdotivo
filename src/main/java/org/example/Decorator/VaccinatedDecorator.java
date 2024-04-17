package org.example.Decorator;

import org.example.Interface.Animal;

public class VaccinatedDecorator extends AnimalDecorator {

    public VaccinatedDecorator(Animal animal) {
        super(animal);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", vacinado";
    }
}
