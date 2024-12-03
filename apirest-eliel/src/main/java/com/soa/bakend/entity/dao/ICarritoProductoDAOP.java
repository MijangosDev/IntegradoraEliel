package com.soa.bakend.entity.dao;

import com.soa.bakend.entity.CarritoProducto;
import com.soa.bakend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICarritoProductoDAOP extends JpaRepository<CarritoProducto, Producto> {

    @Query("select CarritoProducto ")
    public CarritoProducto getSolicitudBy(@Param("CarritoProducto") Long id);


    @Query("select CarritoProducto")
    public CarritoProducto getCarritoProductoBy(@Param("CarritoProducto") Long id);

}
