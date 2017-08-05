package com.example.ventasonline;

/**
 * Created by asirianni on 07/06/2017.
 */

public class Producto {
    private int codigo;
    private String descripcion;
    private String rubro;
    private String subrubro;
    private float precio1;
    private float precio2;
    private float precio3;
    private float precio4;
    private float cantidad;
    private float importe;
    private float descuento;
    private float total;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getSubrubro() {
        return subrubro;
    }

    public void setSubrubro(String subrubro) {
        this.subrubro = subrubro;
    }

    public float getPrecio1() {
        return precio1;
    }

    public void setPrecio1(float precio1) {
        this.precio1 = precio1;
    }

    public float getPrecio2() {
        return precio2;
    }

    public void setPrecio2(float precio2) {
        this.precio2 = precio2;
    }

    public float getPrecio3() {
        return precio3;
    }

    public void setPrecio3(float precio3) {
        this.precio3 = precio3;
    }

    public float getPrecio4() {
        return precio4;
    }

    public void setPrecio4(float precio4) {
        this.precio4 = precio4;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
