package com.fateczl.app_android_consultoria_relatorio.persistence;

import android.database.sqlite.SQLiteException;

import com.fateczl.app_android_consultoria_relatorio.model.Relatorio;

import java.sql.SQLException;

public interface IRelatorioDao {

    public RelatorioDao open() throws SQLException;

    public void close();
}
