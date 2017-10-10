package com.example.lucas.workfromhome.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lucas.workfromhome.Trabajo;

/**
 * Created by martdominguez on 17/08/17.
 */

public class TrabajoDaoSQLite implements TrabajoDao {

    private WorkFromHomeOpenHelper myHelper;

    public TrabajoDaoSQLite(Context ctx){

        myHelper=WorkFromHomeOpenHelper.getInstance(ctx);
    }


    @Override
    public void inicializar() {

    }

    @Override
    public void crearOferta(Trabajo p) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ID_CATEGORIA", p.getCategoria().getId());
        cv.put("DESCRIPCION", p.getDescripcion());
        cv.put("HORAS_PRESUPUESTADAS", p.getHorasPresupuestadas());
        cv.put("PRECIO_MAXIMO_HORA", p.getPrecioMaximoHora());
        cv.put("FECHA_ENTREGA", String.valueOf(p.getFechaEntrega()));
        cv.put("MONEDA_PAGO", p.getMonedaPago());
        cv.put("REQUIERE_INGLES", p.getRequiereIngles());
        db.insert("TRABAJO",null,cv);
    }

    @Override
    public Trabajo[] listaTrabajos() {
        SQLiteDatabase db =myHelper.getReadableDatabase();
        return null;
        //return db.rawQuery("SELECT ID _id,NOMBRE,DESCRIPCION,ID_TIPO_PLATO,PRECIO FROM PLATO",null);
    }
}
