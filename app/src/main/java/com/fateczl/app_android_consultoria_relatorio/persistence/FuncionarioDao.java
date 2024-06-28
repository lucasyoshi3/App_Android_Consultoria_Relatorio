package com.fateczl.app_android_consultoria_relatorio.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fateczl.app_android_consultoria_relatorio.model.Funcionario;
import com.fateczl.app_android_consultoria_relatorio.model.Relatorio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao implements IFuncionarioDao, ICRUDDao<Funcionario> {
    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public FuncionarioDao(Context context){
        this.context = context;
    }

    @Override
    public FuncionarioDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Funcionario funcionario) throws SQLException {
        ContentValues contentValues = getContentValues(funcionario);
        database.insert("funcionario", null, contentValues);
    }

    @Override
    public int update(Funcionario funcionario) throws SQLException {
        ContentValues contentValues = getContentValues(funcionario);
        int ret = database.update("funcionario",contentValues, "pessoaId = " + funcionario.getIdPessoa(),null);
        return 0;
    }

    @Override
    public void delete(Funcionario funcionario) throws SQLException {
        database.delete("funcionario","pessoaId = " + funcionario.getIdPessoa(),null);
    }

    @SuppressWarnings("Range")
    @Override
    public Funcionario findOne(Funcionario funcionario) throws SQLException {
        String sql = "SELECT pessoaId, nome, apelido " +
                "FROM funcionario " +
                "WHERE pessoaId = "+ funcionario.getIdPessoa();
        Cursor cursor = database.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        if(!cursor.isAfterLast()){
            funcionario.setIdPessoa(cursor.getInt(cursor.getColumnIndex("pessoaId")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setApelido(cursor.getString(cursor.getColumnIndex("apelido")));

        }
        cursor.close();
        return funcionario;
    }

    @SuppressWarnings("Range")
    @Override
    public List<Funcionario> findAll(String c) throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT pessoaId, nome, apelido " +
                "FROM funcionario";
        Cursor cursor = database.rawQuery(sql, null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        if(!cursor.isAfterLast()){
            Funcionario funcionario = new Funcionario();
            funcionario.setIdPessoa(cursor.getInt(cursor.getColumnIndex("pessoaId")));
            funcionario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            funcionario.setApelido(cursor.getString(cursor.getColumnIndex("apelido")));

            funcionarios.add(funcionario);
            cursor.moveToNext();
        }
        cursor.close();
        return funcionarios;
    }

    private static ContentValues getContentValues(Funcionario funcionario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pessoaId",funcionario.getIdPessoa());
        contentValues.put("nome", funcionario.getNome());
        contentValues.put("apelido", funcionario.getApelido());

        return contentValues;
    }
}
