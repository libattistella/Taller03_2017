package com.example.lucas.workfromhome.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lucas.workfromhome.Categoria;

/**
 * Created by martdominguez on 28/07/2017.
 */

public class CategoriaDAOSqlite implements CategoriaDAO {

    private WorkFromHomeOpenHelper myHelper;

    public CategoriaDAOSqlite(Context ctx){

        myHelper=WorkFromHomeOpenHelper.getInstance(ctx);
    }

    @Override
    public void inicializar(){
     /*   TipoPlato[] arreglo = new TipoPlato[5];
        arreglo[0]=new TipoPlato("EntradaDB");
        arreglo[1]=new TipoPlato("PrincipalDB");
        arreglo[2]=new TipoPlato("PostreDB");
        arreglo[3]=new TipoPlato("MinutaDB");
        arreglo[4]=new TipoPlato("BebidaDB");

        SQLiteDatabase db = myHelper.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT ID,NOMBRE FROM TIPO_PLATO ",null);
        if(res.getCount()>0) return;
        for(TipoPlato unPlato : arreglo) {
            ContentValues valores = new ContentValues();
            valores.put("ID", unPlato.getId());
            valores.put("NOMBRE", unPlato.getNombre());
            db.insert("TIPO_PLATO", null, valores);
        }*/
    }

    @Override
    public Categoria[] listaCategoria(){
        SQLiteDatabase db = this.myHelper.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT ID, DESCRIPCION FROM CATEGORIA ",null);
        int cantidad = res.getCount();
        Categoria[] arregloResultado = new Categoria[cantidad];
        int indice =0;
        while(res.moveToNext()){
            String desc = res.getString(res.getColumnIndex("DESCRIPCION"));
            arregloResultado[indice]=new Categoria(res.getInt(res.getColumnIndex("ID")), desc);
            indice++;
        }
        return arregloResultado;
    }

}
