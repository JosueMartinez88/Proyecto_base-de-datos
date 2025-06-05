package BD_Alumnos.com.app.mvc.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar operaciones comunes sobre una tabla de alumnos.
 * Supone que la tabla tiene los campos: id (int, PK, AI), nombre (varchar), edad (int), carrera (varchar)
 * 
 * @author García Agustin Christian Santiago
 * @author Martínez Vera Josué Aldair
 * @author Mejía López Luis David
 * @author Solís Contreras Darian Giselle
 */
public class OperacionesBD {

    private Connection conn;

    /**
     * Constructor que establece conexión con la base de datos usando las credenciales proporcionadas.
     * 
     * @param user Usuario para la base de datos
     * @param password Contraseña para la base de datos
     * @param database Nombre de la base de datos
     * @throws RuntimeException si no se puede establecer conexión
     */
    public OperacionesBD(String user, String password, String database) {
        conn = ConexionBD.getConexion(user, password, database);
        if (conn == null) {
            throw new RuntimeException("No se pudo establecer conexión con la base de datos.");
        }
    }
    /**
     * Devuelve la conexión actual a la base de datos.
     * 
     * @return objeto Connection
     */
    public Connection getConnection() {
    return conn;
}

    /**
     * Obtiene una lista con los nombres de todas las tablas en la base de datos.
     * 
     * @return Lista de nombres de tablas
     */
    public List<String> mostrarTablas() {
        List<String> tablas = new ArrayList<>();
        String sql = "SHOW TABLES";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tablas.add(rs.getString(1));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener tablas: " + e.getMessage());
        }

        return tablas;
    }

    /**
     * Obtiene todo el contenido de una tabla especificada.
     * 
     * @param nombreTabla Nombre de la tabla
     * @return ResultSet con el contenido o null si ocurre un error
     */
    public ResultSet mostrarContenidoTabla(String nombreTabla) {
        String sql = "SELECT * FROM " + nombreTabla;

        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de tabla: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca alumnos cuyo nombre contenga la cadena dada.
     * 
     * @param tabla Nombre de la tabla
     * @param nombre Texto a buscar en el campo nombre
     * @return ResultSet con los resultados o null si ocurre un error
     */
    public ResultSet buscarAlumnoPorNombre(String tabla, String nombre) {
        String sql = "SELECT * FROM " + tabla + " WHERE Nombres LIKE ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nombre + "%");
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al buscar alumno por nombre: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca alumno por su ID.
     * 
     * @param tabla Nombre de la tabla
     * @param id ID del alumno
     * @return ResultSet con el resultado o null si ocurre un error
     */
    public ResultSet buscarAlumnoPorId(String tabla, int id) {
        String sql = "SELECT * FROM " + tabla + " WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al buscar alumno por ID: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca alumnos por edad.
     * 
     * @param tabla Nombre de la tabla
     * @param edad Edad a buscar
     * @return ResultSet con resultados o null si hay error
     */
    public ResultSet buscarAlumnoPorEdad(String tabla, int edad) {
        String sql = "SELECT * FROM " + tabla + " WHERE edad = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, edad);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al buscar alumno por edad: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca alumnos cuya carrera contenga la cadena dada.
     * 
     * @param tabla Nombre de la tabla
     * @param carrera Texto a buscar en carrera
     * @return ResultSet con resultados o null si hay error
     */
    public ResultSet buscarAlumnoPorCarrera(String tabla, String carrera) {
        String sql = "SELECT * FROM " + tabla + " WHERE carrera LIKE ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + carrera + "%");
            return pstmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al buscar alumno por carrera: " + e.getMessage());
            return null;
        }
    }

    /**
     * Inserta un nuevo alumno en la tabla especificada.
     * 
     * @param tabla Nombre de la tabla
     * @param nombre Nombre del alumno
     * @param edad Edad del alumno
     * @param carrera Carrera del alumno
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarAlumno(String tabla, String nombre, int edad, String carrera) {
        String sql = "INSERT INTO " + tabla + " (nombre, edad, carrera) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.setString(3, carrera);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar alumno: " + e.getMessage());
            return false;
        }
    }

    /**
     * Borra un alumno de la tabla por su ID.
     * 
     * @param tabla Nombre de la tabla
     * @param id ID del alumno a borrar
     * @return true si se borró algún registro, false si no o si hubo error
     */
    public boolean borrarAlumnoPorId(String tabla, int id) {
        String sql = "DELETE FROM " + tabla + " WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int afectados = pstmt.executeUpdate();
            return afectados > 0;
        } catch (SQLException e) {
            System.err.println("Error al borrar alumno: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ejecuta una consulta personalizada de tipo SELECT.
     * 
     * @param sql Consulta SQL completa
     * @return ResultSet con resultados o null si hubo error
     */
    public ResultSet ejecutarConsulta(String sql) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Error al ejecutar consulta: " + e.getMessage());
            return null;
        }
    }
    

    /**
     * Cierra la conexión a la base de datos.
     */
    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}