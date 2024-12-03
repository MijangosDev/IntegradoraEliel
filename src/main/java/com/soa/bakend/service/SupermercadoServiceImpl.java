package com.soa.bakend.service;

import com.soa.bakend.entity.Cliente;
import com.soa.bakend.entity.CarritoProducto;
import com.soa.bakend.response.SupermercadoResponseRest;
import com.soa.bakend.response.ClienteResponseRest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SupermercadoServiceImpl implements ISupermercadoService {

    private final ClienteServiceImpl clienteService;

    public SupermercadoServiceImpl(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public ResponseEntity<SupermercadoResponseRest> procesarCompra(Long clienteId) {
        ResponseEntity<ClienteResponseRest> clienteResponse = clienteService.buscarPorId(clienteId);

        if (clienteResponse.getStatusCodeValue() != 200) {
            return ResponseEntity.status(clienteResponse.getStatusCode()).body(null);
        }

        Cliente cliente = clienteResponse.getBody().getClientes().get(0);

        double totalCompra = calcularTotalCompra(cliente);

        SupermercadoResponseRest response = new SupermercadoResponseRest();
        response.setMensaje("Compra procesada exitosamente");
        response.setTotal(totalCompra);

        return ResponseEntity.ok(response);
    }


    private double calcularTotalCompra(Cliente cliente) {
        double total = 0.0;

        for (CarritoProducto carritoProducto : cliente.getCarrito()) {
            total += carritoProducto.getProducto().getPrecio() * carritoProducto.getCantidad();
        }

        return total;
    }
}
