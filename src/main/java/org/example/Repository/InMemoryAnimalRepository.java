package org.example.Repository;

import org.example.Interface.AnimalRepository;
import org.example.Interface.IAnimal;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class InMemoryAnimalRepository implements AnimalRepository {
    private final List<IAnimal> animais = new ArrayList<>();

    @Override
    public void salvar(IAnimal animal) {
        ListIterator<IAnimal> iterator = animais.listIterator();
        while (iterator.hasNext()) {
            IAnimal atual = iterator.next();
            if (atual.getId().equals(animal.getId())) {
                iterator.set(animal);
                return;
            }
        }
        animais.add(animal);
    }

    @Override
    public IAnimal buscarPorId(String id) {
        for (IAnimal animal : animais) {
            if (animal.getId().equals(id)) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public List<IAnimal> listar() {
        return new ArrayList<>(animais);
    }

    @Override
    public boolean deletar(String id) {
        return animais.removeIf(animal -> animal.getId().equals(id));
    }
}
