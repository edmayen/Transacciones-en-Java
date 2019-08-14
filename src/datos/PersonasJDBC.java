/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import domain.Persona;
import java.sql.*;
import java.util.*;
/**
 *
 * @author edmayen
 */
public class PersonasJDBC {
    //nos apoyamos de la llave primaria de mysql
    //por lo que se omite el campode personaid
    //se utiliza un preparedStatement, por lo que 
    //podemos utilizar parametros (signos de ?)
    //los cuales posteriormente seran sustituidos 
    //por el paramentro respectivo
    
    private java.sql.Connection userConn;
    
    private final String SQL_INSERT="INSERT INTO persona(nombre,apellido) VALUES(?,?)";
    private final String SQL_UPDATE="UPDATE persona SET nombre=?, apellido=? WHERE idpersona=?";
    private final String SQL_DELETE="DELETE FROM persona WHERE idpersona=?";
    private final String SQL_SELECT="SELECT idpersona, nombre, apellido FROM persona ORDER BY idpersona";
    
    public PersonasJDBC()
    {
        
    }
    
    public PersonasJDBC(Connection conn)
    {
        this.userConn=conn;
    }
    
    public int insert(String nombre, String apellido) throws SQLException
    {
        Connection conx;
        PreparedStatement stat;
        stat = null;
        conx=null;
        ResultSet rs; //no se utiliza en este ejercicio
        int rows; //renglones afectados
        rows=0;
        try
        {
            conx=(this.userConn !=null)?this.userConn:Conexion.getConnection();
            stat=conx.prepareStatement(SQL_INSERT);
            int index=1; //contador de columnas
            stat.setString(index++, nombre); //param 1 => ?
            stat.setString(index++, apellido); //param 2 => ?
            System.out.println("Ejecutando Query: "+SQL_INSERT);
            rows=stat.executeUpdate(); //# registros afectados 
            System.out.println("Registros Afectados: "+rows);
        }
        finally
        {
            Conexion.close(stat);
            if(this.userConn==null)
            {
                Conexion.close(conx);
            }
        }
        return rows;
    }
    
    public int update(int idpersona, String nombre, String apellido) throws SQLException 
    {   
        Connection conx=null;
        PreparedStatement stat=null;
        int rows=0;
        try
        {
            conx=(this.userConn != null) ? this.userConn:Conexion.getConnection();
            System.out.println("Ejecutando Query: "+SQL_UPDATE);
            stat=conx.prepareStatement(SQL_UPDATE);
            int index=1;
            stat.setString(index++, nombre); 
            stat.setString(index++, apellido); 
            stat.setInt(index, idpersona);
            rows=stat.executeUpdate();
            System.out.println("Registros Actualizados: "+rows);
        }
        finally
        {
            Conexion.close(stat);
            if(this.userConn==null)
            {
                Conexion.close(conx);
            }
        }
        return rows;
    }
    
    public int delete(int idpersona) throws SQLException
    {
        Connection conx=null;
        PreparedStatement stat=null;
        int rows=0;
        try
        {
            conx=(this.userConn != null) ? this.userConn:Conexion.getConnection();
            System.out.println("Ejecutando Query: "+SQL_DELETE);
            stat=conx.prepareStatement(SQL_DELETE);
            stat.setInt(1, idpersona);
            rows=stat.executeUpdate();
            System.out.println("Registros Eliminados: "+rows);
        }
        finally
        {
            Conexion.close(stat);
            if(this.userConn==null)
            {
                Conexion.close(conx);
            }
        }
        return rows;
    }
    
    public List<Persona> select() throws SQLException
    {
        Connection conx=null;
        PreparedStatement stat=null;
        ResultSet rs=null;
        Persona persona=null;
        List<Persona> personas;
        personas = new ArrayList<>();
        try
        {
            conx=(this.userConn != null) ? this.userConn:Conexion.getConnection();
            stat=conx.prepareStatement(SQL_SELECT);
            rs=stat.executeQuery();
            while(rs.next())
            {
                int idpersona=rs.getInt(1);
                String nombre=rs.getString(2);
                String apellido=rs.getString(3);
                persona=new Persona();
                persona.setIdpersona(idpersona);
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                personas.add(persona);
            }
        }
        finally
        {
            Conexion.close(rs);
            Conexion.close(stat);
            if(this.userConn==null)
            {
                Conexion.close(conx);
            }
        }
        return personas;
    }
}
