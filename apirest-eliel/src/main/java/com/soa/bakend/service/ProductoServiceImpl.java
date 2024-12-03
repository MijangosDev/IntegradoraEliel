package com.soa.bakend.service;

import com.soa.bakend.entity.Producto;
import com.soa.bakend.entity.dao.IProductoDAO;
import com.soa.bakend.response.ProductoResponseRest;
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
public class ProductoServiceImpl implements IProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired
    private IProductoDAO ProductosDao;

    @Override
    public ResponseEntity<ProductoResponseRest> buscarProducto() {
        log.info("Inicio metodo Producto");

        ProductoResponseRest response = new ProductoResponseRest();
        try {
            List<Producto> productos = (List<Producto>) ProductosDao.findAll();
            response.getProductoResponse().setProductos(productos);
            response.setMetada("Respuesta OK", "00", "Respuesta exitosa");

        } catch (Exception e) {
            response.setMetada("Respuesta FALLIDA", "-1", "Error al consultar las Productos");
            log.error("Error al consultar las Productos: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProductoResponseRest> buscarPorId(Long id) {
        log.info("Inicio metodo buscarPorId");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try {
            Optional<Producto> Productos = ProductosDao.findById(id);
            if (Productos.isPresent()) {
                list.add(Productos.get());
                response.getProductoResponse().setProductos(list);
                response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
            } else {
                log.info("No encontrada la Producto");
                response.setMetada("Respuesta No Encontrada", "-1", "Producto no encontrada");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error No Encontrada", "-1", "Error al consultar por Id");
            log.error("Error al consultar por Id Productoss: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<ProductoResponseRest> crear(Producto Producto) {
        log.info("Inicio metodo crear (Producto)");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try {
            Producto productoGuardar = ProductosDao.save(Producto);
            if (productoGuardar != null) {
                list.add(productoGuardar);
                response.getProductoResponse().setProductos(list);
                response.setMetada("Respuesta OK", "00", "Creacion exitosa");
            } else {
                log.info("No encontrada la Producto");
                response.setMetada("Respuesta No Creada", "-1", "Producto no creada");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al guardar la Producto");
            log.error("Error al guardar la Producto: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponseRest> actualizar(Producto Producto, Long id) {
        log.info("Inicio metodo actualizar (Producto)");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try {
            Optional<Producto> ProductosBuscada = ProductosDao.findById(id);
            if (ProductosBuscada.isPresent()) {

                ProductosBuscada.get().setNombre(Producto.getNombre());
                ProductosBuscada.get().setPrecio(Producto.getPrecio());

                Producto productoActualizar = ProductosDao.save(ProductosBuscada.get());

                if (productoActualizar != null) {
                    list.add(productoActualizar);
                    response.getProductoResponse().setProductos(list);
                    response.setMetada("Respuesta OK", "00", "Creacion exitosa");
                } else {
                    log.info("No se actualizo la Producto");
                    response.setMetada("Respuesta No Actualizada", "-1", "Producto no actualizada");
                    return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                log.info("No encontrada la Producto");
                response.setMetada("Respuesta No Encontrada", "-1", "Producto no localizada");
                return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al actualizar la Producto");
            log.error("Error al actualizar la Producto: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponseRest> eliminar(Long id) {
        log.info("Inicio metodo eliminar (Producto)");
        ProductoResponseRest response = new ProductoResponseRest();
        List<Producto> list = new ArrayList<>();

        try {
            ProductosDao.deleteById(id);
            response.setMetada("Respuesta OK", "00", "Eliminacion exitosa");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al eliminar Producto");
            log.error("Error al eliminar la Producto: ", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductoResponseRest>(response, HttpStatus.OK);
    }
}
