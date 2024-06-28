package com.fateczl.app_android_consultoria_relatorio.model;

import java.time.LocalDate;

public class Acesso {
    private int numeroAcesso;
    private LocalDate data;

    public int getNumeroAcesso() {
        return numeroAcesso;
    }

    public void setNumeroAcesso(int numeroAcesso) {
        this.numeroAcesso = numeroAcesso;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return numeroAcesso+"; "+ data+ "";
    }
}
