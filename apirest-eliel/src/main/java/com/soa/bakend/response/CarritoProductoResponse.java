package com.soa.bakend.response;

import com.soa.bakend.entity.CarritoProducto;

import java.util.List;

public class CarritoProductoResponse {

    private List<CarritoProducto> carritoProductos;

    public List<CarritoProducto> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(List<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }
}
