package com.example.loginpage;

public class itemmodel {
    private String objeto, descripcion;
    private int imgobjeto;

    public itemmodel() {
    }

    public itemmodel(String objeto, String descripcion, int imgobjeto) {
        this.objeto = objeto;
        this.descripcion = descripcion;
        this.imgobjeto = imgobjeto;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImgobjeto() {
        return imgobjeto;
    }

    public void setImgobjeto(int imgobjeto) {
        this.imgobjeto = imgobjeto;
    }
}
