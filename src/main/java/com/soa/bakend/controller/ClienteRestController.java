package com.soa.bakend.controller;

import com.soa.bakend.entity.Cliente;
import com.soa.bakend.response.ClienteResponseRest;
import com.soa.bakend.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ClienteRestController {

    @Autowired
    private IClienteService service;

    @GetMapping("/cliente")
    public ResponseEntity<ClienteResponseRest> consultarClientes() {
        ResponseEntity<ClienteResponseRest> response = service.buscarCliente();
        return response;
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteResponseRest> consultaPorId(@PathVariable Long id) {
        ResponseEntity<ClienteResponseRest> response = service.buscarPorId(id);
        return response;
    }

    @PostMapping("/cliente/agregarCliente")
    public ResponseEntity<ClienteResponseRest> crear(@RequestBody Cliente request) {
        ResponseEntity<ClienteResponseRest> response = service.crear(request);
        return response;
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<ClienteResponseRest> actualizar(@RequestBody Cliente request, @PathVariable Long id) {
        ResponseEntity<ClienteResponseRest> response = service.actualizar(request, id);
        return response;
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<ClienteResponseRest> eliminar(@PathVariable Long id) {
        ResponseEntity<ClienteResponseRest> response = service.eliminar(id);
        return response;
    }
}

