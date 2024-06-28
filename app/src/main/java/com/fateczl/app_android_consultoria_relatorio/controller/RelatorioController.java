package com.fateczl.app_android_consultoria_relatorio.controller;

import com.fateczl.app_android_consultoria_relatorio.model.Relatorio;
import com.fateczl.app_android_consultoria_relatorio.persistence.RelatorioDao;

import java.sql.SQLException;
import java.util.List;

public class RelatorioController implements IRelatorioController {

    private final RelatorioDao rDao;

    public RelatorioController(RelatorioDao rDao){
        this.rDao = rDao;
    }
    @Override
    public void inserir(Relatorio relatorio) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.insert(relatorio);
        rDao.close();
    }

    @Override
    public void modificar(Relatorio relatorio) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.update(relatorio);
        rDao.close();
    }

    @Override
    public void deletar(Relatorio relatorio) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        rDao.delete(relatorio);
        rDao.close();
    }

    @Override
    public Relatorio buscar(Relatorio relatorio) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        return rDao.findOne(relatorio);
    }

    @Override
    public List<Relatorio> listar(String cpf) throws SQLException {
        if(rDao.open() == null){
            rDao.open();
        }
        return rDao.findAll(cpf);
    }
}
