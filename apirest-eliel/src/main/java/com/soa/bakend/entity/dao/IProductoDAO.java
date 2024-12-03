package com.soa.bakend.entity.dao;

import com.soa.bakend.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoDAO extends CrudRepository<Producto, Long> {
}
