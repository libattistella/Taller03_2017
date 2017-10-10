package com.example.lucas.workfromhome.DAO;

import com.example.lucas.workfromhome.Categoria;

/**
 * Created by mdominguez on 03/08/17.
 */

public  interface CategoriaDAO {
    public void inicializar();

    public Categoria[] listaCategoria();
}
