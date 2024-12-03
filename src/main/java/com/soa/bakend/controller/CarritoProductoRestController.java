package com.soa.bakend.controller;

import com.soa.bakend.entity.CarritoProducto;
import com.soa.bakend.response.CarritoProductoResponseRest;
import com.soa.bakend.service.ICarritoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v3")
public class CarritoProductoRestController {

    @Autowired
    private ICarritoProductoService service;

    @GetMapping("/carrito")
    public ResponseEntity<CarritoProductoResponseRest> consultarSolicituds() {
        ResponseEntity<CarritoProductoResponseRest> response = service.buscarCarritoProducto();
        return response;
    }

    @GetMapping("/consulta/{id}")
    public ResponseEntity<CarritoProductoResponseRest> consultaPorId(@PathVariable Long id) {
        ResponseEntity<CarritoProductoResponseRest> response = service.buscarPorId(id);
        return response;
    }

    @PostMapping("/agregar")
    public ResponseEntity<CarritoProductoResponseRest> crear(@RequestBody CarritoProducto request) {
        ResponseEntity<CarritoProductoResponseRest> response = service.crear(request);
        return response;
    }

    @PutMapping("/carrito/{id}")
    public ResponseEntity<CarritoProductoResponseRest> actualizar(@RequestBody CarritoProducto request, @PathVariable Long id) {
        ResponseEntity<CarritoProductoResponseRest> response = service.actualizar(request, id);
        return response;
    }

    @DeleteMapping("/carrito/{id}")
    public ResponseEntity<CarritoProductoResponseRest> eliminar(@PathVariable Long id) {
        ResponseEntity<CarritoProductoResponseRest> response = service.eliminar(id);
        return response;
    }

    //demas metodos

    @GetMapping("/{id}")
    public ResponseEntity<CarritoProductoResponseRest> consultarCarrito(@PathVariable Long clienteId) {
        ResponseEntity<CarritoProductoResponseRest> response = service.buscarCarritoPorCliente(clienteId);
        return response;
    }

    @PostMapping("/carrito/agregar")
    public ResponseEntity<CarritoProductoResponseRest> agregarProductoAlCarrito(@RequestBody CarritoProducto request) {
        ResponseEntity<CarritoProductoResponseRest> response = service.agregarProducto(request);
        return response;
    }
}
