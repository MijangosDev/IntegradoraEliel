package com.soa.bakend.service;

import com.soa.bakend.entity.Cliente;
import com.soa.bakend.response.ClienteResponseRest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface IClienteService {

    @Transactional
    ResponseEntity<ClienteResponseRest> buscarCliente();

    public ResponseEntity<ClienteResponseRest> buscarPorId(Long id);

    public ResponseEntity<ClienteResponseRest> crear(Cliente Cliente);

    public ResponseEntity<ClienteResponseRest> actualizar(Cliente Cliente, Long id);

    public ResponseEntity<ClienteResponseRest> eliminar(Long id);
}
