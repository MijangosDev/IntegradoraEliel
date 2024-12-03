package com.soa.bakend.response;

import com.soa.bakend.entity.Cliente;

import java.util.List;

public class ClienteResponse {

    private List<Cliente> Clientes;

    public List<Cliente> getClientes() {
        return Clientes;
    }

    public void setClientes(List<Cliente> Clientes) {
        this.Clientes = Clientes;
    }
}
