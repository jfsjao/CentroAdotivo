package org.example.Service;

import org.example.Classes.Adotante;
import org.example.Factory.AdotanteFactory;
import org.example.Interface.AdocaoRepository;
import org.example.Interface.AnimalRepository;
import org.example.Interface.IAnimal;
import org.example.Observer.Subject;

public class AdocaoService {
    private final AnimalRepository animalRepository;
    private final AdocaoRepository adocaoRepository;
    private final Subject subject;

    public AdocaoService(AnimalRepository animalRepository, AdocaoRepository adocaoRepository, Subject subject) {
        this.animalRepository = animalRepository;
        this.adocaoRepository = adocaoRepository;
        this.subject = subject;
    }

    public String realizarAdocao(String adotanteId, String nome, String endereco, String especiePreferida) {
        validarEspecie(especiePreferida);

        Adotante adotante = AdotanteFactory.criarAdotante(adotanteId, nome, endereco, especiePreferida);
        subject.addObserver(adotante);

        for (IAnimal animal : animalRepository.listar()) {
            if (animal.getEspecie().equalsIgnoreCase(especiePreferida) && !adocaoRepository.existeParaAnimal(animal.getId())) {
                adocaoRepository.salvar(animal.getId(), adotante.getId());
                System.out.println("Match realizado com sucesso entre " + adotante.getNome() + " e o animal: " + animal.getDescricao() + ".");
                return animal.getDescricao();
            }
        }

        return null;
    }

    private void validarEspecie(String especiePreferida) {
        if (!especiePreferida.equalsIgnoreCase("c\u00E3o") && !especiePreferida.equalsIgnoreCase("gato")) {
            throw new IllegalArgumentException("Esp\u00E9cie inv\u00E1lida, deve ser 'c\u00E3o' ou 'gato'.");
        }
    }
}
