package org.example.Service;

import org.example.Classes.Adotante;
import org.example.Classes.GestaoAdocao;
import org.example.Factory.AdotanteFactory;
import org.example.Interface.IAnimal;

public class AdocaoService {
    private final GestaoAdocao gestaoAdocao;

    public AdocaoService(GestaoAdocao gestaoAdocao) {
        this.gestaoAdocao = gestaoAdocao;
    }

    public String realizarAdocao(String adotanteId, String nome, String endereco, String especiePreferida) {
        validarEspecie(especiePreferida);

        Adotante adotante = AdotanteFactory.criarAdotante(adotanteId, nome, endereco, especiePreferida);
        gestaoAdocao.registrarAdotante(adotante);

        for (IAnimal animal : gestaoAdocao.getAnimaisDisponiveis()) {
            if (animal.getEspecie().equalsIgnoreCase(especiePreferida)
                    && gestaoAdocao.realizarAdocao(
                    animal,
                    adotante,
                    (animalDisponivel, adotanteAtual) -> animalDisponivel.getEspecie().equals(adotanteAtual.getPreferenciaEspecie()))) {
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
