package org.example.Observer;

import org.example.Interface.IAnimal;
import org.example.Classes.Adotante;

public interface MatchingStrategy {
    boolean match(IAnimal animal, Adotante adotante);
}
