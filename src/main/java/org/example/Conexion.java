package org.example;

import java.sql.*;

public class Conexion {


    public static void listarEstaciones() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/transporte","postgres","3003394453");
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM estacion")) {
            while (rs.next()) {
                Integer id_estacion = rs.getInt("id_estacion");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                System.out.println("ID: " + id_estacion + ", Nombre: " + nombre + ", Dirección: " + direccion);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void crearEstacion(String nombre, String direccion) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/transporte";
        String usuario = "postgres";
        String contrasenia = "3003394453";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenia)) {
            String sql = "INSERT INTO estacion (nombre, direccion) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, direccion);
                int rowCount = preparedStatement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Inserción exitosa en la tabla estacion.");
                } else {
                    System.out.println("La inserción no tuvo éxito.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarEstacion(int idEstacion, String nuevoNombre, String nuevaDireccion) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/transporte";
        String usuario = "postgres";
        String contrasenia = "3003394453";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenia)) {
            String sql = "UPDATE estacion SET nombre = ?, direccion = ? WHERE id_estacion = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nuevoNombre);
                preparedStatement.setString(2, nuevaDireccion);
                preparedStatement.setInt(3, idEstacion);
                int rowCount = preparedStatement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Actualización exitosa en la tabla estacion.");
                } else {
                    System.out.println("No se encontró ninguna estación con ese ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarEstacion(int idEstacion) throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/transporte";
        String usuario = "postgres";
        String contrasenia = "3003394453";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenia)) {
            String sql = "DELETE FROM estacion WHERE id_estacion = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idEstacion);
                int rowCount = preparedStatement.executeUpdate();

                if (rowCount > 0) {
                    System.out.println("Eliminación exitosa en la tabla estacion.");
                } else {
                    System.out.println("No se encontró ninguna estación con ese ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

