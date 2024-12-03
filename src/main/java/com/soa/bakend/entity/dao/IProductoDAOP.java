package com.soa.bakend.entity.dao;

import com.soa.bakend.entity.Producto;
import com.soa.bakend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductoDAOP extends JpaRepository<Producto, Producto> {

    @Query("select Producto")
    public Producto getCasilleroBy(@Param("Producto") Long id);

}
