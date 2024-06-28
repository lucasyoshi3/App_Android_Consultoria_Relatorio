package com.fateczl.app_android_consultoria_relatorio.model;

public class Relatorio {
    private int idRelatorio;
    private String titulo;
    private String resumo;
    private String  texto;
    private String imegem;
    private String link;
    private int anonimato;
    private int publico;
    private String cpfAcessoCliente;
    private int funcionarioId;

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImegem() {
        return imegem;
    }

    public void setImegem(String imegem) {
        this.imegem = imegem;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getAnonimato() {
        return anonimato;
    }

    public void setAnonimato(int anonimato) {
        this.anonimato = anonimato;
    }

    public String getCpfAcessoCliente() {
        return cpfAcessoCliente;
    }

    public void setCpfAcessoCliente(String cpfAcessoCliente) {
        this.cpfAcessoCliente = cpfAcessoCliente;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "idRelatorio=" + idRelatorio +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", texto='" + texto + '\'' +
                ", imegem='" + imegem + '\'' +
                ", link='" + link + '\'' +
                ", anonimato=" + anonimato +
                ", publico=" + publico +
                ", cpfAcessoCliente='" + cpfAcessoCliente + '\'' +
                '}';
    }
}
