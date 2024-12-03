package com.soa.bakend.response;

public class SupermercadoResponseRest extends ResponseRest {

    private SupermercadoResponse supermercadoResponse;

    public SupermercadoResponse getSupermercadoResponse() {
        return supermercadoResponse;
    }

    public void setSupermercadoResponse(SupermercadoResponse supermercadoResponse) {
        this.supermercadoResponse = supermercadoResponse;
    }

    public void setMensaje(String mensaje) {
        if (this.supermercadoResponse == null) {
            this.supermercadoResponse = new SupermercadoResponse();
        }
        this.supermercadoResponse.setMensaje(mensaje);
    }

    public void setTotal(double total) {
        if (this.supermercadoResponse == null) {
            this.supermercadoResponse = new SupermercadoResponse();
        }
        this.supermercadoResponse.setTotal(total);
    }
}
