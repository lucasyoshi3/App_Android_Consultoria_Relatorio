package com.fateczl.app_android_consultoria_relatorio.model;

public class Cliente extends Pessoa{
    private String cpf;
    private String endereco;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return getIdPessoa()+"; "+getNome()+"; "+cpf+"; "+endereco;
    }
}
