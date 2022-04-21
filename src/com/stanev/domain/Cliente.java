package com.stanev.domain;

public class Cliente {
    private String nome;
    private String telefone;
    private String endereco;
    private Carro carroFavorito;

    public Cliente(String nome, String telefone, String endereco, Carro carroFavorito) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.carroFavorito = carroFavorito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Carro getCarroFavorito() {
        return carroFavorito;
    }

    public void setCarroFavorito(Carro carroFavorito) {
        this.carroFavorito = carroFavorito;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", carroFavorito=" + carroFavorito.toString() +
                '}';
    }
}
