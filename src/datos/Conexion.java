/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.sql.*;

/**
 *
 * @author edmayen
 */
public class Conexion {
    private static String JDBC_DRIVER="com.mysql.jdbc.Driver";
    private static String JDBC_URL="jdbc:mysql://localhost:3306/sga?useSSL=false";
    private static String JDBC_USER="root";
    private static String JDBC_PASS="Mendenhall2116";
    private static Driver driver=null;
    
    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static synchronized Connection getConnection() throws SQLException 
    {
        if(driver==null)
        {
                try
                {
                    Class jdbcDriverClass=Class.forName(JDBC_DRIVER);
                    driver=(Driver) jdbcDriverClass.newInstance();
                    DriverManager.registerDriver(driver);
                }
                catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
                {
                    System.out.println("Fallo en cargar el driver JDBC.");
                }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }
    
    public static void close(ResultSet rs)
    {
        try
        {
            if(rs!=null)
            {
                rs.close();
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void close(PreparedStatement stat)
    {
        try
        {
            if(stat!=null)
            {
                stat.close();
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void close(Connection conn)
    {
        try
        {
            if(conn!=null)
            {
                conn.close();
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}


