package org.example.Factory;


import org.example.Classes.Adotante;

public class AdotanteFactory {

    public static Adotante criarAdotante(String id, String nome, String endereco, String preferenciaEspecie) {
        return new Adotante(id, nome, endereco, preferenciaEspecie);
    }
}
