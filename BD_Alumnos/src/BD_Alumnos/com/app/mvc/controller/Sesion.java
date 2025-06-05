package BD_Alumnos.com.app.mvc.controller;

/**
 * Clase para manejar los datos de sesión de forma estática.
 * Contiene información del usuario, contraseña y base de datos
 * actualmente en uso durante la sesión de la aplicación.
 * 
 * @author García Agustin Christian Santiago
 * @author Martínez Vera Josué Aldair
 * @author Mejía López Luis David
 * @author Solís Contreras Darian Giselle
 */
public class Sesion {
    
    // Usuario actual de la sesión
    private static String usuario;
    
    // Contraseña del usuario actual
    private static String contrasena;
    
    // Nombre de la base de datos seleccionada
    private static String baseDatos;

    /**
     * Establece los datos de la sesión con el usuario, contraseña y base de datos.
     * 
     * @param user     Nombre del usuario
     * @param pass     Contraseña del usuario
     * @param bd       Nombre de la base de datos
     */
    public static void iniciarSesion(String user, String pass, String bd) {
        // Guardar los datos de sesión
        usuario = user;
        contrasena = pass;
        baseDatos = bd;
    }

    /**
     * Obtiene el usuario actual de la sesión.
     * 
     * @return El nombre del usuario
     */
    public static String getUsuario() {
        return usuario;
    }

    /**
     * Obtiene la contraseña actual de la sesión.
     * 
     * @return La contraseña del usuario
     */
    public static String getContrasena() {
        return contrasena;
    }

    /**
     * Obtiene el nombre de la base de datos en uso.
     * 
     * @return El nombre de la base de datos
     */
    public static String getBaseDatos() {
        return baseDatos;
    }
}
