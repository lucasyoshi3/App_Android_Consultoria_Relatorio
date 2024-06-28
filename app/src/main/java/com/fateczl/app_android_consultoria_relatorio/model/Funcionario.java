package com.fateczl.app_android_consultoria_relatorio.model;

public class Funcionario extends Pessoa{
    private String apelido;

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    @Override
    public String toString() {
        return getIdPessoa()+"; "+getNome()+"; "+apelido+" ";
    }
}
