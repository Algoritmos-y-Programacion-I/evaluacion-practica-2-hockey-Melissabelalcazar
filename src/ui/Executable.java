package ui;

import java.util.Scanner;
import model.Controller;
import model.JugadorHockey;
import model.Posicion;

public class Executable {
    private Scanner escaner;
    private Controller controladora;
    private static boolean flag;

    public Executable() {
        escaner = new Scanner(System.in);
        controladora = new Controller();
    }

    public void run(boolean startFlag) {
        flag = startFlag;

        while (!flag) {
            System.out.println("\n\nBienvenido al menú:\n");
            System.out.println("Opciones:\n" + "1. Crear equipo\n" + "2. Agregar jugador a equipo\n" + "3. Simular jugada de gol\n" + "4. Generar fixture\n" + "5. Salir del programa");

            int option = escaner.nextInt();
            escaner.nextLine(); 

            switch (option) {
                case 1:
                    crearEquipo();
                    break;
                case 2:
                    agregarJugador();
                    break;
                case 3:
                    controladora.simularJugada();
                    break;
                case 4:
                    System.out.println(controladora.fixture());
                    break;
                case 5:
                    flag = true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Por favor ingrese una opción válida");
            }
        }
    }

    private void crearEquipo() {
        System.out.print("Ingrese el nombre del equipo: ");
        String nombreEquipo = escaner.nextLine();
        if (controladora.crearEquipo(nombreEquipo)) {
            System.out.println("Equipo " + nombreEquipo + " creado exitosamente.");
        } else {
            System.out.println("No se pudo crear el equipo. Se alcanzó el límite de equipos.");
        }
    }

    private void agregarJugador() {
        System.out.print("Ingrese el índice del equipo (0 a 3): ");
        int equipoIndex = escaner.nextInt();
        escaner.nextLine(); 

        System.out.print("Ingrese el nombre del jugador: ");
        String nombreJugador = escaner.nextLine();

        System.out.print("Ingrese la edad del jugador: ");
        int edad = escaner.nextInt();

        System.out.print("Ingrese el número del jugador: ");
        int numero = escaner.nextInt();
        escaner.nextLine(); 

        System.out.print("Ingrese la posición del jugador (PORTERO, DEFENSA, ALA, CENTRO): ");
        Posicion posicion = Posicion.valueOf(escaner.nextLine().toUpperCase());

        JugadorHockey jugador = new JugadorHockey(nombreJugador, edad, numero, posicion);
        if (controladora.agregarJugadorAEquipo(equipoIndex, jugador)) {
            System.out.println("Jugador " + nombreJugador + " agregado al equipo.");
        } else {
            System.out.println("No se pudo agregar el jugador. Índice de equipo inválido.");
        }
    }

    public static void main(String[] args) {
        Executable mainApp = new Executable();
        mainApp.run(false);
    }
}
