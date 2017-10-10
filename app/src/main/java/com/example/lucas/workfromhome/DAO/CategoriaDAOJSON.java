package com.example.lucas.workfromhome.DAO;

import android.content.Context;

import com.example.lucas.workfromhome.Categoria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mdominguez on 03/08/17.
 */

public class CategoriaDAOJSON implements CategoriaDAO {

    private Context ctx;
    private String filename = "categorias3.json";
    public CategoriaDAOJSON(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public void inicializar() {

        FileOutputStream outputStream=null;
        Categoria[] arreglo = new Categoria[5];
        arreglo[0] = new Categoria(1, "Arquitecto");
        arreglo[1] = new Categoria(2, "Desarrollador");
        arreglo[2] = new Categoria(3, "Tester");
        arreglo[3] = new Categoria(4, "Analista");
        arreglo[4] = new Categoria(5, "Mobile Developer");
        JSONArray arregloJson = new JSONArray();
        try {
            for(Categoria tp : arreglo){
                JSONObject objeto = new JSONObject();
                objeto.put("id",tp.getId());
                objeto.put("descripcion",tp.getDescripcion());
                arregloJson.put(objeto);
            }
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(arregloJson.toString().getBytes());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream!=null) try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Categoria[] listaCategoria() {
        if(!fileExists(this.filename)){
            this.inicializar();
        }
        Categoria[] resultado =null;
        FileInputStream in = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            in = ctx.openFileInput(filename);
            inputStreamReader = new InputStreamReader(in);
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            JSONArray arr = (JSONArray) new JSONTokener(sb.toString()).nextValue();
            resultado = new Categoria[arr.length()];
            for(int i=0;i<arr.length();i++){
                JSONObject obj = arr.getJSONObject(i);
                Categoria aux = new Categoria(obj.getInt("id"), obj.getString("descripcion"));
                resultado[i]=aux;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(in!=null) try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    private boolean fileExists(String filename) {
        File file = ctx.getFileStreamPath(filename);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }
}
