package com.soa.bakend.service;

import com.soa.bakend.entity.Cliente;
import com.soa.bakend.entity.dao.IClienteDAO;
import com.soa.bakend.response.ClienteResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private IClienteDAO ClientesDao;

    @Override
    public ResponseEntity<ClienteResponseRest> buscarCliente() {
        log.info("Inicio metodo Cliente");

        ClienteResponseRest response = new ClienteResponseRest();
        try {
            List<Cliente> Clientes = (List<Cliente>) ClientesDao.findAll();
            response.setClientes(Clientes);
            response.setMetada("Respuesta OK", "00", "Respuesta exitosa");

        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al consultar las Clientes");
            log.error("Error al consultar las Clientes: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteResponseRest> buscarPorId(Long id) {
        log.info("Inicio metodo buscarPorId");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            Optional<Cliente> Clientes = ClientesDao.findById(id);
            if (Clientes.isPresent()) {
                list.add(Clientes.get());
                response.setClientes(list);
                response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
            } else {
                log.info("No encontrada la Cliente");
                response.setMetada("Respuesta No Encontrada", "-1", "Cliente no encontrada");
                return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error No Encontrada", "-1", "Error al consultar por Id");
            log.error("Error al consultar por Id Clientess: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteResponseRest> crear(Cliente Cliente) {
        log.info("Inicio metodo crear (Cliente)");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            Cliente ClienteGuardar = ClientesDao.save(Cliente);
            if (ClienteGuardar != null) {
                list.add(ClienteGuardar);
                response.setClientes(list);
                response.setMetada("Respuesta OK", "00", "Creacion exitosa");
            } else {
                log.info("No encontrada la Cliente");
                response.setMetada("Respuesta No Creada", "-1", "Cliente no creada");
                return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar la Cliente");
            log.error("Error al guardar la Cliente: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ClienteResponseRest> actualizar(Cliente Cliente, Long id) {
        log.info("Inicio metodo actualizar (Cliente)");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            Optional<Cliente> ClientesBuscada = ClientesDao.findById(id);
            if (ClientesBuscada.isPresent()) {

                ClientesBuscada.get().setNombre(Cliente.getNombre());

                Cliente ClienteActualizar = ClientesDao.save(ClientesBuscada.get());

                if (ClienteActualizar != null) {
                    list.add(ClienteActualizar);
                    response.setClientes(list);
                    response.setMetada("Respuesta OK", "00", "Creacion exitosa");
                } else {
                    log.info("No se actualizo la Cliente");
                    response.setMetada("Respuesta No Actualizada", "-1", "Cliente no actualizada");
                    return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                log.info("No encontrada la Cliente");
                response.setMetada("Respuesta No Encontrada", "-1", "Cliente no localizada");
                return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al actualizar la Cliente");
            log.error("Error al actualizar la Cliente: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteResponseRest> eliminar(Long id) {
        log.info("Inicio metodo eliminar (Cliente)");
        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> list = new ArrayList<>();

        try {
            ClientesDao.deleteById(id);
            response.setMetada("Respuesta OK", "00", "Eliminacion exitosa");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al eliminar Cliente");
            log.error("Error al eliminar la Cliente: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ClienteResponseRest>(response, HttpStatus.OK);
    }
}
