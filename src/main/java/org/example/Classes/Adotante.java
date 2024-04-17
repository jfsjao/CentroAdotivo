package org.example.Classes;

import org.example.Observer.Observer;

public class Adotante implements Observer {
    private String id;
    private String nome;
    private String endereco;
    private String preferenciaEspecie;

    public Adotante(String id, String nome, String endereco, String preferenciaEspecie) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.preferenciaEspecie = preferenciaEspecie;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPreferenciaEspecie() {
        return preferenciaEspecie;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public void update(String message) {
        System.out.println("Notificação para " + nome + ": " + message);
    }
}
