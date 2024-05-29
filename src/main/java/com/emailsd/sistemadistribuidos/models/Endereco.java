package com.emailsd.sistemadistribuidos.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Embeddable
public class Endereco implements Serializable {

    private String rua;
    private String bairro;
    private String cidade;
    private String cep;
    private String numero;

    public Endereco() {

    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Endereco(String rua, String bairro, String cidade, String cep, String numero) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.numero = numero;
    }
}




