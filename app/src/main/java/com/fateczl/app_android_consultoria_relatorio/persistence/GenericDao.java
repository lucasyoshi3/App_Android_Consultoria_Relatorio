package com.fateczl.app_android_consultoria_relatorio.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "CONSULTORIA.DB";
    private static final int DATABASE_VER = 1;

    private static final String CREATE_TABLE_PESSOA =
            "CREATE TABLE pessoa ("+
                    "id INT NOT NULL PRIMARY KEY);";

//    private static final String CREATE_TABLE_CLIENTE =
//            "CREATE TABLE cliente(" +
//                    "pessoaId INT NOT NULL PRIMARY KEY, " +
//                    "cpf VARCHAR(11)  NOT NULL PRIMARY KEY, " +
//                    "endereco varchar(50) NOT NULL, " +
//                    "FOREIGN KEY (pessoaId) REFERENCES pessoa(id));";

    private static final String CREATE_TABLE_FUNCIONARIO=
            "CREATE TABLE funcionario(" +
                    "pessoaId INT NOT NULL PRIMARY KEY," +
                    "apelido VARCHAR(50) NOT NULL," +
                    "nome VARCHAR(50) NOT NULL," +
                    "FOREIGN KEY (pessoaId) REFERENCES pessoa(id));";

    private static final String CREATE_TABLE_RELATORIO =
            "CREATE TABLE relatorio (" +
                    "id INT NOT NULL PRIMARY KEY," +
                    "titulo VARCHAR(50) NOT NULL," +
                    "resumo VARCHAR(100) NOT NULL," +
                    "texto VARCHAR(400) NOT NULL," +
                    "imagem VARCHAR(30)," +
                    "link VARCHAR(60)," +
                    "anonimato INT NOT NULL," +
                    "publico INT NOT NULL," +
                    "cpfAcessoCliente VARCHAR(11)," +
                    "funcionarioPessoaId INT NOT NULL," +
                    "FOREIGN KEY (funcionarioPessoaId) REFERENCES funcionario(pessoaId));";

//    private static final String CREATE_TABLE_ACESSO =
//            "CREATE TABLE acesso (" +
//                    "id INT NOT NULL PRIMARY KEY," +
//                    "relatorioId INT NOT NULL," +
//                    "numeroAcesso INT NOT NULL," +
//                    "data VARCHAR(10) NOT NULL," +
//                    "clientePessoaid INT NOT NULL," +
//                    "clientecpf INT NOT NULL," +
//                    "FOREIGN KEY (relatorioId) REFERENCES relatorio(id)," +
//                    "FOREIGN KEY (clientePessoaid) REFERENCES cliente(pessoaId)," +
//                    "FOREIGN KEY (clientecpf) REFERENCES cliente(cpf));";

    public GenericDao(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PESSOA);
//        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_FUNCIONARIO);
        sqLiteDatabase.execSQL(CREATE_TABLE_RELATORIO);
//        sqLiteDatabase.execSQL(CREATE_TABLE_ACESSO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS acesso");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS relatorio");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS funcionario");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS cliente");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pessoa");
            onCreate(sqLiteDatabase);
        }
    }
}
