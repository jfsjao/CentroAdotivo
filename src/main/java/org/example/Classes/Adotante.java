package org.example.Classes;


public class Adotante {
    private String id;
    private String nome;
    private String endereco;
    private String preferenciaEspecie;

    public Adotante() {
        // Construtor padrão necessário para chamadas ao DataSnapshot.getValue(Adotante.class)
    }

    public Adotante(String id, String nome, String endereco, String preferenciaEspecie) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.preferenciaEspecie = preferenciaEspecie;
    }

    // Getters e setters para propriedades que serão armazenadas no Firebase
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPreferenciaEspecie() {
        return preferenciaEspecie;
    }

    public void setPreferenciaEspecie(String preferenciaEspecie) {
        this.preferenciaEspecie = preferenciaEspecie;
    }

    // Este método será chamado quando o Adotante receber uma atualização do notificador
    public void update(String message) {
        System.out.println("Adotante " + nome + " recebeu a atualização: " + message);
    }
}
