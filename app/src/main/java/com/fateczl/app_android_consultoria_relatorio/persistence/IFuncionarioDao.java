package com.fateczl.app_android_consultoria_relatorio.persistence;

import java.sql.SQLException;

public interface IFuncionarioDao {

    public FuncionarioDao open() throws SQLException;

    public void close();
}
