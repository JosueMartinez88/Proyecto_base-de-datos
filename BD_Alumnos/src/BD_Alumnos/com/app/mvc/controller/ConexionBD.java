package BD_Alumnos.com.app.mvc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos MySQL.
 * Proporciona un método para obtener una conexión usando usuario, contraseña y nombre de la base de datos.
 * 
 * @author García Agustin Christian Santiago
 * @author Martínez Vera Josué Aldair
 * @author Mejía López Luis David
 * @author Solís Contreras Darian Giselle
 */
public class ConexionBD {
   
    /**
     * Establece y devuelve una conexión a la base de datos MySQL especificada.
     * 
     * @param user     Usuario para la conexión a la base de datos
     * @param password Contraseña para la conexión a la base de datos
     * @param database Nombre de la base de datos a la cual conectarse
     * @return Objeto Connection si la conexión fue exitosa, o null si ocurrió algún error
     */
    public static Connection getConexion(String user, String password, String database) {
        // Inicializamos la conexión en null
        Connection conn = null;

        // Construimos la URL de conexión a la base de datos MySQL con los parámetros necesarios
        String url = "jdbc:mysql://localhost:3306/" + database +
                     "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        try {
            // Cargamos el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Intentamos obtener la conexión con el driver y las credenciales dadas
            conn = DriverManager.getConnection(url, user, password);

            // Indicamos en consola que la conexión fue exitosa
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            // Error si no se encuentra el driver JDBC
            System.err.println("Error: No se encontró el driver JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Error si falla la conexión a la base de datos
            System.err.println("Error al conectar: " + e.getMessage());
        }

        // Retornamos la conexión (o null si no fue posible conectarse)
        return conn;
    }
    
}
