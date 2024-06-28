package com.fateczl.app_android_consultoria_relatorio.controller;

import com.fateczl.app_android_consultoria_relatorio.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface IFuncionarioController {

    public void inserir(Funcionario funcionario) throws SQLException;
    public void modificar(Funcionario funcionario) throws SQLException;
    public void deletar(Funcionario funcionario) throws SQLException;
    public Funcionario buscar(Funcionario funcionario)throws SQLException;
    public List<Funcionario> listar() throws SQLException;
}
