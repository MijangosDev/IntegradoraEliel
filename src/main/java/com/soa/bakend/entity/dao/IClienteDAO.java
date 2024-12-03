package com.soa.bakend.entity.dao;

import com.soa.bakend.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {
}

