package com.soa.bakend.entity.dao;

import com.soa.bakend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClienteDAOP extends JpaRepository<Cliente, Cliente> {

    @Query("select Cliente")
    public Cliente getClienteBy(@Param("Cliente") Long id);

}
