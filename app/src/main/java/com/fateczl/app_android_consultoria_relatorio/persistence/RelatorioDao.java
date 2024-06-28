package com.fateczl.app_android_consultoria_relatorio.persistence;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fateczl.app_android_consultoria_relatorio.model.Relatorio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDao implements IRelatorioDao, ICRUDDao<Relatorio>{

    private final Context context;
    private GenericDao gDao;
    private SQLiteDatabase database;

    public RelatorioDao(Context context){
        this.context = context;
    }

    @Override
    public RelatorioDao open() throws SQLException {
        gDao = new GenericDao(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    @Override
    public void insert(Relatorio relatorio) throws SQLException {
        ContentValues contentValues = getContentValues(relatorio);
        database.insert("relatorio", null, contentValues);
    }

    @Override
    public int update(Relatorio relatorio) throws SQLException {
        ContentValues contentValues = getContentValues(relatorio);
        int retorno = database.update("relatorio", contentValues, "id = "+
                relatorio.getIdRelatorio(), null);
        return retorno;
    }

    @Override
    public void delete(Relatorio relatorio) throws SQLException {
        database.delete("relatorio", "id = "+
                relatorio.getIdRelatorio(), null);
    }

    @SuppressWarnings("Range")
    @Override
    public Relatorio findOne(Relatorio relatorio) throws SQLException {
        String sql = "SELECT titulo, resumo, texto, imagem, link, funcionarioPessoaId FROM relatorio WHERE id = "+relatorio.getIdRelatorio();
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            relatorio.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            relatorio.setResumo(cursor.getString(cursor.getColumnIndex("resumo")));
            relatorio.setTexto(cursor.getString(cursor.getColumnIndex("texto")));
            relatorio.setImegem(cursor.getString(cursor.getColumnIndex("imagem")));
            relatorio.setLink(cursor.getString(cursor.getColumnIndex("link")));
            relatorio.setFuncionarioId(cursor.getInt(cursor.getColumnIndex("funcionarioPessoaId")));

        }
        cursor.close();

        return relatorio;
    }

    @SuppressWarnings("Range")
    @Override
    public List<Relatorio> findAll(String cpf) throws SQLException {
        List<Relatorio> relatorios = new ArrayList<>();
        String sql = "SELECT r.id, r.titulo, r.funcionarioPessoaId, f.nome " +
                "FROM relatorio r, funcionario f WHERE cpfAcessoCliente = "+ cpf+ " OR cpfAcessoCliente = null";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            Relatorio relatorio = new Relatorio();
            relatorio.setIdRelatorio(cursor.getInt(cursor.getColumnIndex("id")));
            relatorio.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            relatorio.setFuncionarioId(cursor.getInt(cursor.getColumnIndex("funcionarioPessoaId")));

            relatorios.add(relatorio);
            cursor.moveToNext();
        }
        cursor.close();
        return null;
    }


    private static ContentValues getContentValues(Relatorio relatorio) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", relatorio.getIdRelatorio());
        contentValues.put("titulo", relatorio.getTitulo());
        contentValues.put("resumo", relatorio.getResumo());
        contentValues.put("texto", relatorio.getTexto());
        contentValues.put("imagem", relatorio.getImegem());
        contentValues.put("link", relatorio.getLink());
        contentValues.put("anonimato", relatorio.getAnonimato());
//        contentValues.put("publico", relatorio.getPublico());
        contentValues.put("cpfAcessoCliente", relatorio.getCpfAcessoCliente());
        contentValues.put("funcionarioPessoaId", relatorio.getFuncionarioId());

        return contentValues;
    }

}
