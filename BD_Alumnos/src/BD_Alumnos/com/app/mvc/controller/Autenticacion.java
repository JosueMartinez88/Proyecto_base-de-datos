package BD_Alumnos.com.app.mvc.controller;

import java.sql.Connection;

/**
 * Clase para la autenticación de usuarios contra una base de datos.
 * Proporciona un método para validar las credenciales (usuario, contraseña y base de datos).
 * 
 * @author García Agustin Christian Santiago
 * @author Martínez Vera Josué Aldair
 * @author Mejía López Luis David
 * @author Solís Contreras Darian Giselle
 */
public class Autenticacion {

    /**
     * Valida las credenciales intentando establecer una conexión con la base de datos.
     * Si la conexión se realiza correctamente, devuelve true, en caso contrario false.
     * 
     * @param usuario   Nombre del usuario para la conexión a la base de datos
     * @param contrasena Contraseña para la conexión a la base de datos
     * @param baseDatos Nombre de la base de datos a la que se quiere conectar
     * @return true si las credenciales son válidas y la conexión se establece correctamente, false en caso contrario
     */
    public static boolean validarCredenciales(String usuario, String contrasena, String baseDatos) {
        // Intentamos obtener una conexión con la base de datos usando las credenciales dadas
        Connection conexion = ConexionBD.getConexion(usuario, contrasena, baseDatos);

        if (conexion != null) {
            try {
                // Cerramos la conexión si fue válida para liberar recursos
                conexion.close();
            } catch (Exception e) {
                // Imprime error si hay problema al cerrar la conexión
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
            return true;
        }

        // Retorna false si la conexión no se pudo establecer (credenciales inválidas)
        return false;
    }
}
