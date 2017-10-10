package com.example.lucas.workfromhome.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lucas.workfromhome.Categoria;

/**
 * Created by martdominguez on 28/07/2017.
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
        switch (i1) {
            case 2:
                sqLiteDatabase.execSQL("DELETE FROM CATEGORIA");
                Categoria[] arreglo = new Categoria[5];
                arreglo[0] = new Categoria(1, "Arquitecto");
                arreglo[1] = new Categoria(2, "Desarrollador");
                arreglo[2] = new Categoria(3, "Tester");
                arreglo[3] = new Categoria(4, "Analista");
                arreglo[4] = new Categoria(5, "Mobile Developer");

                Cursor res = sqLiteDatabase.rawQuery("SELECT ID, DESCRIPCION FROM CATEGORIA ", null);
                if (res.getCount() > 0) return;
                for (Categoria unaCat : arreglo) {
                    ContentValues valores = new ContentValues();
                    valores.put("ID", unaCat.getId());
                    valores.put("DESCRIPCION", unaCat.getDescripcion());
                    sqLiteDatabase.insert("CATEGORIA", null, valores);
                }
            case 3:
                sqLiteDatabase.execSQL(SQL_CREATE_TRABAJO);
        }
    }
}
