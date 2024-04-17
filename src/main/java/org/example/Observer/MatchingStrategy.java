package org.example.Observer;

import org.example.Classes.Adotante;
import org.example.Interface.Animal;

public interface MatchingStrategy {
    boolean match(Animal animal, Adotante adotante);
}
