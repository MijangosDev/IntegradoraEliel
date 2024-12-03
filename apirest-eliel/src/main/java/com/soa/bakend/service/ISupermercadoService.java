package com.soa.bakend.service;

import com.soa.bakend.response.SupermercadoResponseRest;
import org.springframework.http.ResponseEntity;

public interface ISupermercadoService {

    ResponseEntity<SupermercadoResponseRest> procesarCompra(Long id);
}