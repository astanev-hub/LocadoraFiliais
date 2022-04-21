package com.stanev.domain;

public class Carro implements Comparable<Carro>{

    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private double valorDiaria;
    private boolean disponivel;

    public Carro(String placa, String marca, String cor, String modelo, double valorDiaria, boolean disponivel) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public int compareTo(Carro o) {
        return Double.compare(this.valorDiaria, o.valorDiaria);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cor='" + cor + '\'' +
                ", valorDiaria=" + valorDiaria +
                ", disponivel=" + disponivel +
                '}';
    }
}