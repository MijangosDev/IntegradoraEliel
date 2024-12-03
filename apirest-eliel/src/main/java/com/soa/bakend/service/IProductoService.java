package com.soa.bakend.service;

import com.soa.bakend.entity.Producto;
import com.soa.bakend.response.ProductoResponseRest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface IProductoService {

    @Transactional
    ResponseEntity<ProductoResponseRest> buscarProducto();

    public ResponseEntity<ProductoResponseRest> buscarPorId(Long id);

    public ResponseEntity<ProductoResponseRest> crear(Producto producto);

    public ResponseEntity<ProductoResponseRest> actualizar(Producto producto, Long id);

    public ResponseEntity<ProductoResponseRest> eliminar(Long id);
}