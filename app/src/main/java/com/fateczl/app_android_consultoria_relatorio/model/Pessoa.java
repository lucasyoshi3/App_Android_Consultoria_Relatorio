package com.fateczl.app_android_consultoria_relatorio.model;

public abstract class Pessoa {
    private int idPessoa;
    private String nome;

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
