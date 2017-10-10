package com.example.lucas.workfromhome.DAO;

import android.content.Context;

import com.example.lucas.workfromhome.Categoria;
import com.example.lucas.workfromhome.Trabajo;

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
 * Created by Lucas on 10/10/2017.
 */

public class TrabajoDAOJSON implements TrabajoDao{

    private Context ctx;
    private String filename = "categorias3.json";
    public TrabajoDAOJSON(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public void inicializar() {

        FileOutputStream outputStream=null;
        Trabajo[] arreglo = new Trabajo[]{
            new Trabajo(1, "Proyecto ABc"),
            new Trabajo(2, "Sistema de Gestion"),
            new Trabajo(3, "Aplicacion XYZ"),
            new Trabajo(4, "Modulo NN1"),
            new Trabajo(5, "Sistema de adminisracion TDF"),
            new Trabajo(6, "Aplicacion OYU 66"),
            new Trabajo(7, "Gestion de laboratorios"),
            new Trabajo(8, "Sistema Universidades"),
            new Trabajo(9, "Portal Compras"),
            new Trabajo(10, "Gestion RRHH"),
            new Trabajo(11, "Traductor Automatico"),
            new Trabajo(12, "Alquileres online"),
            new Trabajo(13, "Gestion financiera"),
            new Trabajo(14, "Modulo Seguridad"),
            new Trabajo(15, "consultoria performance"),
            new Trabajo(16, "Ecommerce Uah"),
            new Trabajo(17, "Portal Web Htz"),
            new Trabajo(18, "Sitio Coroporativo"),
            new Trabajo(19, "Aplicacion www1")
        };
        JSONArray arregloJson = new JSONArray();
        try {
            for(Trabajo tp : arreglo){
                JSONObject objeto = new JSONObject();
                objeto.put("id",tp.getId());
                objeto.put("descripcion",tp.getDescripcion());
                objeto.put("horasPresupuestadas",tp.getHorasPresupuestadas());
                objeto.put("monedaPago",tp.getMonedaPago());
                objeto.put("precioMaximoHora",tp.getPrecioMaximoHora());
                objeto.put("fechaEntrega",tp.getFechaEntrega());
                objeto.put("requiereIngles",tp.getRequiereIngles());
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
    public void crearOferta(Trabajo p) {

    }

    @Override
    public Trabajo cargarOferta() {
        return null;
    }

    @Override
    public Trabajo[] listaTrabajos() {
        if(!fileExists(this.filename)){
            this.inicializar();
        }
        Trabajo[] resultado =null;
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
            resultado = new Trabajo[arr.length()];
            for(int i=0;i<arr.length();i++){
                JSONObject obj = arr.getJSONObject(i);
                Trabajo aux = new Trabajo(obj.getInt("id"), obj.getString("descripcion"));
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
