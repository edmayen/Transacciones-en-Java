/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author edmayen
 */
public class Persona 
{
    private int idpersona;
    private String nombre;
    private String apellido;

    /**
     * @return the idpersona
     */
    public int getIdpersona() {
        return idpersona;
    }

    /**
     * @param idpersona the idpersona to set
     */
    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    /**
     * @return the nomobre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nomobre the nomobre to set
     */
    public void setNombre(String nomobre) {
        this.nombre = nomobre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Persona{" + "idpersona=" + idpersona + ", nomobre=" + nombre + ", apellido=" + apellido + '}';
    }
    
    
}
