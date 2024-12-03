package com.soa.bakend.entity.dao;

import com.soa.bakend.entity.CarritoProducto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICarritoProductoDAO extends CrudRepository<CarritoProducto, Long> {
    List<CarritoProducto> findByClienteId(Long clienteId);
}
