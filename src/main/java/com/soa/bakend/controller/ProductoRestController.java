package com.soa.bakend.controller;

import com.soa.bakend.entity.Producto;
import com.soa.bakend.response.ProductoResponseRest;
import com.soa.bakend.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
public class ProductoRestController {

    @Autowired
    private IProductoService service;

    @GetMapping("/producto")
    public ResponseEntity<ProductoResponseRest> consultarProductos() {
        ResponseEntity<ProductoResponseRest> response = service.buscarProducto();
        return response;
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductoResponseRest> consultaPorId(@PathVariable Long id) {
        ResponseEntity<ProductoResponseRest> response = service.buscarPorId(id);
        return response;
    }

    @PostMapping("/producto/agregarProducto")
    public ResponseEntity<ProductoResponseRest> crear(@RequestBody Producto request) {
        ResponseEntity<ProductoResponseRest> response = service.crear(request);
        return response;
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<ProductoResponseRest> actualizar(@RequestBody Producto request, @PathVariable Long id) {
        ResponseEntity<ProductoResponseRest> response = service.actualizar(request, id);
        return response;
    }

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<ProductoResponseRest> eliminar(@PathVariable Long id) {
        ResponseEntity<ProductoResponseRest> response = service.eliminar(id);
        return response;
    }
}
