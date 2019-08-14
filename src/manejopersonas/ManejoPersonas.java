/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejopersonas;
import datos.Conexion;
import datos.PersonasJDBC;
import domain.Persona;
import java.util.*;
import java.sql.*;
/**
 *
 * @author edmayen
 */
public class ManejoPersonas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        PersonasJDBC personasJDBC=new PersonasJDBC();
        

        /*creamos un objeto conexion, este se va a
        compartir para todos los querys que ejecutemos
        */ 
        Connection conn=null;
        
        try
        {
            conn=Conexion.getConnection();
            //revisamos si la conexion esta en modo autocommit
            //por default es autocommit==true
            if(conn.getAutoCommit())
            {
                conn.setAutoCommit(false);
            }
            //creamos el objeto PersonasJDBC
            //proporcionamos la conexion creada
            PersonasJDBC personas=new PersonasJDBC(conn);
            /*
            empezamos a ejecutar sentencias
            recordar que una transaccion agrupa varias
            sentencias SQL
            si algo falla no se realizan los cambios en la BD
            cambio correcto
            */
            personas.update(2, "nombre22112", "apellido22112");
            
            //provocamos un error superando los 45 caracteres
            //del campo de apellido
            //personas.insert("nombre2222", "apellido222");
            personas.insert("nombre123", "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
            //en caso de no haber error guardamos los cambios
            conn.commit();
        }
        catch(SQLException ex)
        {
            //en caso de error hacemos roolback
            try
            {
                System.out.println("Entramos al rollback");
                //imprimimos la excepcion a consola
                ex.printStackTrace(System.out);
                //hacemos rollback
                conn.rollback();
            }
            catch(SQLException ex2)
            {
                ex2.printStackTrace(System.out);
            }
        }
    }
    
}
