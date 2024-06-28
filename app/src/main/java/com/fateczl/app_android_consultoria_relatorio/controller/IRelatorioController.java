package com.fateczl.app_android_consultoria_relatorio.controller;

import com.fateczl.app_android_consultoria_relatorio.model.Relatorio;

import java.sql.SQLException;
import java.util.List;

public interface IRelatorioController {

    public void inserir(Relatorio relatorio) throws SQLException;
    public void modificar(Relatorio relatorio) throws SQLException;
    public void deletar(Relatorio relatorio) throws SQLException;
    public Relatorio buscar(Relatorio relatorio) throws SQLException;
    public List<Relatorio> listar(String cpf) throws SQLException;
}
