package com.soa.bakend.service;

import com.soa.bakend.entity.CarritoProducto;
import com.soa.bakend.response.CarritoProductoResponseRest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface ICarritoProductoService {

    @Transactional
    ResponseEntity<CarritoProductoResponseRest> buscarCarritoProducto();

    public ResponseEntity<CarritoProductoResponseRest> buscarPorId(Long id);

    public ResponseEntity<CarritoProductoResponseRest> crear(CarritoProducto carritoProducto);

    public ResponseEntity<CarritoProductoResponseRest> actualizar(CarritoProducto carritoProducto, Long id);

    public ResponseEntity<CarritoProductoResponseRest> eliminar(Long id);


    public ResponseEntity<CarritoProductoResponseRest> buscarCarritoPorCliente(Long clienteId);

    public ResponseEntity<CarritoProductoResponseRest> agregarProducto(CarritoProducto request);

}