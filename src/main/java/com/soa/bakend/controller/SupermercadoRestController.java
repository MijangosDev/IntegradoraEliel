package com.soa.bakend.controller;

import com.soa.bakend.response.SupermercadoResponseRest;
import com.soa.bakend.service.ISupermercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v4")
public class SupermercadoRestController {

        @Autowired
        private ISupermercadoService supermercadoService;

        @PostMapping("/supermercado/comprar/{id}")
        public ResponseEntity<SupermercadoResponseRest> procesarCompra(@PathVariable Long id) {
                return supermercadoService.procesarCompra(id);
        }
}