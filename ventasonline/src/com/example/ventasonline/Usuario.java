package com.example.ventasonline;

import java.util.ArrayList;

/**
 * Created by asirianni on 07/06/2017.
 */

public class Usuario {
    private Perfil perfil;
    private ArrayList <Producto> productos;
    private ArrayList <Rubro> rubros;
    private ArrayList <Cliente> clientes;
    private ArrayList <Pedido> pedidos;
    private Pedido pedidoActual;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Rubro> getRubros() {
        return rubros;
    }

    public void setRubros(ArrayList<Rubro> rubros) {
        this.rubros = rubros;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedidoActual() {
        return pedidoActual;
    }

    public void setPedidoActual(Pedido pedidoActual) {
        this.pedidoActual = pedidoActual;
    }

    public void actualizarBase(){

    }

    public void enviarPedidosConfirmados(){

    }
    
}
