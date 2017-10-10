package com.example.lucas.workfromhome.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lbattistella on 10/10/2017.
 */

public class WorkFromHomeOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_CATEGORIA = "CREATE TABLE CATEGORIA " +
            "(ID INTEGER PRIMARY KEY, " +
            "DESCRIPCION TEXT)";

    private static final String SQL_CREATE_TRABAJO= "CREATE TABLE TRABAJO " +
            "(ID INTEGER PRIMARY KEY, " +
            "DESCRIPCION TEXT," +
            "HORAS_PRESUPUESTADAS INTEGER, " +
            "ID_CATEGORIA INTEGER, " +
            "PRECIO_MAXIMO_HORA REAL," +
            "FECHA_ENTREGA TEXT, " +
            "MONEDA_PAGO INTEGER, " +
            "REQUIERE_INGLES INTEGER)";

    private static WorkFromHomeOpenHelper _INSTANCE;

    private WorkFromHomeOpenHelper(Context ctx){
        super(ctx,"WORK_FROM_HOME",null,3);
    }

    public static WorkFromHomeOpenHelper getInstance(Context ctx){
        if(_INSTANCE==null) _INSTANCE = new WorkFromHomeOpenHelper(ctx);
        return _INSTANCE;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_CATEGORIA);
        sqLiteDatabase.execSQL(SQL_CREATE_TRABAJO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
