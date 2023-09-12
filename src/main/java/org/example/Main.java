package org.example;

import java.sql.SQLException;
import java.util.Scanner;

import static org.example.Conexion.crearEstacion;
import static org.example.Conexion.listarEstaciones;

public class Main {
    public static void main(String[] args) throws SQLException {

        Conexion conexion = new Conexion();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menú CRUD:");
        System.out.println("1. Crear");
        System.out.println("2. Leer información");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.println("5. Salir del programa");
        System.out.print("Elije una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Haz seleccionado la opción de crear");
                System.out.print("Ingrese el nombre de la estación: ");
                String nombreEstacion = scanner.nextLine(); //h123
                boolean esNumerica = true;

                for (int i = 0; i < nombreEstacion.length(); i++) {
                    if (!Character.isDigit(nombreEstacion.charAt(i))) {
                        esNumerica = false;
                        break;
                    }
                }

                if (esNumerica) {
                    System.out.println("La cadena es numérica, no se permiten solo caracteres numericos.");
                } else {
                    System.out.print("Ingrese la dirección de la estación: ");
                    String direccionEstacion = scanner.nextLine();
                    conexion.crearEstacion(nombreEstacion, direccionEstacion);
                }
                break;
            case 2:
                System.out.println("Haz seleccionado la opción de Leer información");
                listarEstaciones();
                break;
            case 3:
                System.out.println("Haz seleccionado la opción de Actualizar");
                System.out.print("Ingrese el ID de la estación que desea actualizar: ");

                try {
                    int idEstacionActualizar = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el nuevo nombre de la estación: ");
                    String nuevoNombreEstacion = scanner.nextLine();
                    System.out.print("Ingrese la nueva dirección de la estación: ");
                    String nuevaDireccionEstacion = scanner.nextLine();

                    conexion.actualizarEstacion(idEstacionActualizar, nuevoNombreEstacion, nuevaDireccionEstacion);
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("solo numeros");
                }

                break;
            case 4:
                System.out.println("Haz seleccionado la opción de Eliminar");
                System.out.print("Ingrese el ID de la estación que desea eliminar: ");
                int idEstacionEliminar = scanner.nextInt();
                conexion.eliminarEstacion(idEstacionEliminar);
                break;
            case 5:
                System.out.println("Haz seleccionado la opción de Salir del programa");
                break;
        }

        scanner.close();
    }
}
