package org.example.Decorator;

import org.example.Interface.IAnimal;

public class VaccinatedDecorator extends AnimalDecorator {

    public VaccinatedDecorator(IAnimal animal) {
        super(animal);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", vacinado";
    }
}
