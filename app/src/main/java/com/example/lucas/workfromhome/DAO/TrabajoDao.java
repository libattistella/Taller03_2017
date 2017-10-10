package com.example.lucas.workfromhome.DAO;

import com.example.lucas.workfromhome.Categoria;
import com.example.lucas.workfromhome.Trabajo;

/**
 * Created by mdominguez on 03/08/17.
 */

public interface TrabajoDao {

    public void inicializar();

    public void crearOferta(Trabajo p);

    public Trabajo cargarOferta();

    public Trabajo[] listaTrabajos();

}
