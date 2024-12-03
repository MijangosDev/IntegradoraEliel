package com.soa.bakend.response;

import com.soa.bakend.entity.Cliente;

import java.util.List;

public class ClienteResponseRest extends ResponseRest {

    private List<Cliente> clientes;
    private Cliente cliente;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
