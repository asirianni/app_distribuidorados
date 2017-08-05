package com.example.ventasonline;

import java.util.ArrayList;

/**
 * Created by asirianni on 07/06/2017.
 */

public class Rubro {
    private int codigo;
    private String Descripcion;
    private ArrayList<SubRubro> subrubros;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public ArrayList<SubRubro> getSubrubros() {
        return subrubros;
    }

    public void setSubrubros(ArrayList<SubRubro> subrubros) {
        this.subrubros = subrubros;
    }
}
