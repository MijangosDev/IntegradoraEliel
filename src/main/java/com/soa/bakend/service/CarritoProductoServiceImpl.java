package com.soa.bakend.service;

import com.soa.bakend.entity.CarritoProducto;
import com.soa.bakend.entity.dao.ICarritoProductoDAO;
import com.soa.bakend.response.CarritoProductoResponseRest;
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
public class CarritoProductoServiceImpl implements ICarritoProductoService {

    private static final Logger log = LoggerFactory.getLogger(CarritoProductoServiceImpl.class);

    @Autowired
    private ICarritoProductoDAO CarritoProductosDao;

    private CarritoProducto ultimoProductoEliminado = null;

    @Override
    public ResponseEntity<CarritoProductoResponseRest> buscarCarritoProducto() {
        log.info("Inicio metodo CarritoProducto");

        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        try {
            List<CarritoProducto> carritoProductos = (List<CarritoProducto>) CarritoProductosDao.findAll();
            response.getCarritoProductoResponse().setCarritoProductos(carritoProductos);
            response.setMetada("Respuesta OK", "00", "Respuesta exitosa");

        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al consultar las CarritoProductos");
            log.error("Error al consultar las CarritoProductos: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CarritoProductoResponseRest> buscarPorId(Long id) {
        log.info("Inicio metodo buscarPorId");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> list = new ArrayList<>();

        try {
            Optional<CarritoProducto> CarritoProductos = CarritoProductosDao.findById(id);
            if (CarritoProductos.isPresent()) {
                list.add(CarritoProductos.get());
                response.getCarritoProductoResponse().setCarritoProductos(list);
                response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
            } else {
                log.info("No encontrada la CarritoProducto");
                response.setMetada("Respuesta No Encontrada", "-1", "CarritoProducto no encontrada");
                return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error No Encontrada", "-1", "Error al consultar por Id");
            log.error("Error al consultar por Id CarritoProductoss: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<CarritoProductoResponseRest> crear(CarritoProducto CarritoProducto) {
        log.info("Inicio metodo crear (CarritoProducto)");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> list = new ArrayList<>();

        try {
            CarritoProducto carritoProductoGuardar = CarritoProductosDao.save(CarritoProducto);
            if (carritoProductoGuardar != null) {
                list.add(carritoProductoGuardar);
                response.getCarritoProductoResponse().setCarritoProductos(list);
                response.setMetada("Respuesta OK", "00", "Creacion exitosa");
            } else {
                log.info("No encontrada la CarritoProducto");
                response.setMetada("Respuesta No Creada", "-1", "CarritoProducto no creada");
                return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar la CarritoProducto");
            log.error("Error al guardar la CarritoProducto: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarritoProductoResponseRest> actualizar(CarritoProducto CarritoProducto, Long id) {
        log.info("Inicio metodo actualizar (CarritoProducto)");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> list = new ArrayList<>();

        try {
            Optional<CarritoProducto> CarritoProductosBuscada = CarritoProductosDao.findById(id);
            if (CarritoProductosBuscada.isPresent()) {

                CarritoProductosBuscada.get().setCantidad(CarritoProducto.getCantidad());

                CarritoProducto carritoProductoActualizar = CarritoProductosDao.save(CarritoProductosBuscada.get());

                if (carritoProductoActualizar != null) {
                    list.add(carritoProductoActualizar);
                    response.getCarritoProductoResponse().setCarritoProductos(list);
                    response.setMetada("Respuesta OK", "00", "Creacion exitosa");
                } else {
                    log.info("No se actualizo la CarritoProducto");
                    response.setMetada("Respuesta No Actualizada", "-1", "CarritoProducto no actualizada");
                    return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                log.info("No encontrada la CarritoProducto");
                response.setMetada("Respuesta No Encontrada", "-1", "CarritoProducto no localizada");
                return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al actualizar la CarritoProducto");
            log.error("Error al actualizar la CarritoProducto: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarritoProductoResponseRest> eliminar(Long id) {
        log.info("Inicio metodo eliminar (CarritoProducto)");
        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        List<CarritoProducto> list = new ArrayList<>();

        try {
            CarritoProductosDao.deleteById(id);
            response.setMetada("Respuesta OK", "00", "Eliminacion exitosa");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al eliminar CarritoProducto");
            log.error("Error al eliminar la CarritoProducto: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponseRest>(response, HttpStatus.OK);
    }


    // Método para obtener los productos del carrito de un cliente
    @Override
    public ResponseEntity<CarritoProductoResponseRest> buscarCarritoPorCliente(Long clienteId) {
        log.info("Inicio metodo buscarCarritoPorCliente");

        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        try {
            List<CarritoProducto> carritoProductos = CarritoProductosDao.findByClienteId(clienteId);
            if (carritoProductos.isEmpty()) {
                response.setMetada("Respuesta Vacía", "-1", "No hay productos en el carrito");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.getCarritoProductoResponse().setCarritoProductos(carritoProductos);
            response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al consultar el carrito de productos");
            log.error("Error al consultar carrito de productos: ", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Método para agregar un producto al carrito
    @Override
    public ResponseEntity<CarritoProductoResponseRest> agregarProducto(CarritoProducto carritoProducto) {
        log.info("Inicio metodo agregarProducto");

        CarritoProductoResponseRest response = new CarritoProductoResponseRest();
        try {
            CarritoProducto carritoProductoGuardado = CarritoProductosDao.save(carritoProducto);
            if (carritoProductoGuardado != null) {
                response.getCarritoProductoResponse().setCarritoProductos(List.of(carritoProductoGuardado));
                response.setMetada("Respuesta OK", "00", "Producto agregado con éxito");
            } else {
                response.setMetada("Error", "-1", "No se pudo agregar el producto");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al agregar el producto");
            log.error("Error al agregar el producto al carrito: ", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
