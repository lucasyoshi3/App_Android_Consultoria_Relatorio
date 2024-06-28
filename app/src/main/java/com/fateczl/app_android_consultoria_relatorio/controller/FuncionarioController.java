package com.fateczl.app_android_consultoria_relatorio.controller;

import android.database.Cursor;

import com.fateczl.app_android_consultoria_relatorio.model.Funcionario;
import com.fateczl.app_android_consultoria_relatorio.persistence.FuncionarioDao;

import java.sql.SQLException;
import java.util.List;

public class FuncionarioController implements IFuncionarioController{

    private final FuncionarioDao fDao;

    public FuncionarioController(FuncionarioDao fDao){
        this.fDao = fDao;
    }
    @Override
    public void inserir(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null){
            fDao.open();
        }
        fDao.insert(funcionario);
        fDao.close();
    }

    @Override
    public void modificar(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null){
            fDao.open();
        }
        fDao.update(funcionario);
        fDao.close();
    }

    @Override
    public void deletar(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null){
            fDao.open();
        }
        fDao.delete(funcionario);
        fDao.close();
    }

    @Override
    public Funcionario buscar(Funcionario funcionario) throws SQLException {
        if(fDao.open() == null){
            fDao.open();
        }
        return fDao.findOne(funcionario);

    }

    @Override
    public List<Funcionario> listar() throws SQLException {
        if(fDao.open() == null){
            fDao.open();
        }
        return fDao.findAll("");
    }
}
